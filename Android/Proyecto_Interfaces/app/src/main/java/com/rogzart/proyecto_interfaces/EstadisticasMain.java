package com.rogzart.proyecto_interfaces;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
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
import com.rogzart.proyecto_interfaces.sqlite.OperacionesBaseDatos;

import java.util.ArrayList;

public class EstadisticasMain extends AppCompatActivity {

    private static String TAG = "EstadisticasMain";
    private float[] yData = {88.5f, 11.5f};
    private String[] xData = {"Entregadas", "No Entregadas"};
    PieChart pieChart,piechart2;
    private OperacionesBaseDatos operador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        operador = OperacionesBaseDatos.obtenerInstancia(getApplicationContext());
        setContentView(R.layout.activity_estadisticas_main);
        //Primera PieChart
        int Asignaciones = operador.promedioVoluntariosMes("06", "2018");
        int Total = operador.contarAdultoMayor();
        //Numero de asignaciones al mes
        int AsignacionesMes = operador.asignacionesMes("06", "2018");

        //Usuarios
        int usuarios = operador.numeroUsuarios();

        float PAsignacionesM = ((AsignacionesMes/usuarios)*100);
        float UsuariosFuera= ((100-PAsignacionesM));

        float PAsignaciones = ((Asignaciones / Total) * 100);
        float PFaltantes = (100 - PAsignaciones);
        Log.d(TAG, "onCreate: starting to create chart");

        pieChart = (PieChart) findViewById(R.id.Grafica1);
        pieChart.setRotationEnabled(true);
        pieChart.setUsePercentValues(true);
        piechart2=(PieChart) findViewById(R.id.Grafica2);
        piechart2.setRotationEnabled(true);
        piechart2.setUsePercentValues(true);
        //pieChart.setHoleColor(Color.BLUE);
        //pieChart.setCenterTextColor(Color.BLACK);
        pieChart.setHoleRadius(40f);
        pieChart.animateXY(1500, 1500);
        piechart2.setHoleRadius(40f);
        piechart2.animateXY(1500,1500);
        //pieChart.setTransparentCircleAlpha(0);
        //pieChart.setCenterText("Despensas");
        //pieChart.setCenterTextSize(10);
        // pieChart.setDrawEntryLabels(true);
        //pieChart.setEntryLabelTextSize(20);
        //More options just check out the documentation!

        addDataSet1(PAsignaciones,PFaltantes);
        addDataSet2(PAsignacionesM,UsuariosFuera);


    }
    private void addDataSet1(float asignaciones, float totales) {
        Log.d(TAG, "addDataSet started");
            ArrayList<PieEntry> yEntrys = new ArrayList<PieEntry>();
        yEntrys.add(new PieEntry(asignaciones,0));
        yEntrys.add(new PieEntry(totales,1));


        ArrayList<String> xEntrys = new ArrayList<String>();
        xEntrys.add("Despensas entregadas");
        xEntrys.add("Despensas no entregadas ");


        //create the data set
        PieDataSet pieDataSet1 = new PieDataSet(yEntrys, "Despensas Entregadas");

        //add colors to dataset
        ArrayList<Integer> colors1 = new ArrayList<>();
        colors1.add(Color.YELLOW);
        colors1.add(Color.RED);
        pieDataSet1.setSliceSpace(3f);
        pieDataSet1.setColors(colors1);


        //add legend to chart
        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        //create pie data object
        PieData data = new PieData(pieDataSet1);
        pieChart.setData(data);
        pieChart.highlightValues(null);
        pieChart.invalidate();

    }
    private void addDataSet2(float asignacionesM, float usuarios) {
        Log.d(TAG, "addDataSet started");
        ArrayList<PieEntry> valy = new ArrayList<PieEntry>();
        valy.add(new PieEntry(asignacionesM,0));
        valy.add(new PieEntry(usuarios,1));


        ArrayList<String> valx = new ArrayList<String>();
        valx.add("Usuarios con Asignación");
        valx.add("Usuarios No Asignados ");


        //create the data set
        PieDataSet pieDataSet2 = new PieDataSet(valy, "Asignaciones realizadas");

        //add colors to dataset
        ArrayList<Integer> colors2 = new ArrayList<>();
        colors2.add(Color.GREEN);
        colors2.add(Color.RED);
        pieDataSet2.setSliceSpace(3f);
        pieDataSet2.setColors(colors2);


        //add legend to chart
        Legend legend2 = piechart2.getLegend();
        legend2.setForm(Legend.LegendForm.CIRCLE);
        legend2.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        //create pie data object
        PieData data2 = new PieData(pieDataSet2);
        piechart2.setData(data2);
        piechart2.highlightValues(null);
        piechart2.invalidate();

    }
}
