package com.rogzart.proyecto_interfaces.FragmentosBarra.InformacionAdultoMayor;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.rogzart.proyecto_interfaces.Modelo.Conexion;
import com.rogzart.proyecto_interfaces.Modelo.FotoAlrededores;
import com.rogzart.proyecto_interfaces.R;
import com.rogzart.proyecto_interfaces.Singleton.VolleySingleton;

import java.util.ArrayList;
import java.util.List;

public class LugaresCercanosAdapter extends RecyclerView.Adapter<LugaresCercanosAdapter.LugaresCercanosViewHolder> {

    private ArrayList<FotoAlrededores> datos;
    private Conexion conexion;
    private Context contexto;
    public LugaresCercanosAdapter(ArrayList<FotoAlrededores> data,Context contexto){
        this.datos = data;
        this.contexto = contexto;
    }
    @Override
    public LugaresCercanosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_adulto_mayor, parent, false);

        return new LugaresCercanosViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final LugaresCercanosViewHolder holder, int position) {

        FotoAlrededores foto = datos.get(position);
        conexion = new Conexion();
        conexion.setRuta("WebService/"+foto.getFoto());

        ImageRequest imageRequest = new ImageRequest(conexion.getRuta(), new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                holder.getLugar().setImageBitmap(response);
            }
        }, 0, 0, ImageView.ScaleType.CENTER, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //imagen local
            }
        });
        VolleySingleton.getInstance(contexto).addToRequestQueue(imageRequest);
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public class LugaresCercanosViewHolder extends RecyclerView.ViewHolder {
        private ImageView lugar;
        public LugaresCercanosViewHolder(View itemView) {
            super(itemView);
            lugar = itemView.findViewById(R.id.ImagenLugar);
        }

        public ImageView getLugar() {
            return lugar;
        }
    }
}