package ui;

import impl.ListaDeProductos;
import impl.ListaDeStock;
import impl.ListaDeVentas;
import impl.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class TablaDeVentas {

    JPanel container;
    JTabbedPane menu;

    public TablaDeVentas(TablaDeProductosPorVenta productosPorVenta, ListaDeVentas listaDeVentas, ListaDeProductos listaDeProductos, ListaDeStock listaDeStock) {
        container = new JPanel();
        container.setLayout(new BorderLayout());

        DefaultTableModel tableModel = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tableModel.addColumn("ID Venta");
        tableModel.addColumn("Fecha y Hora");
        tableModel.addColumn("Total");

        JTable table = new JTable(tableModel);
        tableModel.addRow(new Object[]{1, "12/10/2023", 1999.9});
        tableModel.addRow(new Object[]{2, "14/10/2023", 1999.9});

        table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        JScrollPane tableScrollPane = new JScrollPane(table);

        JPanel buttonsContainer = new JPanel();
        JButton buttonAdd = new JButton("Registrar venta");
        JButton buttonGo = new JButton("Ver venta");

        buttonGo.setToolTipText("Debes seleccionar una venta para realizar esta acción.");
        buttonGo.setEnabled(false);

        buttonsContainer.add(buttonAdd);
        buttonsContainer.add(buttonGo);

        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = table.getSelectedRow();
                buttonGo.setEnabled(selectedRow >= 0);
            }
        });

        container.add(tableScrollPane, BorderLayout.CENTER);
        container.add(buttonsContainer, BorderLayout.SOUTH);

        buttonAdd.addActionListener(e -> {
            JPanel productsPanel = new JPanel();
            productsPanel.setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.insets.set(5, 5, 5, 5);

            DefaultTableModel productsTableModel = new DefaultTableModel() {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            DefaultTableModel carritoTableModel = new DefaultTableModel() {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            JTable productsTable = new JTable(productsTableModel);
            JTable carritoTable = new JTable(carritoTableModel);

            table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            productsTableModel.addColumn("Nombre");
            productsTableModel.addColumn("Precio");
            productsTableModel.addColumn("Stock");
            carritoTableModel.addColumn("Nombre");
            carritoTableModel.addColumn("Precio");
            carritoTableModel.addColumn("Cantidad");

            for (Producto p: listaDeProductos.getProductos()) {
                productsTableModel.addRow(new Object[]{
                        p.getNombre(),
                        p.getPrecio(),
                        listaDeStock.getStockById(p.getCodigoProducto()).getStock()
                });
            }

            JLabel productosTableTitle = new JLabel("Productos");
            JLabel carritoTableTitle = new JLabel("Carrito");
            productosTableTitle.setHorizontalAlignment(SwingConstants.CENTER);
            carritoTableTitle.setHorizontalAlignment(SwingConstants.CENTER);

            JButton addProductButton = new JButton("Agregar producto");
            JTextField productQtty = new JTextField(20);

            String placeholder = "Cantidad";
            productQtty.setText(placeholder);

            addProductButton.addActionListener(e1 -> {
                String nombre = (String) productsTable.getValueAt(productsTable.getSelectedRow(), 0);
                double precio = (double) productsTable.getValueAt(productsTable.getSelectedRow(), 1);
                int cantidadProducto = (int) productsTable.getValueAt(productsTable.getSelectedRow(), 2);
                String cantidadRequeridaField = productQtty.getText();
                int cantidadRequerida;

                try {
                    cantidadRequerida = Integer.parseInt(cantidadRequeridaField);
                } catch(NumberFormatException ex) {
                    JOptionPane.showMessageDialog(JOptionPane.getFrameForComponent(container), "Se esperaba un número entero.");
                    return;
                }

                if (cantidadRequerida > cantidadProducto || cantidadRequerida <= 0) {
                    JOptionPane.showMessageDialog(JOptionPane.getFrameForComponent(container), "No hay esa cantidad de stock.");
                    return;
                }

                productsTableModel.setValueAt(cantidadProducto - cantidadRequerida, productsTable.getSelectedRow(), 2);
                carritoTableModel.addRow(new Object[]{ nombre, precio, cantidadRequerida });
            });

            productQtty.addFocusListener(new FocusListener() {
                public void focusGained(FocusEvent e) {
                    if (productQtty.getText().equals(placeholder)) {
                        productQtty.setText("");
                    }
                }
                public void focusLost(FocusEvent e) {
                    if (productQtty.getText().isEmpty()) {
                        productQtty.setText(placeholder);
                    }
                }
            });

            productsPanel.add(productosTableTitle, gbc);
            productsPanel.add(carritoTableTitle, gbc);
            gbc.gridy = 1;

            productsPanel.add(new JScrollPane(productsTable), gbc);
            productsPanel.add(new JScrollPane(carritoTable), gbc);
            gbc.gridy = 2;

            productsPanel.add(addProductButton, gbc);
            productsPanel.add(productQtty, gbc);

            int result = JOptionPane.showConfirmDialog(JOptionPane.getFrameForComponent(container), productsPanel, "Registrar venta", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {
            }
        });

        buttonGo.addActionListener(e -> {
            String idVenta = table.getValueAt(table.getSelectedRow(), 0).toString();
            menu.addTab("Venta " + idVenta, productosPorVenta.container);
            menu.setSelectedIndex(2);
        });

    }

    public void setMenu(JTabbedPane menu) {
        this.menu = menu;
    }

    public void agregarVenta() {

    }

}