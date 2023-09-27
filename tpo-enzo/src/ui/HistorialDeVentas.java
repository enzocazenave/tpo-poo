package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class HistorialDeVentas extends JFrame {
    private DefaultTableModel model;

    public HistorialDeVentas() {
        setTitle("Historial de Venta");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Descripción");
        model.addColumn("Precio unitario");
        model.addColumn("Cantidad");
        model.addColumn("Total");

        JTable table = new JTable(this.model);
        JScrollPane scrollPane = new JScrollPane(table);

        JButton addSale = new JButton("Registrar Venta");
        JButton deleteSale = new JButton("Eliminar Venta");

        JPanel panelBotones = new JPanel();
        panelBotones.add(addSale);
        panelBotones.add(deleteSale);

        Container contenido = getContentPane();
        contenido.setLayout(new BorderLayout());
        contenido.add(scrollPane, BorderLayout.CENTER);
        contenido.add(panelBotones, BorderLayout.SOUTH);
    }
}
