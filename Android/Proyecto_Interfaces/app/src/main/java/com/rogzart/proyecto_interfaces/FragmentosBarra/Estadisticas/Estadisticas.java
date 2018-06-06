package com.rogzart.proyecto_interfaces.FragmentosBarra.Estadisticas;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.juang.jplot.PlotPastelito;
import com.rogzart.proyecto_interfaces.EstadisticasMain;
import com.rogzart.proyecto_interfaces.InterfacesLogin.Inicio;
import com.rogzart.proyecto_interfaces.InterfacesLogin.signup;
import com.rogzart.proyecto_interfaces.MapsActivity;
import com.rogzart.proyecto_interfaces.Modelo.Usuario;
import com.rogzart.proyecto_interfaces.R;
import com.rogzart.proyecto_interfaces.sqlite.OperacionesBaseDatos;

import java.util.ArrayList;
import java.util.Calendar;


public class Estadisticas extends Fragment {
    private OperacionesBaseDatos operador;
    Button btngrafica;
    public Estadisticas() {

    }

    public static Estadisticas newInstance() {
        Estadisticas fragment = new Estadisticas();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        BarChart chart = new BarChart(getContext());
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.f_estadisticas, container, false);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        btngrafica = (Button) getView().findViewById(R.id.Boton_grafica);
        btngrafica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent estadisticas = new Intent(getActivity(), EstadisticasMain.class);
                getActivity().startActivity(estadisticas);
            }
        });

        operador = OperacionesBaseDatos.obtenerInstancia(getContext());
        String x = operador.substring();
        //Toast.makeText(getContext(), ""+x, Toast.LENGTH_SHORT).show();
        ///

        //Estadistica Mensual 1

        // Despensas entregadas mensuales
        int Asignaciones = operador.promedioVoluntariosMes("06", "2018");
        //Toast.makeText(getContext(), ""+Asignaciones, Toast.LENGTH_SHORT).show();
        //Despensas totales mensuales
        int Total = operador.contarAdultoMayor();
        //Toast.makeText(getContext(), ""+Total, Toast.LENGTH_SHORT).show();

        //Estadistica Mensual 2

        //Numero de asignaciones al mes
        int AsignacionesMes = operador.asignacionesMes("06", "2018");
        Toast.makeText(getContext(), "Asignaciones Mes"+AsignacionesMes, Toast.LENGTH_SHORT).show();

        //Usuarios
        int usuarios = operador.numeroUsuarios();
        Toast.makeText(getContext(), "Usuarios app: " + usuarios, Toast.LENGTH_SHORT).show();

        //Estadistica Mensual 3

        ArrayList<Usuario> Usuarios = new ArrayList<Usuario>();
        Usuarios = operador.usuariosAsignacion("06", "2018");
       // Toast.makeText(getContext(), "Usuarios mensual: " + Usuarios.size(), Toast.LENGTH_SHORT).show();

        //Semestrales
        //Select Usuarios
        Usuarios = operador.usuariosActivos();
        //Toast.makeText(getContext(), "Usuarios semestral: " + Usuarios.size(), Toast.LENGTH_SHORT).show();


    }

    private String generarFecha() {
        String Fecha;
        Calendar c = Calendar.getInstance();
        int Dia = c.get(Calendar.DAY_OF_MONTH);
        int Mes = c.get(Calendar.MONTH) + 1;
        int Anio = c.get(Calendar.YEAR);
        String decenaD = "";
        String decenaM = "";
        if (Mes < 10) {
            decenaM = "0";
        }
        if (Dia < 10) {
            decenaD = "0";
        }
        Fecha = String.valueOf(Anio) + "-" + decenaM + String.valueOf(Mes) + "-" + decenaD + String.valueOf(Dia);
        return Fecha;
    }
}


