package com.rogzart.proyecto_interfaces.Modelo;

public class TipoProblematica {
    private int IdTipoProblematica;
    private String Nombre;
    public TipoProblematica(){

    }

    public int getIdTipoProblematica() {
        return IdTipoProblematica;
    }

    public void setIdTipoProblematica(int idTipoProblematica) {
        IdTipoProblematica = idTipoProblematica;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }
}
