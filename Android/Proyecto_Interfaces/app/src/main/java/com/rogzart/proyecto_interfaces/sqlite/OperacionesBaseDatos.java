package com.rogzart.proyecto_interfaces.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.rogzart.proyecto_interfaces.InterfacesLogin.Inicio;
import com.rogzart.proyecto_interfaces.Modelo.Dependencia;
import com.rogzart.proyecto_interfaces.Modelo.Evento;
import com.rogzart.proyecto_interfaces.Modelo.Inventario;
import com.rogzart.proyecto_interfaces.Modelo.Seccion;
import com.rogzart.proyecto_interfaces.Modelo.TipoEvento;
import com.rogzart.proyecto_interfaces.Modelo.TipoProblematica;
import com.rogzart.proyecto_interfaces.Modelo.Ubicacion;
import com.rogzart.proyecto_interfaces.Modelo.Usuario;
import com.rogzart.proyecto_interfaces.sqlite.EstructuraBaseDatos.adultomayor;
import com.rogzart.proyecto_interfaces.sqlite.EstructuraBaseDatos.dependencia;
import com.rogzart.proyecto_interfaces.sqlite.EstructuraBaseDatos.usuario;
import com.rogzart.proyecto_interfaces.sqlite.EstructuraBaseDatos.voluntariofrecuente;
import com.rogzart.proyecto_interfaces.sqlite.EstructuraBaseDatos.ubicacion;
import com.rogzart.proyecto_interfaces.sqlite.EstructuraBaseDatos.tipoproblematica;
import com.rogzart.proyecto_interfaces.sqlite.EstructuraBaseDatos.tipoevento;
import com.rogzart.proyecto_interfaces.sqlite.EstructuraBaseDatos.seccion;
import com.rogzart.proyecto_interfaces.sqlite.EstructuraBaseDatos.scouter;
import com.rogzart.proyecto_interfaces.sqlite.EstructuraBaseDatos.recoger;
import com.rogzart.proyecto_interfaces.sqlite.EstructuraBaseDatos.problematica;
import com.rogzart.proyecto_interfaces.sqlite.EstructuraBaseDatos.inventario;
import com.rogzart.proyecto_interfaces.sqlite.EstructuraBaseDatos.gestioninventario;
import com.rogzart.proyecto_interfaces.sqlite.EstructuraBaseDatos.fotoalrededores;
import com.rogzart.proyecto_interfaces.sqlite.EstructuraBaseDatos.evento;
import com.rogzart.proyecto_interfaces.sqlite.EstructuraBaseDatos.domicilio;
import com.rogzart.proyecto_interfaces.sqlite.EstructuraBaseDatos.comentarioam;
import com.rogzart.proyecto_interfaces.sqlite.EstructuraBaseDatos.asignacion;
import com.rogzart.proyecto_interfaces.Modelo.AdultoMayor;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

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
    public void EliminarDatosTabla(String Tabla){
        SQLiteDatabase query = baseDatos.getReadableDatabase();
        query.delete(Tabla,null,null);
    }
    /**Dependencia**/
    public void InsertarDependencia(Dependencia x){
        SQLiteDatabase query = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(dependencia.Id,x.getIdDependencia());
        valores.put(dependencia.Nombre,x.getNombre());
        query.insert("dependencia",null,valores);
    }
    public void LeerTablaDependendencia(Context contex){
        //List<Dependencia> list;
        Dependencia x = new Dependencia();
        SQLiteDatabase query = baseDatos.getReadableDatabase();
        Cursor c = query.rawQuery("SELECT * FROM dependencia",null);
            Toast.makeText(contex,"Leyendo",Toast.LENGTH_SHORT);
        if(c.moveToFirst()) {
            Toast.makeText(contex,"Entró",Toast.LENGTH_SHORT);
            do {
                x.setIdDependencia(c.getInt(1));
                x.setNombre(c.getString(2));
                Toast.makeText(contex, "Leer:" + x.getIdDependencia() + "---" + x.getNombre(), Toast.LENGTH_LONG).show();
                //list.add(x);
            } while (c.moveToNext());
        }
        //return list;
    }
    /**Seccion**/
    public void InsertarSeccion(Seccion x){
        SQLiteDatabase query = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(seccion.Id,x.getIdSeccion());
        valores.put(seccion.Nombre,x.getNombre());
        query.insert("seccion",null,valores);
    }
    public void LeerTablaSeccion(Context contex){
        //List<Seccion> list;
        Seccion x = new Seccion();
        SQLiteDatabase query = baseDatos.getReadableDatabase();
        Cursor c = query.rawQuery("SELECT * FROM seccion",null);
        if(c.moveToFirst()) {
            do {
                x.setIdSeccion(c.getInt(1));
                x.setNombre(c.getString(2));
                Toast.makeText(contex, "Leer:" + x.getIdSeccion() + "---" + x.getNombre(), Toast.LENGTH_LONG).show();
                //list.add(x);
            } while (c.moveToNext());
        }
        //return list;
    }
    /**Uicacion**/
    public void InsertarUbcacion(Ubicacion x){
        SQLiteDatabase query = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(ubicacion.Id,x.getIdUbicacion());
        valores.put(ubicacion.Longitud,x.getLongitud());
        valores.put(ubicacion.Latitud,x.getLatitud());
        query.insert("ubicacion",null,valores);
    }
    public void LeerTablaUbicacion(Context contex){
        //List<Seccion> list;
        Ubicacion x = new Ubicacion();
        SQLiteDatabase query = baseDatos.getReadableDatabase();
        Cursor c = query.rawQuery("SELECT * FROM ubicacion",null);
        if(c.moveToFirst()) {
            do {
                x.setIdUbicacion(c.getInt(1));
                x.setLongitud(c.getDouble(2));
                x.setLatitud(c.getDouble(3));
                Toast.makeText(contex, "Leer:" + x.getIdUbicacion() + "---" + x.getLongitud()+"---"+x.getLatitud(), Toast.LENGTH_LONG).show();
                //list.add(x);
            } while (c.moveToNext());
        }
        //return list;
    }
    /**TipoEvento**/
    public void InsertarTipoEvento(TipoEvento x){
        SQLiteDatabase query = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(tipoevento.Id,x.getIdTipoEvento());
        valores.put(tipoevento.Nombre,x.getNombre());
        query.insert("tipoevento",null,valores);
    }
    public void LeerTablaTipoEvento(Context contex){
        //List<Seccion> list;
        TipoEvento x = new TipoEvento();
        SQLiteDatabase query = baseDatos.getReadableDatabase();
        Cursor c = query.rawQuery("SELECT * FROM tipoevento",null);
        if(c.moveToFirst()) {
            do {
                x.setIdTipoEvento(c.getInt(1));
                x.setNombre(c.getString(2));
                Toast.makeText(contex, "Leer:" + x.getIdTipoEvento() + "---" + x.getNombre(), Toast.LENGTH_LONG).show();
                //list.add(x);
            } while (c.moveToNext());
        }
        //return list;
    }
    /**TipoProblematica**/
    public void InsertarTipoProblematica(TipoProblematica x){
        SQLiteDatabase query = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(tipoproblematica.Id,x.getIdTipoProblematica());
        valores.put(tipoproblematica.Nombre,x.getNombre());
        query.insert("tipoproblematica",null,valores);
    }
    public void LeerTablaTipoProblematica(Context contex){
        //List<Seccion> list;
        TipoProblematica x = new TipoProblematica();
        SQLiteDatabase query = baseDatos.getReadableDatabase();
        Cursor c = query.rawQuery("SELECT * FROM tipoproblematica",null);
        if(c.moveToFirst()) {
            do {
                x.setIdTipoProblematica(c.getInt(1));
                x.setNombre(c.getString(2));
                Toast.makeText(contex, "Leer:" + x.getIdTipoProblematica() + "---" + x.getNombre(), Toast.LENGTH_LONG).show();
                //list.add(x);
            } while (c.moveToNext());
        }
        //return list;
    }
    /**Inventario**/
    public void InsertarInventario(Inventario x){
        SQLiteDatabase query = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(inventario.Id,x.getIdInventario());
        valores.put(inventario.Producto,x.getProducto());
        valores.put(inventario.Cantidad,x.getCantidad());
        valores.put(inventario.Existencia,x.getExistencia());
        valores.put(inventario.Descripcion,x.getDescripcion());
        valores.put(inventario.Imagen,x.getImagen());
        valores.put(inventario.Comentario,x.getComentario());
        valores.put(inventario.Extra,x.getExtra());
        query.insert("inventario",null,valores);
    }
    public void LeerTablaInventario(Context contex){
        //List<Seccion> list;
        Inventario x = new Inventario();
        SQLiteDatabase query = baseDatos.getReadableDatabase();
        Cursor c = query.rawQuery("SELECT * FROM inventario",null);
        if(c.moveToFirst()) {
            do {
                x.setIdInventario(c.getInt(1));
                x.setProducto(c.getString(2));
                x.setCantidad(c.getFloat(3));
                x.setExistencia(c.getInt(4));
                x.setDescripcion(c.getString(5));
                x.setImagen(c.getString(6));
                x.setComentario(c.getString(7));
                x.setExtra(c.getInt(8));
                Toast.makeText(contex, "Leer:" + x.getIdInventario() + "---" + x.getProducto()+ "---" + x.getCantidad()+ "---" + x.getExistencia()+ "---" + x.getDescripcion()+ "---" + x.getImagen()+ "---" + x.getComentario()+ "---" + x.getExtra(), Toast.LENGTH_LONG).show();
                //list.add(x);
            } while (c.moveToNext());
        }
        //return list;
    }
    /**Usuario**/
    public void InsertarUsuario(Usuario x){
        SQLiteDatabase query = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(usuario.Id,x.getIdUsuario());
        valores.put(usuario.Nombre,x.getNombre());
        valores.put(usuario.ApellidoPaterno,x.getApellidoPaterno());
        valores.put(usuario.ApellidoMaterno,x.getApellidoMaterno());
        valores.put(usuario.Correo,x.getCorreo());
        valores.put(usuario.Fotografia,x.getFotografia());
        valores.put(usuario.FechaNacimiento,x.getFechaNacimiento());
        valores.put(usuario.Scout,x.getScout());
        valores.put(usuario.FkSeccion,x.getFkSeccion());
        query.insert("usuario",null,valores);
    }
    public void LeerTablaUsuario(Context contex){
        //List<Seccion> list;
        Usuario x = new Usuario();
        SQLiteDatabase query = baseDatos.getReadableDatabase();
        Cursor c = query.rawQuery("SELECT * FROM usuario",null);
        if(c.moveToFirst()) {
            do {
                x.setIdUsuario(c.getInt(1));
                x.setNombre(c.getString(2));
                x.setApellidoPaterno(c.getString(3));
                x.setApellidoMaterno(c.getString(4));
                x.setCorreo(c.getString(5));
                x.setFotografia(c.getString(6));
                x.setFechaNacimiento(c.getString(7));
                x.setScout(c.getInt(8));
                x.setFkSeccion(c.getInt(9));
                Toast.makeText(contex, "Leer:" + x.getIdUsuario() + "---" + x.getNombre()+ "---" + x.getApellidoPaterno()+ "---" + x.getApellidoMaterno()+ "---" + x.getCorreo()+ "---" + x.getFotografia()+ "---" + x.getFechaNacimiento()+ "---" + x.getScout()+ "---" + x.getFkSeccion(), Toast.LENGTH_LONG).show();
                //list.add(x);
            } while (c.moveToNext());
        }
        //return list;
    }
    /**Evento*/
    public void InsertarEvento(Evento x){
        SQLiteDatabase query = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(evento.Id,x.getIdEvento());
        valores.put(evento.Hora,x.getHora());
        valores.put(evento.Fecha,x.getFecha());
        valores.put(evento.Lugar,x.getLugar());
        valores.put(evento.Informacion,x.getInformacion());
        valores.put(evento.FkTipoEveto,x.getFkTipoEvento());
        query.insert("evento",null,valores);
    }
    public void LeerTablaEvento(Context contex){
        //List<Seccion> list;
        Evento x = new Evento();
        SQLiteDatabase query = baseDatos.getReadableDatabase();
        Cursor c = query.rawQuery("SELECT * FROM evento",null);
        if(c.moveToFirst()) {
            do {
                x.setIdEvento(c.getInt(1));
                x.setHora(c.getString(2));
                x.setFecha(c.getString(3));
                x.setLugar(c.getString(4));
                x.setInformacion(c.getString(5));
                x.setFkTipoEvento(c.getInt(6));
                Toast.makeText(contex, "Leer:" + x.getIdEvento() + "---" + x.getHora()+ "---" + x.getFecha()+ "---" + x.getLugar()+ "---" + x.getInformacion()+ "---" + x.getFkTipoEvento(), Toast.LENGTH_LONG).show();
                //list.add(x);
            } while (c.moveToNext());
        }
        //return list;
    }
    /***/
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
    public List<AdultoMayor> LeerTablaAdultoMayor(){
        List<AdultoMayor> list = null;
        AdultoMayor x = new AdultoMayor();
        SQLiteDatabase query = baseDatos.getReadableDatabase();
        Cursor c = query.rawQuery("SELECT * FROM adultomayor",null);
        if(c.moveToFirst()) {
            do {
                x.setIdAdultoMayor(c.getInt(1));
                x.setNombre(c.getString(2));
                x.setApellidoPaterno(c.getString(3));
                x.setApellidoMaterno(c.getString(4));
                x.setFotografia(c.getString(5));
                x.setDiabetico(c.getInt(6));
                x.setFkDependencia(c.getInt(7));
                x.setFkDomicilio(c.getInt(8));
                list.add(x);
            } while (c.moveToNext());
        }
        return list;
    }


}
