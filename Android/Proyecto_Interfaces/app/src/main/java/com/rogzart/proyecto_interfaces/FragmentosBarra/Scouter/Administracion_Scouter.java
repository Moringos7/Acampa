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
import android.widget.ProgressBar;
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


    private FloatingActionButton btnScouter;
   private ListView ListG;
   private OperacionesBaseDatos operador;
   private  ActualizacionBaseDatos Act;
   private TextView Resultado;
   private ArrayList<Usuario> ListaScouters;
   private String s;
   private SearchView mSearchView;
   private int Cuenta;
   private HiloCargaLista Hilo;
   private boolean Repite;
   private boolean Cambiar;
   private Conexion conexion;
   private ProgressBar cargando;
    public Administracion_Scouter() {

    }

    //btnAgregarScouter
    public static Administracion_Scouter newInstance() {
        Administracion_Scouter fragment = new Administracion_Scouter();

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
        conexion = new Conexion(getContext());
        Cuenta = 0;
        Act = new ActualizacionBaseDatos(getContext());
        operador = OperacionesBaseDatos.obtenerInstancia(getContext());
        btnScouter = getView().findViewById(R.id.btnAgregarScouter);
        btnScouter.setOnClickListener(this);
        Resultado = getView().findViewById(R.id.ResulScouter);
        Repite = true;
        Cambiar = false;
        cargando = getView().findViewById(R.id.progressBarS);
        if(conexion.isConnected()) {
            Hilo = new HiloCargaLista();
            Hilo.execute();
        }else{
            cargarLista();
        }

    }
    @Override
    public void onClick(View v) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.contenedor, AgregarScouter.newInstance());
        ft.addToBackStack(null);
        ft.commit();

    }

    public void cargarLista() {
        mSearchView = getView().findViewById(R.id.idSearchScouter);
        ListG = getView().findViewById(R.id.ListaScouter);
        ListaScouters = operador.obtenerScoutersActivos();
        s = "s";
        if (ListaScouters.size() == 1) {
            s = "";
        }
        Resultado.setText("Resultado" + s + " " + ListaScouters.size());
        final AdaptadorScouter adaptadorScouter = new AdaptadorScouter(ListaScouters, getContext());
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

    }
    void actualizaciones(){
        operador.EliminarDatosTabla("recoger");
        operador.EliminarDatosTabla("gestioninventario");
        operador.EliminarDatosTabla("asignacion");
        operador.EliminarDatosTabla("scouter");
        Act.ActualizacionScouter(getContext());
        Act.ActualizacionAsignacion(getContext());
        Act.ActualizacionGestionInventario(getContext());
        Act.ActualizacionRecoger(getContext());
    }
    private class HiloCargaLista extends AsyncTask<Void, Void, Void> {

        @Override protected void onPreExecute() {
            cargando.setVisibility(View.VISIBLE);
        }
        @Override
        protected Void doInBackground(Void... voids) {

            actualizaciones();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            cargarLista();
            cargando.setVisibility(View.GONE);

        }
    }
}
