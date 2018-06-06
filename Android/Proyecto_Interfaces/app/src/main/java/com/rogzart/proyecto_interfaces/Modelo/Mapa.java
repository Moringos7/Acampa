package com.rogzart.proyecto_interfaces.Modelo;

import java.io.Serializable;

public class Mapa implements Serializable{
    private AdultoMayor adultoMayor;
    private Ubicacion ubicacion;
    private Boolean Check;



    public Mapa(){
        adultoMayor = new AdultoMayor();
        ubicacion = new Ubicacion();
        this.Check = false;
    }
    public Mapa(AdultoMayor adultoMayor, Ubicacion ubicacion){
        this.adultoMayor = adultoMayor;
        this.ubicacion = ubicacion;
        this.Check = false;
    }

    public AdultoMayor getAdultoMayor() {
        return adultoMayor;
    }

    public void setAdultoMayor(AdultoMayor adultoMayor) {
        this.adultoMayor = adultoMayor;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }
    public Boolean getCheck() {
        return Check;
    }

    public void setCheck(Boolean check) {
        Check = check;
    }
}
