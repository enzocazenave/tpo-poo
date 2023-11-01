package api;

import impl.Stock;

import java.util.UUID;

public interface ListaDeStockTDA {
    Stock getStockById(UUID codigoProducto);
    void addProductStock(Stock s);
    void decrementStockById(UUID codigoProducto, int stock);
}