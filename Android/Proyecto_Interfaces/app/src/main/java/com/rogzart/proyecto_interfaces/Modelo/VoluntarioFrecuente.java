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
        if(fkUsuario == 0){
            fkUsuario = null;
        }
        FkUsuario = fkUsuario;
    }

    public Integer getFkAdultoMayor() {
        return FkAdultoMayor;
    }

    public void setFkAdultoMayor(Integer fkAdultoMayor) {
        if(fkAdultoMayor == 0){
            fkAdultoMayor = null;
        }
        FkAdultoMayor = fkAdultoMayor;
    }
}
