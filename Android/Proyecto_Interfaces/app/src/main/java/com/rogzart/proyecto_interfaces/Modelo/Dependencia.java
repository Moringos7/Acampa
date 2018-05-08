package com.rogzart.proyecto_interfaces.Modelo;

public class Dependencia {
    private int IdDependencia;
    private String Nombre;
    public Dependencia(){

    }

    public int getIdDependencia() {
        return IdDependencia;
    }

    public void setIdDependencia(int idDependencia) {
        IdDependencia = idDependencia;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }
}
