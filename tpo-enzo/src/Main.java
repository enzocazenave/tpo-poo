import impl.ListaDeProductos;
import impl.ListaDeStock;
import impl.ListaDeVentas;
import ui.Layout;
import ui.TablaDeProductos;
import ui.TablaDeProductosPorVenta;
import ui.TablaDeVentas;

public class Main {
    public static void main(String[] args) {
        ListaDeStock listaDeStock = new ListaDeStock();
        ListaDeProductos listaDeProductos = new ListaDeProductos(listaDeStock);
        ListaDeVentas listaDeVentas = new ListaDeVentas(listaDeStock);

        TablaDeProductos productos = new TablaDeProductos(listaDeProductos, listaDeStock);
        TablaDeProductosPorVenta productosPorVenta = new TablaDeProductosPorVenta(listaDeVentas);
        TablaDeVentas ventas = new TablaDeVentas(productosPorVenta, listaDeVentas, listaDeProductos, listaDeStock);

        new Layout(productos, ventas);
    }
}