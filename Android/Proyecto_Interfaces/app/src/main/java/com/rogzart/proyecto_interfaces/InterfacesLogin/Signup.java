package com.rogzart.proyecto_interfaces.InterfacesLogin;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.DatePickerDialog;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.rogzart.proyecto_interfaces.ActivitysConexion.Conexion_Exitosa;
import com.rogzart.proyecto_interfaces.Modelo.Conexion;
import com.rogzart.proyecto_interfaces.Modelo.Usuario;
import com.rogzart.proyecto_interfaces.R;
import com.rogzart.proyecto_interfaces.Singleton.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class signup extends AppCompatActivity implements View.OnClickListener {

    private static final int PICK_IMAGE = 100;
    private static final String CERO = "0";
    private static final String BARRA = "/";

    //Calendario para obtener fecha & hora
    public final Calendar c = Calendar.getInstance();

    //Variables para obtener la fecha
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);

    //Widgets
    EditText etFecha;
    ImageButton ibObtenerFecha;
    ImageView ImagenUsuario;
    Button Registrar;
    Uri ImageUrl;
    Bitmap bitmap;
    EditText Nombre,ApellidoPaterno,ApellidoMaterno,Correo;
    CheckBox Scout;
    String Imagen;
    //Variables
    private boolean CheckImagen = false;
    private JsonObjectRequest jsonObjectRequest;
    private Conexion conexion;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.signup);
            etFecha = findViewById(R.id.et_mostrar_fecha_picker);
            //Widget ImageButton del cual usaremos el evento clic para obtener la fecha
            ibObtenerFecha = findViewById(R.id.ib_obtener_fecha);
            //Evento setOnClickListener - clic
            ibObtenerFecha.setOnClickListener(this);
            Registrar = findViewById(R.id.regitrar);
            Registrar.setOnClickListener(this);
            ImagenUsuario= (ImageView) findViewById(R.id.RegistroImagenUsuario);
            //Vinculación
            Nombre = findViewById(R.id.RegistroNombre);
            ApellidoPaterno = findViewById(R.id.RegistroApellidoPaterno);
            ApellidoMaterno = findViewById(R.id.RegistroApellidoMaterno);
            Scout = findViewById(R.id.RegistroScout);
            Correo = findViewById(R.id.RegistroCorreo);


    }
    void Registro(){
        String Dia,Mes,Anio;
        String nombre = Nombre.getText().toString();
        String apellidoPaterno = ApellidoPaterno.getText().toString();
        String apellidoMaterno = ApellidoMaterno.getText().toString();
        String fecha = etFecha.getText().toString();
        final String correo = Correo.getText().toString();
        String[] ArregloFecha;
        boolean CheckFecha = true;

        if(!isEmailValid(correo)){
            Toast.makeText(this, "Ingrese Formato Correcto de Correo", Toast.LENGTH_SHORT).show();
        }else{
            if(nombre.compareTo("") == 0 && apellidoMaterno.compareTo("") == 0 && apellidoPaterno.compareTo("") == 0 && apellidoPaterno.compareTo("") == 0 && fecha.compareTo("") == 0){
                Toast.makeText(this, "Por favor, llena todos los campos", Toast.LENGTH_SHORT).show();
            }else{
                try {
                    SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
                    formatoFecha.setLenient(false);
                    formatoFecha.parse(fecha);
                } catch (ParseException e) {
                    CheckFecha = false;
                    Toast.makeText(this, "Fecha No valida", Toast.LENGTH_SHORT).show();
                }
                if(CheckFecha) {
                    ArregloFecha = fecha.split("/");
                    Dia = ArregloFecha[0];
                    Mes = ArregloFecha[1];
                    Anio = ArregloFecha[2];
                    fecha = "" + Anio + "-" + Mes + "-" + Dia;
                    conexion = new Conexion(getApplicationContext());
                    conexion.setRuta("WebService/SignUp.php");
                    if (conexion.isConnected()) {
                        Map<String, String> params = new HashMap();
                        params.put("nombre", nombre);
                        params.put("imagen", Imagen);
                        params.put("correo",correo);
                        params.put("apellidop",apellidoPaterno);
                        params.put("apellidom",apellidoMaterno);
                        params.put("fecha",fecha);
                        params.put("scout", String.valueOf(Scout.isChecked()));
                        JSONObject obj = new JSONObject(params);

                    jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, conexion.getRuta(), obj, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            JSONArray json = response.optJSONArray("SignUp");
                            try {
                                JSONObject jsonObject = json.getJSONObject(0);
                                json.length();
                                if(jsonObject.optBoolean("CheckParam")){
                                    if(jsonObject.optBoolean("CheckExiste")){
                                        Toast.makeText(signup.this, "Usuario ya registrado", Toast.LENGTH_LONG).show();
                                    }else{
                                        if(jsonObject.optBoolean("CheckCreacion")){
                                            //Toast.makeText(signup.this, "Registrado", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(getApplicationContext(), Conexion_Exitosa.class);
                                            startActivityForResult(intent, 0);
                                        }else{
                                            Toast.makeText(signup.this, "Usuario NO registrado intentelo otra vez", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }else{
                                    Toast.makeText(signup.this, "Fallo envio solicitud", Toast.LENGTH_LONG).show();
                                }

                            } catch (JSONException e) {
                                Toast.makeText(signup.this, "No se encontró Respuesta", Toast.LENGTH_LONG).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //Toast.makeText(signup.this, ""+error, Toast.LENGTH_LONG).show();
                            Toast.makeText(getApplicationContext(), "Fallo Conexion al Servidor", Toast.LENGTH_SHORT).show();
                        }
                    });
                    VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
                    }else{
                        Toast.makeText(this, "Verifique su conexion a Internet", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_obtener_fecha:
                obtenerFecha();
                break;
            case R.id.RegistroImagenUsuario:
                cargarimagen();
                break;
            case R.id.regitrar:
                Registro();
        }

    }
    private void EnvioCorreo(int IdUsuario){
        Toast.makeText(getApplicationContext(), ""+IdUsuario, Toast.LENGTH_LONG).show();
    }


    private void cargarimagen() {
        Intent intent= new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE);
        // intent.setType("Image/");
        //startActivityForResult(Intent.createChooser(intent,"Seleccione la aplicación"),10);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && requestCode == PICK_IMAGE){
            ImageUrl= data.getData();
            ImagenUsuario.setImageURI(ImageUrl);
            ImagenUsuario.buildDrawingCache();
            bitmap =  ImagenUsuario.getDrawingCache();
            Imagen = BitMaptoString(bitmap);
        }
    }

    private void obtenerFecha() {
        DatePickerDialog recogerFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //Esta variable lo que realiza es aumentar en uno el mes ya que comienza desde 0 = enero
                final int mesActual = month + 1;
                //Formateo el día obtenido: antepone el 0 si son menores de 10
                String diaFormateado = (dayOfMonth < 10) ? CERO + String.valueOf(dayOfMonth) : String.valueOf(dayOfMonth);
                //Formateo el mes obtenido: antepone el 0 si son menores de 10
                String mesFormateado = (mesActual < 10) ? CERO + String.valueOf(mesActual) : String.valueOf(mesActual);
                //Muestro la fecha con el formato deseado
                etFecha.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);


            }
            //Estos valores deben ir en ese orden, de lo contrario no mostrara la fecha actual
            /**
             *También puede cargar los valores que usted desee
             */
        }, anio, mes, dia);
        //Muestro el widget
        recogerFecha.show();

    }
    private String BitMaptoString( Bitmap bitmap){
        ByteArrayOutputStream array = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,array);
        byte[] imagenByte = array.toByteArray();
        String imagenString = Base64.encodeToString(imagenByte,Base64.DEFAULT);
        return imagenString;
    }

}

