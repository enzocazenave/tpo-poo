package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TablaDeVentas {

    JPanel container;
    JTabbedPane menu;

    public TablaDeVentas(TablaDeProductosPorVenta productosPorVenta) {
        container = new JPanel();

        DefaultTableModel tableModel = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tableModel.addColumn("ID Venta");
        tableModel.addColumn("Fecha y Hora");
        tableModel.addColumn("Cantidad");
        tableModel.addColumn("Total");

        JTable table = new JTable(tableModel);
        tableModel.addRow(new Object[]{1, "12/10/2023", 999.9, 2, 1999.9});
        tableModel.addRow(new Object[]{2, "14/10/2023", 999.9, 2, 1999.9});

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

        container.add(tableScrollPane);
        container.add(buttonsContainer);

        buttonGo.addActionListener(e -> {
            String idVenta = table.getValueAt(table.getSelectedRow(), 0).toString();
            menu.addTab("Venta " + idVenta, productosPorVenta.container);
            menu.setSelectedIndex(2);
        });

    }

    public void setMenu(JTabbedPane menu) {
        this.menu = menu;
    }

}