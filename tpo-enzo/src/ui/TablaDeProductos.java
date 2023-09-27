package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TablaDeProductos extends JFrame {
    private DefaultTableModel model;

    public TablaDeProductos() {
        setTitle("Tabla de Productos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Descripción");
        model.addColumn("Precio");

        JTable table = new JTable(this.model);
        JScrollPane scrollPane = new JScrollPane(table);

        JButton sellButton = new JButton("Registrar Venta");
        JButton addButton = new JButton("Agregar Producto");
        JButton deleteButton = new JButton("Eliminar Producto");

        JPanel panelBotones = new JPanel();
        panelBotones.add(sellButton);
        panelBotones.add(addButton);
        panelBotones.add(deleteButton);

        Container contenido = getContentPane();
        contenido.setLayout(new BorderLayout());
        contenido.add(scrollPane, BorderLayout.CENTER);
        contenido.add(panelBotones, BorderLayout.SOUTH);
    }
}
