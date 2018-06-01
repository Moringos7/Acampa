package com.rogzart.proyecto_interfaces.FragmentosBarra.AsignacionAdultosMayores.Coordinador;

import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.rogzart.proyecto_interfaces.Barra_desplegable;
import com.rogzart.proyecto_interfaces.FragmentosBarra.Eventos.Eventos;
import com.rogzart.proyecto_interfaces.Modelo.Conexion;
import com.rogzart.proyecto_interfaces.Modelo.Usuario;
import com.rogzart.proyecto_interfaces.Modelo.UsuarioAsignacion;
import com.rogzart.proyecto_interfaces.R;
import com.rogzart.proyecto_interfaces.sqlite.ActualizacionBaseDatos;
import com.rogzart.proyecto_interfaces.sqlite.OperacionesBaseDatos;

import java.util.ArrayList;
import java.util.Calendar;


public class AsignacionAdultoMayorCoordinador extends Fragment {

    private AlertDialog.Builder AlertaEvento;
    private boolean EventoDisponible;
    private OperacionesBaseDatos operador;
    private Calendar c = Calendar.getInstance();
    private String FechaActual;
    private ListView listaG;
    public  AsignacionAdultoMayorCoordinador(){

    }
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        configurarDialogs();
        operador = OperacionesBaseDatos.obtenerInstancia(getContext());

        FechaActual = generarFecha();
        EventoDisponible = operador.verificarEvento(FechaActual);
        if(!EventoDisponible){
            AlertaEvento.show();
        }else{
            cargarLista();
        }
        Button btn = getView().findViewById(R.id.btntemporal);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operador.EliminarDatosTabla("asignacion");
                new ActualizacionBaseDatos(getContext()).ActualizacionAsignacion(getContext());
                cargarLista();
            }
        });
    }

    public static AsignacionAdultoMayorCoordinador newInstance() {
        AsignacionAdultoMayorCoordinador fragment = new AsignacionAdultoMayorCoordinador();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_asignacion_adulto_mayor_coordinador, container, false);

    }


    private void configurarDialogs(){
        AlertaEvento = new AlertDialog.Builder(getContext());
        AlertaEvento.setTitle("Servicio No agendado");
        AlertaEvento.setMessage("Necesita agendar un servicio o convivio antes de Asignar Adultos Mayores, ¿Desea agendar uno?");
        AlertaEvento.setCancelable(false);
        AlertaEvento.setPositiveButton("Agendar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                agendar();
            }
        });
        AlertaEvento.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                cancelar();
            }
        });
    }
    private void agendar(){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.contenedor, Eventos.newInstance());
        ft.addToBackStack(null);
        ft.commit();
    }
    private void cancelar(){
        getActivity().finish();
        Intent intent = new Intent(getContext(),Barra_desplegable.class);
        startActivityForResult(intent, 0);
    }
    private String generarFecha(){
        String Fecha;
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
    private void cargarLista(){

        listaG = getView().findViewById(R.id.listaAsigancionCoordinador);
        ArrayList<UsuarioAsignacion> arrayList = operador.LeerUsuariosAsignacion(FechaActual);
        ListaAdaptadorAsignacionAdultoMayor miLista = new ListaAdaptadorAsignacionAdultoMayor(arrayList, getContext());
        listaG.setAdapter(miLista);
    }
}
