package impl;

import api.StockTDA;

public class Stock implements StockTDA {
    int codigoProducto;
    int stock;
    int stockMinimo;

    Stock(int codigoProducto, int stock, int stockMinimo) {
        this.codigoProducto = codigoProducto;
        this.stock = stock;
        this.stockMinimo = stockMinimo;
    }

    public void decrementStock(int stock) {
        int decrementQtty = this.stock - stock;
        if (decrementQtty <= 0) return;
        this.stock = decrementQtty;
    }
}
