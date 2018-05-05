package com.josemi.basededatos.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.provider.BaseColumns;
import com.josemi.basededatos.sqlite.ContratoPedido.CabecerasPedido;
import com.josemi.basededatos.sqlite.ContratoPedido.DetallesPedido;
import com.josemi.basededatos.sqlite.ContratoPedido.Productos;
import com.josemi.basededatos.sqlite.ContratoPedido.Clientes;
import com.josemi.basededatos.sqlite.ContratoPedido.FormasPago;

public class BaseDatosPedido extends SQLiteOpenHelper{
    private static final String NOMBRE_BASE_DATOS = "pedidos.db";

    private static final int VERSION_ACTUAL = 1;

    private final Context contexto;

    /**
     * Creacion del constructor unicamente con los datos previstos, no los por default
    * **/
    public BaseDatosPedido(Context contexto) {
        super(contexto, NOMBRE_BASE_DATOS, null, VERSION_ACTUAL);
        this.contexto = contexto;
    }

    interface Tablas {
        String CABECERA_PEDIDO = "cabecera_pedido";
        String DETALLE_PEDIDO = "detalle_pedido";
        String PRODUCTO = "producto";
        String CLIENTE = "cliente";
        String FORMA_PAGO = "forma_pago";
    }

    /**
     * Al parecer aqui se crea la relacion de que campo serán los Primary Key en las tablas (¿o no?)
     * **/
    interface Referencias {

        String ID_CABECERA_PEDIDO = String.format("REFERENCES %s(%s) ON DELETE CASCADE",
                Tablas.CABECERA_PEDIDO, CabecerasPedido.ID);

        String ID_PRODUCTO = String.format("REFERENCES %s(%s)",
                Tablas.PRODUCTO, Productos.ID);

        String ID_CLIENTE = String.format("REFERENCES %s(%s)",
                Tablas.CLIENTE, Clientes.ID);

        String ID_FORMA_PAGO = String.format("REFERENCES %s(%s)",
                Tablas.FORMA_PAGO, FormasPago.ID);
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
        /**
         * El uso de BaseColumns._ID especifica que la columna se llamará _ID se deben hacer pruebas
         * para verificar que esto no interviene con nuestro diseño de base de datos de ser así es
         * necesario cambiar, de hecho la columna _ID es utilizada por los listview
         * http://flipandroid.com/cul-es-el-uso-de-basecolumns-en-android.html
         *
         * **/
        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT UNIQUE NOT NULL,%s DATETIME NOT NULL,%s TEXT NOT NULL %s," +
                        "%s TEXT NOT NULL %s)",
                Tablas.CABECERA_PEDIDO, BaseColumns._ID,
                CabecerasPedido.ID, CabecerasPedido.FECHA,
                CabecerasPedido.ID_CLIENTE, Referencias.ID_CLIENTE,
                CabecerasPedido.ID_FORMA_PAGO, Referencias.ID_FORMA_PAGO));

        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT NOT NULL %s,%s INTEGER NOT NULL CHECK (%s>0),%s INTEGER NOT NULL %s," +
                        "%s INTEGER NOT NULL,%s REAL NOT NULL,UNIQUE (%s,%s) )",
                Tablas.DETALLE_PEDIDO, BaseColumns._ID,
                DetallesPedido.ID, Referencias.ID_CABECERA_PEDIDO,
                DetallesPedido.SECUENCIA, DetallesPedido.SECUENCIA,
                DetallesPedido.ID_PRODUCTO, Referencias.ID_PRODUCTO,
                DetallesPedido.CANTIDAD, DetallesPedido.PRECIO,
                DetallesPedido.ID, DetallesPedido.SECUENCIA));

        db.execSQL(String.format("CREATE TABLE %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT NOT NULL UNIQUE,%s TEXT NOT NULL,%s REAL NOT NULL," +
                        "%s INTEGER NOT NULL CHECK(%s>=0) )",
                Tablas.PRODUCTO, BaseColumns._ID,
                Productos.ID, Productos.NOMBRE,Productos.PRECIO,
                Productos.EXISTENCIAS, Productos.EXISTENCIAS));

        db.execSQL(String.format("CREATE TABLE %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT NOT NULL UNIQUE,%s TEXT NOT NULL,%s TEXT NOT NULL,%s )",
                Tablas.CLIENTE, BaseColumns._ID,
                Clientes.ID, Clientes.NOMBRES, Clientes.APELLIDOS, Clientes.TELEFONO));

        db.execSQL(String.format("CREATE TABLE %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT NOT NULL UNIQUE,%s TEXT NOT NULL )",
                Tablas.FORMA_PAGO, BaseColumns._ID,
                FormasPago.ID, FormasPago.NOMBRE));

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + Tablas.CABECERA_PEDIDO);
        db.execSQL("DROP TABLE IF EXISTS " + Tablas.DETALLE_PEDIDO);
        db.execSQL("DROP TABLE IF EXISTS " + Tablas.PRODUCTO);
        db.execSQL("DROP TABLE IF EXISTS " + Tablas.CLIENTE);
        db.execSQL("DROP TABLE IF EXISTS " + Tablas.FORMA_PAGO);

        onCreate(db);
    }
}
