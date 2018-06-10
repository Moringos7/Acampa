package com.rogzart.proyecto_interfaces.InterfacesLogin;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.rogzart.proyecto_interfaces.ActivitysConexion.ValidadorCorreo;
import com.rogzart.proyecto_interfaces.FragmentosBarra.Inventario.IG.ListaInventario;
import com.rogzart.proyecto_interfaces.FragmentosBarra.VoluntarioFrecuente.AsignarVoluntarioFrecuente;
import com.rogzart.proyecto_interfaces.Modelo.Conexion;
import com.rogzart.proyecto_interfaces.R;
import com.rogzart.proyecto_interfaces.Singleton.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class RecuperacionPassword extends AppCompatActivity {

    private Button Recuperar;
    private EditText Nombre,Correo,Anio;
    private Spinner Dia,Mes;
    private String correo,nombre,vDia,vMes,anio,Fecha;
    private JsonObjectRequest jsonObjectRequest;
    private Conexion conexion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperacion_password);
        conexion = new Conexion(getApplicationContext());
        Correo =  findViewById(R.id.RecuperarCorreo);
        Nombre = findViewById(R.id.RecuperarNombre);
        Anio = findViewById(R.id.RecuperarAnio);
        Dia = findViewById(R.id.spinnerDia);
        Mes = findViewById(R.id.spinnerMes);

        Spinner Dias = (Spinner) findViewById(R.id.spinnerDia);
        Spinner Meses = (Spinner) findViewById(R.id.spinnerMes);
        String[]dia = new String[31];
        String[]mes = new String[12];
        String decena ="";
        for(int i=0; i<31;i++){
            if(i<9){
                decena = "0";
            }else {
                decena ="";
            }
            dia[i] = decena+String.valueOf(i+1);
        }
        for(int j=0; j<12; j++){
            if(j<9){
                decena = "0";
            }else {
                decena ="";
            }
            mes[j]= decena+String.valueOf(j+1);
        }

        ArrayAdapter<String> adapterD = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dia);
        Dias.setAdapter(adapterD);
        ArrayAdapter<String> adapterM = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mes);
        Meses.setAdapter(adapterM);

        Recuperar = findViewById(R.id.btnRestablecer);
        Recuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correo = Correo.getText().toString();
                nombre = Nombre.getText().toString();
                vDia = Dia.getSelectedItem().toString();
                vMes = Mes.getSelectedItem().toString();
                anio = Anio.getText().toString();
                Fecha = anio + "-" + vMes + "-" + vDia;
                if(!conexion.isConnected()){
                    Toast.makeText(RecuperacionPassword.this, "Verifica tu conexion a Internet", Toast.LENGTH_SHORT).show();
                }else{
                    if(!checkFecha()){
                        Toast.makeText(getApplicationContext(), "Fecha No valida", Toast.LENGTH_SHORT).show();
                    }else {
                        checkGeneral();
                    }
                }
            }
        });
    }
    public void checkGeneral(){
        conexion.setRuta("WebService/Password/wsPasswordCheck.php");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, conexion.getRuta(),
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        if(response.compareTo("Si") == 0){
                            Intent intent = new Intent(getApplicationContext(), ValidadorCorreo.class);
                            intent.putExtra("Asunto","Recuperacion");
                            intent.putExtra("Correo", correo);
                            intent.putExtra("Nombre",nombre);
                            intent.putExtra("Fecha",Fecha);
                            finish();
                            startActivityForResult(intent, 0);
                        }else{
                            Toast.makeText(RecuperacionPassword.this, "Datos Incorrectos", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RecuperacionPassword.this, ""+error, Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("Fecha",Fecha);
                params.put("Nombre", nombre);
                params.put("Correo", correo);
                return params;
            }
        };
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);

    }
    public boolean checkFecha(){
        boolean check = true;
        String fecha = vDia+"/"+vMes+"/"+anio;
        //Toast.makeText(getApplicationContext(), ""+fecha, Toast.LENGTH_SHORT).show();
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            formatoFecha.setLenient(false);
            formatoFecha.parse(fecha);
        } catch (ParseException e) {
            check = false;
        }
        return check;
    }
}
