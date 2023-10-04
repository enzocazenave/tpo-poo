package impl;

public class Producto {
    int codigoProducto;
    String descripcion;
    double precio;
    boolean visible;

    public Producto (int codigoProducto, String descripcion, double precio){
        this.codigoProducto = codigoProducto;
        this.descripcion = descripcion;
        this.precio = precio;
        this.visible = true;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isVisible() {
        return visible;
    }
}
