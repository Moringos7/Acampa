package com.rogzart.proyecto_interfaces.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.rogzart.proyecto_interfaces.data.BaseDeDatosLocalTablas.*;

public class BaseDeDatosLocal extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME= "servicioatemajac.db";

    public BaseDeDatosLocal(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    sqLiteDatabase.execSQL("CREATE TABLE "+ Adultos.Table_Name +"("
            + Adultos.IdAdultoMayor + "INTEGER PRIMARY KEY AUTOINCREMENT,"
            + Adultos.Nombre +"VARCHAR(50),"
            + Adultos.ApellidoPaterno +"VARCHAR(50),"
            + Adultos.ApellidoMaterno +"VARCHAR(50),"
            + Adultos.Fotografia + "VARCHAR(100),"
            + Adultos.Diabetico + "TINYINT(1),"
            +

            .FkDependencia + "INTEGER,"
            + Adultos.FkDependencia + "INTEGER,"
            + "UNIQUE(" + Adultos.IdAdultoMayor + "))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public long SaveAdultos(BaseDeDatos baseDeDatos){

        SQLiteDatabase sqLiteDatabase= getWritableDatabase();

        return sqLiteDatabase.insert(
        Adultos.Table_Name, null, baseDeDatos.toContentValues());
        }

    }

