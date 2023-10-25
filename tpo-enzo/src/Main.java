import ui.Layout;
import ui.TablaDeProductos;
import ui.TablaDeProductosPorVenta;
import ui.TablaDeVentas;

public class Main {
    public static void main(String[] args) {
        TablaDeProductos productos = new TablaDeProductos();
        TablaDeProductosPorVenta productosPorVenta = new TablaDeProductosPorVenta();
        TablaDeVentas ventas = new TablaDeVentas(productosPorVenta);

        new Layout(productos, ventas);
    }
}