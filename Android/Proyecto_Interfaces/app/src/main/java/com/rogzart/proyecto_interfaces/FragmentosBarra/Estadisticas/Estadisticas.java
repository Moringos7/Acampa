package com.rogzart.proyecto_interfaces.FragmentosBarra.Estadisticas;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.rogzart.proyecto_interfaces.Barra_desplegable;
import com.rogzart.proyecto_interfaces.R;
import com.rogzart.proyecto_interfaces.sqlite.OperacionesBaseDatos;

import java.util.Calendar;


public class Estadisticas extends Fragment {
    private OperacionesBaseDatos operador;
    private AlertDialog.Builder AlertaEvento;
    Button btnMensuales,btnSemestrales;



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

        operador = OperacionesBaseDatos.obtenerInstancia(getContext());



        btnMensuales = (Button) getView().findViewById(R.id.Boton_grafica);
        btnSemestrales= (Button) getView().findViewById(R.id.Boton_Semestrales);


        btnMensuales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean existeServicio= operador.verificarEvento(generarFecha());
               // if(!existeServicio ) {
                    configurarDialogs();
                    //Toast.makeText(getContext(), "No hay datos de Servicio, se activarán las estadisticas mensuales un dia despues del evento", Toast.LENGTH_SHORT).show();
                //}else{
                    //Toast.makeText(getContext(), "Fecha"+generarFecha(), Toast.LENGTH_SHORT).show();
                    Intent mensuales = new Intent(getActivity(), EstadisticasMain.class);
                    getActivity().startActivity(mensuales);
                //}

            }
        });
        btnSemestrales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean existeConvivio= operador.verificaConvivio(generarFechaConvivio());
                //if(!existeConvivio ) {
                    configurarDialogs();
                    //Toast.makeText(getContext(), "Fecha"+generarFechaConvivio(), Toast.LENGTH_SHORT).show();
                    //Toast.makeText(getContext(), "No hay datos de Convivio, se activarán las estadisticas mensuales un dia despues del evento", Toast.LENGTH_SHORT).show();
                //}else{

                    Intent semestrales = new Intent(getActivity(), Estadisticas_Semestrales.class);
                    getActivity().startActivity(semestrales);
                //}


            }
        });

        operador = OperacionesBaseDatos.obtenerInstancia(getContext());
        String x = operador.substring();
        //Toast.makeText(getContext(), ""+x, Toast.LENGTH_SHORT).show();
        ///

        //Estadistica Mensual 1

        // Despensas entregadas mensuales
        int Asignaciones = operador.promedioVoluntariosMes("06", "2018");
       // Toast.makeText(getContext(), ""+Asignaciones, Toast.LENGTH_SHORT).show();
        //Despensas totales mensuales
        int Total = operador.contarAdultoMayor();
        //Toast.makeText(getContext(), ""+Total, Toast.LENGTH_SHORT).show();

        //Estadistica Mensual 2

        //Numero de asignaciones al mes
        int AsignacionesMes = operador.asignacionesMes("06", "2018");
        //Toast.makeText(getContext(), "Asignaciones Mes"+AsignacionesMes, Toast.LENGTH_SHORT).show();

        //Usuarios
        int usuarios = operador.numeroUsuarios();
        //Toast.makeText(getContext(), "Usuarios app: " + usuarios, Toast.LENGTH_SHORT).show();

        //Estadistica Mensual 3

        //Toast.makeText(getContext(), "Usuarios semestral: " + Usuarios.size(), Toast.LENGTH_SHORT).show();


    }

    private void configurarDialogs() {
        AlertaEvento = new AlertDialog.Builder(getContext());
        AlertaEvento.setTitle("No hay datos de Servicio");
        AlertaEvento.setMessage("Las estadisticas mensuelas serán visibles al dia siguiente del programado para el servicio");
        AlertaEvento.setCancelable(false);
        AlertaEvento.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {

                aceptar();
            }
        });
            }

        private void aceptar(){
            getActivity().finish();
            Intent intent = new Intent(getContext(),Barra_desplegable.class);
            startActivityForResult(intent, 0);
        }


    private String generarFecha() {
        String Fecha;
        Calendar c = Calendar.getInstance();
        int Dia = c.get(Calendar.DAY_OF_MONTH)+1;
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
    private String generarFechaConvivio() {
        String Fecha;
        Calendar c = Calendar.getInstance();
        int Dia = c.get(Calendar.DAY_OF_MONTH)+1;
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


