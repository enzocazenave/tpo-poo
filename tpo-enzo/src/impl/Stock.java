package impl;

import api.StockTDA;

import java.util.UUID;

public class Stock implements StockTDA {
    UUID codigoProducto;
    int stock;
    int stockMinimo;

    public Stock(UUID codigoProducto, int stock, int stockMinimo) {
        this.codigoProducto = codigoProducto;
        this.stock = stock;
        this.stockMinimo = stockMinimo;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public void decrementStock(int stock) {
        int decrementQtty = this.stock - stock;
        if (decrementQtty <= 0) return;
        this.stock = decrementQtty;
    }

    public int getStock() {
        return stock;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }
}