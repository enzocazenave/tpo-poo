package impl;

import api.ListaDeStockTDA;

import java.util.ArrayList;
import java.util.UUID;

public class ListaDeStock implements ListaDeStockTDA {
    private ArrayList<Stock> listaDeStock;

    public ListaDeStock() {
        this.listaDeStock = new ArrayList<Stock>();
    }

    public Stock getStockById(UUID codigoProducto) {
        for (Stock S: listaDeStock) {
            if (codigoProducto.equals(S.codigoProducto)) return S;
        }

        return null;
    }

    public void addProductStock(Stock S) {
        listaDeStock.add(S);
    }

    public void decrementStockById(UUID codigoProducto, int stock) {
        Stock productStock = this.getStockById(codigoProducto);
        productStock.decrementStock(stock);
    }
}