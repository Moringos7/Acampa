package com.rogzart.proyecto_interfaces.FragmentosBarra.Estadisticas;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.rogzart.proyecto_interfaces.Barra_desplegable;
import com.rogzart.proyecto_interfaces.Modelo.Usuario;
import com.rogzart.proyecto_interfaces.R;
import com.rogzart.proyecto_interfaces.sqlite.OperacionesBaseDatos;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Formatter;
import java.util.List;

public class Estadisticas_Semestrales extends AppCompatActivity{
    private static String TAG = "EstadisticasMain";
    PieChart pieChart, piechart3;
    BarChart barchart2;
    Formatter fmt = new Formatter();
    Formatter fmt2 = new Formatter();
    Formatter fmt3 = new Formatter();
    Formatter fmt4 = new Formatter();
    Formatter fmt5 = new Formatter();
    Formatter fmt6 = new Formatter();

    private float PAsignacionesS;
    private int RAsignacionesTotal;
    TextView DatoMes1, DatoMes2, DatoMes3, DatoMes4, DatoMes5, DatoMes6, Mes1, Mes2, Mes3, Mes4, Mes5, Mes6, DatoTotal, Total1, PeriodoMes1, PeriodoMes2, Periodoaño;

    private float Asistentes1, Asistentes2, Asistentes3, Asistentes4, Asistentes5, Asistentes6;
    private float FaltantesEnero, FaltantesFebrero, FaltantesMarzo, FaltantesAbril, FaltantesMayo, FaltantesJunio;
    private float NoAcudieron1, NoAcudieron2, NoAcudieron3, NoAcudieron4, NoAcudieron5, NoAcudieron6;
    private int manada, tropa, comunidad, clan, dirigente, civil;
    private int anio1,anio2,anio3,anio4,anio5,anio6;
    private String string = generarFecha();
    private String label1,label2,label3,label4,label5,label6;
    private String mes1,mes2,mes3,mes4,mes5,mes6;
    private String[] datos = string.split("-");
    private String Fanio1,Fanio2,Fanio3,Fanio4,Fanio5,Fanio6;
    private int TMes1,TMes2,TMes3,TMes4,TMes5,TMes6;

