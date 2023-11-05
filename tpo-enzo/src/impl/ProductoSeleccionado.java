package impl;

import api.ProductoSeleccionadoTDA;

public class ProductoSeleccionado implements ProductoSeleccionadoTDA {
    Producto producto;
    int cantidad;

    public ProductoSeleccionado(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }
}
