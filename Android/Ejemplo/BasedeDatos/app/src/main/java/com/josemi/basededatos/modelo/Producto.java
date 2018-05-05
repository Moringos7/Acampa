package com.josemi.basededatos.modelo;


public class Producto {

    public int idProducto;

    public String nombre;

    public float precio;

    public int existencias;

    public Producto(int idProducto, String nombre, float precio, int existencias) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.existencias = existencias;
    }

    public Producto() {

    }
}
