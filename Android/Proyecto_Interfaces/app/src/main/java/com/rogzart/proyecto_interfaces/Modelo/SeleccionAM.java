package com.rogzart.proyecto_interfaces.Modelo;

import java.io.Serializable;
import java.util.ArrayList;

public class SeleccionAM implements Serializable{
    private int Identificador;
    private ArrayList<AdultoMayor> ListaAsigandos;
    public SeleccionAM(){
        this.Identificador = 0;
        this.ListaAsigandos = new ArrayList<AdultoMayor>();
    }
    public SeleccionAM(int Identificador,ArrayList<AdultoMayor> ListaAsignados){
        this.Identificador = Identificador;
        this.ListaAsigandos = ListaAsignados;
    }

    public int getIdentificador() {
        return Identificador;
    }

    public void setIdentificador(int identificador) {
        Identificador = identificador;
    }

    public ArrayList<AdultoMayor> getListaAsigandos() {
        return ListaAsigandos;
    }

    public void setListaAsigandos(ArrayList<AdultoMayor> listaAsigandos) {
        ListaAsigandos = listaAsigandos;
    }
}
