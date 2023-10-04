package impl;

import api.ListaDeStockTDA;

import java.util.ArrayList;

public class ListaDeStock implements ListaDeStockTDA {
    private ArrayList<Stock> listaDeStock = new ArrayList<Stock>();

    public Stock getStockById(int codigoProducto) {
        for (Stock S: listaDeStock) {
            if (S.codigoProducto == codigoProducto) return S;
        }

        return null;
    }

    public void addProductStock(int codigoProducto, int stock, int stockMinimo) {
        Stock productStock = new Stock(codigoProducto, stock, stockMinimo);
        listaDeStock.add(productStock);
    }

    public void decrementStockById(int codigoProducto, int stock) {
        Stock productStock = this.getStockById(codigoProducto);
        productStock.decrementStock(stock);
    }
}