package com.rogzart.proyecto_interfaces.Modelo;

public class GestionInventario {
    private int IdGestionInventario;
    private String Fecha;
    private Integer FkScouter;
    private Integer FkInventario;
    public GestionInventario(){

    }

    public int getIdGestionInventario() {
        return IdGestionInventario;
    }

    public void setIdGestionInventario(int idGestionInventario) {
        IdGestionInventario = idGestionInventario;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public Integer getFkScouter() {
        return FkScouter;
    }

    public void setFkScouter(Integer fkScouter) {
        FkScouter = fkScouter;
    }

    public Integer getFkInventario() {
        return FkInventario;
    }

    public void setFkInventario(Integer fkInventario) {
        FkInventario = fkInventario;
    }
}
