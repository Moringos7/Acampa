package com.rogzart.proyecto_interfaces.FragmentosBarra.InformacionAdultoMayor;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.rogzart.proyecto_interfaces.R;

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
