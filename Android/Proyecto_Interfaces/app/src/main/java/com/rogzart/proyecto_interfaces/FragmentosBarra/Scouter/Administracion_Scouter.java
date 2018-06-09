package com.rogzart.proyecto_interfaces.FragmentosBarra.Scouter;

import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.rogzart.proyecto_interfaces.FragmentosBarra.Inventario.IG.ListaInventario;
import com.rogzart.proyecto_interfaces.Modelo.Conexion;
import com.rogzart.proyecto_interfaces.Modelo.Usuario;
import com.rogzart.proyecto_interfaces.R;
import com.rogzart.proyecto_interfaces.Singleton.VolleySingleton;
import com.rogzart.proyecto_interfaces.sqlite.ActualizacionBaseDatos;
import com.rogzart.proyecto_interfaces.sqlite.OperacionesBaseDatos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Administracion_Scouter extends Fragment implements View.OnClickListener {

    private static Administracion_Scouter yo;
    private FloatingActionButton btnScouter;
   private ListView ListG;
   private OperacionesBaseDatos operador;
   private TextView Resultado;
   private ArrayList<Usuario> ListaScouters;
   private String s;
   private SearchView mSearchView;
   private int Cuenta;
   //private HiloCargaLista Hilo;
   private boolean Repite;
   private boolean Cambiar;
    public Administracion_Scouter() {

    }

    //btnAgregarScouter
    public static Administracion_Scouter newInstance() {
        Administracion_Scouter fragment = new Administracion_Scouter();
        yo = fragment;
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_administracion__scouter, container, false);
    }
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        Cuenta = 0;
        operador = OperacionesBaseDatos.obtenerInstancia(getContext());
        btnScouter = getView().findViewById(R.id.btnAgregarScouter);
        btnScouter.setOnClickListener(this);
        Resultado = getView().findViewById(R.id.ResulScouter);
        Repite = true;
        Cambiar = false;
        /*Hilo = new HiloCargaLista();
        Hilo.execute();*/
        cargarLista();

    }
    @Override
    public void onClick(View v) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.contenedor, AgregarScouter.newInstance());
        ft.addToBackStack(null);
        ft.commit();

    }

    public void cargarLista(){
        mSearchView = getView().findViewById(R.id.idSearchScouter);
        ListG = getView().findViewById(R.id.ListaScouter);
        ListaScouters = operador.obtenerScoutersActivos();
        s = "s";
        if(ListaScouters.size() == 1){
            s = "";
        }
        Resultado.setText("Resultado"+s+" "+ListaScouters.size());
        final AdaptadorScouter adaptadorScouter = new AdaptadorScouter(ListaScouters,getContext(),yo);
        ListG.setAdapter(adaptadorScouter);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                adaptadorScouter.getFilter().filter(query);
                return false;
            }
        });
        /*if(Hilo.getStatus() == AsyncTask.Status.RUNNING){
            DetenerHilo();
        }
        Hilo = new HiloCargaLista();
        Hilo.execute();*/
    }
    /*@Override
    public void onPause() {
        super.onPause();
        DetenerHilo();
    }
    void DetenerHilo(){
        if(Hilo != null){
            Hilo.cancel(true);
        }
    }*/

    /*private class HiloCargaLista extends AsyncTask<Void, Void, Void> {

        private Conexion conexion;
        private StringRequest stringRequest;

        private int i = 0;
        @Override protected void onPreExecute() {
            conexion = new Conexion(getContext());
            conexion.setRuta("WebService/Scouter/wsScouterContar.php");
            stringRequest = new StringRequest(Request.Method.POST, conexion.getRuta(),
                    new Response.Listener<String>()
                    {
                        @Override
                        public void onResponse(String response) {
                            //Toast.makeText(getContext(), ""+response, Toast.LENGTH_SHORT).show();
                            Toast.makeText(getContext(), "--"+i, Toast.LENGTH_SHORT).show();
                            int dato = Integer.parseInt(response);
                            if(Cuenta == dato){
                                Repite = true;
                                Cambiar = false;
                            }else{
                                Repite = false;
                                Cambiar = true;
                                Cuenta = dato;
                            }
                        }
                    },
                    new Response.ErrorListener()
                    {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getContext(), ""+error, Toast.LENGTH_SHORT).show();
                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams()
                {
                    Map<String, String>  params = new HashMap<String, String>();
                    params.put("Cuenta",Integer.toString(Cuenta));
                    return params;
                }
            };
        }
        @Override
        protected Void doInBackground(Void... voids) {

            while(Repite){
                VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
                if(isCancelled()){
                    break;
                }
                if(Cambiar){
                    i++;
                    operador.EliminarDatosTabla("scouter");
                    new ActualizacionBaseDatos(getContext()).ActualizacionScouter(getContext());
                    Cambiar = false;
                }
                try{
                    Thread.sleep(5000);}
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            cargarLista();
        }
        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

    }*/



}
