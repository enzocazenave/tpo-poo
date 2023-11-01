package impl;

import java.util.ArrayList;

public class Credito extends Pagos {
    public Credito() {
        super();
    }

    public double calcularCosto(double total, int cuotas) {
        if (cuotas == 2) return total * 1.06;
        if (cuotas == 3) return total * 1.12;

        return total * 1.20;
    }
}
