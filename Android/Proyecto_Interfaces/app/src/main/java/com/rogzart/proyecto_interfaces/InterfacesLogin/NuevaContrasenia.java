package com.rogzart.proyecto_interfaces.InterfacesLogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.rogzart.proyecto_interfaces.Barra_desplegable;
import com.rogzart.proyecto_interfaces.Modelo.Conexion;
import com.rogzart.proyecto_interfaces.Modelo.Usuario;
import com.rogzart.proyecto_interfaces.R;
import com.rogzart.proyecto_interfaces.Singleton.LogUser;
import com.rogzart.proyecto_interfaces.Singleton.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class NuevaContrasenia extends AppCompatActivity {

    private Usuario user;
    private TextView cuenta;
    private EditText pass1,pass2;
    private Button btnRecuperar;
    private JsonObjectRequest jsonObjectRequest;
    private Conexion conexion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_contrasenia);
        conexion = new Conexion(getApplicationContext());
        final LogUser ControlUser = LogUser.obtenerInstancia(getApplicationContext());
        user = (Usuario) getIntent().getExtras().getSerializable("Usuario");
        cuenta = findViewById(R.id.idcuenta);
        pass1 = findViewById(R.id.ncontraseña);
        pass2 = findViewById(R.id.cncontraseña);
        cuenta.setText("Cuenta: "+user.getCorreo());
        btnRecuperar = findViewById(R.id.btnRes);
        btnRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CheckPass()){
                    conexion.setRuta("WebService/Password/wsPasswordUpdate.php");
                    Map<String, String> params = new HashMap();
                    params.put("password", pass1.getText().toString());
                    params.put("status", "1");
                    params.put("fkusuario",""+user.getIdUsuario());
                    JSONObject obj = new JSONObject(params);

                    jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, conexion.getRuta(), obj , new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            JSONArray json = response.optJSONArray("Check");
                            try{
                                JSONObject jsonObject = json.getJSONObject(0);
                                if(jsonObject.optBoolean("Actualizado")){
                                    ControlUser.userLogin(user,getIntent().getExtras().getInt("Scouter"),getIntent().getExtras().getInt("Coordinador"));
                                    Intent intent = new Intent(getApplicationContext(), Barra_desplegable.class);
                                    finish();
                                    startActivityForResult(intent, 0);
                                }else{
                                    Toast.makeText(NuevaContrasenia.this, "Fallo modificacion, Intentelo más tarde", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(getApplicationContext(), Inicio.class);
                                    finish();
                                    startActivityForResult(intent, 0);

                                }
                            }catch (JSONException e){
                                Toast.makeText(getApplicationContext(), "Fallo Servidor", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Inicio.class);
                                finish();
                                startActivityForResult(intent, 0);
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(NuevaContrasenia.this, "Fallo Conexion al Servidor", Toast.LENGTH_SHORT).show();
                        }
                    });
                    VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
                }
            }
        });
    }
    boolean CheckPass() {
        int validador = pass1.getText().toString().compareTo(pass2.getText().toString());
        if (validador == 0) {
            if(pass1.getText().toString().length() >= 8){
                return true;
            }else{
                Toast.makeText(this, "Contraseña demasiado corta", Toast.LENGTH_LONG).show();
                return false;
            }
        }else{
            Toast.makeText(this, "Contraseñas no coinciden", Toast.LENGTH_LONG).show();
            pass1.setText("");
            pass2.setText("");
            return false;
        }
    }
}
