package ui;

import impl.ListaDeProductos;
import impl.ListaDeStock;
import impl.Producto;
import impl.Stock;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.UUID;

public class TablaDeProductos {

    JPanel container;
    DefaultTableModel tableModel;
    ListaDeProductos listaDeProductos;
    ListaDeStock listaDeStock;

    public TablaDeProductos(ListaDeProductos listaDeProductos, ListaDeStock listaDeStock) {
        this.listaDeProductos = listaDeProductos;
        this.listaDeStock = listaDeStock;
        container = new JPanel();
        container.setLayout(new BorderLayout());

        tableModel = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tableModel.addColumn("ID");
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Precio");
        tableModel.addColumn("Stock");
        tableModel.addColumn("Stock minimo");
        tableModel.addColumn("Estado");

        JTable table = new JTable(tableModel);

        table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane tableScrollPane = new JScrollPane(table);

        JPanel buttonsContainer = new JPanel();
        JButton buttonAdd = new JButton("Agregar producto");
        JButton buttonEdit = new JButton("Editar producto");
        JButton buttonDelete = new JButton("Eliminar producto");
        buttonDelete.setToolTipText("Debes seleccionar un producto para realizar esta acción.");
        buttonDelete.setEnabled(false);
        buttonEdit.setToolTipText("Debes seleccionar un producto para realizar esta acción.");
        buttonEdit.setEnabled(false);

        buttonAdd.addActionListener(e -> {
            JPanel inputPanel = new JPanel();
            inputPanel.setLayout(new GridLayout(4,2));

            JLabel label1 = new JLabel("Nombre:");
            JTextField field1 = new JTextField(20);
            JLabel label2 = new JLabel("Precio:");
            JTextField field2 = new JTextField(20);
            JLabel label3 = new JLabel("Stock:");
            JTextField field3 = new JTextField(20);
            JLabel label4 = new JLabel("Stock minimo:");
            JTextField field4 = new JTextField(20);

            inputPanel.add(label1);
            inputPanel.add(field1);
            inputPanel.add(label2);
            inputPanel.add(field2);
            inputPanel.add(label3);
            inputPanel.add(field3);
            inputPanel.add(label4);
            inputPanel.add(field4);

            int result = JOptionPane.showConfirmDialog(JOptionPane.getFrameForComponent(container), inputPanel, "Agregar producto", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {
                this.agregarProducto(field1.getText(), Double.parseDouble(field2.getText()), Integer.parseInt(field3.getText()), Integer.parseInt(field4.getText()));
            }
        });

        buttonEdit.addActionListener(e -> {
            UUID uuidSelected = (UUID) table.getValueAt(table.getSelectedRow(), 0);
            Producto producto = listaDeProductos.getProductoById(uuidSelected);
            Stock stock = listaDeStock.getStockById(uuidSelected);
            JPanel inputPanel = new JPanel();
            inputPanel.setLayout(new GridLayout(3,2));

            JLabel label1 = new JLabel("Stock:");
            JTextField field1 = new JTextField(20);
            field1.setText("" + stock.getStock());

            JLabel label2 = new JLabel("Stock Minimo:");
            JTextField field2 = new JTextField(20);
            field2.setText("" + stock.getStockMinimo());

            String[] opciones = {String.valueOf(true), String.valueOf(false)};
            JLabel label3 = new JLabel("Estado:");
            JComboBox<String> selector = new JComboBox<>(opciones);

            if (producto.isVisible()) selector.setSelectedIndex(0);
            else selector.setSelectedIndex(1);

            inputPanel.add(label1);
            inputPanel.add(field1);
            inputPanel.add(label2);
            inputPanel.add(field2);
            inputPanel.add(label3);
            inputPanel.add(selector);

            int result = JOptionPane.showConfirmDialog(JOptionPane.getFrameForComponent(container), inputPanel, "Editar producto", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {
                producto.setVisible(selector.getSelectedItem() == "true");
                stock.setStock(Integer.parseInt(field1.getText()));
                stock.setStockMinimo(Integer.parseInt(field2.getText()));
                this.renderAllProducts();
            }
        });

        buttonDelete.addActionListener(e -> {
            UUID uuidSelected = (UUID) table.getValueAt(table.getSelectedRow(), 0);
            this.eliminarProducto(uuidSelected, table.getSelectedRow());
        });

        buttonsContainer.add(buttonAdd);
        buttonsContainer.add(buttonEdit);
        buttonsContainer.add(buttonDelete);

        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = table.getSelectedRow();
                buttonDelete.setEnabled(selectedRow >= 0);
                buttonEdit.setEnabled(selectedRow >= 0);
            }
        });

        container.add(tableScrollPane, BorderLayout.CENTER);
        container.add(buttonsContainer, BorderLayout.SOUTH);

        this.renderAllProducts();
    }

    public void renderAllProducts() {
        this.tableModel.setRowCount(0);
        for (Producto p: listaDeProductos.getProductos()) {
            Stock stock = listaDeStock.getStockById(p.getCodigoProducto());
            this.tableModel.addRow(new Object[]{p.getCodigoProducto(), p.getNombre(), p.getPrecio(), stock.getStock(), stock.getStockMinimo(), p.isVisible()});
        }
    }

    public void agregarProducto(String nombre, double precio, int cantidad, int cantidadMinima) {
        UUID uuid = UUID.randomUUID();
        Producto productoNuevo = new Producto(uuid, nombre, precio);
        Stock productoStock = new Stock(uuid, cantidad, cantidadMinima);

        this.listaDeProductos.agregarProducto(productoNuevo);
        this.listaDeStock.addProductStock(productoStock);

        this.tableModel.addRow(new Object[]{uuid, nombre, precio, cantidad, cantidadMinima, true});
    }

    public void eliminarProducto(UUID id, int selectedRow) {
        tableModel.removeRow(selectedRow);
        this.listaDeProductos.eliminarProducto(id);
    }
}