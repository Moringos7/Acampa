package com.rogzart.proyecto_interfaces.FragmentosBarra.Estadisticas;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.rogzart.proyecto_interfaces.FragmentosBarra.Estadisticas.Estadisticas;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.rogzart.proyecto_interfaces.Modelo.Usuario;
import com.rogzart.proyecto_interfaces.R;
import com.rogzart.proyecto_interfaces.sqlite.OperacionesBaseDatos;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EstadisticasMain extends AppCompatActivity {

    private static String TAG = "EstadisticasMain";
    private float PAsignacionesM;
    private int manada,tropa,comunidad,clan,dirigente,civil;
    private float UsuariosFuera;
    private int PAsignaciones,PFaltantes;
    private String string=generarFecha();
    private String  []datos=string.split("-");
    private String anio,mes,dia;
    BarChart barChart1;
    Description description1,description2,description3;

    PieChart pieChart,piechart2, piechart3;
    private OperacionesBaseDatos operador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        anio=datos[0];
        mes=datos[1];
        dia=datos[2];
        operador = OperacionesBaseDatos.obtenerInstancia(getApplicationContext());
        setContentView(R.layout.activity_estadisticas_main);
        //Primera PieChart
        int Asignaciones = operador.promedioVoluntariosMes(mes, anio);

        //Toast.makeText(getApplicationContext(), "Asignaciones: "+Asignaciones, Toast.LENGTH_SHORT).show();
        int Total = operador.contarAdultoMayor();

        //Toast.makeText(getApplicationContext(), "Total: "+Total, Toast.LENGTH_SHORT).show();
        double y= ((float)(Asignaciones / Total));

        PAsignaciones = (Asignaciones);
        //Toast.makeText(getApplicationContext(), "PAsignaciones: "+PAsignaciones, Toast.LENGTH_SHORT).show();
        PFaltantes = ( Total-Asignaciones);
        //Toast.makeText(getApplicationContext(), "PFaltantes: : "+PFaltantes, Toast.LENGTH_SHORT).show();
        //Segunda PieChart
        double AsignacionesMes = operador.asignacionesMes(mes, anio);
        double usuarios = operador.numeroUsuarios();
        double x = ((float)(AsignacionesMes/usuarios));
        PAsignacionesM = (float) ((x)*100);

        UsuariosFuera= (100 - PAsignacionesM);

        ArrayList<Usuario> Usuarios = new ArrayList<Usuario>();
        manada = 0;
        tropa = 0;
        comunidad = 0;
        clan = 0;
        civil = 0;
        Usuarios = operador.usuariosAsignacion(mes, anio);
        for (int i = 0; i<Usuarios.size(); i++){
            int datos = Usuarios.get(i).getFkSeccion();
            switch (datos){
                case 1:
                    manada++;
                    break;
                case 2:
                    tropa++;
                    break;
                case 3:
                    comunidad++;
                    break;
                case 4:
                    clan++;
                    break;
                case 5:
                    dirigente++;
                    break;
                case 6:
                    civil++;
                    break;
            }
        }
        // Toast.makeText(getApplicationContext(), "Usuarios mensual: " + Usuarios.size(), Toast.LENGTH_SHORT).show();






        Log.d(TAG, "onCreate: starting to create chart");

        pieChart = (PieChart) findViewById(R.id.Grafica1);
        piechart2=(PieChart) findViewById(R.id.Grafica2);
        piechart3=(PieChart) findViewById(R.id.Grafica3);
        description1= new Description();
        description2= new Description();
        description3= new Description();
        description2.setText("Asitencia General");
        description3.setText("Asistencia Secciones");
        description1.setText("Entregadas");
        pieChart.setRotationEnabled(true);
        pieChart.setUsePercentValues(false);
        piechart2.setRotationEnabled(true);
        piechart2.setUsePercentValues(false);
        piechart3.setRotationEnabled(true);
        piechart3.setUsePercentValues(false);
        // pieChart.setHoleColor(Color.BLUE);
        pieChart.setCenterTextColor(Color.BLACK);
        pieChart.setHoleRadius(45f);
        pieChart.animateXY(1500, 1500);
        piechart2.setHoleRadius(45f);
        piechart2.animateXY(1500, 1500);
        piechart3.setCenterTextColor(Color.BLACK);
        piechart2.setCenterTextColor(Color.BLACK);
        piechart3.setHoleRadius(45f);
        piechart3.animateXY(1500, 1500);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setDrawEntryLabels(false);
        pieChart.setDescription(description1);
        piechart2.setDescription(description2);
        piechart3.setDescription(description3);
        piechart2.setDrawEntryLabels(false);
        piechart3.setDrawEntryLabels(false);
        pieChart.setCenterText("Despensas");
        piechart2.setTransparentCircleAlpha(0);
        piechart2.setCenterText("Usuarios %");
        piechart3.setTransparentCircleAlpha(0);
        piechart3.setCenterText("Personas");
        pieChart.setCenterTextSize(20);
        piechart2.setCenterTextSize(20);
        piechart3.setCenterTextSize(20);
        //More options just check out the documentation!
        // Toast.makeText(getApplicationContext(), ""+PAsignacionesM, Toast.LENGTH_SHORT).show();
        //Toast.makeText(getApplicationContext(), ""+UsuariosFuera, Toast.LENGTH_SHORT).show();

        addDataSet1(PAsignaciones,PFaltantes);
        addDataSet2(PAsignacionesM,UsuariosFuera);
        addDataSet3(manada,tropa,comunidad,clan,dirigente,civil);


    }

    private void addDataSet1(int asignaciones, int totales) {
        Log.d(TAG, "addDataSet started");

        List<PieEntry> entries = new ArrayList<>();

        entries.add(new PieEntry(asignaciones, "Entregadas"));
        entries.add(new PieEntry(totales,"No Entregadas"));
        PieDataSet set = new PieDataSet(entries, null);
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.GREEN);
        colors.add(Color.LTGRAY);

        //create pie data object
        PieData data1 = new PieData(set);
        set.setColors(colors);
        pieChart.setData(data1);
        pieChart.highlightValues(null);
        pieChart.invalidate();

    }
    private void addDataSet2(float asignacionesM, float usuarios) {
        Log.d(TAG, "addDataSet started");

        List<PieEntry> entries = new ArrayList<>();

        entries.add(new PieEntry(asignacionesM, "Asistentes"));
        entries.add(new PieEntry(usuarios,"No asistentes"));
        PieDataSet set = new PieDataSet(entries, null);
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.GREEN);
        colors.add(Color.LTGRAY);

        //create pie data object
        PieData data1 = new PieData(set);
        set.setColors(colors);
        piechart2.setData(data1);
        piechart2.highlightValues(null);
        piechart2.invalidate();

    }
    private void addDataSet3(int seccion1,int seccion2, int seccion3, int seccion4, int seccion5, int seccion6) {
        Log.d(TAG, "addDataSet started");

        List<PieEntry> entries = new ArrayList<>();
        ArrayList<Integer> colors = new ArrayList<>();
        /*entries.add(new PieEntry(seccion1, "Manada"));
        entries.add(new PieEntry(seccion2,"Tropa"));
        entries.add(new PieEntry(seccion3,"comunidad"));
        entries.add(new PieEntry(seccion4,"clan"));
        entries.add(new PieEntry(seccion5,"dirigente"));
        entries.add(new PieEntry(seccion6,"civil"));


        colors.add(Color.YELLOW);
        colors.add(Color.GREEN);
        colors.add(Color.BLUE);
        colors.add(Color.RED);
        colors.add(Color.BLUE);
        colors.add(Color.GRAY);
        */
        if(seccion1>0.00){
            entries.add(new PieEntry(seccion1, "Manada"));
            colors.add(Color.YELLOW);
        }
        if(seccion2>0.00){
            entries.add(new PieEntry(seccion2,"Tropa"));
            colors.add(Color.GREEN);
        }
        if(seccion3>0.00){
            entries.add(new PieEntry(seccion3,"Comunidad"));
            colors.add(Color.BLUE);
        }
        if (seccion4>0.00){
            entries.add(new PieEntry(seccion4,"Clan"));
            colors.add(Color.RED);
        }
        if(seccion5>0.00){
            entries.add(new PieEntry(seccion5,"Dirigente"));
            colors.add(Color.CYAN);
        }
        if(seccion6>0.00){
            entries.add(new PieEntry(seccion6,"Civil"));
            colors.add(Color.GRAY);
        }
        PieDataSet set = new PieDataSet(entries, null);

        //create pie data object
        PieData data1 = new PieData(set);
        set.setColors(colors);
        piechart3.setData(data1);
        piechart3.highlightValues(null);
        piechart3.invalidate();

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
