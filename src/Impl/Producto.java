package Impl;

public class Producto {
	int codigoProducto;
	String descripcion;
	double precio;
	boolean estado;
	
	Producto(int codigoProducto, String descripcion, double precio,boolean estado){
		this.codigoProducto = codigoProducto;
		this.descripcion = descripcion;
		this.precio = precio;
		this.estado = estado;
	}
}