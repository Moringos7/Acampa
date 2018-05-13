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
        if(fkUsuario == 0){
            fkUsuario = null;
        }
        FkUsuario = fkUsuario;
    }

    public Integer getFkTipoProblematica() {
        return FkTipoProblematica;
    }

    public void setFkTipoProblematica(Integer fkTipoProblematica) {
        if(fkTipoProblematica == 0){
            fkTipoProblematica = null;
        }
        FkTipoProblematica = fkTipoProblematica;
    }
}
