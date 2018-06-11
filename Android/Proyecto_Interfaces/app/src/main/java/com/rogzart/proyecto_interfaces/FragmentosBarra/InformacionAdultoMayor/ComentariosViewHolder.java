package com.rogzart.proyecto_interfaces.FragmentosBarra.InformacionAdultoMayor;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rogzart.proyecto_interfaces.R;

public class ComentariosViewHolder extends RecyclerView.ViewHolder{
    private TextView Fecha;
    private TextView Comnetario;
    public ComentariosViewHolder(View itemView) {
        super(itemView);

        Fecha = itemView.findViewById(R.id.FechaComentario);
        Comnetario = itemView.findViewById(R.id.Comentario);
    }

    public TextView getFecha() {
        return Fecha;
    }

    public TextView getComnetario() {
        return Comnetario;
    }

    public void setFecha(TextView fecha) {
        Fecha = fecha;
    }

    public void setComnetario(TextView comnetario) {
        Comnetario = comnetario;
    }
}
