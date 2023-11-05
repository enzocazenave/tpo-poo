package ui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Layout {
    public Layout(TablaDeProductos productos, TablaDeVentas ventas) {
        JFrame frame = new JFrame("Gestor de ventas y stock.");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,700);
        frame.setResizable(false);

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Productos", productos.container);
        tabbedPane.addTab("Ventas", ventas.container);

        tabbedPane.addChangeListener(e -> {
            if (tabbedPane.getSelectedIndex() != 2 && tabbedPane.getTabCount() > 2) {
                tabbedPane.remove(2);
            }

            if (tabbedPane.getSelectedIndex() == 0) {
                productos.renderAllProducts();
            }
        });

        ventas.setMenu(tabbedPane);

        frame.add(tabbedPane);
        frame.setVisible(true);
    }
}