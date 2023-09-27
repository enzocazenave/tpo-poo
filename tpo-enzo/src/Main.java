import ui.HistorialDeVentas;
import ui.TablaDeProductos;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TablaDeProductos productos = new TablaDeProductos();
            HistorialDeVentas historial = new HistorialDeVentas();
            productos.setVisible(true);
            historial.setVisible(true);
        });
    }
}