package com.rogzart.proyecto_interfaces.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.DashPathEffect;
import android.provider.BaseColumns;
import android.util.EventLog;

import com.rogzart.proyecto_interfaces.Barra_desplegable;
import com.rogzart.proyecto_interfaces.Splash.SplashActivity;



import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static com.rogzart.proyecto_interfaces.data.BaseDeDatosLocalTablas.*;

public class BaseDeDatosLocal extends SQLiteOpenHelper {
    private static String  DB_PATH = "/data/data/com.rogzart/proyecto_interfaces/data";
    public static final int DATABASE_VERSION = 1;
    public static final String DB_NAME = "servicioatemajac.db";

    public BaseDeDatosLocal(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);



    createDatabase();

    }

    public  void createDatabase() {
        boolean dbExist = checkDatabase();
        SQLiteDatabase db_Read = null;
        if (dbExist){
            //la base de datos ya existe
        }
        else{
            db_Read= this.getReadableDatabase();
            db_Read.close();
        }

    }

    private  boolean checkDatabase() {
        File dbfile= new File(DB_PATH + DB_NAME);
        return dbfile.exists();
    }

    interface Tablas{
        String Adultos="Adultos";

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
        /**sqLiteDatabase.execSQL("CREATE TABLE " + Coordinador.Table_NameCo +"("
                +Coordinador.IdCoordinador + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                +Coordinador.FkScouter + "INTEGER,"
                + "UNIQUE("+ Coordinador.IdCoordinador+ "))" );**/
        sqLiteDatabase.execSQL("CREATE TABLE " + Dependencia.Table_NameD+"("
                +Dependencia.IdDependencia + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                +Dependencia.NombreD + "VARCHAR(30),"
                + "UNIQUE("+ Dependencia.IdDependencia+ "))");
        sqLiteDatabase.execSQL("CREATE TABLE " + Domicilio.Table_NameDom+"("
                +Domicilio.IdDomicilio + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                +Domicilio.Numero + "INTEGER,"
                +Domicilio.Calle + "VARCHAR(50),"
                +Domicilio.Colonia + "VARCHAR(50),"
                +Domicilio.FotoD + "VARCHAR(100),"
                +Domicilio.FkUbicacion + "INTEGER,"
                + "UNIQUE("+ Dependencia.IdDependencia+ "))");
        sqLiteDatabase.execSQL("CREATE TABLE " + Evento.Table_NameE+"("
                +Evento.IdEvento + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                +Evento.FechaC + "DATE,"
                +Evento.Hora + "TIME,"
                +Evento.Lugar + "vARCHAR(100),"
                +Evento.Informacion +"TEXT,"
                +Evento.FkTipoEvento + "INTEGER,"
                + "UNIQUE(" + Evento.IdEvento+ "))");
        sqLiteDatabase.execSQL("CREATE TABLE " + FotoAlrededores.Table_NameFA+"("
                +FotoAlrededores.IdEventosAlrededores + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                +FotoAlrededores.Foto + "VARCHAR(100),"
                +FotoAlrededores.FkAdultoMayor + "INTEGER,"
                + "UNIQUE(" +FotoAlrededores.IdEventosAlrededores+ "))");
        sqLiteDatabase.execSQL("CREATE TABLE " + GestionInventario.Table_NameGI+"("
                +GestionInventario.IdGestionInventario + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                +GestionInventario.FechaGT + "DATE,"
                +GestionInventario.FkScouter + "INTEGER,"
                +GestionInventario.Fkinventario + "INTEGER,"
                + "UNIQUE("+ GestionInventario.IdGestionInventario+ "))");
        sqLiteDatabase.execSQL("CREATE TABLE " + Inventario.Table_NameIN+"("
                +Inventario.IdInventario + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                +Inventario.Producto + "VARCHAR(30),"
                +Inventario.Cantidad + "FLOAT,"
                +Inventario.Existencia + "INTEGER,"
                +Inventario.Descripcion + "TEXT,"
                +Inventario.Imagen + "VARCHAR(30),"
                +Inventario.Comentario + "TEXT,"
                +Inventario.Extra + "TINYINT,"
                + "UNIQUE(" + Inventario.IdInventario+ "))");
        /**sqLiteDatabase.execSQL("CREATE TABLE " +Password.Table_NameP+"("
                +Password.IdPassword + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                +Password.Password + "VARCHAR(30),"
                +Password.Intentos + "INT,"
         //Falta fecha CUIDADO
                +Password.FkUsuario + "INT,"
                + "UNIQUE(" + Password.IdPassword+ "))");**/
        sqLiteDatabase.execSQL("CREATE TABLE " +Problematica.Table_NamePr + "("
                +Problematica.IdProblematica + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                +Problematica.Fecha + "DATE,"
                +Problematica.Nombre + "TEXT,"
                +Problematica.Sugerencia + "TEXT,"
                +Problematica.FkUsuario + "INTEGER,"
                +Problematica.FkTipoProblematica + "INTEGER,"
                + "UNIQUE(" + Problematica.IdProblematica+"))");
        sqLiteDatabase.execSQL("CREATE TABLE " +Recoger.Table_NameR + "("
                +Recoger.IdRecoger + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                +Recoger.FkScouter + "INTEGER,"
                +Recoger.FkAsignacion + "INTEGER,"
                + "UNIQUE(" +Recoger.IdRecoger+"))");
        sqLiteDatabase.execSQL("CREATE TABLE " + Scouter.Table_NameS+"("
                +Scouter.IdScouter +  "INTEGER PRIMARY KEY AUTOINCREMENT,"
                +Scouter.FechaInicio + "DATE,"
                +Scouter.FechaFinal + "DATE,"
                +Scouter.FkUsuario + "INTEGER,"
                + "UNIQUE(" +Recoger.IdRecoger +"))");
        sqLiteDatabase.execSQL("CREATE TABLE " +Seccion.Table_NameSe+"("
                +Seccion.IdSeccion + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                +Seccion.Nombre + "VARCHAR(20),"
                + "UNIQUE(" +Seccion.IdSeccion + "))");
        sqLiteDatabase.execSQL("CREATE TABLE " +TipoEvento.Table_NameTE+"("
                +TipoEvento.IdTipoEvento + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                +TipoEvento.Nombre + "VARCHAR(20),"
                +"UNIQUE(" +TipoEvento.IdTipoEvento + "))");
        sqLiteDatabase.execSQL("CREATE TABLE " +TipoProblematica.Table_NameTP+"("
                +TipoProblematica.IdTipoProblematica + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                +TipoProblematica.Nombre + "VARCHAR(50),"
                + "UNIQUE(" +TipoProblematica.IdTipoProblematica + "))");
        sqLiteDatabase.execSQL("CREATE TABLE " +Ubicacion.Table_NameU+"("
                +Ubicacion.IdUbicacion + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                +Ubicacion.Longitud + "DOUBLE,"
                +Ubicacion.Latitud + "DOUBLE,"
                + "UNIQUE(" +TipoProblematica.IdTipoProblematica + "))");
        sqLiteDatabase.execSQL("CREATE TABLE " +Usuario.Table_NameUs+"("
                +Usuario.IdUsuario + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                +Usuario.Nombre + "VARCHAR(50),"
                +Usuario.ApellidoPaterno + "VARCHAR(50),"
                +Usuario.ApellidoMaterno + "VARCHAR(50),"
                +Usuario.Correo + "VARCHAR(50),"
                +Usuario.Fotografia + "VARCHAR(100),"
                +Usuario.FechaNacimiento + "DATE,"
                +Usuario.Scout + "TINYINT,"
                +Usuario.FkSeccion + "INTEGER,"
                + "UNIQUE(" +Usuario.IdUsuario + "))");
        sqLiteDatabase.execSQL("CREATE TABLE " + VoluntarioFrecuente.Table_NameVF+"("
                +VoluntarioFrecuente.IdVoluntarioFrecuente + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                +VoluntarioFrecuente.FkUsuario + "INTEGER,"
                +VoluntarioFrecuente.FkAdultoMayor + "INTEGER,"
                + "UNIQUE(" +VoluntarioFrecuente.IdVoluntarioFrecuente + "))");








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
    public long SaveCoordinador(BaseDeDatos baseDeDatos){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert(
                Coordinador.Table_NameCo, null, baseDeDatos.toContentValuesCo());

    }
}

