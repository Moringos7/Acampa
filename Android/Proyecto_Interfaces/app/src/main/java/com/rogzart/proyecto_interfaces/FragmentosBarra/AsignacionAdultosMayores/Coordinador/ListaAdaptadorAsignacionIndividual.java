package com.rogzart.proyecto_interfaces.FragmentosBarra.AsignacionAdultosMayores.Coordinador;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
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
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.rogzart.proyecto_interfaces.FragmentosBarra.InformacionAdultoMayor.AdaptadorInformacionAdultoMayor;
import com.rogzart.proyecto_interfaces.Modelo.AdultoMayor;
import com.rogzart.proyecto_interfaces.Modelo.Conexion;
import com.rogzart.proyecto_interfaces.R;
import com.rogzart.proyecto_interfaces.Singleton.VolleySingleton;

import java.util.ArrayList;

public class ListaAdaptadorAsignacionIndividual extends BaseAdapter implements Filterable{
    private ArrayList<AdultoMayor> adultomayor;
    private ArrayList<AdultoMayor> Frecuentes;
    private Context contexto;
    private CustomFilter filter;
    private ArrayList<AdultoMayor> filterList;
    public ListaAdaptadorAsignacionIndividual(ArrayList<AdultoMayor> adultomayor,ArrayList<AdultoMayor> Frecuentes,Context contexto){
        this.adultomayor = adultomayor;
        this.contexto = contexto;
        this.Frecuentes = Frecuentes;
        this.filterList = adultomayor;
    }
    @Override
    public int getCount() {
        return adultomayor.size();
    }

    @Override
    public Object getItem(int position) {

        return adultomayor.get(position);
    }

    @Override
    public long getItemId(int position) {

        return adultomayor.get(position).getIdAdultoMayor();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflate = LayoutInflater.from(contexto);

        final ViewHolder holder;

        //if(convertView == null){
            convertView = inflate.inflate(R.layout.fragment_asignacion_individual_list,null);
            holder = new ViewHolder();
            holder.Nombre = convertView.findViewById(R.id.IdNombreAdultoMayorAsignacion);
            holder.imagen = convertView.findViewById(R.id.imagenAdultoMayorAsignacionIndividual);
            holder.check = convertView.findViewById(R.id.checkAsignacion);
            holder.Frecuente = convertView.findViewById(R.id.IdVoluntarioFrecuente);
            holder.adultoMayor = adultomayor.get(position);
            convertView.setTag(holder);
        /*}else{
            holder = (ViewHolder)convertView.getTag();
            holder.check.setOnCheckedChangeListener(null);
        }*/

        for(int i=0; i<Frecuentes.size();i++){
            if(Frecuentes.get(i).getIdAdultoMayor() == holder.adultoMayor.getIdAdultoMayor()){
                holder.Frecuente.setVisibility(View.VISIBLE);
            }
        }
        holder.check.setChecked(holder.adultoMayor.getCheck());
        /*
        if(adultomayor.get(position).getCheck()){
            holder.check.setChecked(true);
        }*/

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

        String NombreCompleto = adultomayor.get(position).getNombre()+" "+adultomayor.get(position).getApellidoPaterno()+" "+adultomayor.get(position).getApellidoMaterno();
        holder.Nombre.setText(NombreCompleto);

        holder.check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    adultomayor.get(position).setCheck(true);
                }else{
                    adultomayor.get(position).setCheck(false);
                }

            }
        });

        return convertView;
    }
    private static class ViewHolder{
        TextView Nombre,Frecuente;
        ImageView imagen;
        CheckBox check;
        AdultoMayor adultoMayor;
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
            adultomayor = (ArrayList<AdultoMayor>) results.values;
            notifyDataSetChanged();
        }
    }
}

