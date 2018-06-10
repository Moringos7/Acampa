package com.rogzart.proyecto_interfaces.FragmentosBarra.InformacionAdultoMayor;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.rogzart.proyecto_interfaces.FragmentosBarra.VoluntarioFrecuente.AdaptadorVoluntarioFrecuente;
import com.rogzart.proyecto_interfaces.Modelo.AdultoMayor;
import com.rogzart.proyecto_interfaces.Modelo.Conexion;
import com.rogzart.proyecto_interfaces.Modelo.Usuario;
import com.rogzart.proyecto_interfaces.R;
import com.rogzart.proyecto_interfaces.Singleton.VolleySingleton;

import java.util.ArrayList;

public class AdaptadorInformacionAdultoMayor extends BaseAdapter implements Filterable {

    private ArrayList<AdultoMayor> adultoMayores;
    private Context contexto;
    private CustomFilter filter;
    private ArrayList<AdultoMayor> filterList;

    public AdaptadorInformacionAdultoMayor(ArrayList<AdultoMayor> adultosMayores, Context contexto){
        this.adultoMayores = adultosMayores;
        this.contexto = contexto;
        this.filterList = adultosMayores;
    }
    @Override
    public int getCount() {
        return adultoMayores.size();
    }

    @Override
    public Object getItem(int position) {
        return adultoMayores.get(position);
    }

    @Override
    public long getItemId(int position) {
        return adultoMayores.get(position).getIdAdultoMayor();
    }
    private static class ViewHolder{
        TextView Nombre,Apellido;
        ImageView Fotografia;
        View v;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Conexion conexion = new Conexion(contexto);

        LayoutInflater inflate = LayoutInflater.from(contexto);
        View v = inflate.inflate(R.layout.list_voluntarios,null);

        AdultoMayor adultoMayor = adultoMayores.get(position);
        final ViewHolder holder;
        holder = new ViewHolder();

        holder.Fotografia = v.findViewById(R.id.FotografiaVF);
        holder.Nombre = v.findViewById(R.id.IdNombre);
        holder.Apellido = v.findViewById(R.id.IdApellido);

        conexion.setRuta("WebService/"+adultoMayor.getFotografia());
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
        holder.Nombre.setText(adultoMayor.getNombre());
        holder.Apellido.setText(adultoMayor.getApellidoPaterno()+" "+adultoMayor.getApellidoMaterno());
        return v;
    }

    @Override
    public Filter getFilter() {
        if(filter == null){
            filter = new CustomFilter();
        }

        return filter;
    }
    class CustomFilter extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if(constraint != null && constraint.length() > 0){
                constraint = constraint.toString().toUpperCase();
                ArrayList<AdultoMayor> filters = new ArrayList<AdultoMayor>();
                AdultoMayor a = new AdultoMayor();
                for(int i=0; i<filterList.size();i++){
                    String Nombre = filterList.get(i).getNombre()+" "+filterList.get(i).getApellidoPaterno()+" "+filterList.get(i).getApellidoMaterno();
                    Nombre.replace('á','a');
                    Nombre.replace('é','e');
                    Nombre.replace('í','i');
                    Nombre.replace('ó','o');
                    Nombre.replace('u','ú');
                    if(Nombre.toUpperCase().contains(constraint)){
                        a = filterList.get(i);
                        filters.add(a);
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
            adultoMayores = (ArrayList<AdultoMayor>) results.values;
            notifyDataSetChanged();
        }
    }
}
