package com.rogzart.proyecto_interfaces.Modelo;

import java.io.Serializable;

public class Evento implements Serializable{
    private int IdEvento;
    private String Fecha;
    private String Hora;
    private String Lugar;
    private String Informacion;
    private Integer FkTipoEvento;
    public Evento(){

    }

    public int getIdEvento() {
        return IdEvento;
    }

    public void setIdEvento(int idEvento) {
        IdEvento = idEvento;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getHora() {
        return Hora;
    }

    public void setHora(String hora) {
        Hora = hora;
    }

    public String getLugar() {
        return Lugar;
    }

    public void setLugar(String lugar) {
        Lugar = lugar;
    }

    public String getInformacion() {
        return Informacion;
    }

    public void setInformacion(String informacion) {
        Informacion = informacion;
    }

    public Integer getFkTipoEvento() { return FkTipoEvento;
    }

    public void setFkTipoEvento(Integer fkTipoEvento) {
        if(fkTipoEvento == 0){
            fkTipoEvento = null;
        }
        FkTipoEvento = fkTipoEvento;
    }
}
