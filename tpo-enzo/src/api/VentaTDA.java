package api;

import impl.ProductoSeleccionado;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public interface VentaTDA {
    double calcularCostoTotal(ArrayList<ProductoSeleccionado> productos, int metodoDePago);
    UUID getCodigoVenta();
    int getMetodoDePago();
    Date getFecha();
    double getTotal();
    ArrayList<ProductoSeleccionado> getProductos();
}
