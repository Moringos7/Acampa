package com.rogzart.proyecto_interfaces.FragmentosBarra.Inventario.IG;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.rogzart.proyecto_interfaces.FragmentosBarra.InventarioGeneral;
import com.rogzart.proyecto_interfaces.Modelo.Conexion;
import com.rogzart.proyecto_interfaces.Modelo.Inventario;
import com.rogzart.proyecto_interfaces.Modelo.Usuario;
import com.rogzart.proyecto_interfaces.R;
import com.rogzart.proyecto_interfaces.Singleton.VolleySingleton;
import com.rogzart.proyecto_interfaces.sqlite.OperacionesBaseDatos;

import java.util.ArrayList;

public class ListaAdaptadorInventario extends BaseAdapter {
    private ArrayList<Inventario> Cosas;
    private Context contexto;
    private OperacionesBaseDatos operador;
    public ListaAdaptadorInventario(ArrayList<Inventario> cosas, Context contexto)
    {
        this.Cosas = cosas;
        this.contexto = contexto;
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
        View v = inflate.inflate(R.layout.activity_inventario_general,null);

        TextView nombre = (TextView) v.findViewById(R.id.list_inventario_general_nombre_articulo);
        TextView existencia =(TextView) v.findViewById(R.id.list_inventario_general_existencia);
        final ImageView  imagenarticulo= (ImageView) v.findViewById(R.id.list_inventario_general_imagen);
        TextView cantidad = (TextView) v.findViewById(R.id.list_inventario_general_cantidad);
        TextView descripcion= (TextView) v.findViewById(R.id.list_inventario_general_descripcion);
        operador = OperacionesBaseDatos.obtenerInstancia(contexto);


        nombre.setText(Cosas.get(position).getProducto());
        existencia.setText(Cosas.get(position).getExistencia());
        cantidad.setText(String.valueOf( Cosas.get(position).getCantidad()) );
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
}
