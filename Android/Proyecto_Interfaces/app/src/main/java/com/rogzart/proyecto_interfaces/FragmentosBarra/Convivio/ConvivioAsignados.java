package com.rogzart.proyecto_interfaces.FragmentosBarra.Convivio;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.rogzart.proyecto_interfaces.FragmentosBarra.AsignacionAdultosMayores.Usuario.ListaAdaptadorAsignados;
import com.rogzart.proyecto_interfaces.FragmentosBarra.InformacionAdultoMayor.InformacionAdultoMayor;
import com.rogzart.proyecto_interfaces.MapsActivity;
import com.rogzart.proyecto_interfaces.Modelo.AdultoMayor;
import com.rogzart.proyecto_interfaces.Modelo.Mapa;
import com.rogzart.proyecto_interfaces.Modelo.Ubicacion;
import com.rogzart.proyecto_interfaces.R;
import com.rogzart.proyecto_interfaces.Singleton.LogUser;
import com.rogzart.proyecto_interfaces.sqlite.OperacionesBaseDatos;

import java.util.ArrayList;
import java.util.Calendar;

public class ConvivioAsignados extends Fragment implements View.OnClickListener {


    private ListView ListaG;
    private OperacionesBaseDatos operador;
    private LogUser ControlUser;
    private TextView NumeroAdultos;
    private Button BtnRuta;
    private ArrayList<AdultoMayor> MisAsignados;

    public ConvivioAsignados() {

    }
    public static ConvivioAsignados newInstance() {
        ConvivioAsignados fragment = new ConvivioAsignados();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_convivio_asignados, container, false);
    }
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

        operador = OperacionesBaseDatos.obtenerInstancia(getContext());
        ControlUser = LogUser.obtenerInstancia(getContext());
        ListaG = getView().findViewById(R.id.listConvivioAsignados);
        NumeroAdultos = getView().findViewById(R.id.numeroAdultos);
        BtnRuta = getView().findViewById(R.id.btnTrazaRutaConvivio);
        BtnRuta.setOnClickListener(this);
        MisAsignados = operador.obtenerAdultosMayoresConvivioAsignados(ControlUser.getScouter(),generarFechaActual());
        ListaAdaptadorAsignados miLista = new ListaAdaptadorAsignados(MisAsignados,getContext());
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

        NumeroAdultos.setText(Integer.toString(MisAsignados.size()));

    }
    private String generarFechaActual(){
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


    @Override
    public void onClick(View v) {
        ArrayList<Mapa> mapas = new ArrayList<Mapa>();
        Mapa miMapa;
        Ubicacion ubicacion;
        for(int i = 0; i<MisAsignados.size();i++){
            ubicacion = operador.obtenerUdicacionPorAdultoMayor(MisAsignados.get(i));
            miMapa = new Mapa(MisAsignados.get(i),ubicacion);
            mapas.add(miMapa);
        }

        Intent Imapa = new Intent(getActivity(), MapsActivity.class);
        Imapa.putExtra("Lista",mapas);
        getActivity().startActivity(Imapa);
    }
}
