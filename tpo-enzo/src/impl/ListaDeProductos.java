package impl;

import api.ListaDeProductosTDA;

import java.util.ArrayList;
import java.util.UUID;

public class ListaDeProductos implements ListaDeProductosTDA {
    ArrayList<Producto> listaDeProductos = new ArrayList<Producto>();

    public ListaDeProductos(ListaDeStock listaDeStock) {
        UUID uuid1 = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();
        UUID uuid3 = UUID.randomUUID();

        Producto productoNuevo1 = new Producto(uuid1, "iPhone X", 999.99);
        Producto productoNuevo2 = new Producto(uuid2, "iPhone 7", 599.99);
        Producto productoNuevo3 = new Producto(uuid3, "iPhone 5", 599.99);
        Stock productoStock1 = new Stock(uuid1, 10, 2);
        Stock productoStock2 = new Stock(uuid2, 5, 2);
        Stock productoStock3 = new Stock(uuid3, 5, 2);

        this.agregarProducto(productoNuevo1);
        listaDeStock.addProductStock(productoStock1);
        this.agregarProducto(productoNuevo2);
        listaDeStock.addProductStock(productoStock2);
        this.agregarProducto(productoNuevo3);
        listaDeStock.addProductStock(productoStock3);
    }

    public void agregarProducto(Producto P){
        this.listaDeProductos.add(P);
    }

    public void eliminarProducto(UUID codigoProducto) {
        for(Producto P: listaDeProductos){
            if (codigoProducto.equals(P.codigoProducto)){
                this.listaDeProductos.remove(P);
                return;
            }
        }
    }

    public void cambiarEstadoProducto(UUID codigoProducto, boolean estado){

        for(Producto P: listaDeProductos){
            if (codigoProducto.equals(P.codigoProducto)){
                P.setVisible(estado);
            }
        }
    }

    public ArrayList<Producto> getProductos() {
        return listaDeProductos;
    }

    public Producto getProductoById(UUID codigoProducto){
        for(Producto P: listaDeProductos){
            if(codigoProducto.equals(P.codigoProducto)){
                return P;
            }
        }
        return  null;
    }
}