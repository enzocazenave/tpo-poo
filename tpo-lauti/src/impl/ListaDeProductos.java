package impl;

import java.util.ArrayList;

public class ListaDeProductos {
    ArrayList<Producto> listaDeProductos = new ArrayList<Producto>();

    public void agregarProducto(Producto P){
        this.listaDeProductos.add(P);
    }

    public void cambiarEstadoPoducto(int codigoProducto, boolean estado){

        for(Producto P: listaDeProductos){
           if (codigoProducto == P.codigoProducto){
               P.setVisible(estado);
           }
        }
    }

    public ArrayList<Producto> getProductos() {
        return listaDeProductos;
    }

    public Producto getProductoById(int codigoProducto){
        for(Producto P: listaDeProductos){
            if(codigoProducto == P.codigoProducto){
                return P;
            }
        }
        return  null;
    }
}
