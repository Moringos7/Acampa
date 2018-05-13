package com.rogzart.proyecto_interfaces.Modelo;

import android.support.annotation.IntegerRes;

public class AdultoMayor {
    private int IdAdultoMayor;
    private String Nombre;
    private String ApellidoPaterno;
    private String ApellidoMaterno;
    private String Fotografia;
    private int Diabetico;
    private Integer FkDependencia;
    private Integer FkDomicilio;
    public AdultoMayor(){

    }

    public int getIdAdultoMayor() {
        return IdAdultoMayor;
    }

    public void setIdAdultoMayor(int idAdultoMayor) {
        IdAdultoMayor = idAdultoMayor;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellidoPaterno() {
        return ApellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        ApellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return ApellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        ApellidoMaterno = apellidoMaterno;
    }

    public String getFotografia() {
        return Fotografia;
    }

    public void setFotografia(String fotografia) {
        Fotografia = fotografia;
    }

    public int getDiabetico() {
        return Diabetico;
    }

    public void setDiabetico(int diabetico) {
        Diabetico = diabetico;
    }

    public Integer getFkDependencia() {
        return FkDependencia;
    }

    public void setFkDependencia(Integer fkDependencia) {
        if(fkDependencia == 0){
            fkDependencia = null;
        }
        FkDependencia = fkDependencia;
    }
    public Integer getFkDomicilio() {
        return FkDomicilio;
    }

    public void setFkDomicilio(Integer fkDomicilio) {
        if(fkDomicilio == 0){
            fkDomicilio = null;
        }
        FkDomicilio = fkDomicilio;
    }
}
