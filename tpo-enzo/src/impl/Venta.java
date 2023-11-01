package impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Venta {
    UUID codigoVenta;
    int metodoDePago;
    Date fecha;
    double total;
    ArrayList<ProductoSeleccionado> productos;


    public Venta(UUID codigoVenta, ArrayList<ProductoSeleccionado> productos, int metodoDePago) {
        this.codigoVenta = codigoVenta;
        this.fecha = new Date();
        this.metodoDePago = metodoDePago;
        this.productos = productos;
        this.total = this.calcularCostoTotal(productos, metodoDePago);
    }

    public double calcularCostoTotal(ArrayList<ProductoSeleccionado> productos, int metodoDePago) {
        double total = 0;

        for (ProductoSeleccionado p: productos) {
            total += p.producto.precio * p.cantidad;
        }

        if (metodoDePago == 1) return new Efectivo().calcularCosto(total);
        if (metodoDePago == 2) return new Debito().calcularCosto(total);

        return new Credito().calcularCosto(total, 3);
    }

}
