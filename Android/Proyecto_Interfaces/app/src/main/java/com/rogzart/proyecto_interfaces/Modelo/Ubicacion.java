package com.rogzart.proyecto_interfaces.Modelo;

public class Ubicacion {
    private int IdUbicacion;
    private double Longitud;
    private double Latitud;
    public Ubicacion(){

    }

    public int getIdUbicacion() {
        return IdUbicacion;
    }

    public void setIdUbicacion(int idUbicacion) {
        IdUbicacion = idUbicacion;
    }

    public double getLongitud() {
        return Longitud;
    }

    public void setLongitud(double longitud) {
        Longitud = longitud;
    }

    public double getLatitud() {
        return Latitud;
    }

    public void setLatitud(double latitud) {
        Latitud = latitud;
    }
}
