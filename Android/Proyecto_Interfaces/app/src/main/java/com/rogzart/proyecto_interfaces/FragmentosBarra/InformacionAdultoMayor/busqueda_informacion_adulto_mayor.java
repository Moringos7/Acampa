package com.rogzart.proyecto_interfaces.FragmentosBarra.InformacionAdultoMayor;

import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.rogzart.proyecto_interfaces.FragmentosBarra.Administrar.AU.AdministrarUsuario;
import com.rogzart.proyecto_interfaces.Modelo.AdultoMayor;
import com.rogzart.proyecto_interfaces.Modelo.Usuario;
import com.rogzart.proyecto_interfaces.R;
import com.rogzart.proyecto_interfaces.sqlite.OperacionesBaseDatos;

import java.util.ArrayList;

public class busqueda_informacion_adulto_mayor extends Fragment {

    private ListView ListaG;
    private ArrayList<AdultoMayor> adultosMayores;
    private SearchView busqueda;
    private OperacionesBaseDatos operador;
    private TextView contador;

    public busqueda_informacion_adulto_mayor() {
        // Required empty public constructor
    }


    public static busqueda_informacion_adulto_mayor newInstance() {
        busqueda_informacion_adulto_mayor fragment = new busqueda_informacion_adulto_mayor();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_busqueda_informacion_adulto_mayor, container, false);
    }
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        ListaG = getView().findViewById(R.id.lista_adulto_mayor);
        busqueda = getView().findViewById(R.id.searchAdultoMayor);
        contador = getView().findViewById(R.id.nResultados);
        operador = OperacionesBaseDatos.obtenerInstancia(getContext());
        ArrayList<AdultoMayor> Adultos = operador.LeerTablaAdultoMayor();
        if (Adultos.size() != 1){
            contador.setText(Adultos.size()+" Resultados");
        }else{
            contador.setText(Adultos.size()+" Resultado");

        }
        final AdaptadorInformacionAdultoMayor miLista = new AdaptadorInformacionAdultoMayor(Adultos,getContext());
        ListaG.setAdapter(miLista);

        ListaG.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle packet = new Bundle();
                packet.putSerializable("AdultoMayor",(AdultoMayor) parent.getItemAtPosition(position));
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.contenedor, InformacionAdultoMayor.newInstance(packet));
                ft.addToBackStack(null);
                ft.commit();
            }
        });
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
    }

}
