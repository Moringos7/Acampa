package com.rogzart.proyecto_interfaces.FragmentosBarra.Scouter;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.rogzart.proyecto_interfaces.FragmentosBarra.Administrar.AU.ListaAdaptadorUsuario;
import com.rogzart.proyecto_interfaces.FragmentosBarra.Inventario.LR.Lista_Rapida;
import com.rogzart.proyecto_interfaces.Modelo.Conexion;
import com.rogzart.proyecto_interfaces.Modelo.Seccion;
import com.rogzart.proyecto_interfaces.Modelo.Usuario;
import com.rogzart.proyecto_interfaces.R;
import com.rogzart.proyecto_interfaces.Singleton.LogUser;
import com.rogzart.proyecto_interfaces.Singleton.VolleySingleton;
import com.rogzart.proyecto_interfaces.sqlite.OperacionesBaseDatos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdaptadorScouter extends BaseAdapter implements Filterable{

    private ArrayList<Usuario> user;
    private Context contexto;
    private Conexion conexion;
    private CustomFilter filter;
    private ArrayList<Usuario> filterList;

    public AdaptadorScouter(ArrayList<Usuario> user,Context contexto){
        this.user = user;
        this.contexto = contexto;
        this.conexion = new Conexion(contexto);
        this.filterList = user;
    }

    @Override
    public int getCount() {
        return user.size();
    }

    @Override
    public Object getItem(int position) {

        return user.get(position);
    }

    @Override
    public long getItemId(int position) {

        return user.get(position).getIdUsuario();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflate = LayoutInflater.from(contexto);
        View v = inflate.inflate(R.layout.list_scouter,null);

        TextView Nombre;
        TextView Apellido;
        final ImageView Imagen;
        Button Btn;

        Nombre = v.findViewById(R.id.Nombre_Scouter);
        Apellido = v.findViewById(R.id.Apellidos_Scouter);
        Imagen = v.findViewById(R.id.ImagenScouter);
        Btn = v.findViewById(R.id.btnScouter);

        if(LogUser.obtenerInstancia(contexto).getCoordinador() >0){
            Btn.setVisibility(View.VISIBLE);
        }

        String Apellidos = user.get(position).getApellidoPaterno()+" "+user.get(position).getApellidoMaterno();
        Nombre.setText(user.get(position).getNombre());
        Apellido.setText(Apellidos);

        conexion.setRuta("WebService/"+user.get(position).getFotografia());
        ImageRequest imageRequest = new ImageRequest(conexion.getRuta(), new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                Imagen.setImageBitmap(response);
            }
        }, 0, 0, ImageView.ScaleType.CENTER, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInstance(contexto).addToRequestQueue(imageRequest);

        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(LogUser.obtenerInstancia(contexto).getCoordinador() >0){
                    conexion.setRuta("WebService/Scouter/wsScouterDelete.php");
                    if(LogUser.obtenerInstancia(contexto).getUser().getIdUsuario() != user.get(position).getIdUsuario() ){
                        if(conexion.isConnected()) {
                            StringRequest stringRequest = new StringRequest(Request.Method.POST, conexion.getRuta(),
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            Toast.makeText(contexto, "" + response, Toast.LENGTH_SHORT).show();
                                            if (response.compareTo("Eliminado") == 0) {
                                                FragmentTransaction ft = ((Activity) contexto).getFragmentManager().beginTransaction();
                                                ft.replace(R.id.contenedor, Administracion_Scouter.newInstance());
                                                ft.commit();
                                            }
                                        }
                                    },
                                    new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            Toast.makeText(contexto, "" + error, Toast.LENGTH_SHORT).show();
                                        }
                                    }
                            ) {
                                @Override
                                protected Map<String, String> getParams() {
                                    Map<String, String> params = new HashMap<String, String>();
                                    params.put("FkUsuario", Integer.toString(user.get(position).getIdUsuario()));
                                    return params;
                                }
                            };
                            VolleySingleton.getInstance(contexto).addToRequestQueue(stringRequest);
                        }else{
                            Toast.makeText(contexto, "Verifica tu conexion", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(contexto, "No puedes eliminarte a ti mismo", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
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
            user = (ArrayList<Usuario>) results.values;
            notifyDataSetChanged();
        }
    }

}