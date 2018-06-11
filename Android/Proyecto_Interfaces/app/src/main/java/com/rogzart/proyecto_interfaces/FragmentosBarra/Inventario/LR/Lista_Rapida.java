package com.rogzart.proyecto_interfaces.FragmentosBarra.Inventario.LR;

import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.rogzart.proyecto_interfaces.FragmentosBarra.Scouter.Administracion_Scouter;
import com.rogzart.proyecto_interfaces.Modelo.Conexion;
import com.rogzart.proyecto_interfaces.Modelo.ListaInventario;
import com.rogzart.proyecto_interfaces.Modelo.VoluntarioFrecuente;
import com.rogzart.proyecto_interfaces.R;
import com.rogzart.proyecto_interfaces.Singleton.LogUser;
import com.rogzart.proyecto_interfaces.Singleton.VolleySingleton;
import com.rogzart.proyecto_interfaces.sqlite.OperacionesBaseDatos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Lista_Rapida extends Fragment {


    private ListView ListaG;
    private Conexion conexion;
    private int Time;
    private HiloVerifica hilo;
    public Lista_Rapida() {
        // Required empty public constructor
    }
    public static Lista_Rapida newInstance() {
        Lista_Rapida fragment = new Lista_Rapida();
        return fragment;
    }


    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        ListaG = getView().findViewById(R.id.listRapida);
        conexion = new Conexion(getContext());
        Time = 0;
        cargarLista();

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lista__rapida, container, false);
    }

    public void cargarLista(){
        final ArrayList<ListaInventario> Lista = new ArrayList<ListaInventario>();
        Time = 0;
        conexion.setRuta("WebService/ListaRapida/wsListaRapida.php");
        final ListaInventario[] producto = new ListaInventario[1];
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, conexion.getRuta(), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray json = response.optJSONArray("ListaRapida");
                    for (int i = 0; i < json.length(); i++) {
                        JSONObject jsonObject = json.getJSONObject(i);
                        producto[0] = new ListaInventario();
                        producto[0].setIdInventario(jsonObject.getInt("IdInventario"));
                        producto[0].setProducto(jsonObject.getString("Producto"));
                        producto[0].setCantidad((float)jsonObject.getDouble("Cantidad"));
                        producto[0].setExistencia(jsonObject.getInt("Existencia"));
                        producto[0].setRequerido(jsonObject.getInt("Requerido"));
                        producto[0].setDescripcion(jsonObject.getString("Descripcion"));
                        producto[0].setImagen(jsonObject.getString("Imagen"));
                        Lista.add(producto[0]);
                    }
                    AdaptadorListaRapida miLista = new AdaptadorListaRapida(Lista,getContext());
                    ListaG.setAdapter(miLista);
                } catch (JSONException e) {
                    Toast.makeText(getContext(), "Fallo Carga Lista " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), ""+error, Toast.LENGTH_SHORT).show();
            }
        });
        VolleySingleton.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);
        hilo =  new HiloVerifica();
        hilo.execute();
    }
    @Override
    public void onPause() {
        super.onPause();
        if(hilo != null){
            hilo.cancel(true);
        }
    }


    private class HiloVerifica extends AsyncTask<Void, Void, Void> {

        @Override protected void onPreExecute() {
            Time = 0;
        }
        @Override
        protected Void doInBackground(Void... voids) {

            while (Time < 5){
                try {
                    Time++;
                    if(isCancelled()){
                        break;
                    }
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            cargarLista();
        }
    }

}
