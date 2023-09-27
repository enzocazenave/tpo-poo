import ui.TablaDeProductos;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TablaDeProductos frame = new TablaDeProductos();
            frame.setVisible(true);
        });
    }
}