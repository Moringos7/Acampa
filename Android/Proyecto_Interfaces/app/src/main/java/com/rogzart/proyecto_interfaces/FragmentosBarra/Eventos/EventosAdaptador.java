package com.rogzart.proyecto_interfaces.FragmentosBarra.Eventos;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.rogzart.proyecto_interfaces.FragmentosBarra.Inventario.IG.ListaAdaptadorInventario;
import com.rogzart.proyecto_interfaces.FragmentosBarra.Scouter.Administracion_Scouter;
import com.rogzart.proyecto_interfaces.Modelo.Conexion;
import com.rogzart.proyecto_interfaces.Modelo.Evento;
import com.rogzart.proyecto_interfaces.Modelo.TipoEvento;
import com.rogzart.proyecto_interfaces.R;
import com.rogzart.proyecto_interfaces.Singleton.VolleySingleton;
import com.rogzart.proyecto_interfaces.sqlite.OperacionesBaseDatos;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EventosAdaptador extends BaseAdapter implements Filterable {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflate = LayoutInflater.from(contexto);
        View v = inflate.inflate(R.layout.list_eventos,null);
        final Conexion conexion = new Conexion(contexto);
        TextView Fecha= (TextView) v.findViewById(R.id.list_evento_fecha);
        TextView TipoEventoT= (TextView) v.findViewById(R.id.list_evento_tipo);
        TextView Hora = (TextView) v.findViewById(R.id.list_evento_Hora);
        TextView Lugar= (TextView) v.findViewById(R.id.list_evento_lugar);
        TextView Informacion= (TextView) v.findViewById(R.id.list_evento_informacion);
        FloatingActionButton EliminarEvento = (FloatingActionButton) v.findViewById(R.id.list_evento_borrar);
        OperacionesBaseDatos operador= OperacionesBaseDatos.obtenerInstancia(contexto);


        Informacion.setText(Cosas.get(position).getInformacion());
        Fecha.setText(String.valueOf(Cosas.get(position).getFecha()));
        Hora.setText(String.valueOf(Cosas.get(position).getHora()));
        Lugar.setText(Cosas.get(position).getLugar());

        TipoEvento tipoEvento = operador.ObtenerTipoEvento(Cosas.get(position).getFkTipoEvento());
        TipoEventoT.setText(tipoEvento.getNombre());

        EliminarEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conexion.setRuta("WebService/Evento/wsEventoDelete.php");
                if(conexion.isConnected()){

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, conexion.getRuta(),
                            new Response.Listener<String>()
                            {
                                @Override
                                public void onResponse(String response) {
                                    Toast.makeText(contexto, ""+response, Toast.LENGTH_SHORT).show();
                                    if(response.compareTo("Eliminado") == 0){
                                        FragmentTransaction ft = ((Activity) contexto).getFragmentManager().beginTransaction();
                                        ft.replace(R.id.contenedor, ListaEventos.newInstance());
                                        ft.commit();
                                    }
                                }
                            },
                            new Response.ErrorListener()
                            {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(contexto, ""+error, Toast.LENGTH_SHORT).show();
                                }
                            }
                    ) {
                        @Override
                        protected Map<String, String> getParams()
                        {
                            Map<String, String>  params = new HashMap<String, String>();
                            params.put("idevento", Integer.toString(Cosas.get(position).getIdEvento()));
                            return params;
                        }
                    };
                    VolleySingleton.getInstance(contexto).addToRequestQueue(stringRequest);
                }else{
                    Toast.makeText(contexto, "Verifica tu conexion", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return v;
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
