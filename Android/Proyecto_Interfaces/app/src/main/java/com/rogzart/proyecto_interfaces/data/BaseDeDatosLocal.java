package com.rogzart.proyecto_interfaces.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import static com.rogzart.proyecto_interfaces.data.BaseDeDatosLocalTablas.*;

public class BaseDeDatosLocal extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "servicioatemajac.db";

    public BaseDeDatosLocal(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + Adultos.Table_NameAM + "("
                + Adultos.IdAdultoMayor + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Adultos.Nombre + "VARCHAR(50),"
                + Adultos.ApellidoPaterno + "VARCHAR(50),"
                + Adultos.ApellidoMaterno + "VARCHAR(50),"
                + Adultos.Fotografia + "VARCHAR(100),"
                + Adultos.Diabetico + "TINYINT(1),"
                + Adultos.FkDependencia + "INTEGER,"
                + Adultos.FkDependencia + "INTEGER,"
                + "UNIQUE(" + Adultos.IdAdultoMayor + "))");
        sqLiteDatabase.execSQL("CREATE TABLE " + Asignacion.Table_NameAs + "("
                + Asignacion.IdAsignacion + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Asignacion.Status + "TINYINT(1),"
                + Asignacion.Fecha + "DATE,"
                + Asignacion.FkUsuario + "INTEGER,"
                + Asignacion.FkAdultoMayor + "INTEGER,"
                + "UNIQUE(" + Asignacion.IdAsignacion + "))");
        sqLiteDatabase.execSQL("CREATE TABLE " + ComentarioAM.Table_NameCAM +"("
                +ComentarioAM.IdComentarioAM + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                +ComentarioAM.NombreC + "TEXT,"
                +ComentarioAM.FechaC + "DATE,"
                +ComentarioAM.FkAdultoMayor + "INTEGER,"
                + "UNIQUE(" + ComentarioAM.IdComentarioAM + "))");

    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long SaveAdultos(BaseDeDatos baseDeDatos) {

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        return sqLiteDatabase.insert(
                Adultos.Table_NameAM, null, baseDeDatos.toContentValuesAM());
    }

    public long SaveAsignacion(BaseDeDatos baseDeDatos) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert(
                Asignacion.Table_NameAs, null, baseDeDatos.toContentValuesAS());
    }
    public long SaveComentariosAM(BaseDeDatos baseDeDatos){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert(
                ComentarioAM.Table_NameCAM, null, baseDeDatos.toContentValuesCAM());

    }
}

