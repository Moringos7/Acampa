package com.rogzart.proyecto_interfaces.FragmentosBarra.AsignacionAdultosMayores.Coordinador;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.JsonObject;
import com.rogzart.proyecto_interfaces.FragmentosBarra.Administrar.MenuAdministrar;
import com.rogzart.proyecto_interfaces.FragmentosBarra.Inventario.IG.ListaInventarioMain;
import com.rogzart.proyecto_interfaces.Modelo.AdultoMayor;
import com.rogzart.proyecto_interfaces.Modelo.Conexion;
import com.rogzart.proyecto_interfaces.Modelo.SeleccionAM;
import com.rogzart.proyecto_interfaces.Modelo.Usuario;
import com.rogzart.proyecto_interfaces.Modelo.UsuarioAsignacion;
import com.rogzart.proyecto_interfaces.R;
import com.rogzart.proyecto_interfaces.Singleton.VolleySingleton;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListaAdaptadorAsignacionAdultoMayor extends BaseAdapter {
    private ArrayList<UsuarioAsignacion> users;
    private Context contexto;
    private String Fecha;
    private ArrayList<SeleccionAM> Selecciones;
    public ListaAdaptadorAsignacionAdultoMayor(ArrayList<UsuarioAsignacion> users, Context contexto,String Fecha,ArrayList<SeleccionAM> Selecciones){
        this.users = users;
        this.contexto = contexto;
        this.Fecha = Fecha;
        this.Selecciones = Selecciones;
    }
    @Override
    public int getCount() {

        return users.size();
    }

    @Override
    public Object getItem(int position) {

        return users.get(position);
    }

    @Override
    public long getItemId(int position) {

        return users.get(position).getIdUsuario();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflate = LayoutInflater.from(contexto);
        View v = inflate.inflate(R.layout.list_asignacion_adulto_mayor_coordinador,null);

        TextView Nombre = v.findViewById(R.id.nombreUsuarioAsignacion);
        final ImageView imagen = v.findViewById(R.id.imagenUsuarioAsignacion);
        final Conexion conexion = new Conexion(contexto);
        final LinearLayout layout = v.findViewById(R.id.IdLayoutListaAsignacion);
        Button Agregar = v.findViewById(R.id.btnAgregarAdultoMayor);
        Button Enviar = v.findViewById(R.id.btnEnviarAsignacion);
        EditText AdultosAsignados = v.findViewById(R.id.adultosasignados);
        String AdultosMayores = "";

        Nombre.setText(users.get(position).getNombre());
        conexion.setRuta("WebService/"+users.get(position).getFotografia());
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

        if(users.get(position).getAdultosMayores() != null){
            for(int i=0; i<users.get(position).getAdultosMayores().size(); i++){
                String NombreAdultoMayor = users.get(position).getAdultosMayores().get(i).getNombre();
                String ApellidoP = users.get(position).getAdultosMayores().get(i).getApellidoPaterno();
                String ApellidoM = users.get(position).getAdultosMayores().get(i).getApellidoMaterno();
                AdultosMayores = AdultosMayores +"→"+NombreAdultoMayor+" "+ApellidoP+" "+ApellidoM+"\n";
            }
        }
        AdultosAsignados.setText(AdultosMayores);
        AdultosAsignados.setEnabled(false);
        AdultosAsignados.setCursorVisible(false);
        AdultosAsignados.setKeyListener(null);
        AdultosAsignados.setTextColor(Color.BLACK);

        Agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bolsa = new Bundle();
                bolsa.putSerializable("Usuario",users.get(position));
                bolsa.putString("Fecha",Fecha);
                bolsa.putSerializable("Selecciones",Selecciones);
                FragmentTransaction ft = ((Activity) contexto).getFragmentManager().beginTransaction();
                ft.replace(R.id.contenedor, AsignacionIndividual.newInstance(bolsa));
                ft.addToBackStack(null);
                ft.commit();

            }
        });
        Enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(conexion.isConnected()){
                    if (users.get(position).getAdultosMayores().size() == 0) {
                        Toast.makeText(contexto, "Asigna Adulto Mayor Primero", Toast.LENGTH_SHORT).show();
                    } else {
                        String id = "";
                        for (AdultoMayor Adulto : users.get(position).getAdultosMayores()) {
                            id += "-" + Adulto.getIdAdultoMayor();
                        }
                        final String Id = id;
                        conexion.setRuta("WebService/Asignacion/wsAsignarAdultosMayores.php");
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, conexion.getRuta(),
                                new Response.Listener<String>()
                                {
                                    @Override
                                    public void onResponse(String response) {
                                        Toast.makeText(contexto, "Enviando...", Toast.LENGTH_SHORT).show();
                                        if(response.compareTo("Enviado") == 0){
                                            layout.setVisibility(View.GONE);
                                        }else if(response.compareTo("Fallo Envio") == 0){
                                            Toast.makeText(contexto, "Verifique su Conexion", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                },
                                new Response.ErrorListener()
                                {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        // error
                                        Toast.makeText(contexto, ""+error, Toast.LENGTH_SHORT).show();
                                    }
                                }
                        ) {
                            @Override
                            protected Map<String, String> getParams()
                            {
                                Map<String, String>  params = new HashMap<String, String>();
                                params.put("Fecha", Fecha);
                                params.put("Id", Id);
                                params.put("IdUsuario", Integer.toString(users.get(position).getIdUsuario()));
                                return params;
                            }
                        };
                        VolleySingleton.getInstance(contexto).addToRequestQueue(stringRequest);
                    }
                }else{
                    Toast.makeText(contexto, "Verifique su conexion", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return v;
    }
}
