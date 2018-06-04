package com.rogzart.proyecto_interfaces.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.rogzart.proyecto_interfaces.Modelo.Asignacion;
import com.rogzart.proyecto_interfaces.Modelo.ComentarioAM;
import com.rogzart.proyecto_interfaces.Modelo.Dependencia;
import com.rogzart.proyecto_interfaces.Modelo.Domicilio;
import com.rogzart.proyecto_interfaces.Modelo.Evento;
import com.rogzart.proyecto_interfaces.Modelo.FotoAlrededores;
import com.rogzart.proyecto_interfaces.Modelo.GestionInventario;
import com.rogzart.proyecto_interfaces.Modelo.Inventario;
import com.rogzart.proyecto_interfaces.Modelo.Problematica;
import com.rogzart.proyecto_interfaces.Modelo.Recoger;
import com.rogzart.proyecto_interfaces.Modelo.Scouter;
import com.rogzart.proyecto_interfaces.Modelo.Seccion;
import com.rogzart.proyecto_interfaces.Modelo.TipoEvento;
import com.rogzart.proyecto_interfaces.Modelo.TipoProblematica;
import com.rogzart.proyecto_interfaces.Modelo.Ubicacion;
import com.rogzart.proyecto_interfaces.Modelo.Usuario;
import com.rogzart.proyecto_interfaces.Modelo.UsuarioAsignacion;
import com.rogzart.proyecto_interfaces.Modelo.VoluntarioFrecuente;
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

