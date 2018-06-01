package com.rogzart.proyecto_interfaces.FragmentosBarra.AsignacionAdultosMayores.Coordinador;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.rogzart.proyecto_interfaces.Modelo.Conexion;
import com.rogzart.proyecto_interfaces.Modelo.Usuario;
import com.rogzart.proyecto_interfaces.Modelo.UsuarioAsignacion;
import com.rogzart.proyecto_interfaces.R;
import com.rogzart.proyecto_interfaces.Singleton.VolleySingleton;

import java.util.ArrayList;

public class ListaAdaptadorAsignacionAdultoMayor extends BaseAdapter {
    private ArrayList<UsuarioAsignacion> users;
    private Context contexto;
    public ListaAdaptadorAsignacionAdultoMayor(ArrayList<UsuarioAsignacion> users, Context contexto){
        this.users = users;
        this.contexto = contexto;

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
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflate = LayoutInflater.from(contexto);
        View v = inflate.inflate(R.layout.list_asignacion_adulto_mayor_coordinador,null);

        TextView Nombre = v.findViewById(R.id.nombreUsuarioAsignacion);
        final ImageView imagen = v.findViewById(R.id.imagenUsuarioAsignacion);
        Conexion conexion = new Conexion(contexto);
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

        for(int i=0; i<users.get(position).getAdultosMayores().size(); i++){
            String NombreAdultoMayor = users.get(position).getAdultosMayores().get(i).getNombre();
            String ApellidoP = users.get(position).getAdultosMayores().get(i).getApellidoPaterno();
            String ApellidoM = users.get(position).getAdultosMayores().get(i).getApellidoMaterno();
            AdultosMayores = AdultosMayores +NombreAdultoMayor+" "+ApellidoP+" "+ApellidoM+"\n";
        }

        AdultosAsignados.setText(AdultosMayores);
        AdultosAsignados.setEnabled(false);
        AdultosAsignados.setCursorVisible(false);
        AdultosAsignados.setKeyListener(null);
        AdultosAsignados.setTextColor(Color.BLACK);

        Agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



        Enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return v;
    }
}