    private String anio, mes, dia, Periodomes1, Periodomes6, PrimerMesDato;
    private OperacionesBaseDatos operador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas__semestrales);
        Description description = new Description();
        PeriodoMes1 = (TextView) findViewById(R.id.TPeriodo1);
        PeriodoMes2 = (TextView) findViewById(R.id.TPeriodo2);
        Periodoaño = (TextView) findViewById(R.id.TAño);
        operador = OperacionesBaseDatos.obtenerInstancia(getApplicationContext());
        anio = datos[0];
        Periodomes1 = datos[1];
        //Toast.makeText(this, "Mes: "+Periodomes1, Toast.LENGTH_SHORT).show();
        int anioT= Integer.parseInt(datos[0]);
        if (Periodomes1.compareTo("01") == 0) {
            PeriodoMes1.setText("Enero ");

            PeriodoMes2.setText("Agosto -");
            anio1=anioT;
            anio2=(anioT-1);
            anio3=(anioT-1);
            anio4=(anioT-1);
            anio5=(anioT-1);
            anio6=(anioT-1);
            int Nanio2=anioT--;
            String anio2 = String.valueOf(Nanio2);
            String valor=anio+"-"+anio2;
            Periodoaño.setText(valor);
        }
        if (Periodomes1.compareTo("02") == 0) {
            PeriodoMes1.setText("Febrero");

            PeriodoMes2.setText("Septiembre -");
            anio1=anioT;
            anio2=anioT;
            anio3=(anioT-1);
            anio4=(anioT-1);
            anio5=(anioT-1);
            anio6=(anioT-1);
            int Nanio2=anioT--;
            String anio2 = String.valueOf(Nanio2);
            String valor=anio+"-"+anio2;
            Periodoaño.setText(valor);
        }
        if (Periodomes1.compareTo("03") == 0) {
            PeriodoMes1.setText("Marzo");

            PeriodoMes2.setText("Octubre -");
            anio1=anioT;
            anio2=anioT;
            anio3=anioT;
            anio4=(anioT-1);
            anio5=(anioT-1);
            anio6=(anioT-1);
            int Nanio2=anioT--;
            String anio2 = String.valueOf(Nanio2);
            String valor=anio+"-"+anio2;
            Periodoaño.setText(valor);

        }
        if (Periodomes1.compareTo("04") == 0) {
            PeriodoMes1.setText("Abril");

            PeriodoMes2.setText("Noviembre -");
            anio1=anioT;
            anio2=anioT;
            anio3=anioT;
            anio4=anioT;
            anio5=(anioT-1);
            anio6=(anioT-1);
            int Nanio2=anioT--;
            String anio2 = String.valueOf(Nanio2);
            String valor=anio+"-"+anio2;
            Periodoaño.setText(valor);

        }
        if (Periodomes1.compareTo("05") == 0) {
            PeriodoMes1.setText("Mayo");

            PeriodoMes2.setText("Diciembre -");
            anio1=anioT;
            anio2=anioT;
            anio3=anioT;
            anio4=anioT;
            anio5=anioT;
            anio6=(anioT-1);
            int Nanio2=anioT--;
            String anio2 = String.valueOf(Nanio2);
            String valor=anio+"-"+anio2;
            Periodoaño.setText(valor);
        }
        if (Periodomes1.compareTo("06") == 0) {
            PeriodoMes1.setText("Junio");

            PeriodoMes2.setText("Enero -");
            anio1=anioT;
            anio2=anioT;
            anio3=anioT;
            anio4=anioT;
            anio5=anioT;
            anio6=anioT;
            Periodoaño.setText(anio);

        }
        if (Periodomes1.compareTo("07") == 0) {
            PeriodoMes1.setText("Julio");

            PeriodoMes2.setText("Febrero -");
            anio1=anioT;
            anio2=anioT;
            anio3=anioT;
            anio4=anioT;
            anio5=anioT;
            anio6=anioT;
            Periodoaño.setText(anio);


        }
        if (Periodomes1.compareTo("08") == 0) {
            PeriodoMes1.setText("Agosto");

            PeriodoMes2.setText("Marzo -");
            anio1=anioT;
            anio2=anioT;
            anio3=anioT;
            anio4=anioT;
            anio5=anioT;
            anio6=anioT;
            Periodoaño.setText(anio);


        }
        if (Periodomes1.compareTo("09") == 0) {
            PeriodoMes1.setText("Septiembre");

            PeriodoMes2.setText("Abril -");
            anio1=anioT;
            anio2=anioT;
            anio3=anioT;
            anio4=anioT;
            anio5=anioT;
            anio6=anioT;
            Periodoaño.setText(anio);


        }
        if (Periodomes1.compareTo("10") == 0) {
            PeriodoMes1.setText("Octubre");

            PeriodoMes2.setText("Mayo -");
            anio1=anioT;
            anio2=anioT;
            anio3=anioT;
            anio4=anioT;
            anio5=anioT;
            anio6=anioT;
            Periodoaño.setText(anio);


        }
        if (Periodomes1.compareTo("11") == 0) {
            PeriodoMes1.setText("Noviembre");

            PeriodoMes2.setText("Junio -");
            anio1=anioT;
            anio2=anioT;
            anio3=anioT;
            anio4=anioT;
            anio5=anioT;
            anio6=anioT;
            Periodoaño.setText(anio);


        }
        if (Periodomes1.compareTo("12") == 0) {
            PeriodoMes1.setText("Diciembre");

            PeriodoMes2.setText("Julio -");
            anio1=anioT;
            anio2=anioT;
            anio3=anioT;
            anio4=anioT;
            anio5=anioT;
            anio6=anioT;
            Periodoaño.setText(anio);


        }
        Fanio1= String.valueOf(anio1);
        Fanio2= String.valueOf(anio2);
        Fanio3= String.valueOf(anio3);
        Fanio4= String.valueOf(anio4);
        Fanio5= String.valueOf(anio5);
        Fanio6= String.valueOf(anio6);



        int mesI= Integer.parseInt(datos[1]);

        switch (mesI){
            case 1:
                TMes1=1;
                TMes2=12;
                TMes3=11;
                TMes4=10;
                TMes5=9;
                TMes6=8;
                label1="Enero";
                label2="Diciembre";
                label3="Noviembre";
                label4="Octubre";
                label5="Septiembre";
                label6="Agosto";
                break;
            case 2:
                TMes1=2;
                TMes2=1;
                TMes3=12;
                TMes4=11;
                TMes5=10;
                TMes6=9;
                label1="Febrero";
                label2="Enero";
                label3="Diciembre";
                label4="Noviembre";
                label5="Octubre";
                label6="Septiembre";
                break;
            case 3:
                TMes1=3;
                TMes2=2;
                TMes3=1;
                TMes4=12;
                TMes5=11;
                TMes6=10;
                label1="Marzo";
                label2="Febrero";
                label3="Enero";
                label4="Diciembre";
                label5="Noviembre";
                label6="Octubre";
                break;
            case 4:
                TMes1=4;
                TMes2=3;
                TMes3=2;
                TMes4=1;
                TMes5=12;
                TMes6=11;
                label1="Abril";
                label2="Marzo";
                label3="Febrero";
                label4="Enero";
                label5="Diciembre";
                label6="Noviembre";
                break;
            case 5:
                TMes1=5;
                TMes2=4;
                TMes3=3;
                TMes4=2;
                TMes5=1;
                TMes6=12;
                label1="Mayo";
                label2="Abril";
                label3="Marzo";
                label4="Febrero";
                label5="Enero";
                label6="Diciembre";

                break;
            case 6:
                TMes1=6;
                TMes2=5;
                TMes3=4;
                TMes4=3;
                TMes5=2;
                TMes6=1;
                label1="Junio";
                label2="Mayo";
                label3="Abril";
                label4="Marzo";
                label5="Febrero";
                label6="Enero";
                break;
            case 7:
                TMes1=7;
                TMes2=6;
                TMes3=5;
                TMes4=4;
                TMes5=3;
                TMes6=2;
                label1="Julio";
                label2="Junio";
                label3="Mayo";
                label4="Abril";
                label5="Marzo";
                label6="Febrero";

                break;
            case 8:
                TMes1=8;
                TMes2=7;
                TMes3=6;
                TMes4=5;
                TMes5=4;
                TMes6=3;
                label1="Agosto";
                label2="Julio";
                label3="Junio";
                label4="Mayo";
                label5="Abril";
                label6="Marzo";
                break;
            case 9:
                TMes1=9;
                TMes2=8;
                TMes3=7;
                TMes4=6;
                TMes5=5;
                TMes6=4;
                label1="Septiembre";
                label2="Agosto";
                label3="Julio";
                label4="Junio";
                label5="Mayo";
                label6="Abril";

                break;
            case 10:
                TMes1=10;
                TMes2=9;
                TMes3=8;
                TMes4=7;
                TMes5=6;
                TMes6=5;
                label1="Octubre";
                label2="Septiembre";
                label3="Agosto";
                label4="Julio";
                label5="Junio";
                label6="Mayo";
                break;
            case 11:
                TMes1=11;
                TMes2=10;
                TMes3=9;
                TMes4=8;
                TMes5=7;
                TMes6=6;
                label1="Noviembre";
                label2="Octubre";
                label3="Septiembre";
                label4="Agosto";
                label5="Julio";
                label6="Junio";
                break;
            case 12:
                TMes1=12;
                TMes2=11;
                TMes3=10;
                TMes4=9;
                TMes5=8;
                TMes6=7;
                label1="Diciembre";
                label2="Noviembre";
                label3="Octubre";
                label4="Septiembre";
                label5="Agosto";
                label6="Julio";
                break;
        }
        fmt.format("%02d",TMes1);
        fmt2.format("%02d",TMes2);
        fmt3.format("%02d",TMes3);
        fmt4.format("%02d",TMes4);
        fmt5.format("%02d",TMes5);
        fmt6.format("%02d",TMes6);
        mes1 = String.valueOf(fmt);
        mes2 = String.valueOf(fmt2);
        mes3 = String.valueOf(fmt3);
        mes4 = String.valueOf(fmt4);
        mes5 = String.valueOf(fmt5);
        mes6 = String.valueOf(fmt6);
        //Para Primera Consulta
        int AsignacionesEnero = operador.promedioVoluntariosMes(mes1, Fanio1);
        int AsignacionesFebrero = operador.promedioVoluntariosMes(mes2, Fanio2);
        int AsignacionesMarzo = operador.promedioVoluntariosMes(mes3, Fanio3);
        int AsignacionesAbril = operador.promedioVoluntariosMes(mes4, Fanio4);
        int AsignacionesMayo = operador.promedioVoluntariosMes(mes5, Fanio4);
        int AsignacionesJunio = operador.promedioVoluntariosMes(mes6, Fanio5);
        int SumaEntregada = (AsignacionesEnero + AsignacionesFebrero + AsignacionesMarzo + AsignacionesAbril + AsignacionesMayo + AsignacionesJunio);

        int Total = operador.contarAdultoMayor();
        int AdultosSemestrales = (Total * 6);
        int NoEntregada = (AdultosSemestrales - SumaEntregada);


        /*double DivisionEnero = ((float) (AsignacionesEnero / Total));
        double DivisionFebrero = ((float) (AsignacionesFebrero / Total));
        double DivisionMarzo = ((float) (AsignacionesMarzo / Total));
        double DivisionAbril = ((float) (AsignacionesAbril / Total));
        double DivisionMayo = ((float) (AsignacionesMayo / Total));
        double DivisionJunio = ((float) (AsignacionesJunio / Total));
        */
       /* RAsignacionesEnero = (float) ((DivisionEnero) * 100);
        RAsignacionesFebrero = (float) ((DivisionFebrero) * 100);
        RAsignacionesMarzo = (float) ((DivisionMarzo) * 100);
        RAsignacionesAbril = (float) ((DivisionAbril) * 100);
        RAsignacionesMayo = (float) ((DivisionMayo) * 100);
        RAsignacionesJunio = (float) ((DivisionJunio) * 100);

        FaltantesEnero = (100 - RAsignacionesEnero);
        FaltantesFebrero = (100 - RAsignacionesFebrero);
        FaltantesMarzo = (100 - RAsignacionesMarzo);
        FaltantesAbril = (100 - RAsignacionesAbril);
        FaltantesMayo = (100 - RAsignacionesMayo);
        FaltantesJunio = (100 - RAsignacionesJunio);
        */

        //Para Segunda Consulta
        double Acudieron1 = operador.asignacionesMes(mes1, Fanio1);
        double Acudieron2 = operador.asignacionesMes(mes2, Fanio2);
        double Acudieron3 = operador.asignacionesMes(mes3, Fanio3);
        double Acudieron4 = operador.asignacionesMes(mes4, Fanio4);
        double Acudieron5 = operador.asignacionesMes(mes5, Fanio5);
        double Acudieron6 = operador.asignacionesMes(mes6, Fanio6);
        double usuarios = operador.numeroUsuarios();
        double ADivision1 = ((float) (Acudieron1 / usuarios));
        double ADivision2 = ((float) (Acudieron2 / usuarios));
        double ADivision3 = ((float) (Acudieron3 / usuarios));
        double ADivision4 = ((float) (Acudieron4 / usuarios));
        double ADivision5 = ((float) (Acudieron5 / usuarios));
        double ADivision6 = ((float) (Acudieron6 / usuarios));
        Asistentes1 = (float) ((ADivision1) * 100);
        Asistentes2 = (float) ((ADivision2) * 100);
        Asistentes3 = (float) ((ADivision3) * 100);
        Asistentes4 = (float) ((ADivision4) * 100);
        Asistentes5 = (float) ((ADivision5) * 100);
        Asistentes6 = (float) ((ADivision6) * 100);
        if(Asistentes1 >0.00){
            NoAcudieron1 = (100 - Asistentes1);
        }
        if(Asistentes2 >0.00){
            NoAcudieron1 = (100 - Asistentes2);
        }
        if(Asistentes3 >0.00){
            NoAcudieron1 = (100 - Asistentes3);
        }
        if(Asistentes4 >0.00){
            NoAcudieron1 = (100 - Asistentes4);
        }
        if(Asistentes5 >0.00){
            NoAcudieron1 = (100 - Asistentes5);
        }
        if(Asistentes6 >0.00){
            NoAcudieron1 = (100 - Asistentes6);
        }




        //Para tercer consulta
        ArrayList<Usuario> Usuarios = new ArrayList<Usuario>();
        Usuarios = operador.usuariosActivos();
        manada = 0;
        tropa = 0;
        comunidad = 0;
        clan = 0;
        civil = 0;
        for (int i = 0; i < Usuarios.size(); i++) {
            int datos = Usuarios.get(i).getFkSeccion();
            switch (datos) {
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

        pieChart = (PieChart) findViewById(R.id.Estadisticas_semestrales_grafica1);
        barchart2 = (BarChart) findViewById(R.id.Estadisticas_semestrales_grafica2);
        piechart3 = (PieChart) findViewById(R.id.Estadisticas_semestrales_grafica3);
        DatoMes1 = (TextView) findViewById(R.id.dato_mes1);
        DatoMes2 = (TextView) findViewById(R.id.dato_mes2);
        DatoMes3 = (TextView) findViewById(R.id.dato_mes3);
        DatoMes4 = (TextView) findViewById(R.id.dato_mes4);
        DatoMes5 = (TextView) findViewById(R.id.dato_mes5);
        DatoMes6 = (TextView) findViewById(R.id.dato_mes6);
        Mes1 = (TextView) findViewById(R.id.TMes1);
        Mes2 = (TextView) findViewById(R.id.TMes2);
        Mes3 = (TextView) findViewById(R.id.TMes3);
        Mes4 = (TextView) findViewById(R.id.TMes4);
        Mes5 = (TextView) findViewById(R.id.TMes5);
        Mes6 = (TextView) findViewById(R.id.TMes6);
        Total1 = (TextView) findViewById(R.id.TTotal);
        DatoTotal = (TextView) findViewById(R.id.dato_total);


        pieChart.setRotationEnabled(true);
        pieChart.setUsePercentValues(true);
        piechart3.setRotationEnabled(true);
        piechart3.setUsePercentValues(true);
        // pieChart.setHoleColor(Color.BLUE);
        pieChart.setCenterTextColor(Color.BLACK);
        pieChart.setHoleRadius(45f);
        pieChart.animateXY(1500, 1500);
        barchart2.animateXY(1500, 1500);
        piechart3.setCenterTextColor(Color.BLACK);
        piechart3.setHoleRadius(40f);
        piechart3.animateXY(1500, 1500);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setDrawEntryLabels(false);
        piechart3.setDrawEntryLabels(false);
        pieChart.setCenterText("Despensas");
        piechart3.setTransparentCircleAlpha(0);
        piechart3.setCenterText("Sección");
        pieChart.setCenterTextSize(20);
        piechart3.setCenterTextSize(20);

        DatosGrafica1(SumaEntregada, NoEntregada);
        DatosGrafica2(Asistentes1, Asistentes2, Asistentes3, Asistentes4, Asistentes5, Asistentes6, NoAcudieron1, NoAcudieron2, NoAcudieron3, NoAcudieron4, NoAcudieron5, NoAcudieron6);
        DatosGrafica3(manada, tropa, comunidad, clan, dirigente, civil);

    }


    private void DatosGrafica1(int DivisionTotal, int RAsignacionesTotal) {
        Log.d(TAG, "addDataSet started");

        List<PieEntry> entries = new ArrayList<>();

        entries.add(new PieEntry(DivisionTotal, "Entregadas"));
        entries.add(new PieEntry(RAsignacionesTotal, "No Entregadas"));
        PieDataSet set = new PieDataSet(entries, null);
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.GREEN);
        colors.add(Color.LTGRAY);

        //create pie data object
        PieData data1 = new PieData(set);
        set.setColors(colors);
        set.setHighlightEnabled(true);
        pieChart.setData(data1);
        // pieChart.highlightValues(null);
        pieChart.invalidate();
    }

    private void DatosGrafica2(float asistentes1, float asistentes2, float asistentes3, float asistentes4, float asistentes5, float asistentes6, float noAcudieron1, float noAcudieron2, float noAcudieron3, float noAcudieron4, float noAcudieron5, float noAcudieron6) {
        Log.d(TAG, "addDataSet started");
       // Toast.makeText(this, "NoAsistentesEnero"+noAcudieron1, Toast.LENGTH_SHORT).show();
        ArrayList<BarEntry> grupo1 = new ArrayList<>();

        grupo1.add(new BarEntry(0, asistentes6));
        grupo1.add(new BarEntry(1,asistentes5 ));
        grupo1.add(new BarEntry(2,asistentes4 ));
        grupo1.add(new BarEntry(3, asistentes3));
        grupo1.add(new BarEntry(4, asistentes2));
        grupo1.add(new BarEntry(5, asistentes1));


        ArrayList<BarEntry> grupo2 = new ArrayList<>();

        grupo2.add(new BarEntry(0, noAcudieron6));
        grupo2.add(new BarEntry(1,noAcudieron5 ));
        grupo2.add(new BarEntry(2, noAcudieron4));
        grupo2.add(new BarEntry(3, noAcudieron3));
        grupo2.add(new BarEntry(4, noAcudieron2));
        grupo2.add(new BarEntry(5, noAcudieron1));
        BarDataSet set1 = new BarDataSet(grupo1, "Asistentes");
        BarDataSet set2 = new BarDataSet(grupo2, "No Asistentes");
        XAxis xAxis = barchart2.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setCenterAxisLabels(false);
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(getXAxisValues()));



        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.BLUE);
        colors.add(Color.RED);
        colors.add(Color.YELLOW);
        colors.add(Color.MAGENTA);
        colors.add(Color.CYAN);
        colors.add(Color.GREEN);

        ArrayList<Integer> colors2 = new ArrayList<>();
        colors2.add(Color.GRAY);
        set1.setColors(colors);
        Legend l = barchart2.getLegend();
        l.setFormSize(10f);
        l.setForm(Legend.LegendForm.DEFAULT);
        l.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);
        l.setTextSize(12f);
        l.setTextColor(Color.BLACK);
        l.setXEntrySpace(5f); // set the space between the legend entries on the x-axis
        l.setYEntrySpace(5f);
        //create pie data object
        BarData data1 = new BarData(set1,set2);
        data1.setBarWidth(0.45f);
        barchart2.setData(data1);
        barchart2.groupBars(0f,0.06f,0.02f);
        barchart2.setFitBars(true);
        set2.setColors(colors2);
    }

    private void DatosGrafica3(int manada, int tropa, int comunidad, int clan, int dirigente, int civil) {

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
        if (manada > 0) {
            entries.add(new PieEntry(manada, "Manada"));
            colors.add(Color.YELLOW);
        }
        if (tropa > 0) {
            entries.add(new PieEntry(tropa, "Tropa"));
            colors.add(Color.GREEN);
        }
        if (comunidad > 0) {
            entries.add(new PieEntry(comunidad, "Comunidad"));
            colors.add(Color.BLUE);
        }
        if (clan > 0) {
            entries.add(new PieEntry(clan, "Clan"));
            colors.add(Color.RED);
        }
        if (dirigente > 0) {
            entries.add(new PieEntry(dirigente, "Dirigente"));
            colors.add(Color.CYAN);
        }
        if (civil > 0) {
            entries.add(new PieEntry(civil, "Civil"));
            colors.add(Color.LTGRAY);
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
        int Dia = c.get(Calendar.DAY_OF_MONTH) + 1;
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
    public void volver(View view){
        Intent intent = new Intent(getApplicationContext(), Barra_desplegable.class);
        finish();
        startActivityForResult(intent, 0);
    }
    private ArrayList<String> getXAxisValues(){
        ArrayList<String> labels = new ArrayList<String> ();

        labels.add( label6);
        labels.add( label5);
        labels.add( label4);
        labels.add( label3);
        labels.add( label2);
        labels.add( label1);
        return labels;
    }
}




