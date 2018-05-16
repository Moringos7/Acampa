package com.rogzart.proyecto_interfaces.InterfacesLogin;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.rogzart.proyecto_interfaces.ActivitysConexion.Conexion_Exitosa;
import com.rogzart.proyecto_interfaces.ActivitysConexion.ErrorPeticion;
import com.rogzart.proyecto_interfaces.Barra_desplegable;
import com.rogzart.proyecto_interfaces.Modelo.AdultoMayor;
import com.rogzart.proyecto_interfaces.Modelo.Conexion;
import com.rogzart.proyecto_interfaces.Modelo.Dependencia;
import com.rogzart.proyecto_interfaces.Modelo.Usuario;
import com.rogzart.proyecto_interfaces.R;
import com.rogzart.proyecto_interfaces.Singleton.LogUser;
import com.rogzart.proyecto_interfaces.Singleton.VolleySingleton;
import com.rogzart.proyecto_interfaces.sqlite.ActualizacionBaseDatos;
import com.rogzart.proyecto_interfaces.sqlite.OperacionesBaseDatos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.Manifest.permission.READ_CONTACTS;
import static java.lang.Thread.sleep;

/**
 * A login screen that offers login via email/password.
 */
public class Inicio extends AppCompatActivity{
    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    /*------*/
    private Conexion conexion;
    private String Correo;
    private String Pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActualizacionBaseDatos.CreacionBaseDatos(getApplicationContext());
        /*if(LogUser.obtenerInstancia().getUser().getNombre() == null){
            Intent intent = new Intent(getApplicationContext(), Barra_desplegable.class);
            startActivityForResult(intent, 0);
        }*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        mEmailView = (AutoCompleteTextView) findViewById(R.id.IdNombre);
        mPasswordView = (EditText) findViewById(R.id.password);
        conexion = new Conexion(getApplicationContext());
        final LogUser ControlUser = LogUser.obtenerInstancia();
        //Boton LogIn
        final Button mEmailSignInButton = (Button) findViewById(R.id.btnInicio);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Correo = mEmailView.getText().toString();
                Pass = mPasswordView.getText().toString();
                if (!isEmailValid(Correo)) {
                    Toast.makeText(Inicio.this, "Ingrese Formato Correcto de Correo", Toast.LENGTH_SHORT).show();
                } else if (!true/*isPasswordValid(Pass)*/) {
                    Toast.makeText(Inicio.this, "Contraseña muy corta", Toast.LENGTH_SHORT).show();
                } else {
                    if (!conexion.isConnected()) {
                        Toast.makeText(Inicio.this, "Fallo Conexion, por favor conecte su dispositivo a Internet", Toast.LENGTH_SHORT).show();
                    } else {
                        conexion.setRuta("WebService/wsLogin.php");

                        Map<String, String> params = new HashMap();
                        params.put("correo", Correo);
                        params.put("password", Pass);
                        JSONObject obj = new JSONObject(params);

                        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, conexion.getRuta(), obj , new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                JSONArray json = response.optJSONArray("Password");
                                try {
                                    JSONObject jsonObject = json.getJSONObject(0);
                                    if(!(jsonObject.optBoolean("ValidacionCorreo"))){
                                        Toast.makeText(Inicio.this, "Correo No registrado", Toast.LENGTH_SHORT).show();
                                    }else{
                                        if(!(jsonObject.optBoolean("ValidacionPassword"))){
                                            Toast.makeText(Inicio.this, "Contraseña Incorrecta", Toast.LENGTH_SHORT).show();
                                        }else{
                                            Usuario user = new Usuario();
                                            user.setNombre(jsonObject.optString("Nombre"));
                                            user.setApellidoPaterno(jsonObject.optString("ApellidoPaterno"));
                                            user.setApellidoMaterno(jsonObject.optString("ApellidoMaterno"));
                                            user.setFkSeccion(jsonObject.optInt("FkSeccion"));
                                            ControlUser.setUser(user);
                                            ControlUser.setFkScouter(jsonObject.optInt("FkScouter"));
                                            ControlUser.setFkCoordinador(jsonObject.optInt("FkCoordinador"));
                                            Intent intent = new Intent(getApplicationContext(), Barra_desplegable.class);
                                            startActivityForResult(intent, 0);
                                        }
                                    }
                                } catch (JSONException e) {
                                    Toast.makeText(Inicio.this, "Fallo Verificación", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(Inicio.this, "Fallo Conexion al Servidor", Toast.LENGTH_SHORT).show();
                            }
                        });
                        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
                    }

                }
            }
        });

        ///Boton Registrarse
        final Button Registro =  (Button) findViewById(R.id.btnLR);
        Registro.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (view.getContext(), Signup.class);
                startActivityForResult(intent, 0);
            }
        });
        ///Boton
        final Button Recuperar =  (Button) findViewById(R.id.btnRecuperar);
        Recuperar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go = new Intent (view.getContext(), Conexion_Exitosa.class);
                startActivityForResult(go, 0);
            }
        });
    }


    private boolean isEmailValid(String email) {
        boolean message = false;
        if(email.compareTo("") == 0){
            message = false;
        }else if(email.contains("@")){
            message = true;
        }else{
            message = false;
        }
        return message;
    }

    private boolean isPasswordValid(String password) {
        boolean message = false;
        if(password.compareTo("") == 0){
            message = false;
        }else if(password.length() >= 8 ){
            message = true;
        }else{
            message = false;
        }
        return message;
    }
}

