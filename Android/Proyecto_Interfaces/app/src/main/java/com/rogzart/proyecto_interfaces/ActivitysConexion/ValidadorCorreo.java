package com.rogzart.proyecto_interfaces.ActivitysConexion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.rogzart.proyecto_interfaces.Modelo.Conexion;
import com.rogzart.proyecto_interfaces.R;
import com.rogzart.proyecto_interfaces.Singleton.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ValidadorCorreo extends AppCompatActivity {
    private JsonObjectRequest jsonObjectRequest;
    private Conexion conexion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.empty);
        String Asunto = getIntent().getExtras().getString("Asunto");
        conexion = new Conexion(getApplicationContext());

        Map<String, String> params = new HashMap();
        params.put("asunto",Asunto);
        params.put("correo",getIntent().getExtras().getString("Correo"));

        if(Asunto.compareTo("Recuperacion") == 0){
            params.put("nombre",getIntent().getExtras().getString("Nombre"));
            params.put("fecha",getIntent().getExtras().getString("Fecha"));
        }
        JSONObject obj = new JSONObject(params);

        conexion.setRuta("WebService/wsEnvioCorreo.php");

        jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, conexion.getRuta(), obj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONArray json = response.optJSONArray("Correo");
                try {
                    JSONObject jsonObject = json.getJSONObject(0);
                    boolean state = jsonObject.optBoolean("Enviado");
                    boolean errorRegister = jsonObject.optBoolean("Registrado");
                    boolean maxNumber = jsonObject.getBoolean("Maximo");
                    if(state){
                        finish();
                        Intent intent = new Intent(getApplicationContext(), Conexion_Exitosa.class);
                        startActivityForResult(intent, 0);
                    }else{
                        //finish();
                        if(errorRegister){
                            finish();
                            Intent intent = new Intent(getApplicationContext(), ErrorPeticion.class);
                            //intent.putExtra("Mensaje1", "Verifique su conexión a Internet");
                            //intent.putExtra("Mensaje2" , "Intentelo más tarde");
                            Toast.makeText(ValidadorCorreo.this, "errorRegister", Toast.LENGTH_SHORT).show();
                            startActivityForResult(intent, 0);

                        }else if(maxNumber){
                            finish();
                            Intent intent = new Intent(getApplicationContext(), ErrorPeticion.class);
                            //intent.putExtra("Mensaje1", "Verifique su conexión a Internet");
                            //intent.putExtra("Mensaje2" , "Intentelo más tarde");
                            Toast.makeText(ValidadorCorreo.this, "maxNumber", Toast.LENGTH_SHORT).show();
                            startActivityForResult(intent, 0);
                        }else{
                            finish();
                            Intent intent = new Intent(getApplicationContext(), ErrorPeticion.class);
                            //intent.putExtra("Mensaje1", "Verifique su conexión a Internet");
                            //intent.putExtra("Mensaje2" , "Intentelo más tarde");
                            Toast.makeText(ValidadorCorreo.this, "Default", Toast.LENGTH_SHORT).show();
                            startActivityForResult(intent, 0);
                        }
                    }
                } catch (JSONException e) {
                    Toast.makeText(ValidadorCorreo.this, "Error del catch: "+e, Toast.LENGTH_SHORT).show();
                    /*
                    finish();
                    Intent intent = new Intent(getApplicationContext(), ErrorPeticion.class);
                    intent.putExtra("Mensaje1","Verifique su conexión a Internet");
                    intent.putExtra("Mensaje2", "Intentelo más tarde");
                    startActivityForResult(intent, 0);*/
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                finish();
                Intent intent = new Intent(getApplicationContext(), ErrorPeticion.class);
                intent.putExtra("Mensaje1", "Verifique su conexión a Internet");
                intent.putExtra("Mensaje2" , "Intentelo más tarde");
                startActivityForResult(intent, 0);
            }
        });
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
    }
}
