package com.rogzart.proyecto_interfaces.Modelo;

public class VoluntarioFrecuente {
    private int IdVoluntarioFrecuente;
    private Integer FkUsuario;
    private Integer FkAdultoMayor;
    public VoluntarioFrecuente(){

    }

    public int getIdVoluntarioFrecuente() {
        return IdVoluntarioFrecuente;
    }

    public void setIdVoluntarioFrecuente(int idVoluntarioFrecuente) {
        IdVoluntarioFrecuente = idVoluntarioFrecuente;
    }

    public Integer getFkUsuario() {
        return FkUsuario;
    }

    public void setFkUsuario(Integer fkUsuario) {
        FkUsuario = fkUsuario;
    }

    public Integer getFkAdultoMayor() {
        return FkAdultoMayor;
    }

    public void setFkAdultoMayor(Integer fkAdultoMayor) {
        FkAdultoMayor = fkAdultoMayor;
    }
}
