package ui;

import impl.ListaDeVentas;
import impl.ProductoSeleccionado;
import impl.Venta;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.UUID;

public class TablaDeProductosPorVenta {

    JPanel container;
    ListaDeVentas listaDeVentas;
    DefaultTableModel tableModel;
    JLabel title;

    public TablaDeProductosPorVenta(ListaDeVentas listaDeVentas) {
        this.listaDeVentas = listaDeVentas;
        container = new JPanel();
        container.setLayout(new BorderLayout());

        tableModel = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tableModel.addColumn("ID Producto");
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Precio unitario");
        tableModel.addColumn("Cantidad");

        JTable table = new JTable(tableModel);

        table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane tableScrollPane = new JScrollPane(table);

        this.title = new JLabel("");

        container.add(tableScrollPane, BorderLayout.NORTH);
        container.add(this.title, BorderLayout.CENTER);
        container.setVisible(true);
    }

    public void setProducts(UUID codigoVenta) {
        Venta venta = listaDeVentas.getVentaById(codigoVenta);
        ArrayList<ProductoSeleccionado> productosvendidos = venta.getProductos();
        this.tableModel.setRowCount(0);

        for (ProductoSeleccionado p: productosvendidos) {
            this.tableModel.addRow(new Object[]{
                    p.getProducto().getCodigoProducto(),
                    p.getProducto().getNombre(),
                    p.getProducto().getPrecio(),
                    p.getCantidad()
            });
        }

        this.title.setText("Total: " + String.format("%.2f", venta.getTotal()) + " ARS");
    }
}