package com.rogzart.proyecto_interfaces.FragmentosBarra.Administrar.AU;

import android.annotation.SuppressLint;
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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.rogzart.proyecto_interfaces.Modelo.Conexion;
import com.rogzart.proyecto_interfaces.Modelo.Seccion;
import com.rogzart.proyecto_interfaces.Modelo.Usuario;
import com.rogzart.proyecto_interfaces.Modelo.Seccion;
import com.rogzart.proyecto_interfaces.R;
import com.rogzart.proyecto_interfaces.Singleton.VolleySingleton;
import com.rogzart.proyecto_interfaces.sqlite.OperacionesBaseDatos;

import java.util.ArrayList;

import static com.rogzart.proyecto_interfaces.R.layout.list_usuario_administrar;

public class ListaAdaptadorUsuario extends BaseAdapter implements Filterable{

    private ArrayList<Usuario> datos;
    private Context contexto;
    private OperacionesBaseDatos operador;
    private CustomFilter filter;
    private ArrayList<Usuario> filterList;
    public ListaAdaptadorUsuario(ArrayList<Usuario> datos, Context contexto)
    {
        this.datos = datos;
        this.contexto = contexto;
        this.filterList = datos;
    }


    @Override
    public int getCount() {

        return datos.size();
    }

    @Override
    public Object getItem(int position) {

        return datos.get(position);
    }

    @Override
    public long getItemId(int position) {

        return datos.get(position).getIdUsuario();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Conexion conexion = new Conexion(contexto);
        LayoutInflater inflate = LayoutInflater.from(contexto);
        View v = inflate.inflate(R.layout.list_usuario_administrar,null);

        TextView nombre = (TextView) v.findViewById(R.id.list_usuario_nombre);
        TextView apellidos = (TextView) v.findViewById(R.id.list_usuario_apellidos);
        final ImageView imagen = (ImageView) v.findViewById(R.id.list_usuario_imagen);
        TextView seccionLista = (TextView) v.findViewById(R.id.list_usuario_seccion);
        operador = OperacionesBaseDatos.obtenerInstancia(contexto);

        nombre.setText(datos.get(position).getNombre());
        apellidos.setText(datos.get(position).getApellidoPaterno()+" "+datos.get(position).getApellidoMaterno());

        Seccion seccionUsuario = operador.ObtenerSeccion(datos.get(position).getFkSeccion());
        seccionLista.setText(seccionUsuario.getNombre());
        switch (seccionUsuario.getIdSeccion()){
            case 1:
                seccionLista.setBackgroundColor(Color.argb(255, 255, 255, 0));
            break;
            case 2:
                seccionLista.setBackgroundColor(Color.argb(255, 41, 138, 8));
            break;
            case 3:
                seccionLista.setBackgroundColor(Color.argb(255, 4, 95, 180));
                seccionLista.setTextSize(15);
            break;
            case 4:
                seccionLista.setBackgroundColor(Color.argb(255, 255, 0, 0));
            break;
            case 5:
                seccionLista.setBackgroundColor(Color.argb(255, 132, 132, 132));
            break;
        }

        conexion.setRuta("WebService/"+datos.get(position).getFotografia());
        ImageRequest imageRequest = new ImageRequest(conexion.getRuta(), new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                imagen.setImageBitmap(response);
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
                    if(filterList.get(i).getNombre().toUpperCase().contains(constraint)){
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
            datos = (ArrayList<Usuario>) results.values;
            notifyDataSetChanged();
        }
    }
}
