package com.rogzart.proyecto_interfaces.Modelo;

public class Problematica {
    private int IdProblematica;
    private String Fecha;
    private String Nombre;
    private String Sugerencia;
    private Integer FkUsuario;
    private Integer FkTipoProblematica;
    public Problematica(){

    }

    public int getIdProblematica() {
        return IdProblematica;
    }

    public void setIdProblematica(int idProblematica) {
        IdProblematica = idProblematica;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getSugerencia() {
        return Sugerencia;
    }

    public void setSugerencia(String sugerencia) {
        Sugerencia = sugerencia;
    }

    public Integer getFkUsuario() {
        return FkUsuario;
    }

    public void setFkUsuario(Integer fkUsuario) {
        FkUsuario = fkUsuario;
    }

    public Integer getFkTipoProblematica() {
        return FkTipoProblematica;
    }

    public void setFkTipoProblematica(Integer fkTipoProblematica) {
        FkTipoProblematica = fkTipoProblematica;
    }
}
