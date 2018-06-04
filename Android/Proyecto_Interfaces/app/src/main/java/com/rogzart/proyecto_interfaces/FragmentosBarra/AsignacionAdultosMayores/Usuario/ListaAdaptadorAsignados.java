package com.rogzart.proyecto_interfaces.FragmentosBarra.AsignacionAdultosMayores.Usuario;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.rogzart.proyecto_interfaces.Modelo.AdultoMayor;
import com.rogzart.proyecto_interfaces.Modelo.Conexion;
import com.rogzart.proyecto_interfaces.Modelo.UsuarioAsignacion;
import com.rogzart.proyecto_interfaces.R;
import com.rogzart.proyecto_interfaces.Singleton.VolleySingleton;

import java.util.ArrayList;

public class ListaAdaptadorAsignados extends BaseAdapter {

    private ArrayList<AdultoMayor> AdultosMayores;
    private Context contexto;
    private Conexion conexion;
    public ListaAdaptadorAsignados(ArrayList<AdultoMayor> AdultosMayores, Context contexto){
        this.AdultosMayores = AdultosMayores;
        this.contexto = contexto;

    }
    @Override
    public int getCount() {
        return AdultosMayores.size();
    }

    @Override
    public Object getItem(int position) {
        return AdultosMayores.get(position);
    }

    @Override
    public long getItemId(int position) {
        return AdultosMayores.get(position).getIdAdultoMayor();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflate = LayoutInflater.from(contexto);
        View v = inflate.inflate(R.layout.list_item_regular,null);
        conexion = new Conexion(contexto);
        AdultoMayor adultomayor = AdultosMayores.get(position);
        final ImageView Imagen;
        TextView Texto;

        Imagen = v.findViewById(R.id.imagen_list_regular);
        Texto = v.findViewById(R.id.texto_list_regular);

        String Nombre = ""+adultomayor.getNombre()+" "+adultomayor.getApellidoPaterno()+" "+adultomayor.getApellidoMaterno();
        Texto.setText(Nombre);

        conexion.setRuta("WebService/"+adultomayor.getFotografia());
        ImageRequest imageRequest = new ImageRequest(conexion.getRuta(), new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                Imagen.setImageBitmap(response);
            }
        }, 0, 0, ImageView.ScaleType.CENTER, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //imagen local
            }
        });
        VolleySingleton.getInstance(contexto).addToRequestQueue(imageRequest);
        return v;
    }
}
