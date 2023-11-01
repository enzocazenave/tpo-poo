package impl;

import api.ProductoTDA;

import java.util.UUID;

public class Producto implements ProductoTDA {
    UUID codigoProducto;
    String nombre;
    double precio;
    boolean estado;

    public Producto(UUID codigoProducto, String nombre, double precio) {
        this.codigoProducto = codigoProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.estado = true;
    }

    public void setVisible(boolean estado) {
        this.estado = estado;
    }

    public boolean isVisible() {
        return estado;
    }

    public UUID getCodigoProducto() {
        return codigoProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }
}
