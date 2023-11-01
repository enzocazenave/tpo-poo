import impl.ListaDeProductos;
import impl.ListaDeStock;
import impl.ListaDeVentas;
import ui.Layout;
import ui.TablaDeProductos;
import ui.TablaDeProductosPorVenta;
import ui.TablaDeVentas;

public class Main {
    public static void main(String[] args) {
        ListaDeProductos listaDeProductos = new ListaDeProductos();
        ListaDeStock listaDeStock = new ListaDeStock();
        ListaDeVentas listaDeVentas = new ListaDeVentas();

        TablaDeProductos productos = new TablaDeProductos(listaDeProductos, listaDeStock);
        TablaDeProductosPorVenta productosPorVenta = new TablaDeProductosPorVenta();
        TablaDeVentas ventas = new TablaDeVentas(productosPorVenta, listaDeVentas, listaDeProductos, listaDeStock);

        new Layout(productos, ventas);
    }
}