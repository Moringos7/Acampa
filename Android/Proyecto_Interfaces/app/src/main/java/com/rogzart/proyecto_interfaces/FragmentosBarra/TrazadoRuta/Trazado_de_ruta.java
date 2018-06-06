package com.rogzart.proyecto_interfaces.FragmentosBarra.TrazadoRuta;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.rogzart.proyecto_interfaces.MapsActivity;
import com.rogzart.proyecto_interfaces.Modelo.AdultoMayor;
import com.rogzart.proyecto_interfaces.Modelo.Mapa;
import com.rogzart.proyecto_interfaces.Modelo.TipoEvento;
import com.rogzart.proyecto_interfaces.Modelo.Ubicacion;
import com.rogzart.proyecto_interfaces.R;
import com.rogzart.proyecto_interfaces.sqlite.OperacionesBaseDatos;

import java.util.ArrayList;


public class Trazado_de_ruta extends Fragment {
    private Button mapa;
    private ListView ListaG;
    private OperacionesBaseDatos operador = OperacionesBaseDatos.obtenerInstancia(getContext());
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.f_trazado_de_ruta, container, false);
        return view;
    }

    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        mapa = (Button) getView().findViewById(R.id.BMapa);
        ListaG = getView().findViewById(R.id.idLista);


        Ubicacion y = new Ubicacion();

        ArrayList<Mapa> lugares = operador.obtenerUbicacionesyAdultosMayores();
        AdaptadorLugares miLista = new AdaptadorLugares(lugares,getContext());
        ListaG.setAdapter(miLista);

        mapa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ArrayList<Mapa> mapasSeleccionados = new ArrayList<Mapa>();
                    Adapter adapter = (Adapter)ListaG.getAdapter();
                    Mapa mapa;
                    for(int i = 0; i<adapter.getCount(); i++){
                        mapa = (Mapa) adapter.getItem(i);
                        //Toast.makeText(getContext(), ""+adultoMayor.getNombre()+""+adultoMayor.getCheck(), Toast.LENGTH_SHORT).show();
                        if(mapa.getCheck()){
                            mapasSeleccionados.add(mapa);
                        }
                    }
                    Toast.makeText(getContext(), ""+mapasSeleccionados.size(), Toast.LENGTH_SHORT).show();

                    Intent Imapa = new Intent(getActivity(), MapsActivity.class);
                    Imapa.putExtra("Lista",mapasSeleccionados);
                    getActivity().startActivity(Imapa);
                }
            }
        );
    }
}

