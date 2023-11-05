package ui;

import impl.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.UUID;

public class TablaDeVentas {

    JPanel container;
    JTabbedPane menu;
    ListaDeVentas listaDeVenta;
    DefaultTableModel tableModel;


    public TablaDeVentas(TablaDeProductosPorVenta productosPorVenta, ListaDeVentas listaDeVentas, ListaDeProductos listaDeProductos, ListaDeStock listaDeStock) {
        this.listaDeVenta = listaDeVentas;

        container = new JPanel();
        container.setLayout(new BorderLayout());

        tableModel = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tableModel.addColumn("ID Venta");
        tableModel.addColumn("Fecha y Hora");
        tableModel.addColumn("Total");

        JTable table = new JTable(tableModel);

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
            ArrayList<ProductoSeleccionado> productosSeleccionados = new ArrayList<ProductoSeleccionado>();
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

            productsTableModel.addColumn("ID");
            productsTableModel.addColumn("Nombre");
            productsTableModel.addColumn("Precio");
            productsTableModel.addColumn("Stock");
            carritoTableModel.addColumn("Nombre");
            carritoTableModel.addColumn("Precio");
            carritoTableModel.addColumn("Cantidad");

            for (Producto p: listaDeProductos.getProductos()) {
                if (p.isVisible()) {
                    productsTableModel.addRow(new Object[]{
                            p.getCodigoProducto(),
                            p.getNombre(),
                            p.getPrecio(),
                            listaDeStock.getStockById(p.getCodigoProducto()).getStock()
                    });
                }
            }

            JLabel productosTableTitle = new JLabel("Productos");
            JLabel carritoTableTitle = new JLabel("Carrito");
            productosTableTitle.setHorizontalAlignment(SwingConstants.CENTER);
            carritoTableTitle.setHorizontalAlignment(SwingConstants.CENTER);

            JButton addProductButton = new JButton("Agregar producto");
            JTextField productQtty = new JTextField(20);
            double total = 0.0;
            final double[] totalWrapper = { total };

            JLabel totalMoney = new JLabel("Total: " + total + " ARS");

            String placeholder = "Cantidad";
            productQtty.setText(placeholder);

            addProductButton.addActionListener(e1 -> {
                UUID codigo = (UUID) productsTable.getValueAt(productsTable.getSelectedRow(), 0);
                String nombre = (String) productsTable.getValueAt(productsTable.getSelectedRow(), 1);
                double precio = (double) productsTable.getValueAt(productsTable.getSelectedRow(), 2);
                int cantidadProducto = (int) productsTable.getValueAt(productsTable.getSelectedRow(), 3);
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

                totalWrapper[0] += (precio * cantidadRequerida);

                totalMoney.setText("Total: " + String.format("%.2f", totalWrapper[0]) + " ARS");
                productsTableModel.setValueAt(cantidadProducto - cantidadRequerida, productsTable.getSelectedRow(), 3);
                carritoTableModel.addRow(new Object[]{ nombre, precio, cantidadRequerida });

                ProductoSeleccionado producto = new ProductoSeleccionado(listaDeProductos.getProductoById(codigo), cantidadRequerida);
                productosSeleccionados.add(producto);
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

            String[] opcionesMetodos = { "Efectivo", "Debito", "Credito" };
            JComboBox<String> selectorMetodo = new JComboBox<>(opcionesMetodos);

            String[] opcionesCuotas = { "2", "3", "6" };
            JComboBox<String> selectorCuotas = new JComboBox<>(opcionesCuotas);
            selectorCuotas.setVisible(false);

            selectorMetodo.addActionListener(ev -> {
                selectorCuotas.setVisible(selectorMetodo.getSelectedItem() == "Credito");
            });

            productsPanel.add(productosTableTitle, gbc);
            productsPanel.add(carritoTableTitle, gbc);
            gbc.gridy = 1;

            productsPanel.add(new JScrollPane(productsTable), gbc);
            productsPanel.add(new JScrollPane(carritoTable), gbc);
            gbc.gridy = 2;

            productsPanel.add(addProductButton, gbc);
            productsPanel.add(productQtty, gbc);
            gbc.gridy = 3;

            productsPanel.add(totalMoney, gbc);
            gbc.gridy = 4;

            productsPanel.add(selectorMetodo, gbc);
            productsPanel.add(selectorCuotas, gbc);

            int result = JOptionPane.showConfirmDialog(JOptionPane.getFrameForComponent(container), productsPanel, "Registrar venta", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {
                this.agregarVenta(
                        productosSeleccionados,
                        selectorMetodo.getSelectedIndex(),
                        (String) selectorCuotas.getSelectedItem()
                );
            }
        });

        buttonGo.addActionListener(e -> {
            UUID idVenta = (UUID) table.getValueAt(table.getSelectedRow(), 0);

            menu.addTab("Venta " + idVenta.toString(), productosPorVenta.container);
            productosPorVenta.setProducts(idVenta);
            menu.setSelectedIndex(2);
        });

    }

    public void setMenu(JTabbedPane menu) {
        this.menu = menu;
    }

    public void agregarVenta(ArrayList<ProductoSeleccionado> productos, int metodo, String cuotas) {
        UUID uuid = UUID.randomUUID();
        Venta venta = new Venta(uuid, productos, metodo, Integer.parseInt(cuotas));
        this.listaDeVenta.agregarVenta(venta);
        SimpleDateFormat dateFormatted = new SimpleDateFormat("dd/MM/yyyy");
        String date = dateFormatted.format(venta.getFecha());
        this.tableModel.addRow(new Object[]{uuid, date, String.format("%.2f", venta.getTotal())});
    }

}