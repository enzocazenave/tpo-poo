package api;

import impl.Stock;

public interface ListaDeStockTDA {
    Stock getStockById(int codigoProducto);
    void addProductStock(int codigoProducto, int stock, int stockMinimo);
    void decrementStockById(int codigoProducto, int stock, int stockMinimo);
}
