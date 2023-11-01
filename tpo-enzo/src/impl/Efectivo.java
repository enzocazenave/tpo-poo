package impl;

import java.util.ArrayList;

public class Efectivo extends Pagos {
    public Efectivo() {
        super();
    }

    public double calcularCosto(double total) {
        return total * 0.90;
    }
}
