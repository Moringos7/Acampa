package com.rogzart.proyecto_interfaces.FragmentosBarra.VoluntarioFrecuente;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.rogzart.proyecto_interfaces.Barra_desplegable;
import com.rogzart.proyecto_interfaces.FragmentosBarra.Administrar.AU.AdministrarUsuario;
import com.rogzart.proyecto_interfaces.FragmentosBarra.AsignacionAdultosMayores.Coordinador.AsignacionAdultoMayorCoordinador;
import com.rogzart.proyecto_interfaces.FragmentosBarra.Eventos.Eventos;
import com.rogzart.proyecto_interfaces.FragmentosBarra.InformacionAdultoMayor.InformacionAdultoMayor;
import com.rogzart.proyecto_interfaces.Modelo.AdultoMayor;
import com.rogzart.proyecto_interfaces.Modelo.Conexion;
import com.rogzart.proyecto_interfaces.Modelo.Usuario;
import com.rogzart.proyecto_interfaces.R;
import com.rogzart.proyecto_interfaces.Singleton.VolleySingleton;
import com.rogzart.proyecto_interfaces.sqlite.ActualizacionBaseDatos;
import com.rogzart.proyecto_interfaces.sqlite.OperacionesBaseDatos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@SuppressLint("ValidFragment")
public class AsignarVoluntarioFrecuente extends Fragment {

    private AdultoMayor adultoMayor;
    private ListView ListaG;
    private SearchView busqueda;
    private Button btn;
    private OperacionesBaseDatos operador;
    private ArrayList<Usuario> usuarios;
    private Usuario user;
    private View afterV;
    private Conexion conexion;
    private ActualizacionBaseDatos Act;
    private LinearLayout Cargando, General;
    @SuppressLint("ValidFragment")
    public AsignarVoluntarioFrecuente(Bundle bolsa) {
        adultoMayor = (AdultoMayor) bolsa.getSerializable("AdultoMayor");
    }

    public static AsignarVoluntarioFrecuente newInstance(Bundle bolsa) {
        AsignarVoluntarioFrecuente fragment = new AsignarVoluntarioFrecuente(bolsa);
        return fragment;
    }

    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        conexion = new Conexion(getContext());
        Act = new ActualizacionBaseDatos(getContext());
        Cargando = getView().findViewById(R.id.layoutActualizandoFrecuente);
        General = getView().findViewById(R.id.LayoutFrecuente);
        ListaG = getView().findViewById(R.id.lista_voluntarios_frecuentes);
        busqueda = getView().findViewById(R.id.searchVoluntarioFrecuente);
        btn = getView().findViewById(R.id.btnAsignarVoluntario);
        operador = OperacionesBaseDatos.obtenerInstancia(getContext());

        usuarios = operador.usuariosActivos();

        final AdaptadorVoluntarioFrecuente miLista = new AdaptadorVoluntarioFrecuente(usuarios, getContext());
        ListaG.setAdapter(miLista);

        busqueda.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                miLista.getFilter().filter(query);
                return false;
            }
        });
        ListaG.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(user == null){
                    user = (Usuario) adapterView.getItemAtPosition(i);
                }else{
                    Usuario userTemp = (Usuario) adapterView.getItemAtPosition(i);
                    if(user.getIdUsuario() == userTemp.getIdUsuario()){
                        user = null;
                    }else{
                        user = null;
                        user = (Usuario)adapterView.getItemAtPosition(i);
                    }
                }

                if(afterV != null){
                    afterV.setBackgroundColor(Color.WHITE);
                }
                if(afterV == view){
                    view.setBackgroundColor(Color.WHITE);
                    afterV = null;
                }else{
                    view.setBackgroundColor(Color.rgb(45, 115, 191));
                    afterV = view;
                }

                if(user != null){
                    Toast.makeText(getContext(), "->"+user.getNombre(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "-> No seleccion", Toast.LENGTH_SHORT).show();
                }
                //Toast.makeText(getContext(), "P: "+l, Toast.LENGTH_SHORT).show();
            }
        });



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user != null){
                    if(conexion.isConnected()){
                        conexion.setRuta("WebService/VoluntarioFrecuente/wsVoluntarioFrecuenteCreate.php");
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, conexion.getRuta(),
                                new Response.Listener<String>()
                                {
                                    // operador.EliminarDatosTabla("voluntariofrecuente");
                                    //                                                        Salir = new ActualizacionBaseDatos(getContext()).ActualizacionVoluntarioFrecuente(getContext());
                                    @Override
                                    public void onResponse(String response) {
                                        //Toast.makeText(getContext(), ""+response, Toast.LENGTH_SHORT).show();
                                        if(response.compareTo("Asignado") == 0){
                                            HiloCargaLista x = new HiloCargaLista();
                                            x.execute();
                                        }
                                    }
                                },
                                new Response.ErrorListener()
                                {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        // error
                                        Toast.makeText(getContext(), ""+error, Toast.LENGTH_SHORT).show();
                                    }
                                }
                        ) {
                            @Override
                            protected Map<String, String> getParams()
                            {
                                Map<String, String>  params = new HashMap<String, String>();
                                params.put("fkusuario",Integer.toString(user.getIdUsuario()));
                                params.put("fkadultomayor", Integer.toString(adultoMayor.getIdAdultoMayor()));
                                return params;
                            }
                        };
                        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
                    }else {
                        Toast.makeText(getContext(), "Verifica Tu Conexion", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(getContext(), "Selecciona un Usuario", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_asignar_voluntario_frecuente, container, false);
    }




    private class HiloCargaLista extends AsyncTask<Void, Void, Void>{

        @Override protected void onPreExecute() {
            General.setVisibility(View.GONE);
            Cargando.setVisibility(View.VISIBLE);
        }
        @Override
        protected Void doInBackground(Void... voids) {
            operador.EliminarDatosTabla("voluntariofrecuente");
            Act.ActualizacionVoluntarioFrecuente(getContext());
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
            Toast.makeText(getContext(), "Actualizado", Toast.LENGTH_SHORT).show();
            Bundle bolsa = new Bundle();
            bolsa.putSerializable("AdultoMayor",adultoMayor);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.contenedor, InformacionAdultoMayor.newInstance(bolsa));
            ft.addToBackStack(null);
            ft.commit();


        }
    }
}


