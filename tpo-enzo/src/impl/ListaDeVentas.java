package impl;

import api.ListaDeVentasTDA;

import java.util.ArrayList;
import java.util.UUID;

public class ListaDeVentas implements ListaDeVentasTDA {
    ArrayList<Venta> listaDeVentas = new ArrayList<Venta>();
    ListaDeStock listaDeStock;

    public ListaDeVentas(ListaDeStock listaDeStock) {
        this.listaDeStock = listaDeStock;
    }

    public void agregarVenta(Venta V){
        for (ProductoSeleccionado p: V.getProductos()) {
            Stock stock = listaDeStock.getStockById(p.getProducto().getCodigoProducto());
            stock.decrementStock(p.getCantidad());

            if (stock.getStock() < stock.getStockMinimo()) {
                p.getProducto().setVisible(false);
            }
        }

        this.listaDeVentas.add(V);
    }

    public void eliminarVenta(UUID codigoVenta) {
        for(Venta V: listaDeVentas){
            if (codigoVenta.equals(V.codigoVenta)){
                this.listaDeVentas.remove(V);
                return;
            }
        }
    }

    public ArrayList<Venta> getVentas() {
        return listaDeVentas;
    }

    public Venta getVentaById(UUID codigoVenta){
        for(Venta V: listaDeVentas){
            if(codigoVenta.equals(V.codigoVenta)){
                return V;
            }
        }
        return  null;
    }
}