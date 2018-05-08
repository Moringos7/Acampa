package com.rogzart.proyecto_interfaces.Modelo;

public class FotoAlrededores {
    private int IdFotoAlrededores;
    private String Foto;
    private Integer FkDomicilio;
    public FotoAlrededores(){

    }

    public int getIdFotoAlrededores() {
        return IdFotoAlrededores;
    }

    public void setIdFotoAlrededores(int idFotoAlrededores) {
        IdFotoAlrededores = idFotoAlrededores;
    }

    public String getFoto() {
        return Foto;
    }

    public void setFoto(String foto) {
        Foto = foto;
    }

    public Integer getFkDomicilio() {
        return FkDomicilio;
    }

    public void setFkDomicilio(Integer fkDomicilio) {
        FkDomicilio = fkDomicilio;
    }
}
