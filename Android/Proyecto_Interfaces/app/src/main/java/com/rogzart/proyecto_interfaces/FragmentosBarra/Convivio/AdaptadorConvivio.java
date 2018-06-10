package com.rogzart.proyecto_interfaces.FragmentosBarra.Convivio;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.rogzart.proyecto_interfaces.FragmentosBarra.LocalizacionLugares.AdaptadorLugares;
import com.rogzart.proyecto_interfaces.Modelo.AdultoMayor;
import com.rogzart.proyecto_interfaces.Modelo.Conexion;
import com.rogzart.proyecto_interfaces.Modelo.Mapa;
import com.rogzart.proyecto_interfaces.Modelo.Usuario;
import com.rogzart.proyecto_interfaces.R;
import com.rogzart.proyecto_interfaces.Singleton.VolleySingleton;

import java.util.ArrayList;

public class AdaptadorConvivio extends BaseAdapter {

    private ArrayList<AdultoMayor> Adultos;
    private Context contexto;

    public AdaptadorConvivio(ArrayList<AdultoMayor> adultos, Context contexto){
        this.Adultos = adultos;
        this.contexto = contexto;
    }
    @Override
    public int getCount() {
        return Adultos.size();
    }

    @Override
    public Object getItem(int position) {
        return Adultos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Adultos.get(position).getIdAdultoMayor();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflate = LayoutInflater.from(contexto);

        final ViewHolder holder;

        convertView = inflate.inflate(R.layout.fragment_asignacion_individual_list,null);
        holder = new ViewHolder();
        holder.Nombre = convertView.findViewById(R.id.IdNombreAdultoMayorAsignacion);
        holder.imagen = convertView.findViewById(R.id.imagenAdultoMayorAsignacionIndividual);
        holder.check = convertView.findViewById(R.id.checkAsignacion);
        holder.adultoMayor = Adultos.get(position);
        convertView.setTag(holder);

        holder.check.setChecked(holder.adultoMayor.getCheck());

        Conexion conexion = new Conexion(contexto);

        conexion.setRuta("WebService/"+holder.adultoMayor.getFotografia());
        ImageRequest imageRequest = new ImageRequest(conexion.getRuta(), new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                holder.imagen.setImageBitmap(response);
            }
        }, 0, 0, ImageView.ScaleType.CENTER, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //imagen local
            }
        });
        VolleySingleton.getInstance(contexto).addToRequestQueue(imageRequest);

        String NombreCompleto = holder.adultoMayor.getNombre()+" "+holder.adultoMayor.getApellidoPaterno()+" "+holder.adultoMayor.getApellidoMaterno();
        holder.Nombre.setText(NombreCompleto);

        holder.check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    holder.adultoMayor.setCheck(true);
                }else{
                    holder.adultoMayor.setCheck(false);
                }

            }
        });


        return convertView;
    }
    private static class ViewHolder{
        TextView Nombre;
        ImageView imagen;
        CheckBox check;
        AdultoMayor adultoMayor;
    }
}