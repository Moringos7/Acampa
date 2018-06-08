package com.rogzart.proyecto_interfaces.FragmentosBarra;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.rogzart.proyecto_interfaces.Modelo.Notificacion;
import com.rogzart.proyecto_interfaces.R;

import java.util.ArrayList;

public class AdaptadorNotificacion extends BaseAdapter {
    private ArrayList<Notificacion> notificaciones;
    private Context contexto;

    public AdaptadorNotificacion(ArrayList<Notificacion> notificaciones,Context contexto){
        this.notificaciones = notificaciones;
        this.contexto = contexto;
    }


    @Override
    public int getCount() {
        return notificaciones.size();
    }

    @Override
    public Object getItem(int position) {
        return notificaciones.get(position);
    }

    @Override
    public long getItemId(int position) {
        return notificaciones.get(position).getIdNotificacion();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflate = LayoutInflater.from(contexto);
        View v = inflate.inflate(R.layout.item_novedades,null);

        Notificacion notificacion = notificaciones.get(position);

        TextView Titulo = v.findViewById(R.id.IdNotificacion);
        TextView Dato1 = v.findViewById(R.id.IdDato1);
        TextView Dato2 = v.findViewById(R.id.IdDato2);
        TextView Dato3 = v.findViewById(R.id.IdDato3);
        TextView Dato4 = v.findViewById(R.id.IdDato4);
        TextView Dato5 = v.findViewById(R.id.IdDato5);


        if(notificacion.getEvento() != null){


            String []partes = notificacion.getEvento().getFecha().split("-");
            String Dia = partes[2];
            String Mes = partes[1];
            String Anio= partes[0];


            String Fecha ="Fecha: "+Dia+"/"+Mes+"/"+""+Anio;
            Dato1.setText(Fecha);
            String Hora = "Hora: "+notificacion.getEvento().getHora();
            Dato2.setText(Hora);
            String Lugar = "Lugar: "+notificacion.getEvento().getLugar();
            Dato3.setText(Lugar);
            String Informacion = ""+notificacion.getEvento().getInformacion();
            Dato4.setText(Informacion);
            Dato5.setVisibility(View.GONE);

          if(notificacion.getTipoEvento() != null ){
              Titulo.setText(notificacion.getTipoEvento().getNombre());
          }else{
              Titulo.setText("Notificacion");
          }
        }else if(notificacion.getUsuario() != null){

        }
        return v;
    }
}
