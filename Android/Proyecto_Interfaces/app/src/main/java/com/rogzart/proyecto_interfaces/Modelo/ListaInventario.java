package com.rogzart.proyecto_interfaces.Modelo;

import java.security.PrivateKey;

public class ListaInventario extends Inventario {

    private int Requerido;
    public ListaInventario(){

    }
    public int getRequerido() {
        return Requerido;
    }

    public void setRequerido(int requerido) {
        Requerido = requerido;
    }



}
