package com.rogzart.proyecto_interfaces.sqlite;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.rogzart.proyecto_interfaces.Modelo.AdultoMayor;
import com.rogzart.proyecto_interfaces.Modelo.Conexion;
import com.rogzart.proyecto_interfaces.Modelo.Dependencia;
import com.rogzart.proyecto_interfaces.Modelo.Evento;
import com.rogzart.proyecto_interfaces.Modelo.Inventario;
import com.rogzart.proyecto_interfaces.Modelo.Seccion;
import com.rogzart.proyecto_interfaces.Modelo.TipoEvento;
import com.rogzart.proyecto_interfaces.Modelo.TipoProblematica;
import com.rogzart.proyecto_interfaces.Modelo.Ubicacion;
import com.rogzart.proyecto_interfaces.Modelo.Usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ActualizacionBaseDatos {
    private static BaseDatosAcampa baseDatos;
    private static ActualizacionBaseDatos instancia = new ActualizacionBaseDatos();
    private static RequestQueue request;
    private JsonObjectRequest jsonObjectRequest;

    public ActualizacionBaseDatos(){

    }
    public ActualizacionBaseDatos(Context contexto) {
        request = Volley.newRequestQueue(contexto);
    }

    public static ActualizacionBaseDatos CreacionBaseDatos(Context contexto) {
        if (baseDatos == null) {
            baseDatos = new BaseDatosAcampa(contexto);
        }
        request = Volley.newRequestQueue(contexto);
        return instancia;
    }
    public void ActualizarBasedeDatos(Context context){
        ActualizacionDependencia(context);
        ActualizacionSeccion(context);
        ActualizacionUbicacion(context);
        ActualizacionTipoEvento(context);
        ActualizacionTipoProblematica(context);
        ActualizacionInventario(context);
        ActualizacionUsuario(context);
        ActualizacionEvento(context);

    }

    public void VolcarBasedeDatos() {
        OperacionesBaseDatos Delete = new OperacionesBaseDatos();
        Delete.EliminarDatosTabla("voluntariofrecuente");
        Delete.EliminarDatosTabla("recoger");
        Delete.EliminarDatosTabla("gestioninventario");
        Delete.EliminarDatosTabla("comentarioam");
        Delete.EliminarDatosTabla("asignacion");
        Delete.EliminarDatosTabla("adultomayor");
        Delete.EliminarDatosTabla("problematica");
        Delete.EliminarDatosTabla("fotoalrededores");
        Delete.EliminarDatosTabla("scouter");
        Delete.EliminarDatosTabla("domicilio");
        Delete.EliminarDatosTabla("evento");
        Delete.EliminarDatosTabla("usuario");
        Delete.EliminarDatosTabla("inventario");
        Delete.EliminarDatosTabla("tipoproblematica");
        Delete.EliminarDatosTabla("tipoevento");
        Delete.EliminarDatosTabla("ubicacion");
        Delete.EliminarDatosTabla("seccion");
        Delete.EliminarDatosTabla("dependencia");
    }
    public void ActualizacionDependencia(final Context context){
        Conexion x = new Conexion();
        final Dependencia mDependencia = new Dependencia();
        final OperacionesBaseDatos Insert = new OperacionesBaseDatos();
        x.setRuta("WebService/Dependencia/wsDependenciaReadTable.php");
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,x.getRuta(),null,new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray json = response.optJSONArray("Dependencia");
                    for (int i = 0; i < json.length(); i++) {
                        JSONObject jsonObject = json.getJSONObject(i);
                        mDependencia.setIdDependencia(jsonObject.optInt("IdDependencia"));
                        mDependencia.setNombre(jsonObject.optString("Nombre"));
                        //Toast.makeText(contexto, "-o" + mDependencia.getIdDependencia() + "---" + mDependencia.getNombre(), Toast.LENGTH_LONG).show();
                        Insert.InsertarDependencia(mDependencia);
                    }
                } catch (JSONException e) {
                    Toast.makeText(context, "Fallo Sincroniacion: " +e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Fallo Conexion Servidor", Toast.LENGTH_SHORT).show();
            }
        });
        request.add(jsonObjectRequest);
    }
    public void ActualizacionSeccion(final Context context){
        Conexion x = new Conexion();
        final Seccion mSeccion = new Seccion();
        final OperacionesBaseDatos Insert = new OperacionesBaseDatos();
        x.setRuta("WebService/Seccion/wsSeccionReadTable.php");
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,x.getRuta(),null,new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray json = response.optJSONArray("Seccion");
                    for (int i = 0; i < json.length(); i++) {
                        JSONObject jsonObject = json.getJSONObject(i);
                        mSeccion.setIdSeccion(jsonObject.optInt("IdSeccion"));
                        mSeccion.setNombre(jsonObject.optString("Nombre"));
                        Insert.InsertarSeccion(mSeccion);
                    }
                } catch (JSONException e) {
                    Toast.makeText(context, "Fallo Sincroniacion: " +e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Fallo Conexion Servidor", Toast.LENGTH_SHORT).show();
            }
        });
        request.add(jsonObjectRequest);
    }
    public void ActualizacionUbicacion(final Context context){
        Conexion x = new Conexion();
        final Ubicacion mUbicacion = new Ubicacion();
        final OperacionesBaseDatos Insert = new OperacionesBaseDatos();
        x.setRuta("WebService/Ubicacion/wsUbicacionReadTable.php");
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,x.getRuta(),null,new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray json = response.optJSONArray("Ubicacion");
                    for (int i = 0; i < json.length(); i++) {
                        JSONObject jsonObject = json.getJSONObject(i);
                        mUbicacion.setIdUbicacion(jsonObject.optInt("IdUbicacion"));
                        mUbicacion.setLongitud(jsonObject.optDouble("Longitud"));
                        mUbicacion.setLatitud(jsonObject.optDouble("Latitud"));
                        Insert.InsertarUbcacion(mUbicacion);
                    }
                } catch (JSONException e) {
                    Toast.makeText(context, "Fallo Sincroniacion: " +e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Fallo Conexion Servidor", Toast.LENGTH_SHORT).show();
            }
        });
        request.add(jsonObjectRequest);
    }
    private void ActualizacionTipoEvento(final Context context) {
        Conexion x = new Conexion();
        final TipoEvento mTipoEvento = new TipoEvento();
        final OperacionesBaseDatos Insert = new OperacionesBaseDatos();
        x.setRuta("WebService/TipoEvento/wsTipoEventoReadTable.php");
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,x.getRuta(),null,new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray json = response.optJSONArray("TipoEvento");
                    for (int i = 0; i < json.length(); i++) {
                        JSONObject jsonObject = json.getJSONObject(i);
                        mTipoEvento.setIdTipoEvento(jsonObject.optInt("IdTipoEvento"));
                        mTipoEvento.setNombre(jsonObject.optString("Nombre"));
                        Insert.InsertarTipoEvento(mTipoEvento);
                    }
                } catch (JSONException e) {
                    Toast.makeText(context, "Fallo Sincroniacion: " +e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Fallo Conexion Servidor", Toast.LENGTH_SHORT).show();
            }
        });
        request.add(jsonObjectRequest);
    }
    private void ActualizacionTipoProblematica(final Context context) {
        Conexion x = new Conexion();
        final TipoProblematica mTipoProblematica = new TipoProblematica();
        final OperacionesBaseDatos Insert = new OperacionesBaseDatos();
        x.setRuta("WebService/TipoProblematica/wsTipoProblematicaReadTable.php");
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,x.getRuta(),null,new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray json = response.optJSONArray("TipoProblematica");
                    for (int i = 0; i < json.length(); i++) {
                        JSONObject jsonObject = json.getJSONObject(i);
                        mTipoProblematica.setIdTipoProblematica(jsonObject.optInt("IdTipoProblematica"));
                        mTipoProblematica.setNombre(jsonObject.optString("Nombre"));
                        Insert.InsertarTipoProblematica(mTipoProblematica);
                    }
                } catch (JSONException e) {
                    Toast.makeText(context, "Fallo Sincroniacion: " +e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Fallo Conexion Servidor", Toast.LENGTH_SHORT).show();
            }
        });
        request.add(jsonObjectRequest);
    }
    private void ActualizacionInventario(final Context context) {
        Conexion x = new Conexion();
        final Inventario mInventario = new Inventario();
        final OperacionesBaseDatos Insert = new OperacionesBaseDatos();
        x.setRuta("WebService/Inventario/wsInventarioReadTable.php");
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,x.getRuta(),null,new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray json = response.optJSONArray("Inventario");
                    for (int i = 0; i < json.length(); i++) {
                        JSONObject jsonObject = json.getJSONObject(i);
                        mInventario.setIdInventario(jsonObject.optInt("IdInventario"));
                        mInventario.setProducto(jsonObject.optString("Producto"));
                        mInventario.setCantidad((float) jsonObject.optDouble("Cantidad"));
                        mInventario.setExistencia(jsonObject.optInt("Existencia"));
                        mInventario.setDescripcion(jsonObject.optString("Descripcion"));
                        mInventario.setImagen(jsonObject.optString("Imagen"));
                        mInventario.setComentario(jsonObject.optString("Comentario"));
                        mInventario.setExtra(jsonObject.optInt("Extra"));
                        Insert.InsertarInventario(mInventario);
                    }
                } catch (JSONException e) {
                    Toast.makeText(context, "Fallo Sincroniacion: " +e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Fallo Conexion Servidor", Toast.LENGTH_SHORT).show();
            }
        });
        request.add(jsonObjectRequest);
    }
    private void ActualizacionUsuario(final Context context) {
        Conexion x = new Conexion();
        final Usuario mUsuario = new Usuario();
        final OperacionesBaseDatos Insert = new OperacionesBaseDatos();
        x.setRuta("WebService/Usuario/wsUsuarioReadTable.php");
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,x.getRuta(),null,new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray json = response.optJSONArray("Usuario");
                    for (int i = 0; i < json.length(); i++) {
                        JSONObject jsonObject = json.getJSONObject(i);
                        mUsuario.setIdUsuario(jsonObject.optInt("IdUsuario"));
                        mUsuario.setNombre(jsonObject.optString("Nombre"));
                        mUsuario.setApellidoPaterno(jsonObject.optString("ApellidoPaterno"));
                        mUsuario.setApellidoMaterno(jsonObject.optString("ApellidoMaterno"));
                        mUsuario.setCorreo(jsonObject.optString("Correo"));
                        mUsuario.setFotografia(jsonObject.optString("Fotografia"));
                        mUsuario.setFechaNacimiento(jsonObject.optString("FechaNacimiento"));
                        mUsuario.setScout(jsonObject.optInt("Scout"));
                        mUsuario.setFkSeccion(jsonObject.optInt("FkSeccion"));
                        Insert.InsertarUsuario(mUsuario);
                    }
                } catch (JSONException e) {
                    Toast.makeText(context, "Fallo Sincroniacion: " +e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Fallo Conexion Servidor", Toast.LENGTH_SHORT).show();
            }
        });
        request.add(jsonObjectRequest);
    }
    private void ActualizacionEvento(final Context context) {
        Conexion x = new Conexion();
        final Evento mEvento = new Evento();
        final OperacionesBaseDatos Insert = new OperacionesBaseDatos();
        x.setRuta("WebService/Evento/wsEventoReadTable.php");
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,x.getRuta(),null,new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray json = response.optJSONArray("Evento");
                    for (int i = 0; i < json.length(); i++) {
                        JSONObject jsonObject = json.getJSONObject(i);
                        mEvento.setIdEvento(jsonObject.optInt("IdEvento"));
                        mEvento.setHora(jsonObject.optString("Hora"));
                        mEvento.setFecha(jsonObject.optString("Fecha"));
                        mEvento.setLugar(jsonObject.optString("Lugar"));
                        mEvento.setInformacion(jsonObject.optString("Informacion"));
                        mEvento.setFkTipoEvento(jsonObject.optInt("FkTipoEvento"));
                        Toast.makeText(context, ""+ mEvento.getFkTipoEvento(), Toast.LENGTH_SHORT).show();
                        Insert.InsertarEvento(mEvento);
                    }
                } catch (JSONException e) {
                    Toast.makeText(context, "Fallo Sincroniacion: " +e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Fallo Conexion Servidor", Toast.LENGTH_SHORT).show();
            }
        });
        request.add(jsonObjectRequest);
    }
    public void ActualizacionAdultoMayor(final Context contexto){

    }
}
