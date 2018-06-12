package com.rogzart.proyecto_interfaces.FragmentosBarra.LocalizacionLugares;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.rogzart.proyecto_interfaces.FragmentosBarra.Inventario.IG.ListaAdaptadorInventario;
import com.rogzart.proyecto_interfaces.Modelo.AdultoMayor;
import com.rogzart.proyecto_interfaces.Modelo.Conexion;
import com.rogzart.proyecto_interfaces.Modelo.Inventario;
import com.rogzart.proyecto_interfaces.Modelo.Mapa;
import com.rogzart.proyecto_interfaces.R;
import com.rogzart.proyecto_interfaces.Singleton.VolleySingleton;

import java.util.ArrayList;

public class AdaptadorLugares extends BaseAdapter implements Filterable{

    private ArrayList<Mapa> lugares;
    private Context contexto;
    private CustomFilter MiFiltro;
    private ArrayList <Mapa> filterList;

    public AdaptadorLugares(ArrayList<Mapa> lugares, Context contexto){
        this.lugares = lugares;
        this.contexto = contexto;
        this.filterList = lugares;
    }
    @Override
    public int getCount() {
        return lugares.size();
    }

    @Override
    public Object getItem(int position) {
        return lugares.get(position);
    }

    @Override
    public long getItemId(int position) {
        return lugares.get(position).getAdultoMayor().getIdAdultoMayor();
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
        holder.adultoMayor = lugares.get(position).getAdultoMayor();
        holder.lugares = lugares.get(position);
        convertView.setTag(holder);

        holder.check.setChecked(holder.lugares.getCheck());

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
                    holder.lugares.setCheck(true);
                }else{
                    holder.lugares.setCheck(false);
                }

            }
        });


        return convertView;
    }

    @Override
    public Filter getFilter() {
        if (MiFiltro == null) {
            MiFiltro = new CustomFilter();

        }
        return MiFiltro;
    }

    private static class ViewHolder{
        TextView Nombre;
        ImageView imagen;
        CheckBox check;
        AdultoMayor adultoMayor;
        Mapa lugares;
    }

    private class CustomFilter extends  Filter{
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if(constraint != null && constraint.length() > 0){
                constraint = constraint.toString().toUpperCase();
                ArrayList<Mapa> filters = new ArrayList<Mapa>();
                Mapa mapa = new Mapa();
                for(int i=0; i<filterList.size();i++){
                    if(filterList.get(i).getAdultoMayor().getNombre().toUpperCase().contains(constraint)){
                        mapa = filterList.get(i);
                        filters.add(mapa);
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
            lugares = (ArrayList<Mapa>) results.values;
            notifyDataSetChanged();
        }

    }
}
