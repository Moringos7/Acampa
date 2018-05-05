package com.josemi.basededatos.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import com.josemi.basededatos.sqlite.BaseDatosPedido.Tablas;

import com.josemi.basededatos.modelo.CabeceraPedido;
import com.josemi.basededatos.modelo.Cliente;
import com.josemi.basededatos.modelo.DetallePedido;
import com.josemi.basededatos.modelo.FormaPago;
import com.josemi.basededatos.modelo.Producto;

import com.josemi.basededatos.sqlite.ContratoPedido.CabecerasPedido;
import com.josemi.basededatos.sqlite.ContratoPedido.DetallesPedido;
import com.josemi.basededatos.sqlite.ContratoPedido.Productos;
import com.josemi.basededatos.sqlite.ContratoPedido.Clientes;
import com.josemi.basededatos.sqlite.ContratoPedido.FormasPago;

public final class OperacionesBaseDatos {
    private static BaseDatosPedido baseDatos;

    private static OperacionesBaseDatos instancia = new OperacionesBaseDatos();


    public OperacionesBaseDatos() {
    }

    public static OperacionesBaseDatos obtenerInstancia(Context contexto) {
        if (baseDatos == null) {
            baseDatos = new BaseDatosPedido(contexto);
        }
        return instancia;
    }
    public void InsertarProducto(Producto x){
        SQLiteDatabase query = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(ContratoPedido.Productos.ID,x.idProducto);
        valores.put(ContratoPedido.Productos.NOMBRE,x.nombre);
        valores.put(ContratoPedido.Productos.PRECIO,x.precio);
        valores.put(ContratoPedido.Productos.EXISTENCIAS,x.existencias);
        query.insert("producto",null,valores);
    }
    public Cursor LeerProducto(){
        SQLiteDatabase query = baseDatos.getReadableDatabase();
        Cursor c = query.rawQuery("SELECT * FROM producto",null);
        return c;
    }
    public SQLiteDatabase getDb() {
        return baseDatos.getWritableDatabase();
    }

}

