package com.rogzart.proyecto_interfaces.Adultos;

import android.text.BoringLayout;

/**
 * Created by dany on 15/03/2018.
 */

public class Adultos {
    private int id;
    private String nombre="";
    private String Apellidop="";
    private String Apellidom="";
    private Boolean Diabetico;
    public Adultos(){}

    public Adultos(int i,String n,String p,String m, Boolean d){
        this.id=i;
        this.nombre=n;
        this.Apellidop=p;
        this.Apellidom=m;
        this.Diabetico=d;
    }
    public int getId(){return id;}
    public void setId(int i){this.id=i;}
    public String  getNombre(){return nombre;}
    public void  setNombre(String n){this.nombre=n;}
    public String  getApellidop(){return Apellidop;}
    public void  setApellidop(String p){this.Apellidop=p;}
    public String  getApellidom(){return Apellidom;}
    public void  setApellidom(String m){this.Apellidom=m;}
    public Boolean  getDiabetico(){return Diabetico;}
    public void  setDiabetico(Boolean d){this.Diabetico=d;}
    @Override
    public String toString(){
        return nombre+" "+Apellidop+ " "+Apellidom+" "+Diabetico;
    }
}
