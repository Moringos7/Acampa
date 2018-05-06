package com.josemi.basededatos.sqlite;
import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.josemi.basededatos.modelo.Conexion;
import com.josemi.basededatos.modelo.Producto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ActualizacionBaseDatos{
    private static BaseDatosPedido baseDatos;
    private static ActualizacionBaseDatos instancia = new ActualizacionBaseDatos();
    private static RequestQueue request;
    private JsonObjectRequest jsonObjectRequest;

    public static ActualizacionBaseDatos CreacionBaseDatos(Context contexto) {
        if (baseDatos == null) {
            baseDatos = new BaseDatosPedido(contexto);
        }
        request = Volley.newRequestQueue(contexto);
        return instancia;
    }
    public void ActualizacionProducto(final Context contexto){
        Conexion x = new Conexion(contexto);
        final Producto mProducto = new Producto();
        final OperacionesBaseDatos Insert = new OperacionesBaseDatos();
        x.setRuta("WebService/Prueba/wsReadTablePrueba.php");
        //Toast.makeText(contexto, ""+x.getRuta(), Toast.LENGTH_SHORT).show();
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,x.getRuta(),null,new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray json = response.optJSONArray("Producto");
                    for (int i = 0; i < json.length(); i++) {
                        JSONObject jsonObject = json.getJSONObject(i);
                        mProducto.idProducto = jsonObject.optInt("IdProducto");
                        mProducto.nombre = jsonObject.optString("Nombre");
                        mProducto.precio = (float) ((float)Math.round(jsonObject.optDouble("Precio") * 100d) / 100d);
                        mProducto.existencias = jsonObject.optInt("Existencia");
                        //Toast.makeText(contexto, "" + mProducto.idProducto + "" + mProducto.nombre + "" + mProducto.precio + "" + mProducto.existencias, Toast.LENGTH_SHORT).show();
                        Insert.InsertarProducto(mProducto);
                    }
                } catch (JSONException e) {
                    Toast.makeText(contexto, "" +e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(contexto, "Fallo Conexion Servidor", Toast.LENGTH_SHORT).show();
            }
        });
        request.add(jsonObjectRequest);
    }
}

