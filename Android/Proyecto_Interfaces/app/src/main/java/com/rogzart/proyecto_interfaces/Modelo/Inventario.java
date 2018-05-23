package com.rogzart.proyecto_interfaces.Modelo;

import java.io.Serializable;

public class Inventario implements Serializable {
    private int IdInventario;
    private String Producto;
    private float Cantidad;
    private int Existencia;
    private String Descripcion;
    private String Imagen;
    private String Comentario;
    private int Extra;
    public Inventario(){

    }

    public int getIdInventario() {
        return IdInventario;
    }

    public void setIdInventario(int idInventario) {
        IdInventario = idInventario;
    }

    public String getProducto() {
        return Producto;
    }

    public void setProducto(String producto) {
        Producto = producto;
    }

    public float getCantidad() {
        return Cantidad;
    }

    public void setCantidad(float cantidad) {
        Cantidad = cantidad;
    }

    public int getExistencia() {
        return Existencia;
    }

    public void setExistencia(int existencia) {
        Existencia = existencia;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String imagen) {
        Imagen = imagen;
    }

    public String getComentario() {
        return Comentario;
    }

    public void setComentario(String comentario) {
        Comentario = comentario;
    }

    public int getExtra() {
        return Extra;
    }

    public void setExtra(int extra) {
        Extra = extra;
    }
}
