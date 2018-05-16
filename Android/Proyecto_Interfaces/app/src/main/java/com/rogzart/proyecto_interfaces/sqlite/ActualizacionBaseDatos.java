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
import com.rogzart.proyecto_interfaces.Modelo.Asignacion;
import com.rogzart.proyecto_interfaces.Modelo.ComentarioAM;
import com.rogzart.proyecto_interfaces.Modelo.Conexion;
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
import com.rogzart.proyecto_interfaces.Modelo.VoluntarioFrecuente;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ActualizacionBaseDatos {
    private static BaseDatosAcampa baseDatos;
    private static ActualizacionBaseDatos instancia = new ActualizacionBaseDatos();
    private static RequestQueue request;
    private JsonObjectRequest jsonObjectRequest;
    private boolean ErrorActualizacion;
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
        ErrorActualizacion = false;
        if(ActualizacionDependencia(context))
        {Toast.makeText(context, "Error: Actualización, Verifique su conexión", Toast.LENGTH_LONG).show(); }
        else if(ActualizacionSeccion(context))
        {Toast.makeText(context, "Error: Actualización, Verifique su conexión", Toast.LENGTH_LONG).show(); }
        else if(ActualizacionUbicacion(context))
        {Toast.makeText(context, "Error: Actualización, Verifique su conexión", Toast.LENGTH_LONG).show(); }
        else if(ActualizacionTipoEvento(context))
        {Toast.makeText(context, "Error: Actualización, Verifique su conexión", Toast.LENGTH_LONG).show(); }
        else if(ActualizacionTipoProblematica(context))
        {Toast.makeText(context, "Error: Actualización, Verifique su conexión", Toast.LENGTH_LONG).show(); }
        else if(ActualizacionInventario(context))
        {Toast.makeText(context, "Error: Actualización, Verifique su conexión", Toast.LENGTH_LONG).show(); }
        else if(ActualizacionUsuario(context))
        {Toast.makeText(context, "Error: Actualización, Verifique su conexión", Toast.LENGTH_LONG).show(); }
        else if(ActualizacionEvento(context))
        {Toast.makeText(context, "Error: Actualización, Verifique su conexión", Toast.LENGTH_LONG).show(); }
        else if(ActualizacionDomicilio(context))
        {Toast.makeText(context, "Error: Actualización, Verifique su conexión", Toast.LENGTH_LONG).show(); }
        else if(ActualizacionScouter(context))
        {Toast.makeText(context, "Error: Actualización, Verifique su conexión", Toast.LENGTH_LONG).show(); }
        else if(ActualizacionFotoAlrededores(context))
        {Toast.makeText(context, "Error: Actualización, Verifique su conexión", Toast.LENGTH_LONG).show(); }
        else if(ActualizacionProblematica(context))
        {Toast.makeText(context, "Error: Actualización, Verifique su conexión", Toast.LENGTH_LONG).show(); }
        else if(ActualizacionAdultoMayor(context))
        {Toast.makeText(context, "Error: Actualización, Verifique su conexión", Toast.LENGTH_LONG).show(); }
        else if(ActualizacionAsignacion(context))
        {Toast.makeText(context, "Error: Actualización, Verifique su conexión", Toast.LENGTH_LONG).show(); }
        else if(ActualizacionComentarioAM(context))
        {Toast.makeText(context, "Error: Actualización, Verifique su conexión", Toast.LENGTH_LONG).show(); }
        else if( ActualizacionGestionInventario(context))
        {Toast.makeText(context, "Error: Actualización, Verifique su conexión", Toast.LENGTH_LONG).show(); }
        else if(ActualizacionRecoger(context))
        {Toast.makeText(context, "Error: Actualización, Verifique su conexión", Toast.LENGTH_LONG).show(); }
        else if(ActualizacionVoluntarioFrecuente(context))
        {Toast.makeText(context, "Error: Actualización, Verifique su conexión", Toast.LENGTH_LONG).show(); }
        else{
            Toast.makeText(context, "Actualización Completada", Toast.LENGTH_LONG).show();
        }
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
    public boolean ActualizacionDependencia(final Context context){
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
                ErrorActualizacion = true;
            }
        });
        request.add(jsonObjectRequest);
        return ErrorActualizacion;
    }
    public boolean ActualizacionSeccion(final Context context){
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
                ErrorActualizacion = true;
            }
        });
        request.add(jsonObjectRequest);
        return ErrorActualizacion;
    }
    public boolean ActualizacionUbicacion(final Context context){
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
                ErrorActualizacion = true;
            }
        });
        request.add(jsonObjectRequest);
        return ErrorActualizacion;
    }
    private boolean ActualizacionTipoEvento(final Context context) {
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
                ErrorActualizacion = true;
            }
        });
        request.add(jsonObjectRequest);
        return ErrorActualizacion;
    }
    private boolean ActualizacionTipoProblematica(final Context context) {
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
                ErrorActualizacion = true;
            }
        });
        request.add(jsonObjectRequest);
        return ErrorActualizacion;
    }
    private boolean ActualizacionInventario(final Context context) {
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
                ErrorActualizacion = true;
            }
        });
        request.add(jsonObjectRequest);
        return ErrorActualizacion;
    }
    private boolean ActualizacionUsuario(final Context context) {
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
                ErrorActualizacion = true;
            }
        });
        request.add(jsonObjectRequest);
        return ErrorActualizacion;
    }
    private boolean ActualizacionEvento(final Context context) {
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
                        Insert.InsertarEvento(mEvento);
                    }
                } catch (JSONException e) {
                    Toast.makeText(context, "Fallo Sincroniacion: " +e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ErrorActualizacion = true;
            }
        });
        request.add(jsonObjectRequest);
        return ErrorActualizacion;
    }
    private boolean ActualizacionDomicilio(final Context context) {
        Conexion x = new Conexion();
        final Domicilio mDomicilio = new Domicilio();
        final OperacionesBaseDatos Insert = new OperacionesBaseDatos();
        x.setRuta("WebService/Domicilio/wsDomicilioReadTable.php");
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,x.getRuta(),null,new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray json = response.optJSONArray("Domicilio");
                    for (int i = 0; i < json.length(); i++) {
                        JSONObject jsonObject = json.getJSONObject(i);
                        mDomicilio.setIdDomicilio(jsonObject.optInt("IdDomicilio"));
                        mDomicilio.setNumero(jsonObject.optInt("Numero"));
                        mDomicilio.setCalle(jsonObject.optString("Calle"));
                        mDomicilio.setColonia(jsonObject.optString("Colonia"));
                        mDomicilio.setFoto(jsonObject.optString("Foto"));
                        mDomicilio.setFkUbicacion(jsonObject.optInt("FkUbicacion"));
                        Insert.InsertarDomicilio(mDomicilio);
                    }
                } catch (JSONException e) {
                    Toast.makeText(context, "Fallo Sincroniacion: " +e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ErrorActualizacion = true;
            }
        });
        request.add(jsonObjectRequest);
        return ErrorActualizacion;
    }
    private boolean ActualizacionScouter(final Context context) {
        Conexion x = new Conexion();
        final Scouter mScouter = new Scouter();
        final OperacionesBaseDatos Insert = new OperacionesBaseDatos();
        x.setRuta("WebService/Scouter/wsScouterReadTable.php");
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,x.getRuta(),null,new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray json = response.optJSONArray("Scouter");
                    for (int i = 0; i < json.length(); i++) {
                        JSONObject jsonObject = json.getJSONObject(i);
                        mScouter.setIdScouter(jsonObject.optInt("IdScouter"));
                        mScouter.setFechaInicio(jsonObject.optString("FechaInicio"));
                        mScouter.setFechaFinal(jsonObject.optString("FechaFinal"));
                        mScouter.setFkUsuario(jsonObject.optInt("FkUsuario"));
                        Insert.InsertarScouter(mScouter);
                    }
                } catch (JSONException e) {
                    Toast.makeText(context, "Fallo Sincroniacion: " +e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ErrorActualizacion = true;
            }
        });
        request.add(jsonObjectRequest);
        return ErrorActualizacion;
    }
    private boolean ActualizacionFotoAlrededores(final Context context) {
        Conexion x = new Conexion();
        final FotoAlrededores mFotoAlrededores = new FotoAlrededores();
        final OperacionesBaseDatos Insert = new OperacionesBaseDatos();
        x.setRuta("WebService/FotoAlrededores/wsFotoAlrededoresReadTable.php");
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,x.getRuta(),null,new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray json = response.optJSONArray("FotoAlrededores");
                    for (int i = 0; i < json.length(); i++) {
                        JSONObject jsonObject = json.getJSONObject(i);
                        mFotoAlrededores.setIdFotoAlrededores(jsonObject.optInt("IdFotoAlrededores"));
                        mFotoAlrededores.setFoto(jsonObject.optString("Foto"));
                        mFotoAlrededores.setFkDomicilio(jsonObject.optInt("FkDomicilio"));
                        Insert.InsertarFotosAlrededores(mFotoAlrededores);
                    }
                } catch (JSONException e) {
                    Toast.makeText(context, "Fallo Sincroniacion: " +e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ErrorActualizacion = true;
            }
        });
        request.add(jsonObjectRequest);
        return ErrorActualizacion;
    }
    private boolean ActualizacionProblematica(final Context context) {
        Conexion x = new Conexion();
        final Problematica mProblematica = new Problematica();
        final OperacionesBaseDatos Insert = new OperacionesBaseDatos();
        x.setRuta("WebService/Problematica/wsProblematicaReadTable.php");
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,x.getRuta(),null,new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray json = response.optJSONArray("Problematica");
                    for (int i = 0; i < json.length(); i++) {
                        JSONObject jsonObject = json.getJSONObject(i);
                        mProblematica.setIdProblematica(jsonObject.optInt("IdProblematica"));
                        mProblematica.setFecha(jsonObject.optString("Fecha"));
                        mProblematica.setNombre(jsonObject.optString("Nombre"));
                        mProblematica.setSugerencia(jsonObject.optString("Sugerencia"));
                        mProblematica.setFkUsuario(jsonObject.optInt("FkUsuario"));
                        mProblematica.setFkTipoProblematica(jsonObject.optInt("FkTipoProblematica"));
                        Insert.InsertarProblematica(mProblematica);
                    }
                } catch (JSONException e) {
                    Toast.makeText(context, "Fallo Sincroniacion: " +e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ErrorActualizacion = true;
            }
        });
        request.add(jsonObjectRequest);
        return ErrorActualizacion;
    }
    private boolean ActualizacionAdultoMayor(final Context context) {
        Conexion x = new Conexion();
        final AdultoMayor mAdultoMayor = new AdultoMayor();
        final OperacionesBaseDatos Insert = new OperacionesBaseDatos();
        x.setRuta("WebService/AdultoMayor/wsAdultoMayorReadTable.php");
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,x.getRuta(),null,new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray json = response.optJSONArray("AdultoMayor");
                    for (int i = 0; i < json.length(); i++) {
                        JSONObject jsonObject = json.getJSONObject(i);
                        mAdultoMayor.setIdAdultoMayor(jsonObject.optInt("IdAdultoMayor"));
                        mAdultoMayor.setNombre(jsonObject.optString("Nombre"));
                        mAdultoMayor.setApellidoPaterno(jsonObject.optString("ApellidoPaterno"));
                        mAdultoMayor.setApellidoMaterno(jsonObject.optString("ApellidoMaterno"));
                        mAdultoMayor.setFotografia(jsonObject.optString("Fotografia"));
                        mAdultoMayor.setDiabetico(jsonObject.optInt("Diabetico"));
                        mAdultoMayor.setFkDependencia(jsonObject.optInt("FkDependencia"));
                        mAdultoMayor.setFkDomicilio(jsonObject.optInt("FkDomicilio"));
                        Insert.InsertarAdultoMayor(mAdultoMayor);
                    }
                } catch (JSONException e) {
                    Toast.makeText(context, "Fallo Sincroniacion: " +e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ErrorActualizacion = true;
            }
        });
        request.add(jsonObjectRequest);
        return ErrorActualizacion;
    }
    private boolean ActualizacionAsignacion(final Context context) {
        Conexion x = new Conexion();
        final Asignacion mAsignacion = new Asignacion();
        final OperacionesBaseDatos Insert = new OperacionesBaseDatos();
        x.setRuta("WebService/Asignacion/wsAsignacionReadTable.php");
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,x.getRuta(),null,new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray json = response.optJSONArray("Asignacion");
                    for (int i = 0; i < json.length(); i++) {
                        JSONObject jsonObject = json.getJSONObject(i);
                        mAsignacion.setIdAsignacion(jsonObject.optInt("IdAsignacion"));
                        mAsignacion.setStatus(jsonObject.optInt("Status"));
                        mAsignacion.setFecha(jsonObject.optString("Fecha"));
                        mAsignacion.setFkUsuario(jsonObject.optInt("FkUsuario"));
                        mAsignacion.setFkAdultoMayor(jsonObject.optInt("FkAdultoMayor"));
                        Insert.InsertarAsignacion(mAsignacion);
                    }
                } catch (JSONException e) {
                    Toast.makeText(context, "Fallo Sincroniacion: " +e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ErrorActualizacion = true;
            }
        });
        request.add(jsonObjectRequest);
        return ErrorActualizacion;
    }
    private boolean ActualizacionComentarioAM(final Context context) {
        Conexion x = new Conexion();
        final ComentarioAM mComentario = new ComentarioAM();
        final OperacionesBaseDatos Insert = new OperacionesBaseDatos();
        x.setRuta("WebService/ComentarioAM/wsComentarioAMReadTable.php");
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,x.getRuta(),null,new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray json = response.optJSONArray("ComentarioAM");
                    for (int i = 0; i < json.length(); i++) {
                        JSONObject jsonObject = json.getJSONObject(i);
                        mComentario.setIdComentarioAM(jsonObject.optInt("IdComentarioAM"));
                        mComentario.setNombre(jsonObject.optString("Nombre"));
                        mComentario.setFecha(jsonObject.optString("Fecha"));
                        mComentario.setFkAdultoMayor(jsonObject.optInt("FkAdultoMayor"));
                        Insert.InsertarComentarioAM(mComentario);
                    }
                } catch (JSONException e) {
                    Toast.makeText(context, "Fallo Sincroniacion: " +e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ErrorActualizacion = true;
            }
        });
        request.add(jsonObjectRequest);
        return ErrorActualizacion;
    }
    private boolean ActualizacionGestionInventario(final Context context) {
        Conexion x = new Conexion();
        final GestionInventario mGestionInventario = new GestionInventario();
        final OperacionesBaseDatos Insert = new OperacionesBaseDatos();
        x.setRuta("WebService/GestionInventario/wsGestionInventarioReadTable.php");
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,x.getRuta(),null,new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray json = response.optJSONArray("GestionInventario");
                    for (int i = 0; i < json.length(); i++) {
                        JSONObject jsonObject = json.getJSONObject(i);
                        mGestionInventario.setIdGestionInventario(jsonObject.optInt("IdGestionInventario"));
                        mGestionInventario.setFecha(jsonObject.optString("Fecha"));
                        mGestionInventario.setFkScouter(jsonObject.optInt("FkScouter"));
                        mGestionInventario.setFkInventario(jsonObject.optInt("FkInventario"));
                        Insert.InsertarGestionInventario(mGestionInventario);
                    }
                } catch (JSONException e) {
                    Toast.makeText(context, "Fallo Sincroniacion: " +e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ErrorActualizacion = true;
            }
        });
        request.add(jsonObjectRequest);
        return ErrorActualizacion;
    }
    private boolean ActualizacionRecoger(final Context context) {
        Conexion x = new Conexion();
        final Recoger mRecoger = new Recoger();
        final OperacionesBaseDatos Insert = new OperacionesBaseDatos();
        x.setRuta("WebService/Recoger/wsRecogerReadTable.php");
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,x.getRuta(),null,new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray json = response.optJSONArray("Recoger");
                    for (int i = 0; i < json.length(); i++) {
                        JSONObject jsonObject = json.getJSONObject(i);
                        mRecoger.setIdRecoger(jsonObject.optInt("IdRecoger"));
                        mRecoger.setFkScouter(jsonObject.optInt("FkScouter"));
                        mRecoger.setFkAsignacion(jsonObject.optInt("FkAsignacion"));
                        Insert.InsertarRecoger(mRecoger);
                    }
                } catch (JSONException e) {
                    Toast.makeText(context, "Fallo Sincroniacion: " +e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ErrorActualizacion = true;
            }
        });
        request.add(jsonObjectRequest);
        return ErrorActualizacion;
    }
    private boolean ActualizacionVoluntarioFrecuente(final Context context) {
        Conexion x = new Conexion();
        final VoluntarioFrecuente mVoluntarioFrecuente = new VoluntarioFrecuente();
        final OperacionesBaseDatos Insert = new OperacionesBaseDatos();
        x.setRuta("WebService/VoluntarioFrecuente/wsVoluntarioFrecuenteReadTable.php");
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,x.getRuta(),null,new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray json = response.optJSONArray("VoluntarioFrecuente");
                    for (int i = 0; i < json.length(); i++) {
                        JSONObject jsonObject = json.getJSONObject(i);
                        mVoluntarioFrecuente.setIdVoluntarioFrecuente(jsonObject.optInt("IdVoluntarioFrecuente"));
                        mVoluntarioFrecuente.setFkUsuario(jsonObject.optInt("FkUsuario"));
                        mVoluntarioFrecuente.setFkAdultoMayor(jsonObject.optInt("FkAdultoMayor"));
                        Insert.InsertarVoluntarioFrecuente(mVoluntarioFrecuente);
                    }
                } catch (JSONException e) {
                    Toast.makeText(context, "Fallo Sincroniacion: " +e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ErrorActualizacion = true;
            }
        });
        request.add(jsonObjectRequest);
        return ErrorActualizacion;
    }
}
