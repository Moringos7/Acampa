package com.rogzart.proyecto_interfaces.FragmentosBarra.Eventos;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.rogzart.proyecto_interfaces.FragmentosBarra.Inventario.IG.ListaAdaptadorInventario;
import com.rogzart.proyecto_interfaces.Modelo.Conexion;
import com.rogzart.proyecto_interfaces.Modelo.Evento;
import com.rogzart.proyecto_interfaces.Modelo.TipoEvento;
import com.rogzart.proyecto_interfaces.R;
import com.rogzart.proyecto_interfaces.sqlite.OperacionesBaseDatos;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class EventosAdaptador extends BaseAdapter implements Filterable{
    private ArrayList<Evento> Cosas;
    private ArrayList<TipoEvento>Cosas2;
    private Context contexto;
    private CustomFilter MiFiltro;
    private OperacionesBaseDatos operador;
    private ArrayList<TipoEvento> filterList;
    public EventosAdaptador(ArrayList<Evento> cosas, Context contexto) {
        this.Cosas = cosas;
        this.contexto = contexto;

    }

    public void EventosAdaptadorFiltro(ArrayList<TipoEvento> cosas, Context contexto) {
        this.Cosas2 = cosas;
        this.contexto = contexto;
        this.filterList= cosas;


    }

    @Override
    public int getCount() {
        return Cosas.size();
    }

    @Override
    public Object getItem(int position) {
        return Cosas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Cosas.get(position).getIdEvento();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Conexion conexion = new Conexion(contexto);
        LayoutInflater inflate = LayoutInflater.from(contexto);
        View v = inflate.inflate(R.layout.list_eventos,null);

        TextView Fecha= (TextView) v.findViewById(R.id.list_evento_fecha);
        TextView TipoEventoT= (TextView) v.findViewById(R.id.list_evento_tipo);
        TextView Hora = (TextView) v.findViewById(R.id.list_evento_Hora);
        TextView Lugar= (TextView) v.findViewById(R.id.list_evento_lugar);
        TextView Informacion= (TextView) v.findViewById(R.id.list_evento_informacion);
        FloatingActionButton EliminarEvento = (FloatingActionButton) v.findViewById(R.id.list_evento_borrar);
        operador= OperacionesBaseDatos.obtenerInstancia(contexto);



        Informacion.setText(Cosas.get(position).getInformacion());
        Fecha.setText(String.valueOf(Cosas.get(position).getFecha()));
        Hora.setText(String.valueOf(Cosas.get(position).getHora()));
        Lugar.setText(Cosas.get(position).getLugar());

        TipoEvento tipoEvento = operador.ObtenerTipoEvento(Cosas.get(position).getFkTipoEvento());
        TipoEventoT.setText(tipoEvento.getNombre());



        return null;
    }

    @Override
    public Filter getFilter() {
        if (MiFiltro == null) {
            MiFiltro = new CustomFilter();

        }
        return MiFiltro;
    }

    private class CustomFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if(constraint != null && constraint.length() > 0){
                constraint = constraint.toString().toUpperCase();
                ArrayList<TipoEvento> filters = new ArrayList<TipoEvento>();
                TipoEvento evento = new TipoEvento();
                for(int i=0; i<filterList.size();i++){
                    if(filterList.get(i).getNombre().toUpperCase().contains(constraint)){
                        evento = filterList.get(i);
                        filters.add(evento);
                    }
                }
                results.count = filters.size();
                results.values = filters;
            }else {
                results.count = filterList.size();
                results.values = filterList;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            Cosas2 = (ArrayList<TipoEvento>) results.values;
            notifyDataSetChanged();
        }
    }
}
