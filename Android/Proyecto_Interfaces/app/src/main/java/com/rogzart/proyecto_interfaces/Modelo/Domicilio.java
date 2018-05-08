package com.rogzart.proyecto_interfaces.Modelo;

public class Domicilio {
    private int IdDomicilio;
    private int Numero;
    private String Calle;
    private String Colonia;
    private String Foto;
    private Integer FkUbicacion;
    public Domicilio(){

    }

    public int getIdDomicilio() {
        return IdDomicilio;
    }

    public void setIdDomicilio(int idDomicilio) {
        IdDomicilio = idDomicilio;
    }

    public int getNumero() {
        return Numero;
    }

    public void setNumero(int numero) {
        Numero = numero;
    }

    public String getCalle() {
        return Calle;
    }

    public void setCalle(String calle) {
        Calle = calle;
    }

    public String getColonia() {
        return Colonia;
    }

    public void setColonia(String colonia) {
        Colonia = colonia;
    }

    public String getFoto() {
        return Foto;
    }

    public void setFoto(String foto) {
        Foto = foto;
    }

    public Integer getFkUbicacion() {
        return FkUbicacion;
    }

    public void setFkUbicacion(Integer fkUbicacion) {
        FkUbicacion = fkUbicacion;
    }
}
