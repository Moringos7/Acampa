package com.rogzart.proyecto_interfaces.FragmentosBarra.Scouter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.rogzart.proyecto_interfaces.FragmentosBarra.VoluntarioFrecuente.AdaptadorVoluntarioFrecuente;
import com.rogzart.proyecto_interfaces.Modelo.Conexion;
import com.rogzart.proyecto_interfaces.Modelo.Usuario;
import com.rogzart.proyecto_interfaces.R;
import com.rogzart.proyecto_interfaces.Singleton.VolleySingleton;

import java.util.ArrayList;

public class AdaptadorAgregarScouter extends BaseAdapter {

    private ArrayList<Usuario> users;
    private Context contexto;
    public AdaptadorAgregarScouter(ArrayList<Usuario> usuarios, Context contexto){
        this.users = usuarios;
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
    private static class ViewHolder{
        TextView Nombre,Apellido;
        ImageView Fotografia;
        Usuario usuario;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Conexion conexion = new Conexion(contexto);

        LayoutInflater inflate = LayoutInflater.from(contexto);
        View v = inflate.inflate(R.layout.list_voluntarios,null);



        final ViewHolder holder;
        holder = new ViewHolder();
        holder.usuario = users.get(position);
        holder.Fotografia = v.findViewById(R.id.FotografiaVF);
        holder.Nombre = v.findViewById(R.id.IdNombre);
        holder.Apellido = v.findViewById(R.id.IdApellido);

        if(holder.usuario.getCheck()){
            v.setBackgroundColor(Color.rgb(45, 115, 191));
        }else{
            v.setBackgroundColor(Color.WHITE);
        }


        conexion.setRuta("WebService/"+holder.usuario.getFotografia());
        ImageRequest imageRequest = new ImageRequest(conexion.getRuta(), new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                holder.Fotografia.setImageBitmap(response);
            }
        }, 0, 0, ImageView.ScaleType.CENTER, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInstance(contexto).addToRequestQueue(imageRequest);
        holder.Nombre.setText(holder.usuario.getNombre());
        holder.Apellido.setText(holder.usuario.getApellidoPaterno()+" "+holder.usuario.getApellidoMaterno());
        return v;
    }
}