import java.util.ArrayList;
import java.util.List;

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
    public void LeerTablaDependendencia(){
        //List<Dependencia> list;
        Dependencia x = new Dependencia();
        SQLiteDatabase query = baseDatos.getReadableDatabase();
        Cursor c = query.rawQuery("SELECT * FROM dependencia",null);
        if(c.moveToFirst()) {
            do {
                x.setIdDependencia(c.getInt(1));
                x.setNombre(c.getString(2));
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
    public void LeerTablaSeccion(){
        //List<Seccion> list;
        Seccion x = new Seccion();
        SQLiteDatabase query = baseDatos.getReadableDatabase();
        Cursor c = query.rawQuery("SELECT * FROM seccion",null);
        if(c.moveToFirst()) {
            do {
                x.setIdSeccion(c.getInt(1));
                x.setNombre(c.getString(2));
                //list.add(x);
            } while (c.moveToNext());
        }
        //return list;
    }
    public Seccion ObtenerSeccion(int IdSeccion){
        Seccion seccion = new Seccion();
        SQLiteDatabase query = baseDatos.getReadableDatabase();
        Cursor c = query.rawQuery("SELECT * FROM seccion WHERE IdSeccion = "+IdSeccion,null);
        if(c.moveToFirst()) {
            seccion.setIdSeccion(c.getInt(1));
            seccion.setNombre(c.getString(2));
        }else{
            seccion.setIdSeccion(6);
            seccion.setNombre("Civil");
        }
        return seccion;
    }
    /**Ubicacion**/
    public void InsertarUbcacion(Ubicacion x){
        SQLiteDatabase query = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(ubicacion.Id,x.getIdUbicacion());
        valores.put(ubicacion.Longitud,x.getLongitud());
        valores.put(ubicacion.Latitud,x.getLatitud());
        query.insert("ubicacion",null,valores);
    }
    public void LeerTablaUbicacion(){
        //List<Seccion> list;
        Ubicacion x = new Ubicacion();
        SQLiteDatabase query = baseDatos.getReadableDatabase();
        Cursor c = query.rawQuery("SELECT * FROM ubicacion",null);
        if(c.moveToFirst()) {
            do {
                x.setIdUbicacion(c.getInt(1));
                x.setLongitud(c.getDouble(2));
                x.setLatitud(c.getDouble(3));
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
    public void LeerTablaTipoEvento(){
        //List<Seccion> list;
        TipoEvento x = new TipoEvento();
        SQLiteDatabase query = baseDatos.getReadableDatabase();
        Cursor c = query.rawQuery("SELECT * FROM tipoevento",null);
        if(c.moveToFirst()) {
            do {
                x.setIdTipoEvento(c.getInt(1));
                x.setNombre(c.getString(2));
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
    public void LeerTablaTipoProblematica(){
        //List<Seccion> list;
        TipoProblematica x = new TipoProblematica();
        SQLiteDatabase query = baseDatos.getReadableDatabase();
        Cursor c = query.rawQuery("SELECT * FROM tipoproblematica",null);
        if(c.moveToFirst()) {
            do {
                x.setIdTipoProblematica(c.getInt(1));
                x.setNombre(c.getString(2));
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
    public ArrayList<Inventario> LeerTablaInventarioSinExtras(){
        ArrayList<Inventario> list = new ArrayList<Inventario>();
        Inventario x;
        SQLiteDatabase query = baseDatos.getReadableDatabase();
        Cursor c = query.rawQuery("SELECT * FROM inventario where Extra=0",null);
        if(c.moveToFirst()) {
            do {
                x = new Inventario();
                x.setIdInventario(c.getInt(1));
                x.setProducto(c.getString(2));
                x.setCantidad(c.getFloat(3));
                x.setExistencia(c.getInt(4));
                x.setDescripcion(c.getString(5));
                x.setImagen(c.getString(6));
                x.setComentario(c.getString(7));
                x.setExtra(c.getInt(8));
                list.add(x);
            } while (c.moveToNext());
        }
        return list;
    }
    public ArrayList<Inventario> LeerTablaInventarioExtras(){
        ArrayList<Inventario> list = new ArrayList<Inventario>();
        Inventario x;
        SQLiteDatabase query = baseDatos.getReadableDatabase();
        Cursor c = query.rawQuery("SELECT * FROM inventario where Extra= 1",null);
        if(c.moveToFirst()) {
            do {
                x = new Inventario();
                x.setIdInventario(c.getInt(1));
                x.setProducto(c.getString(2));
                x.setCantidad(c.getFloat(3));
                x.setExistencia(c.getInt(4));
                x.setDescripcion(c.getString(5));
                x.setImagen(c.getString(6));
                x.setComentario(c.getString(7));
                x.setExtra(c.getInt(8));
                list.add(x);
            } while (c.moveToNext());
        }
        return list;
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
    public ArrayList<Usuario> LeerTablaUsuario(){
        ArrayList<Usuario> list = new ArrayList<Usuario>();
        SQLiteDatabase query = baseDatos.getReadableDatabase();
        Usuario x;
        Cursor c = query.rawQuery("SELECT * FROM usuario",null);
        if(c.moveToFirst()) {
            do {
                x = new Usuario();
                x.setIdUsuario(c.getInt(1));
                x.setNombre(c.getString(2));
                x.setApellidoPaterno(c.getString(3));
                x.setApellidoMaterno(c.getString(4));
                x.setCorreo(c.getString(5));
                x.setFotografia(c.getString(6));
                x.setFechaNacimiento(c.getString(7));
                x.setScout(c.getInt(8));
                x.setFkSeccion(c.getInt(9));
                list.add(x);
            } while (c.moveToNext());
        }
        return list;
    }
    public Usuario ObtenerUsuario(int Id){
        Usuario usuario = new Usuario();
        SQLiteDatabase query = baseDatos.getReadableDatabase();
        Cursor c = query.rawQuery("SELECT * FROM usuario WHERE IdUsuario = ?",new String[]{String.valueOf(Id)});
        if(c.moveToFirst()){
            usuario.setIdUsuario(c.getInt(1));
            usuario.setNombre(c.getString(2));
            usuario.setApellidoPaterno(c.getString(3));
            usuario.setApellidoMaterno(c.getString(4));
            usuario.setCorreo(c.getString(5));
            usuario.setFotografia(c.getString(6));
            usuario.setFechaNacimiento(c.getString(7));
            usuario.setScout(c.getInt(8));
            usuario.setFkSeccion(c.getInt(9));
        }
        return usuario;
    }
    public ArrayList<UsuarioAsignacion> LeerUsuariosAsignacion(String Fecha){
        ArrayList<UsuarioAsignacion> list = new ArrayList<UsuarioAsignacion>();
        SQLiteDatabase query = baseDatos.getReadableDatabase();

        UsuarioAsignacion usuarioAsignacion;
        Cursor c = query.rawQuery("SELECT FkUsuario FROM asignacion WHERE Fecha = ? AND FkAdultoMayor IS null AND Status = 0",new String[]{Fecha});
        if(c.moveToFirst()) {
            do {
                usuarioAsignacion = new UsuarioAsignacion(ObtenerUsuario(c.getInt(0)));
                //usuarioAsignacion.setAdultosMayores(obtenerAdultosMayoresPorVoluntarioFrecuente(c.getInt(0)));
                list.add(usuarioAsignacion);
            } while (c.moveToNext());
        }
        return list;
    }

    public ArrayList<AdultoMayor> obtenerAdultosMayoresAsignados(String Fecha){
        ArrayList<AdultoMayor> list = new ArrayList<AdultoMayor>();
        SQLiteDatabase query = baseDatos.getReadableDatabase();
        Cursor c = query.rawQuery("SELECT IdAdultoMayor FROM adultomayor,asignacion WHERE IdAdultoMayor = FkAdultoMayor AND Fecha = ?",new String[]{Fecha});
        if(c.moveToFirst()) {
            do {
                list.add(obtenerAdultoMayor(c.getInt(0)));
            } while (c.moveToNext());
        }
        return list;
    }

    /**Evento*/
    public void InsertarEvento(Evento x){
        SQLiteDatabase query = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(evento.Id,x.getIdEvento());
        valores.put(evento.Fecha,x.getFecha());
        valores.put(evento.Hora,x.getHora());
        valores.put(evento.Lugar,x.getLugar());
        valores.put(evento.Informacion,x.getInformacion());
        valores.put(evento.FkTipoEveto,x.getFkTipoEvento());
        query.insert("evento",null,valores);
    }
    public void LeerTablaEvento(){
        //List<Seccion> list;
        Evento x = new Evento();
        SQLiteDatabase query = baseDatos.getReadableDatabase();
        Cursor c = query.rawQuery("SELECT * FROM evento",null);
        if(c.moveToFirst()) {
            do {
                x.setIdEvento(c.getInt(1));
                x.setFecha(c.getString(2));
                x.setHora(c.getString(3));
                x.setLugar(c.getString(4));
                x.setInformacion(c.getString(5));
                x.setFkTipoEvento(c.getInt(6));
                //list.add(x);
            } while (c.moveToNext());
        }
        //return list;
    }
    public boolean verificarEvento(String Fecha){
        boolean Existe = false;
        SQLiteDatabase query = baseDatos.getReadableDatabase();
        Cursor c = query.rawQuery("SELECT * FROM evento,tipoevento WHERE Fecha =  ? AND  FkTipoEvento = IdTipoEvento AND tipoevento.Nombre = ? ",new String[]{Fecha,"Servicio",});
        if(c.moveToFirst()) {
            Existe = true;
        }
        return Existe;
    }
    /**Domicilio**/
    public void InsertarDomicilio(Domicilio x){
        SQLiteDatabase query = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(domicilio.Id,x.getIdDomicilio());
        valores.put(domicilio.Numero,x.getNumero());
        valores.put(domicilio.Calle,x.getCalle());
        valores.put(domicilio.Colonia,x.getColonia());
        valores.put(domicilio.Foto,x.getFoto());
        valores.put(domicilio.FkUbicacion,x.getFkUbicacion());
        query.insert("domicilio",null,valores);
    }
    public void LeerTablaDomicilio(){
        //List<Seccion> list;
        Domicilio x = new Domicilio();
        SQLiteDatabase query = baseDatos.getReadableDatabase();
        Cursor c = query.rawQuery("SELECT * FROM domicilio",null);
        if(c.moveToFirst()) {
            do {
                x.setIdDomicilio(c.getInt(1));
                x.setNumero(c.getInt(2));
                x.setCalle(c.getString(3));
                x.setColonia(c.getString(4));
                x.setFoto(c.getString(5));
                x.setFkUbicacion(c.getInt(6));
                //list.add(x);
            } while (c.moveToNext());
        }
        //return list;
    }
    /**Scouter**/
    public void InsertarScouter(Scouter x){
        SQLiteDatabase query = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(scouter.Id,x.getIdScouter());
        valores.put(scouter.FechaInicio,x.getFechaInicio());
        valores.put(scouter.FechaFinal,x.getFechaFinal());
        valores.put(scouter.FkUsuario,x.getFkUsuario());
        query.insert("scouter",null,valores);
    }
    public void LeerTablaScouter(){
        //List<Scouter> list = new ArrayList<Scouter>();
        Scouter x = new Scouter();
        SQLiteDatabase query = baseDatos.getReadableDatabase();
        Cursor c = query.rawQuery("SELECT * FROM scouter",null);
        if(c.moveToFirst()) {
            do {
                x.setIdScouter(c.getInt(1));
                x.setFechaInicio(c.getString(2));
                x.setFechaFinal(c.getString(3));
                x.setFkUsuario(c.getInt(4));
                //list.add(x);
            } while (c.moveToNext());
        }
        //return list;
    }
    /**FotoAlrededores**/
    public void InsertarFotosAlrededores(FotoAlrededores x){
        SQLiteDatabase query = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(fotoalrededores.Id,x.getIdFotoAlrededores());
        valores.put(fotoalrededores.Foto,x.getFoto());
        valores.put(fotoalrededores.FkDomiilio,x.getFkDomicilio());
        query.insert("fotoalrededores",null,valores);
    }
    public void LeerTablaFotoAlrededores(){
        //List<Scouter> list = new ArrayList<Scouter>();
        FotoAlrededores x = new FotoAlrededores();
        SQLiteDatabase query = baseDatos.getReadableDatabase();
        Cursor c = query.rawQuery("SELECT * FROM fotoalrededores",null);
        if(c.moveToFirst()) {
            do {
                x.setIdFotoAlrededores(c.getInt(1));
                x.setFoto(c.getString(2));
                x.setFkDomicilio(c.getInt(3));
                //list.add(x);
            } while (c.moveToNext());
        }
        //return list;
    }
    /**Problematica**/
    public void InsertarProblematica(Problematica x){
        SQLiteDatabase query = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(problematica.Id,x.getIdProblematica());
        valores.put(problematica.Fecha,x.getFecha());
        valores.put(problematica.Nombre,x.getNombre());
        valores.put(problematica.Sugerencia,x.getSugerencia());
        valores.put(problematica.FkUsuario,x.getFkUsuario());
        valores.put(problematica.FkTipoProblematica,x.getFkTipoProblematica());
        query.insert("problematica",null,valores);
    }
    public void LeerTablaProblematica(){
        //List<Seccion> list;
        Problematica x = new Problematica();
        SQLiteDatabase query = baseDatos.getReadableDatabase();
        Cursor c = query.rawQuery("SELECT * FROM problematica",null);
        if(c.moveToFirst()) {
            do {
                x.setIdProblematica(c.getInt(1));
                x.setFecha(c.getString(2));
                x.setNombre(c.getString(3));
                x.setSugerencia(c.getString(4));
                x.setFkUsuario(c.getInt(5));
                x.setFkTipoProblematica(c.getInt(6));
                //list.add(x);
            } while (c.moveToNext());
        }
        //return list;
    }
    /**AdultoMayor**/
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
    public ArrayList<AdultoMayor> LeerTablaAdultoMayor(){
        ArrayList<AdultoMayor> list = new ArrayList<AdultoMayor>();
        AdultoMayor x;
        SQLiteDatabase query = baseDatos.getReadableDatabase();
        Cursor c = query.rawQuery("SELECT * FROM adultomayor",null);
        if(c.moveToFirst()) {
            do {
                x = new AdultoMayor();
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
    public AdultoMayor obtenerAdultoMayor(int Id){
        AdultoMayor adultoMayor = new AdultoMayor();
        SQLiteDatabase query = baseDatos.getReadableDatabase();
        Cursor c = query.rawQuery("SELECT * FROM adultomayor WHERE IdAdultoMayor = ? ",new String[]{String.valueOf(Id)});
        if(c.moveToFirst()) {
            adultoMayor = new AdultoMayor();
            adultoMayor.setIdAdultoMayor(c.getInt(1));
            adultoMayor.setNombre(c.getString(2));
            adultoMayor.setApellidoPaterno(c.getString(3));
            adultoMayor.setApellidoMaterno(c.getString(4));
            adultoMayor.setFotografia(c.getString(5));
            adultoMayor.setDiabetico(c.getInt(6));
            adultoMayor.setFkDependencia(c.getInt(7));
            adultoMayor.setFkDomicilio(c.getInt(8));
        }
        return adultoMayor;
    }
    /**Asignacion**/
    public void InsertarAsignacion(Asignacion x){
        SQLiteDatabase query = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(asignacion.Id,x.getIdAsignacion());
        valores.put(asignacion.Status,x.getStatus());
        valores.put(asignacion.Fecha,x.getFecha());
        valores.put(asignacion.FkUsuario,x.getFkUsuario());
        valores.put(asignacion.FkAdultoMayor,x.getFkAdultoMayor());
        query.insert("asignacion",null,valores);
    }
    public void LeerTablaAsignacion(Context contexto){
        //List<AdultoMayor> list = new Array List<AdultoMayor>();
        Asignacion x = new Asignacion();
        SQLiteDatabase query = baseDatos.getReadableDatabase();
        Cursor c = query.rawQuery("SELECT * FROM asignacion",null);
        if(c.moveToFirst()) {
            do {
                x.setIdAsignacion(c.getInt(1));
                x.setStatus(c.getInt(2));
                x.setFecha(c.getString(3));
                x.setFkUsuario(c.getInt(4));
                x.setFkAdultoMayor(c.getInt(5));
                Toast.makeText(contexto, ""+x.getFecha(), Toast.LENGTH_SHORT).show();
                //list.add(x);
            } while (c.moveToNext());
        }
        //return list;
    }

    public ArrayList<AdultoMayor> ObtenerAsignaciones(String Fecha, int Id) {
        ArrayList<AdultoMayor> list = new ArrayList<AdultoMayor>();
        SQLiteDatabase query = baseDatos.getReadableDatabase();
        AdultoMayor aM;
        Cursor c = query.rawQuery("SELECT adultomayor.* FROM adultomayor,asignacion WHERE IdAdultoMayor = FkAdultoMayor AND Fecha = ? AND FkUsuario = ?", new String[]{Fecha, String.valueOf(Id)});
        if (c.moveToFirst()) {
            do {
                aM = new AdultoMayor();
                aM.setIdAdultoMayor(c.getInt(1));
                aM.setNombre(c.getString(2));
                aM.setApellidoPaterno(c.getString(3));
                aM.setApellidoMaterno(c.getString(4));
                aM.setFotografia(c.getString(5));
                aM.setDiabetico(c.getInt(6));
                aM.setFkDependencia(c.getInt(7));
                aM.setFkDomicilio(c.getInt(8));
                list.add(aM);
            } while (c.moveToNext());
        }
        return list;
    }
    /**ComentarioAM**/
    public void InsertarComentarioAM(ComentarioAM x){
        SQLiteDatabase query = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(comentarioam.Id,x.getIdComentarioAM());
        valores.put(comentarioam.Nombre,x.getNombre());
        valores.put(comentarioam.Fecha,x.getFecha());
        valores.put(comentarioam.FkAdultoMayor,x.getFkAdultoMayor());
        query.insert("comentarioam",null,valores);
    }
    public void LeerTablaComentarioAM(){
        //List<AdultoMayor> list = new Array List<AdultoMayor>();
        ComentarioAM x = new ComentarioAM();
        SQLiteDatabase query = baseDatos.getReadableDatabase();
        Cursor c = query.rawQuery("SELECT * FROM comentarioam",null);
        if(c.moveToFirst()) {
            do {
                x.setIdComentarioAM(c.getInt(1));
                x.setNombre(c.getString(2));
                x.setFecha(c.getString(3));
                x.setFkAdultoMayor(c.getInt(4));
                //list.add(x);
            } while (c.moveToNext());
        }
        //return list;
    }
    /**GestionInventario**/
    public void InsertarGestionInventario(GestionInventario x){
        SQLiteDatabase query = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(gestioninventario.Id,x.getIdGestionInventario());
        valores.put(gestioninventario.Fecha,x.getFecha());
        valores.put(gestioninventario.FkScouter,x.getFkScouter());
        valores.put(gestioninventario.FkInventario,x.getFkInventario());
        query.insert("gestioninventario",null,valores);
    }
    public void LeerTablaGestionInventario(){
        //List<AdultoMayor> list = new Array List<AdultoMayor>();
        GestionInventario x = new GestionInventario();
        SQLiteDatabase query = baseDatos.getReadableDatabase();
        Cursor c = query.rawQuery("SELECT * FROM gestioninventario",null);
        if(c.moveToFirst()) {
            do {
                x.setIdGestionInventario(c.getInt(1));
                x.setFecha(c.getString(2));
                x.setFkScouter(c.getInt(3));
                x.setFkInventario(c.getInt(4));
                //list.add(x);
            } while (c.moveToNext());
        }
        //return list;
    }
    /**Recoger**/
    public void InsertarRecoger(Recoger x){
        SQLiteDatabase query = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(recoger.Id,x.getIdRecoger());
        valores.put(recoger.FkRScouter,x.getFkScouter());
        valores.put(recoger.FkAsignacion,x.getFkAsignacion());
        query.insert("recoger",null,valores);
    }
    public void LeerTablaRecoger(){
        //List<AdultoMayor> list = new Array List<AdultoMayor>();
        Recoger x = new Recoger();
        SQLiteDatabase query = baseDatos.getReadableDatabase();
        Cursor c = query.rawQuery("SELECT * FROM recoger",null);
        if(c.moveToFirst()) {
            do {
                x.setIdRecoger(c.getInt(1));
                x.setFkScouter(c.getInt(2));
                x.setFkAsignacion(c.getInt(3));
                //list.add(x);
            } while (c.moveToNext());
        }
        //return list;
    }
    /**VoluntarioFrecuente**/
    public void InsertarVoluntarioFrecuente(VoluntarioFrecuente x){
        SQLiteDatabase query = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(voluntariofrecuente.Id,x.getIdVoluntarioFrecuente());
        valores.put(voluntariofrecuente.FkUsuario,x.getFkUsuario());
        valores.put(voluntariofrecuente.FkAdultoMayor,x.getFkAdultoMayor());
        query.insert("voluntariofrecuente",null,valores);
    }
    public void LeerTablaVoluntarioFrecuente(){
        //List<AdultoMayor> list = new Array List<AdultoMayor>();
        VoluntarioFrecuente x = new VoluntarioFrecuente();
        SQLiteDatabase query = baseDatos.getReadableDatabase();
        Cursor c = query.rawQuery("SELECT * FROM voluntariofrecuente",null);
        if(c.moveToFirst()) {
            do {
                x.setIdVoluntarioFrecuente(c.getInt(1));
                x.setFkUsuario(c.getInt(2));
                x.setFkAdultoMayor(c.getInt(3));
                //Toast.makeText(context, "(1)-"+x.getIdVoluntarioFrecuente()+"(2)-"+x.getFkUsuario()+"(3)-"+x.getFkAdultoMayor(), Toast.LENGTH_SHORT).show();
                //list.add(x);
            } while (c.moveToNext());
        }
        //return list;
    }
    public ArrayList<AdultoMayor> obtenerAdultosMayoresPorVoluntarioFrecuente(int Id){
        ArrayList<AdultoMayor> list = new ArrayList<AdultoMayor>();
        AdultoMayor adultoMayor;
        SQLiteDatabase query = baseDatos.getReadableDatabase();
        Cursor c = query.rawQuery("SELECT adultomayor.*  FROM voluntariofrecuente,adultomayor WHERE FkAdultoMayor = IdAdultoMayor AND FkUsuario = ?",new String[]{String.valueOf(Id)});
        if(c.moveToFirst()) {
            do{
                adultoMayor = new AdultoMayor();
                adultoMayor.setIdAdultoMayor(c.getInt(1));
                adultoMayor.setNombre(c.getString(2));
                adultoMayor.setApellidoPaterno(c.getString(3));
                adultoMayor.setApellidoMaterno(c.getString(4));
                adultoMayor.setFotografia(c.getString(5));
                adultoMayor.setDiabetico(c.getInt(6));
                adultoMayor.setFkDependencia(c.getInt(7));
                adultoMayor.setFkDomicilio(c.getInt(8));
                list.add(adultoMayor);
            }while(c.moveToNext());
        }
        return list;
    }
    /**Estaditicas**/
    //(1)
    public int promedioVoluntariosMes(String mes, String anio){
        int numero = 0;
        SQLiteDatabase query = baseDatos.getReadableDatabase();
        Cursor c = query.rawQuery("SELECT COUNT(*) FROM asignacion WHERE substr(Fecha,1,4) = ?  AND substr(Fecha,6,2) = ? AND Status = ?",new String[]{anio,mes,String.valueOf(1)});
        if(c.moveToFirst()){
            numero = c.getInt(0);
        }
        return numero;
    }
    public int contarAdultoMayor(){
        int cuenta = 0;
        SQLiteDatabase query = baseDatos.getReadableDatabase();
        Cursor c = query.rawQuery("SELECT COUNT(*) FROM adultomayor",null);
        if(c.moveToFirst()){
            cuenta = c.getInt(0);
        }
        return cuenta;
    }
    //(2)
    public int asignacionesMes(String mes, String anio){
        int numero = 0;
        SQLiteDatabase query = baseDatos.getReadableDatabase();
        Cursor c = query.rawQuery("SELECT COUNT(*) FROM asignacion,adultomayor WHERE substr(Fecha,1,4) = ?  AND substr(Fecha,6,2) = ? AND FkAdultoMayor = IdAdultoMayor",new String[]{anio,mes});
        if(c.moveToFirst()){
            numero = c.getInt(0);
        }
        return numero;
    }
    public int numeroUsuarios(){
        int numero = 0;
        SQLiteDatabase query = baseDatos.getReadableDatabase();
        Cursor c = query.rawQuery("SELECT COUNT(*) FROM usuario",null);
        if(c.moveToFirst()){
            numero = c.getInt(0);
            numero = numero-1;
        }
        return numero;
    }
    //(3)
    public ArrayList<Usuario> usuariosAsignacion(String mes, String anio){
        ArrayList<Usuario> List = new ArrayList<Usuario>();
        Usuario x;
        SQLiteDatabase query = baseDatos.getReadableDatabase();
        Cursor c = query.rawQuery("SELECT DISTINCT usuario.* FROM usuario,asignacion,adultomayor " +
                "WHERE IdUsuario = FkUsuario " +
                "AND IdAdultoMayor = FkAdultoMayor " +
                "AND substr(Fecha,1,4) = ?" +
                "AND substr(Fecha,6,2) = ?",new String[]{anio,mes});
        if(c.moveToFirst()) {
            do {
                x = new Usuario();
                x.setIdUsuario(c.getInt(1));
                x.setNombre(c.getString(2));
                x.setApellidoPaterno(c.getString(3));
                x.setApellidoMaterno(c.getString(4));
                x.setCorreo(c.getString(5));
                x.setFotografia(c.getString(6));
                x.setFechaNacimiento(c.getString(7));
                x.setScout(c.getInt(8));
                x.setFkSeccion(c.getInt(9));
                List.add(x);
            } while (c.moveToNext());
        }
        return List;
    }
    //Semestral
    public ArrayList<Usuario> usuariosActivos(){
        ArrayList<Usuario> List = new ArrayList<Usuario>();
        Usuario x;
        SQLiteDatabase query = baseDatos.getReadableDatabase();
        Cursor c = query.rawQuery("SELECT * FROM usuario",null);
        if(c.moveToFirst()) {
            do {
                x = new Usuario();
                x.setIdUsuario(c.getInt(1));
                x.setNombre(c.getString(2));
                x.setApellidoPaterno(c.getString(3));
                x.setApellidoMaterno(c.getString(4));
                x.setCorreo(c.getString(5));
                x.setFotografia(c.getString(6));
                x.setFechaNacimiento(c.getString(7));
                x.setScout(c.getInt(8));
                x.setFkSeccion(c.getInt(9));
                List.add(x);
            } while (c.moveToNext());
        }
        return List;
    }
    public String substring(){
        String pedazo = "";
        SQLiteDatabase query = baseDatos.getReadableDatabase();
        Cursor c = query.rawQuery("SELECT substr('2018-06-04',6,2)",null);
        //2018-06-04
        //12345678910
        if(c.moveToFirst()){
            pedazo = c.getString(0);
        }
        return pedazo;
    }



}
