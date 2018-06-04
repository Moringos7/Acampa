package com.rogzart.proyecto_interfaces.FragmentosBarra.Eventos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.rogzart.proyecto_interfaces.Modelo.Usuario;
import com.rogzart.proyecto_interfaces.R;
import com.rogzart.proyecto_interfaces.sqlite.EstructuraBaseDatos;
import com.rogzart.proyecto_interfaces.sqlite.OperacionesBaseDatos;

import java.util.ArrayList;
import java.util.Calendar;


public class Eventos extends Fragment {
    private OperacionesBaseDatos operador;
    public  Eventos(){

    }

    public static Eventos newInstance() {
        Eventos fragment = new Eventos();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_eventos, container, false);

    }

    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        ///
        operador = OperacionesBaseDatos.obtenerInstancia(getContext());
        String x = operador.substring();
        //Toast.makeText(getContext(), ""+x, Toast.LENGTH_SHORT).show();
        ///

        //Estadistica Mensual 1

        // Despensas entregadas mensuales
        int Asignaciones = operador.promedioVoluntariosMes("06","2018");
        //Toast.makeText(getContext(), ""+Asignaciones, Toast.LENGTH_SHORT).show();
        //Despensas totales mensuales
        int Total = operador.contarAdultoMayor();
        //Toast.makeText(getContext(), ""+Total, Toast.LENGTH_SHORT).show();

        //Estadistica Mensual 2

        //Numero de asignaciones al mes
        int AsignacionesMes = operador.asignacionesMes("06","2018");
        //Toast.makeText(getContext(), ""+AsignacionesMes, Toast.LENGTH_SHORT).show();

        //Usuarios
        int usuarios = operador.numeroUsuarios();
        Toast.makeText(getContext(), ""+usuarios, Toast.LENGTH_SHORT).show();

        //Estadistica Mensual 3

        ArrayList<Usuario> Usuarios = new ArrayList<Usuario>();
        Usuarios = operador.usuariosAsignacion("06","2018");
        Toast.makeText(getContext(), ""+Usuarios.size(), Toast.LENGTH_SHORT).show();

        //Semestrales
        //Select Usuarios
        Usuarios = operador.usuariosActivos();
        Toast.makeText(getContext(), ""+Usuarios.size(), Toast.LENGTH_SHORT).show();





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
