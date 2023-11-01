package api;

import impl.Venta;

import java.util.ArrayList;
import java.util.UUID;

public interface ListaDeVentasTDA {
    void agregarVenta(Venta v);
    void eliminarVenta(UUID codigoVenta);
    ArrayList<Venta> getVentas();
    Venta getVentaById(UUID codigoVenta);
}
