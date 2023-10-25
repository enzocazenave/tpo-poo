package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TablaDeProductos {

    JPanel container;

    public TablaDeProductos() {
        container = new JPanel();

        DefaultTableModel tableModel = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tableModel.addColumn("ID");
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Precio");
        tableModel.addColumn("Stock");
        tableModel.addColumn("Estado");

        JTable table = new JTable(tableModel);
        tableModel.addRow(new Object[]{1, "iPhone X", 999.9, 5, true });
        tableModel.addRow(new Object[]{2, "iPhone XR", 899.0, 6, true });

        table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane tableScrollPane = new JScrollPane(table);

        JPanel buttonsContainer = new JPanel();
        JButton buttonAdd = new JButton("Agregar producto");
        JButton buttonDelete = new JButton("Eliminar producto");
        buttonDelete.setToolTipText("Debes seleccionar un producto para realizar esta acción.");
        buttonDelete.setEnabled(false);

        buttonDelete.addActionListener(e -> {
            tableModel.removeRow(table.getSelectedRow());
        });

        buttonsContainer.add(buttonAdd);
        buttonsContainer.add(buttonDelete);

        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = table.getSelectedRow();
                buttonDelete.setEnabled(selectedRow >= 0);
            }
        });

        container.add(tableScrollPane);
        container.add(buttonsContainer);
    }
}