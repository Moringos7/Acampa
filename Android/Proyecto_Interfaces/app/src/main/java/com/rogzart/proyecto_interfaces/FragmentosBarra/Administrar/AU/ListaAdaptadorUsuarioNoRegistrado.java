package com.rogzart.proyecto_interfaces.FragmentosBarra.Administrar.AU;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.rogzart.proyecto_interfaces.ActivitysConexion.ValidadorCorreo;
import com.rogzart.proyecto_interfaces.Barra_desplegable;
import com.rogzart.proyecto_interfaces.FragmentosBarra.VoluntarioFrecuente.AsignarVoluntarioFrecuente;
import com.rogzart.proyecto_interfaces.Modelo.Conexion;
import com.rogzart.proyecto_interfaces.Modelo.Seccion;
import com.rogzart.proyecto_interfaces.Modelo.Usuario;
import com.rogzart.proyecto_interfaces.R;
import com.rogzart.proyecto_interfaces.Singleton.VolleySingleton;
import com.rogzart.proyecto_interfaces.sqlite.ActualizacionBaseDatos;
import com.rogzart.proyecto_interfaces.sqlite.OperacionesBaseDatos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static android.support.v4.app.ActivityCompat.startActivityForResult;


public class ListaAdaptadorUsuarioNoRegistrado extends BaseAdapter {
    private ArrayList<Usuario> datos;
    private Context contexto;
    private OperacionesBaseDatos operador;
    private ListaAdaptadorUsuario.CustomFilter filter;
    private ArrayList<Usuario> filterList;
    private ActualizacionBaseDatos Act;
    public ListaAdaptadorUsuarioNoRegistrado
            (ArrayList<Usuario> datos, Context contexto)
    {
        this.datos = datos;
        this.contexto = contexto;
        this.filterList = datos;
        Act = new ActualizacionBaseDatos(contexto);
    }


    @Override
    public int getCount() {

        return datos.size();
    }

    @Override
    public Object getItem(int position) {

        return datos.get(position);
    }

    @Override
    public long getItemId(int position) {

        return datos.get(position).getIdUsuario();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final Conexion conexion = new Conexion(contexto);
        LayoutInflater inflate = LayoutInflater.from(contexto);
        final View V = inflate.inflate(R.layout.list_usuario_no_registrado_administrar,null);

        Button btnAceptar = (Button) V.findViewById(R.id.btnAceptarUsuario);
        Button btnEliminar = (Button) V.findViewById(R.id.btnEliminarUsuario);

        TextView nombre = (TextView) V.findViewById(R.id.list_usuario_nombre);
        TextView apellidos = (TextView) V.findViewById(R.id.list_usuario_apellidos);
        final ImageView imagen = (ImageView) V.findViewById(R.id.list_usuario_imagen);
        TextView seccionLista = (TextView) V.findViewById(R.id.list_usuario_seccion);
        operador = OperacionesBaseDatos.obtenerInstancia(contexto);

        nombre.setText(datos.get(position).getNombre());
        apellidos.setText(datos.get(position).getApellidoPaterno()+" "+datos.get(position).getApellidoMaterno());

        Seccion seccionUsuario = operador.ObtenerSeccion(datos.get(position).getFkSeccion());
        seccionLista.setText(seccionUsuario.getNombre());
        switch (seccionUsuario.getIdSeccion()){
            case 1:
                seccionLista.setBackgroundColor(Color.argb(255, 255, 255, 0));
                break;
            case 2:
                seccionLista.setBackgroundColor(Color.argb(255, 41, 138, 8));
                break;
            case 3:
                seccionLista.setBackgroundColor(Color.argb(255, 4, 95, 180));
                seccionLista.setTextSize(15);
                break;
            case 4:
                seccionLista.setBackgroundColor(Color.argb(255, 255, 0, 0));
                break;
            case 5:
                seccionLista.setBackgroundColor(Color.argb(255, 132, 132, 132));
                break;
        }

        conexion.setRuta("WebService/"+datos.get(position).getFotografia());
        ImageRequest imageRequest = new ImageRequest(conexion.getRuta(), new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                imagen.setImageBitmap(response);
            }
        }, 0, 0, ImageView.ScaleType.CENTER, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //imagen local
            }
        });
        VolleySingleton.getInstance(contexto).addToRequestQueue(imageRequest);
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(contexto, ValidadorCorreo.class);
                intent.putExtra("Asunto","Aceptado");
                intent.putExtra("Correo",datos.get(position).getCorreo());
                v.setVisibility(View.GONE);
                contexto.startActivity(intent);
            }
        });
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(conexion.isConnected()){

                    conexion.setRuta("WebService/Usuario/wsUsuarioDelete.php");
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, conexion.getRuta(),
                            new Response.Listener<String>()
                            {
                                @Override
                                public void onResponse(String response) {
                                    if(response.compareTo("Eliminado") == 0){
                                        Toast.makeText(contexto, response, Toast.LENGTH_SHORT).show();
                                        actualizar();
                                        FragmentTransaction ft = ((Activity) contexto).getFragmentManager().beginTransaction();
                                        ft.replace(R.id.contenedor, ListaAdministrarUsuario.newInstance());
                                        ft.addToBackStack(null);
                                        ft.commit();
                                    }
                                }
                            },
                            new Response.ErrorListener()
                            {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(contexto, ""+error, Toast.LENGTH_SHORT).show();
                                }
                            }
                    ) {
                        @Override
                        protected Map<String, String> getParams()
                        {
                            Map<String, String>  params = new HashMap<String, String>();
                            params.put("IdUsuario",Integer.toString(datos.get(position).getIdUsuario()));
                            return params;
                        }
                    };
                    VolleySingleton.getInstance(contexto).addToRequestQueue(stringRequest);

                }else{
                    Toast.makeText(contexto, "Verifique su Conexion", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return V;
    }
    void actualizar(){
        operador.EliminarDatosTabla("voluntariofrecuente");
        operador.EliminarDatosTabla("recoger");
        operador.EliminarDatosTabla("gestioninventario");
        operador.EliminarDatosTabla("asignacion");
        operador.EliminarDatosTabla("problematica");
        operador.EliminarDatosTabla("scouter");
        operador.EliminarDatosTabla("usuario");
        Act.ActualizacionUsuario(contexto);
        Act.ActualizacionScouter(contexto);
        Act.ActualizacionProblematica(contexto);
        Act.ActualizacionAsignacion(contexto);
        Act.ActualizacionGestionInventario(contexto);
        Act.ActualizacionRecoger(contexto);
        Act.ActualizacionVoluntarioFrecuente(contexto);
    }

}
