package com.rogzart.proyecto_interfaces.FragmentosBarra.Eventos;

import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.rogzart.proyecto_interfaces.FragmentosBarra.Scouter.Administracion_Scouter;
import com.rogzart.proyecto_interfaces.Modelo.Conexion;
import com.rogzart.proyecto_interfaces.Modelo.Evento;
import com.rogzart.proyecto_interfaces.Modelo.Inventario;
import com.rogzart.proyecto_interfaces.R;
import com.rogzart.proyecto_interfaces.Singleton.LogUser;
import com.rogzart.proyecto_interfaces.sqlite.ActualizacionBaseDatos;
import com.rogzart.proyecto_interfaces.sqlite.OperacionesBaseDatos;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class ListaEventos extends Fragment {

    ListView ListEventos;
    FloatingActionButton AgregarEvento;
    SearchView BuscadorEventos;
    private OperacionesBaseDatos operador;
    private int cuenta;
    private FloatingActionButton BtnAgregar;
    private ActualizacionBaseDatos Act;
    private HiloCargaLista Hilo;
    private ProgressBar cargando;
    private Conexion conexion;

    public ListaEventos(){

    }

    public static ListaEventos newInstance() {
        ListaEventos fragment = new ListaEventos();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_eventos, container, false);

    }

    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        ListEventos = (ListView) getView().findViewById(R.id.eventos_lista);
        Conexion conexion = new Conexion(getContext());
        Act = new ActualizacionBaseDatos(getContext());
        AgregarEvento = (FloatingActionButton) getView().findViewById(R.id.eventos_agregar);
        BuscadorEventos = (SearchView) getView().findViewById(R.id.eventos_buscador);
        operador = OperacionesBaseDatos.obtenerInstancia(getContext());
        cargando = getView().findViewById(R.id.progressBarE);
        if(conexion.isConnected()) {
            Hilo = new HiloCargaLista();
            Hilo.execute();
        }else{
            CargarListaEventos();
        }


    }


    private void CargarListaEventos() {
        ArrayList<Evento> arrayList = operador.LeerTablaEvento();
        cuenta = arrayList.size();
        //Toast.makeText(getContext(), "" + arrayList.size(), Toast.LENGTH_SHORT).show();
        final EventosAdaptador miLista = new EventosAdaptador(arrayList, getContext());
        ListEventos.setAdapter(miLista);

        BuscadorEventos.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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

        BtnAgregar = getView().findViewById(R.id.eventos_agregar);
        BtnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(), "Hola", Toast.LENGTH_SHORT).show();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.contenedor, Agregar_Evento.newInstance());
                ft.addToBackStack(null);
                ft.commit();
            }
        });

    }
    void actualizaciones(){
        operador.EliminarDatosTabla("evento");
        Act.ActualizacionEvento(getContext());
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
            CargarListaEventos();
            cargando.setVisibility(View.GONE);

        }
    }

}
