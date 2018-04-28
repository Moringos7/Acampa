package com.rogzart.proyecto_interfaces.Modelos;

public class Conexion {
    private String IpServer;
    private String Ruta;
    public Conexion(){
        IpServer =  "http://35.196.37.188/";
    }
    public void setRuta(String comp){
        this.Ruta = comp;
    }
    public String getRuta(){

        return IpServer+Ruta;
    }
}
