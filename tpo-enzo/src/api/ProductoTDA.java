package api;

import java.util.UUID;

public interface ProductoTDA {
    void setVisible(boolean visible);
    boolean isVisible();
    UUID getCodigoProducto();
    String getNombre();
    double getPrecio();
}
