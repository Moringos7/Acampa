package com.rogzart.proyecto_interfaces.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.provider.BaseColumns;


public class BaseDatosAcampa extends SQLiteOpenHelper {
    private static final String NOMBRE_BASE_DATOS = "servicioatemajac.db";

    private static final int VERSION_ACTUAL = 1;

    private final Context contexto;

    public BaseDatosAcampa(Context contexto) {
        super(contexto, NOMBRE_BASE_DATOS, null, VERSION_ACTUAL);
        this.contexto = contexto;
    }
    interface Tablas {
        String adultomayor = "adultomayor";
        String asignacion = "asignacion";
        String comentarioam = "comentarioam";
        String dependencia = "dependencia";
        String domicilio = "domicilio";
        String evento = "evento";
        String fotoalrededores = "fotoalrededores";
        String gestioninventario = "gestioninventario";
        String inventario = "inventario";
        String problematica = "problematica";
        String recoger = "recoger";
        String scouter = "scouter";
        String seccion = "seccion";
        String tipoevento = "tipoevento";
        String tipoproblematica = "tipoproblematica";
        String ubicacion = "ubicacion";
        String usuario = "usuario";
        String voluntariofrecuente = "voluntariofrecuente";
    }
    interface  Referencias{
        //Adultomayor
        String ID_AdultoMayor = String.format("REFERENCES %s(%s)",
                Tablas.adultomayor, EstructuraBaseDatos.adultomayor.Id);
        //Asignacion
        String ID_Asignacion = String.format("REFERENCES %s(%s)",
                Tablas.asignacion, EstructuraBaseDatos.asignacion.Id);
        //Dependencia
        String ID_Dependencia = String.format("REFERENCES %s(%s)",
                Tablas.dependencia, EstructuraBaseDatos.dependencia.Id);
        //Domicilio
        String ID_Domicilio = String.format("REFERENCES %s(%s)",
                Tablas.domicilio, EstructuraBaseDatos.domicilio.Id);
        //Inventario
        String ID_Inventario = String.format("REFERENCES %s(%s)",
                Tablas.inventario, EstructuraBaseDatos.inventario.Id);
        //Scouter
        String ID_Scouter = String.format("REFERENCES %s(%s)",
                Tablas.scouter, EstructuraBaseDatos.scouter.Id);
        //Seccion
        String ID_Seccion = String.format("REFERENCES %s(%s)",
                Tablas.seccion, EstructuraBaseDatos.seccion.Id);
        //Tipoevento
        String ID_TipoEvento = String.format("REFERENCES %s(%s)",
                Tablas.tipoevento, EstructuraBaseDatos.tipoevento.Id);
        //Tipoproblematica
        String ID_TipoProblematica = String.format("REFERENCES %s(%s)",
                Tablas.tipoproblematica, EstructuraBaseDatos.tipoproblematica.Id);
        //Ubicacion
        String ID_Ubicacion = String.format("REFERENCES %s(%s)",
                Tablas.ubicacion, EstructuraBaseDatos.ubicacion.Id);
        //Usuario
        String ID_Usuario = String.format("REFERENCES %s(%s)",
                Tablas.usuario, EstructuraBaseDatos.usuario.Id);
    }
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                db.setForeignKeyConstraintsEnabled(true);
            } else {
                db.execSQL("PRAGMA foreign_keys=ON");
            }
        }
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        /**AdultoMayor**/
        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT NOT NULL UNIQUE," +
                        "%s TEXT  NOT NULL," +
                        "%s TEXT  NOT NULL," +
                        "%s TEXT NOT NULL," +
                        "%s TEXT NOT NULL," +
                        "%s INTEGER NOT NULL," +
                        "%s INTEGER NULL %s," +
                        "%s INTEGER NULL %s)"
                ,
                Tablas.adultomayor, BaseColumns._ID,
                EstructuraBaseDatos.adultomayor.Id,
                EstructuraBaseDatos.adultomayor.Nombre,
                EstructuraBaseDatos.adultomayor.ApellidoPaterno,
                EstructuraBaseDatos.adultomayor.ApellidoMaterno,
                EstructuraBaseDatos.adultomayor.Fotografia,
                EstructuraBaseDatos.adultomayor.Diabetico,
                EstructuraBaseDatos.adultomayor.FkDependencia,Referencias.ID_Dependencia,
                EstructuraBaseDatos.adultomayor.FkDomicilio,Referencias.ID_Domicilio));
        /**Asignacion**/
        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT NOT NULL UNIQUE," +
                        "%s INTEGER  NOT NULL," +
                        "%s TEXT NOT NULL," +
                        "%s INTEGER NULL %s," +
                        "%s INTEGER NULL %s)"
                ,
                Tablas.asignacion, BaseColumns._ID,
                EstructuraBaseDatos.asignacion.Id,
                EstructuraBaseDatos.asignacion.Status,
                EstructuraBaseDatos.asignacion.Fecha,
                EstructuraBaseDatos.asignacion.FkUsuario,Referencias.ID_Usuario,
                EstructuraBaseDatos.asignacion.FkAdultoMayor,Referencias.ID_AdultoMayor));
        /**Comentario AM**/
        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT NOT NULL UNIQUE," +
                        "%s TEXT NOT NULL," +
                        "%s TEXT NOT NULL," +
                        "%s INTEGER NULL %s)"
                ,
                Tablas.comentarioam,BaseColumns._ID,
                EstructuraBaseDatos.comentarioam.Id,
                EstructuraBaseDatos.comentarioam.Nombre,
                EstructuraBaseDatos.comentarioam.Fecha,
                EstructuraBaseDatos.comentarioam.FkAdultoMayor,Referencias.ID_AdultoMayor));
        /**Dependencia**/
        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT NOT NULL UNIQUE," +
                        "%s TEXT NOT NULL)"
                ,
                Tablas.dependencia,BaseColumns._ID,
                EstructuraBaseDatos.dependencia.Id,
                EstructuraBaseDatos.dependencia.Nombre));

        /**Domicilio**/
        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT NOT NULL UNIQUE," +
                        "%s INTEGER NOT NULL," +
                        "%s TEXT NOT NULL," +
                        "%s TEXT NOT NULL," +
                        "%s TEXT NOT NULL," +
                        "%S INTEGER NULL %s)"
                ,
                Tablas.domicilio, BaseColumns._ID,
                EstructuraBaseDatos.domicilio.Id,
                EstructuraBaseDatos.domicilio.Numero,
                EstructuraBaseDatos.domicilio.Calle,
                EstructuraBaseDatos.domicilio.Colonia,
                EstructuraBaseDatos.domicilio.Foto,
                EstructuraBaseDatos.domicilio.FkUbicacion,Referencias.ID_Ubicacion));
        /**Evento**/
        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT NOT NULL UNIQUE," +
                        "%s TEXT NOT NULL," +
                        "%s TEXT NOT NULL," +
                        "%s TEXT NOT NULL," +
                        "%s TEXT NOT NULL," +
                        "%S INTEGER NULL %s)"
                ,
                Tablas.evento, BaseColumns._ID,
                EstructuraBaseDatos.evento.Id,
                EstructuraBaseDatos.evento.Fecha,
                EstructuraBaseDatos.evento.Hora,
                EstructuraBaseDatos.evento.Lugar,
                EstructuraBaseDatos.evento.Informacion,
                EstructuraBaseDatos.evento.FkTipoEveto,Referencias.ID_TipoEvento));
        /**FotoAlrededores**/
        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT NOT NULL UNIQUE," +
                        "%s TEXT NOT NULL," +
                        "%S INTEGER NULL %s)"
                ,
                Tablas.fotoalrededores, BaseColumns._ID,
                EstructuraBaseDatos.fotoalrededores.Id,
                EstructuraBaseDatos.fotoalrededores.Foto,
                EstructuraBaseDatos.fotoalrededores.FkDomiilio,Referencias.ID_Domicilio));
        /**GestionInventario**/
        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT NOT NULL UNIQUE," +
                        "%s TEXT NOT NULL," +
                        "%s INTEGER NULL %s," +
                        "%S INTEGER NULL %s)"
                ,
                Tablas.gestioninventario, BaseColumns._ID,
                EstructuraBaseDatos.gestioninventario.Id,
                EstructuraBaseDatos.gestioninventario.Fecha,
                EstructuraBaseDatos.gestioninventario.FkScouter,Referencias.ID_Scouter,
                EstructuraBaseDatos.gestioninventario.FkInventario, Referencias.ID_Inventario));
        /**Inventario**/
        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT NOT NULL UNIQUE," +
                        "%s TEXT NOT NULL," +
                        "%s REAL NOT NULL," +
                        "%s INTEGER NOT NULL," +
                        "%s TEXT NOT NULL," +
                        "%s TEXT NOT NULL," +
                        "%s TEXT NOT NULL," +
                        "%S INTEGER NOT NULL)"
                ,
                Tablas.inventario, BaseColumns._ID,
                EstructuraBaseDatos.inventario.Id,
                EstructuraBaseDatos.inventario.Producto,
                EstructuraBaseDatos.inventario.Cantidad,
                EstructuraBaseDatos.inventario.Existencia,
                EstructuraBaseDatos.inventario.Descripcion,
                EstructuraBaseDatos.inventario.Imagen,
                EstructuraBaseDatos.inventario.Comentario,
                EstructuraBaseDatos.inventario.Extra));
        /**Problematica**/
        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT NOT NULL UNIQUE," +
                        "%s TEXT NOT NULL," +
                        "%s TEXT NOT NULL," +
                        "%s TEXT NOT NULL," +
                        "%s INTEGER NULL %s," +
                        "%S INTEGER NULL %s)"
                ,
                Tablas.problematica, BaseColumns._ID,
                EstructuraBaseDatos.problematica.Id,
                EstructuraBaseDatos.problematica.Fecha,
                EstructuraBaseDatos.problematica.Nombre,
                EstructuraBaseDatos.problematica.Sugerencia,
                EstructuraBaseDatos.problematica.FkUsuario,Referencias.ID_Usuario,
                EstructuraBaseDatos.problematica.FkTipoProblematica,Referencias.ID_TipoProblematica));
        /**Recoger**/
        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT NOT NULL UNIQUE," +
                        "%s INTEGER NULL %s," +
                        "%S INTEGER NULL %s)"
                ,
                Tablas.recoger, BaseColumns._ID,
                EstructuraBaseDatos.recoger.Id,
                EstructuraBaseDatos.recoger.FkRScouter,Referencias.ID_Scouter,
                EstructuraBaseDatos.recoger.FkAsignacion,Referencias.ID_Asignacion));
        /**Scouter**/
        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT NOT NULL UNIQUE," +
                        "%s TEXT NOT NULL," +
                        "%s TEXT NOT NULL," +
                        "%S INTEGER NULL %s)"
                ,
                Tablas.scouter, BaseColumns._ID,
                EstructuraBaseDatos.scouter.Id,
                EstructuraBaseDatos.scouter.FechaInicio,
                EstructuraBaseDatos.scouter.FechaFinal,
                EstructuraBaseDatos.problematica.FkUsuario,Referencias.ID_Usuario));
        /**Seccion**/
        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT NOT NULL UNIQUE," +
                        "%s TEXT NOT NULL)"
                ,
                Tablas.seccion, BaseColumns._ID,
                EstructuraBaseDatos.seccion.Id,
                EstructuraBaseDatos.seccion.Nombre));

        /**Tipo Evento**/
        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT NOT NULL UNIQUE," +
                        "%s TEXT NOT NULL)"
                ,
                Tablas.tipoevento, BaseColumns._ID,
                EstructuraBaseDatos.tipoevento.Id,
                EstructuraBaseDatos.tipoevento.Nombre));

        /**Tipo Problematica**/
        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT NOT NULL UNIQUE," +
                        "%s TEXT NOT NULL)"
                ,
                Tablas.tipoproblematica,BaseColumns._ID,
                EstructuraBaseDatos.tipoproblematica.Id,
                EstructuraBaseDatos.tipoproblematica.Nombre));
        /**Ubicacion**/
        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT NOT NULL UNIQUE," +
                        "%s REAL NOT NULL," +
                        "%s REAL NOT NULL)"
                ,
                Tablas.ubicacion, BaseColumns._ID,
                EstructuraBaseDatos.ubicacion.Id,
                EstructuraBaseDatos.ubicacion.Longitud,
                EstructuraBaseDatos.ubicacion.Latitud));
        /**Usuario**/
        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT NOT NULL UNIQUE," +
                        "%s TEXT NOT NULL," +
                        "%s TEXT NOT NULL," +
                        "%s TEXT NOT NULL," +
                        "%s TEXT NOT NULL," +
                        "%s TEXT NOT NULL," +
                        "%s TEXT NOT NULL," +
                        "%s INTEGER NOT NULL," +
                        "%S INTEGER NULL %s)"
                ,
                Tablas.usuario, BaseColumns._ID,
                EstructuraBaseDatos.usuario.Id,
                EstructuraBaseDatos.usuario.Nombre,
                EstructuraBaseDatos.usuario.ApellidoPaterno,
                EstructuraBaseDatos.usuario.ApellidoMaterno,
                EstructuraBaseDatos.usuario.Correo,
                EstructuraBaseDatos.usuario.Fotografia,
                EstructuraBaseDatos.usuario.FechaNacimiento,
                EstructuraBaseDatos.usuario.Scout,
                EstructuraBaseDatos.usuario.FkSeccion,Referencias.ID_Seccion));

        /**VoluntarioFrecuente**/
        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT NOT NULL UNIQUE," +
                        "%s INTEGER NULL %s," +
                        "%s INTEGER NULL %s)"
                ,
                Tablas.voluntariofrecuente, BaseColumns._ID,
                EstructuraBaseDatos.voluntariofrecuente.Id,
                EstructuraBaseDatos.voluntariofrecuente.FkUsuario,Referencias.ID_Usuario,
                EstructuraBaseDatos.voluntariofrecuente.FkAdultoMayor,Referencias.ID_AdultoMayor));

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Tablas.adultomayor);
        db.execSQL("DROP TABLE IF EXISTS " + Tablas.asignacion);
        db.execSQL("DROP TABLE IF EXISTS " + Tablas.comentarioam);
        db.execSQL("DROP TABLE IF EXISTS " + Tablas.dependencia);
        db.execSQL("DROP TABLE IF EXISTS " + Tablas.domicilio);
        db.execSQL("DROP TABLE IF EXISTS " + Tablas.evento);
        db.execSQL("DROP TABLE IF EXISTS " + Tablas.fotoalrededores);
        db.execSQL("DROP TABLE IF EXISTS " + Tablas.gestioninventario);
        db.execSQL("DROP TABLE IF EXISTS " + Tablas.inventario);
        db.execSQL("DROP TABLE IF EXISTS " + Tablas.problematica);
        db.execSQL("DROP TABLE IF EXISTS " + Tablas.recoger);
        db.execSQL("DROP TABLE IF EXISTS " + Tablas.scouter);
        db.execSQL("DROP TABLE IF EXISTS " + Tablas.seccion);
        db.execSQL("DROP TABLE IF EXISTS " + Tablas.tipoevento);
        db.execSQL("DROP TABLE IF EXISTS " + Tablas.tipoproblematica);
        db.execSQL("DROP TABLE IF EXISTS " + Tablas.ubicacion);
        db.execSQL("DROP TABLE IF EXISTS " + Tablas.usuario);
        db.execSQL("DROP TABLE IF EXISTS " + Tablas.voluntariofrecuente);
        onCreate(db);
    }
}
