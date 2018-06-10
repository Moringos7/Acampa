package com.rogzart.proyecto_interfaces.FragmentosBarra.Administrar.AU;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.rogzart.proyecto_interfaces.FragmentosBarra.VoluntarioFrecuente.AsignarVoluntarioFrecuente;
import com.rogzart.proyecto_interfaces.Modelo.Conexion;
import com.rogzart.proyecto_interfaces.Modelo.Usuario;
import com.rogzart.proyecto_interfaces.R;
import com.rogzart.proyecto_interfaces.Singleton.VolleySingleton;

import java.util.HashMap;
import java.util.Map;


public class AdministrarUsuario extends Fragment {
    private Bundle miPacket;
    private EditText Nombre,ApellidoP,ApellidoM,Correo;
    private TextView TvSeccion;
    private CheckBox checkBox;
    private Usuario miUsuario;
    private ImageView imagen;
    private String NombreSeccion;
    private int ColorSeccion[];
    private Button BTN;
    ImageRequest imageRequest;
    public AdministrarUsuario(){

    }
    @SuppressLint("ValidFragment")
    public AdministrarUsuario(Bundle bundle) {
        miPacket = bundle;
        miUsuario = (Usuario) miPacket.getSerializable("Objeto");
        NombreSeccion = (String) miPacket.getString("Seccion");
        ColorSeccion = miPacket.getIntArray("Color");
    }


    public static AdministrarUsuario newInstance(Bundle bundle) {
        AdministrarUsuario fragment = new AdministrarUsuario(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_administrar_usuario_datos, container, false);
    }

    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        Nombre = (EditText) getView().findViewById(R.id.administar_nombre_usuario);
        ApellidoP = (EditText) getView().findViewById(R.id.administar_apellidoP_usuario);
        ApellidoM = (EditText) getView().findViewById(R.id.administar_apellidoM_usuario);
        Correo = (EditText) getView().findViewById(R.id.administar_correo_usuario);
        checkBox = (CheckBox) getView().findViewById(R.id.administar_checkscout_usuario);
        imagen = (ImageView) getView().findViewById(R.id.imagen);
        BTN = getView().findViewById(R.id.BTNReinicio);
        final Conexion conexion = new Conexion(getContext());

        Nombre.setText(miUsuario.getNombre());
        ApellidoP.setText(miUsuario.getApellidoPaterno());
        ApellidoM.setText(miUsuario.getApellidoMaterno());
        Correo.setText(miUsuario.getCorreo());

        conexion.setRuta("WebService/"+miUsuario.getFotografia());
        imageRequest = new ImageRequest(conexion.getRuta(), new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                imagen.setImageBitmap(response);
            }
        }, 0, 0, ImageView.ScaleType.CENTER, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInstance(getContext()).addToRequestQueue(imageRequest);
        conexion.setRuta("WebService/Password/wsPasswordVerificaRecuperacion.php");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, conexion.getRuta(),
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        if(response.compareTo("Validar") == 0) {
                            BTN.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), ""+error, Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("IdUsuario",Integer.toString(miUsuario.getIdUsuario()));
                return params;
            }
        };
        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);







        BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(conexion.isConnected()){
                    conexion.setRuta("WebService/Password/wsPasswordUpdateIntentos.php ");
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, conexion.getRuta(),
                            new Response.Listener<String>()
                            {
                                @Override
                                public void onResponse(String response) {
                                    if(response.compareTo("Actualizado") == 0){
                                        Toast.makeText(getContext(), "Actualizado", Toast.LENGTH_SHORT).show();
                                        BTN.setVisibility(View.GONE);
                                    }else{
                                        Toast.makeText(getContext(), "Intentelo más tarde", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            },
                            new Response.ErrorListener()
                            {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(getContext(), ""+error, Toast.LENGTH_SHORT).show();
                                }
                            }
                    ) {
                        @Override
                        protected Map<String, String> getParams()
                        {
                            Map<String, String>  params = new HashMap<String, String>();
                            params.put("FkUsuario",Integer.toString(miUsuario.getIdUsuario()));
                            return params;
                        }
                    };
                    VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
                }else {
                    Toast.makeText(getContext(), "Verifica tu conexion", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}
