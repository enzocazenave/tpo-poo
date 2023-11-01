package api;

import impl.Producto;

import java.util.ArrayList;
import java.util.UUID;

public interface ListaDeProductosTDA {
    void agregarProducto(Producto P);
    void eliminarProducto(UUID codigoProducto);
    void cambiarEstadoProducto(UUID codigoProducto, boolean estado);
    ArrayList<Producto> getProductos();
    Producto getProductoById(UUID codigoProducto);
}
