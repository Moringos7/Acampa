package com.rogzart.proyecto_interfaces;

import android.graphics.drawable.Drawable;

public class Lista_inventario {
    private String Nombre;
    private String IdInventario;
    private String descripcion;
    private String Existencia;
    private Drawable imagen;

    public Lista_inventario() {
        super();
    }
    public Lista_inventario(String Idinventario, String Nombre, String descripcion, String Existencia, Drawable imagen){
        super();
        this.Nombre = Nombre;
        this.IdInventario= Idinventario;
        this.descripcion= descripcion;
        this.Existencia= Existencia;
        this.imagen= imagen;

    }

}

