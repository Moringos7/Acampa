package com.rogzart.proyecto_interfaces.FragmentosBarra.Eventos;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.rogzart.proyecto_interfaces.Modelo.Evento;
import com.rogzart.proyecto_interfaces.Modelo.Inventario;
import com.rogzart.proyecto_interfaces.R;
import com.rogzart.proyecto_interfaces.Singleton.LogUser;
import com.rogzart.proyecto_interfaces.sqlite.OperacionesBaseDatos;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class ListaEventos extends Fragment {

    ListView ListEventos;
    FloatingActionButton AgregarEvento;
    SearchView BuscadorEventos;
    private OperacionesBaseDatos operador;
    private LogUser ControlUser;
    private int cuenta;

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
        ControlUser = LogUser.obtenerInstancia(getContext());
        ListEventos = (ListView) getView().findViewById(R.id.eventos_lista);
        AgregarEvento = (FloatingActionButton) getView().findViewById(R.id.eventos_agregar);
        BuscadorEventos = (SearchView) getView().findViewById(R.id.eventos_buscador);
        operador = OperacionesBaseDatos.obtenerInstancia(getContext());
        CargarListaEventos();

    }

    private void CargarListaEventos() {
        ArrayList<Evento> arrayList = operador.LeerTablaEvento();
        cuenta = arrayList.size();
        Toast.makeText(getContext(), "" + arrayList.size(), Toast.LENGTH_SHORT).show();
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
        ListEventos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int x, long l) {
                Evento evento = (Evento) adapterView.getItemAtPosition(x);
                Bundle bolsa = new Bundle();
                bolsa.putSerializable("casos", evento);

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.contenedor, ListaEventosMain.newInstance(bolsa));
                transaction.addToBackStack(null);
                transaction.commit();
            }


        }
        );

    }

    private String generarFecha(){
        String Fecha;
        Calendar c = Calendar.getInstance();
        int Dia = c.get(Calendar.DAY_OF_MONTH);
        int Mes = c.get(Calendar.MONTH)+1;
        int Anio = c.get(Calendar.YEAR);
        String decenaD = "";
        String decenaM = "";
        if(Mes < 10){
            decenaM = "0";
        }
        if(Dia < 10){
            decenaD = "0";
        }
        Fecha = String.valueOf(Anio)+"-"+decenaM+String.valueOf(Mes)+"-"+decenaD+String.valueOf(Dia);
        return Fecha;
    }
}
