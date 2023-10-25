package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TablaDeProductosPorVenta {

    JPanel container;

    public TablaDeProductosPorVenta() {
        container = new JPanel();

        DefaultTableModel tableModel = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tableModel.addColumn("ID Producto");
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Precio unitario");
        tableModel.addColumn("Cantidad");

        JTable table = new JTable(tableModel);
        tableModel.addRow(new Object[]{1, "iPhone X", 999.9, 1});
        tableModel.addRow(new Object[]{2, "iPhone XR", 699.9, 1});

        table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane tableScrollPane = new JScrollPane(table);

        JPanel buttonsContainer = new JPanel();
        JButton buttonEdit = new JButton("Editar cantidad");
        JButton buttonDelete = new JButton("Eliminar producto");

        buttonEdit.setToolTipText("Debes seleccionar una venta para realizar esta acción.");
        buttonEdit.setEnabled(false);

        buttonDelete.setToolTipText("Debes seleccionar una venta para realizar esta acción.");
        buttonDelete.setEnabled(false);

        buttonEdit.addActionListener(e -> {

        });

        buttonsContainer.add(buttonDelete);
        buttonsContainer.add(buttonEdit);

        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = table.getSelectedRow();
                buttonDelete.setEnabled(selectedRow >= 0);
                buttonEdit.setEnabled(selectedRow >= 0);
            }
        });

        JLabel total = new JLabel("Total: 1.700 USD");

        container.add(tableScrollPane);
        container.add(buttonsContainer);
        container.add(total);
        container.setVisible(true);
    }
}