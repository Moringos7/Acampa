package com.rogzart.proyecto_interfaces.FragmentosBarra.Inventario.IG;

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
import com.rogzart.proyecto_interfaces.FragmentosBarra.Administrar.AU.ListaAdaptadorUsuario;
import com.rogzart.proyecto_interfaces.FragmentosBarra.InventarioGeneral;
import com.rogzart.proyecto_interfaces.Modelo.Conexion;
import com.rogzart.proyecto_interfaces.Modelo.Inventario;
import com.rogzart.proyecto_interfaces.Modelo.Usuario;
import com.rogzart.proyecto_interfaces.R;
import com.rogzart.proyecto_interfaces.Singleton.VolleySingleton;
import com.rogzart.proyecto_interfaces.sqlite.OperacionesBaseDatos;

import java.util.ArrayList;

public class ListaAdaptadorInventario extends BaseAdapter implements Filterable {
    private ArrayList<Inventario> Cosas;
    private Context contexto;
    private CustomFilter MiFiltro;
    private OperacionesBaseDatos operador;
    private ArrayList<Inventario> filterList;
    public ListaAdaptadorInventario(ArrayList<Inventario> cosas, Context contexto)
    {
        this.Cosas = cosas;
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

        return Cosas.get(position).getIdInventario();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Conexion conexion = new Conexion(contexto);
        LayoutInflater inflate = LayoutInflater.from(contexto);
        View v = inflate.inflate(R.layout.list_inventario_general,null);

        TextView nombre = (TextView) v.findViewById(R.id.list_inventario_general_nombre_articulo);
        TextView existencia =(TextView) v.findViewById(R.id.list_inventario_general_existencia);
        final ImageView  imagenarticulo= (ImageView) v.findViewById(R.id.list_inventario_general_imagen);
        TextView cantidad = (TextView) v.findViewById(R.id.list_inventario_general_cantidad);
        TextView descripcion= (TextView) v.findViewById(R.id.list_inventario_general_descripcion);
        operador = OperacionesBaseDatos.obtenerInstancia(contexto);

        nombre.setText(Cosas.get(position).getProducto());
        existencia.setText(String.valueOf(Cosas.get(position).getExistencia()));

        int valor = (int) (1/Cosas.get(position).getCantidad());
        if(valor <= 1){
            cantidad.setText(String.valueOf((int)Cosas.get(position).getCantidad()));
        }else{
            cantidad.setText("1/"+valor);
        }
        descripcion.setText(Cosas.get(position).getDescripcion());

        conexion.setRuta("WebService/"+Cosas.get(position).getImagen());
        ImageRequest imageRequest = new ImageRequest(conexion.getRuta(), new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                imagenarticulo.setImageBitmap(response);
            }
        }, 0, 0, ImageView.ScaleType.CENTER, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInstance(contexto).addToRequestQueue(imageRequest);
        return v;
    }

    @Override
    public Filter getFilter() {

        if (MiFiltro == null) {
            MiFiltro = new CustomFilter();

        }
        return MiFiltro;
    }

    private class CustomFilter extends  Filter{
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if(constraint != null && constraint.length() > 0){
                constraint = constraint.toString().toUpperCase();
                ArrayList<Inventario> filters = new ArrayList<Inventario>();
                Inventario inventario = new Inventario();
                for(int i=0; i<filterList.size();i++){
                    if(filterList.get(i).getProducto().toUpperCase().contains(constraint)){
                        inventario = filterList.get(i);
                        filters.add(inventario);
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
            Cosas = (ArrayList<Inventario>) results.values;
            notifyDataSetChanged();
        }

    }
}
