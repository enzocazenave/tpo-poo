import ui.HistorialDeVentas;
import ui.Menu;
import ui.TablaDeProductos;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TablaDeProductos productos = new TablaDeProductos();
            HistorialDeVentas ventas = new HistorialDeVentas();
            new Menu(ventas, productos);
        });
    }
}