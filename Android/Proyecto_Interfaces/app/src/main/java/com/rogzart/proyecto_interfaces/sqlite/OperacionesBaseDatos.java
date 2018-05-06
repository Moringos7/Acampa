package com.rogzart.proyecto_interfaces.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.rogzart.proyecto_interfaces.sqlite.EstructuraBaseDatos.adultomayor;
import com.rogzart.proyecto_interfaces.Modelo.AdultoMayor;

public final class OperacionesBaseDatos {
    private static BaseDatosAcampa baseDatos;

    private static OperacionesBaseDatos instancia = new OperacionesBaseDatos();


    public OperacionesBaseDatos() {
    }

    public static OperacionesBaseDatos obtenerInstancia(Context contexto) {
        if (baseDatos == null) {
            baseDatos = new BaseDatosAcampa(contexto);
        }
        return instancia;
    }
    public void InsertarAdultoMayor(AdultoMayor x){
        SQLiteDatabase query = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put(adultomayor.Id,x.getIdAdultoMayor());
        valores.put(adultomayor.Nombre,x.getNombre());
        valores.put(adultomayor.ApellidoPaterno,x.getApellidoPaterno());
        valores.put(adultomayor.ApellidoMaterno,x.getApellidoMaterno());
        valores.put(adultomayor.Fotografia,x.getFotografia());
        valores.put(adultomayor.Diabetico,x.getDiabetico());
        valores.put(adultomayor.FkDependencia,x.getFkDependencia());
        valores.put(adultomayor.FkDomicilio,x.getFkDomicilio());
        query.insert("adultomayor",null,valores);

    }
    public Cursor LeerAdultoMayor(){
        SQLiteDatabase query = baseDatos.getReadableDatabase();
        Cursor c = query.rawQuery("SELECT * FROM adultomayor",null);
        return c;
    }


}
