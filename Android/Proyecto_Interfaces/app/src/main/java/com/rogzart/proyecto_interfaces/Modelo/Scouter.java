package com.rogzart.proyecto_interfaces.Modelo;

public class Scouter {
    private int IdScouter;
    private String FechaInicio;
    private String FechaFinal;
    private Integer FkUsuario;
    public Scouter(){

    }

    public int getIdScouter() {
        return IdScouter;
    }

    public void setIdScouter(int idScouter) {
        IdScouter = idScouter;
    }

    public String getFechaInicio() {
        return FechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        FechaInicio = fechaInicio;
    }

    public String getFechaFinal() {
        return FechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
        FechaFinal = fechaFinal;
    }

    public Integer getFkUsuario() {
        return FkUsuario;
    }

    public void setFkUsuario(Integer fkUsuario) {
        FkUsuario = fkUsuario;
    }
}
