package com.rogzart.proyecto_interfaces.FragmentosBarra.Inventario.LR;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.rogzart.proyecto_interfaces.FragmentosBarra.Convivio.Convivio;
import com.rogzart.proyecto_interfaces.Modelo.Conexion;
import com.rogzart.proyecto_interfaces.Modelo.ListaInventario;
import com.rogzart.proyecto_interfaces.R;
import com.rogzart.proyecto_interfaces.Singleton.VolleySingleton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdaptadorListaRapida extends BaseAdapter {

    private ArrayList<ListaInventario> Lista;
    private Context contexto;
    public Conexion conexion;

    public AdaptadorListaRapida(ArrayList<ListaInventario> Lista,Context contexto){
        this.Lista = Lista;
        this.contexto = contexto;
        conexion = new Conexion(contexto);
    }


    @Override
    public int getCount() {
        return Lista.size();
    }

    @Override
    public Object getItem(int position) {
        return Lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Lista.get(position).getIdInventario();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflate = LayoutInflater.from(contexto);
        View v = inflate.inflate(R.layout.item_lista_rapida,null);


        final ListaInventario ListaJM = Lista.get(position);
        final ImageView Imagen = v.findViewById(R.id.ImagenLista_Rapida);
        TextView Producto = v.findViewById(R.id.ProductoLista_Rapida);
        TextView Explicacion = v.findViewById(R.id.ExplicacionLista_Rapida);
        final TextView Existencia = v.findViewById(R.id.ExistenciaLista_Rapida);
        TextView Requerido = v.findViewById(R.id.RequeridoLista_Rapida);
        Button Mas = v.findViewById(R.id.BtnMas);
        Button Menos = v.findViewById(R.id.BtnMenos);

        conexion.setRuta("WebService/"+ListaJM.getImagen());
        ImageRequest imageRequest = new ImageRequest(conexion.getRuta(), new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                Imagen.setImageBitmap(response);
            }
        }, 0, 0, ImageView.ScaleType.CENTER, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //imagen local
            }
        });
        VolleySingleton.getInstance(contexto).addToRequestQueue(imageRequest);

        Producto.setText(ListaJM.getProducto());

        int valor = (int) (1/ListaJM.getCantidad());
        String Cantidad;
        if(valor <= 1){
            Cantidad = String.valueOf((int)ListaJM.getCantidad());
        }else{
            Cantidad = "1/"+valor;
        }
        Explicacion.setText(String.valueOf(Cantidad+" "+ListaJM.getDescripcion()));
        Existencia.setText(String.valueOf(ListaJM.getExistencia()));
        Requerido.setText(String.valueOf(ListaJM.getRequerido()));

        Mas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int valor = Integer.parseInt((String) Existencia.getText());
                valor++;
                conexion.setRuta("WebService/ListaRapida/wsListaRapidaUpdate.php");
                final int finalValor = valor;
                StringRequest stringRequest = new StringRequest(Request.Method.POST, conexion.getRuta(),
                        new Response.Listener<String>()
                        {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(contexto, ""+response, Toast.LENGTH_SHORT).show();
                            }
                        },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                                Toast.makeText(contexto, "Error: "+error, Toast.LENGTH_SHORT).show();
                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams()
                    {
                        Map<String, String>  params = new HashMap<String, String>();
                        params.put("Id",String.valueOf(ListaJM.getIdInventario()));
                        params.put("Existencia",String.valueOf(finalValor));
                        return params;
                    }
                };
                VolleySingleton.getInstance(contexto).addToRequestQueue(stringRequest);


                FragmentTransaction ft = ((Activity) contexto).getFragmentManager().beginTransaction();
                ft.replace(R.id.contenedor, Lista_Rapida.newInstance());
                ft.commit();
            }
        });
        Menos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int valor = Integer.parseInt((String) Existencia.getText());
                valor--;
                if(valor < 0){
                    valor = 0;
                }
                conexion.setRuta("WebService/ListaRapida/wsListaRapidaUpdate.php");
                final int finalValor = valor;
                StringRequest stringRequest = new StringRequest(Request.Method.POST, conexion.getRuta(),
                        new Response.Listener<String>()
                        {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(contexto, ""+response, Toast.LENGTH_SHORT).show();
                            }
                        },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                                Toast.makeText(contexto, "Error: "+error, Toast.LENGTH_SHORT).show();
                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams()
                    {
                        Map<String, String>  params = new HashMap<String, String>();
                        params.put("Id",String.valueOf(ListaJM.getIdInventario()));
                        params.put("Existencia",String.valueOf(finalValor));
                        return params;
                    }
                };
                VolleySingleton.getInstance(contexto).addToRequestQueue(stringRequest);


                FragmentTransaction ft = ((Activity) contexto).getFragmentManager().beginTransaction();
                ft.replace(R.id.contenedor, Lista_Rapida.newInstance());
                ft.commit();
            }
        });

        return v;
    }
}
