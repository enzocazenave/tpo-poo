package api;

import impl.ProductoSeleccionado;

import java.util.ArrayList;

public interface VentaTDA {
    double calcularCostoTotal(ArrayList<ProductoSeleccionado> productos, int metodoDePago);
}
