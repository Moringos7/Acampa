package com.rogzart.proyecto_interfaces.FragmentosBarra.InformacionAdultoMayor;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.rogzart.proyecto_interfaces.Modelo.ComentarioAM;
import com.rogzart.proyecto_interfaces.R;

import java.util.ArrayList;

public class AdaptadorComentarios extends RecyclerView.Adapter<ComentariosViewHolder> {

    ArrayList<ComentarioAM> comentario;
    Context contexto;

    public AdaptadorComentarios(ArrayList<ComentarioAM> comentario,Context contexto){
        this.comentario = comentario;
        this.contexto = contexto;
    }

    @Override
    public ComentariosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_comentarios, parent, false);
        return  new ComentariosViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ComentariosViewHolder holder, int position) {


        holder.getFecha().setText(comentario.get(position).getFecha());
        holder.getComnetario().setText(comentario.get(position).getNombre());
    }

    @Override
    public int getItemCount() {
        return comentario.size();
    }
}
