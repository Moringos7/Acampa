package com.rogzart.proyecto_interfaces.Modelo;

public class Mapas{
    private AdultoMayor adultoMayor;
    private Ubicacion ubicacion;
    public Mapas(){
        adultoMayor = new AdultoMayor();
        ubicacion = new Ubicacion();
    }
    public Mapas(AdultoMayor adultoMayor,Ubicacion ubicacion){
        this.adultoMayor = adultoMayor;
        this.ubicacion = ubicacion;
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
}
