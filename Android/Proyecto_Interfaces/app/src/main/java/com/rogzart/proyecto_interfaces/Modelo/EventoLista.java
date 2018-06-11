package com.rogzart.proyecto_interfaces.Modelo;

import java.io.Serializable;

public class EventoLista implements Serializable{
    private int IdEvento;
    private String Fecha;
    private EventoLista evento;
    private String Hora;
    private String Lugar;
    private String Informacion;
    private Integer FkTipoEvento;
    private String anio,mes,dia;
    public EventoLista(){

    }

    public int getIdEvento() {
        return IdEvento;
    }

    public void setIdEvento(int idEvento) {
        IdEvento = idEvento;
    }

    public String getFecha() {
        String  []datos=Fecha.split("-");
        anio=datos[0];
        mes=datos[1];
        dia=datos[2];
        String fechafinal= datos[2]+"/ "+datos[1]+"/ "+datos[0];

        return fechafinal;
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
