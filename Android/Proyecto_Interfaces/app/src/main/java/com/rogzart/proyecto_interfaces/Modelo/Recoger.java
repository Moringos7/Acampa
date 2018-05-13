package com.rogzart.proyecto_interfaces.Modelo;

public class Recoger {
    private int IdRecoger;
    private Integer FkScouter;
    private Integer FkAsignacion;
    public Recoger(){

    }

    public int getIdRecoger() {
        return IdRecoger;
    }

    public void setIdRecoger(int idRecoger) {
        IdRecoger = idRecoger;
    }

    public Integer getFkScouter() {
        return FkScouter;
    }

    public void setFkScouter(Integer fkScouter) {
        if(fkScouter == 0){
            fkScouter = null;
        }
        FkScouter = fkScouter;
    }

    public Integer getFkAsignacion() {
        return FkAsignacion;
    }

    public void setFkAsignacion(Integer fkAsignacion) {
        if(fkAsignacion == 0){
            fkAsignacion = null;
        }
        FkAsignacion = fkAsignacion;
    }
}
