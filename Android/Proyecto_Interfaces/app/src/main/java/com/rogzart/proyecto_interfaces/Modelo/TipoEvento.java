package com.rogzart.proyecto_interfaces.Modelo;

public class TipoEvento {
    private int IdTipoEvento;
    private String Nombre;
    public TipoEvento(){

    }

    public int getIdTipoEvento() {
        return IdTipoEvento;
    }

    public void setIdTipoEvento(int idTipoEvento) {
        IdTipoEvento = idTipoEvento;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }
}
