package com.rogzart.proyecto_interfaces.FragmentosBarra.VoluntarioFrecuente;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.rogzart.proyecto_interfaces.FragmentosBarra.Administrar.AU.ListaAdaptadorUsuario;
import com.rogzart.proyecto_interfaces.Modelo.Conexion;
import com.rogzart.proyecto_interfaces.Modelo.Usuario;
import com.rogzart.proyecto_interfaces.R;
import com.rogzart.proyecto_interfaces.Singleton.VolleySingleton;

import java.util.ArrayList;

public class AdaptadorVoluntarioFrecuente extends BaseAdapter implements Filterable {

    private ArrayList<Usuario> users;
    private Context contexto;
    private CustomFilter filter;
    private ArrayList<Usuario> filterList;
    public AdaptadorVoluntarioFrecuente(ArrayList<Usuario> users, Context contexto){
        this.users = users;
        this.contexto = contexto;
        this.filterList = users;
    }
    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int position) {
        return users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return users.get(position).getIdUsuario();
    }
    private static class ViewHolder{
        TextView Nombre,Apellido;
        ImageView Fotografia;
        View v;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Conexion conexion = new Conexion(contexto);

        LayoutInflater inflate = LayoutInflater.from(contexto);
        View v = inflate.inflate(R.layout.list_voluntarios,null);
        Usuario usuario = users.get(position);

        final ViewHolder holder;
        holder = new ViewHolder();

        holder.Fotografia = v.findViewById(R.id.FotografiaVF);
        holder.Nombre = v.findViewById(R.id.IdNombre);
        holder.Apellido = v.findViewById(R.id.IdApellido);

        conexion.setRuta("WebService/"+usuario.getFotografia());
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
        holder.Nombre.setText(usuario.getNombre());
        holder.Apellido.setText(usuario.getApellidoPaterno()+" "+usuario.getApellidoMaterno());

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
                ArrayList<Usuario> filters = new ArrayList<Usuario>();
                Usuario u = new Usuario();
                for(int i=0; i<filterList.size();i++){
                    String Nombre = filterList.get(i).getNombre()+" "+filterList.get(i).getApellidoPaterno()+" "+filterList.get(i).getApellidoMaterno();
                    Nombre.replace('á','a');
                    Nombre.replace('é','e');
                    Nombre.replace('í','i');
                    Nombre.replace('ó','o');
                    Nombre.replace('u','ú');
                    if(Nombre.toUpperCase().contains(constraint)){
                        u = filterList.get(i);
                        filters.add(u);
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
            users = (ArrayList<Usuario>) results.values;
            notifyDataSetChanged();
        }
    }
}
