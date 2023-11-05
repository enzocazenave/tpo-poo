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

    public Venta(UUID codigoVenta, ArrayList<ProductoSeleccionado> productos, int metodoDePago, int cuotas) {
        this.codigoVenta = codigoVenta;
        this.fecha = new Date();
        this.metodoDePago = metodoDePago;
        this.productos = productos;
        this.total = this.calcularCostoTotal(productos, metodoDePago, cuotas);
    }

    public double calcularCostoTotal(ArrayList<ProductoSeleccionado> productos, int metodoDePago, int cuotas) {
        double total = 0;

        for (ProductoSeleccionado p: productos) {
            total += p.producto.precio * p.cantidad;
        }

        System.out.println(metodoDePago + " - " + cuotas);

        if (metodoDePago == 0) return new Efectivo().calcularCosto(total);
        if (metodoDePago == 1) return new Debito().calcularCosto(total);

        return new Credito().calcularCosto(total, cuotas);
    }

    public UUID getCodigoVenta() {
        return codigoVenta;
    }

    public int getMetodoDePago() {
        return metodoDePago;
    }

    public Date getFecha() {
        return fecha;
    }

    public double getTotal() {
        return total;
    }

    public ArrayList<ProductoSeleccionado> getProductos() {
        return productos;
    }
}
