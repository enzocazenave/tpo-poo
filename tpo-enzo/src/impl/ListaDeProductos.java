package impl;

import api.ListaDeProductosTDA;

import java.util.ArrayList;
import java.util.UUID;

public class ListaDeProductos implements ListaDeProductosTDA {
    ArrayList<Producto> listaDeProductos = new ArrayList<Producto>();

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