package com.rogzart.proyecto_interfaces.Modelo;

public class Notificacion {

    private Usuario usuario;
    private Evento evento;
    private TipoEvento tipoEvento;
    private int IdNotificacion;
    public Notificacion(){
        usuario = null;
        evento =  null;
        tipoEvento = null;
    }

    public int getIdNotificacion() {
        return IdNotificacion;
    }

    public void setIdNotificacion(int idNotificacion) {
        IdNotificacion = idNotificacion;
    }

    public Notificacion(Usuario usuario, Evento evento, TipoEvento tipoEvento){
        this.usuario = usuario;
        this.evento = evento;
        this.tipoEvento = tipoEvento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public TipoEvento getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(TipoEvento tipoEvento) {
        this.tipoEvento = tipoEvento;
    }
}
