package com.rogzart.proyecto_interfaces.FragmentosBarra.Estadisticas;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Estadisticas_Semestrales extends AppCompatActivity {
    private static String TAG = "EstadisticasMain";
    PieChart pieChart, piechart2, piechart3;
    private float PAsignacionesS;
    private int RAsignacionesTotal;
    TextView DatoMes1, DatoMes2, DatoMes3, DatoMes4, DatoMes5, DatoMes6, Mes1, Mes2, Mes3, Mes4, Mes5, Mes6, DatoTotal, Total1,PeriodoMes1,PeriodoMes2,Periodoaño;

    private float AsistentesEnero, AsistentesFebrero, AsistentesMarzo, AsistentesAbril, AsistentesMayo, AsistentesJunio;
    private float FaltantesEnero, FaltantesFebrero, FaltantesMarzo, FaltantesAbril, FaltantesMayo, FaltantesJunio;
    private float NoAcudieronEnero, NoAcudieronFebrero, NoAcudieronMarzo, NoAcudieronAbril, NoAcudieronMayo, NoAcudieronJunio;
    private int manada, tropa, comunidad, clan, dirigente, civil;
    private String string = generarFecha();
    private String[] datos = string.split("-");

    private String anio, mes, dia,Periodomes1,Periodomes6,PrimerMesDato;
    private OperacionesBaseDatos operador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas__semestrales);
        PeriodoMes1=(TextView) findViewById(R.id.TPeriodo1);
        PeriodoMes2= (TextView) findViewById(R.id.TPeriodo2);
        Periodoaño= (TextView) findViewById(R.id.TAño);
        operador = OperacionesBaseDatos.obtenerInstancia(getApplicationContext());
        anio = datos[0];
        Periodomes1=datos[1];
        //Toast.makeText(this, "Mes: "+Periodomes1, Toast.LENGTH_SHORT).show();

        if(Periodomes1.compareTo("01") == 0){
            PeriodoMes1.setText("Enero ");

            PeriodoMes2.setText("Agosto -");
        }
        if(Periodomes1.compareTo("02") == 0){
            PeriodoMes1.setText("Febrero");

            PeriodoMes2.setText("Septiembre -");
        }
        if(Periodomes1.compareTo("03") == 0){
            PeriodoMes1.setText("Marzo");

            PeriodoMes2.setText("Octubre -");

        }
        if(Periodomes1.compareTo("04") == 0 ){
            PeriodoMes1.setText("Abril");

            PeriodoMes2.setText("Noviembre -");
        }
        if(Periodomes1.compareTo("05") == 0){
            PeriodoMes1.setText("Mayo");

            PeriodoMes2.setText("Diciembre -");
        }
        if(Periodomes1.compareTo("06") == 0){
            PeriodoMes1.setText("Junio");

            PeriodoMes2.setText("Enero -");
        }

        Periodoaño.setText(anio);

        mes = datos[1];
        dia = datos[2];
        //Para Primera Consulta
        int AsignacionesEnero = operador.promedioVoluntariosMes(mes, anio);
        int AsignacionesFebrero = operador.promedioVoluntariosMes(mes, anio);
        int AsignacionesMarzo = operador.promedioVoluntariosMes(mes, anio);
        int AsignacionesAbril = operador.promedioVoluntariosMes(mes, anio);
        int AsignacionesMayo = operador.promedioVoluntariosMes(mes, anio);
        int AsignacionesJunio = operador.promedioVoluntariosMes(mes, anio);
        int SumaEntregada= (AsignacionesEnero+AsignacionesFebrero+AsignacionesMarzo+AsignacionesAbril+AsignacionesMayo+AsignacionesJunio);

        int Total = operador.contarAdultoMayor();
        int AdultosSemestrales=(Total*6);
        int NoEntregada= (AdultosSemestrales-SumaEntregada);



        double DivisionEnero = ((float) (AsignacionesEnero / Total));
        double DivisionFebrero = ((float) (AsignacionesFebrero / Total));
        double DivisionMarzo = ((float) (AsignacionesMarzo / Total));
        double DivisionAbril = ((float) (AsignacionesAbril / Total));
        double DivisionMayo = ((float) (AsignacionesMayo / Total));
        double DivisionJunio = ((float) (AsignacionesJunio / Total));
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
        double AcudieronEnero = operador.asignacionesMes(mes, anio);
        double AcudieronFebrero = operador.asignacionesMes(mes, anio);
        double AcudieronMarzo = operador.asignacionesMes(mes, anio);
        double AcudieronAbril = operador.asignacionesMes(mes, anio);
        double AcudieronMayo = operador.asignacionesMes(mes, anio);
        double AcudieronJunio = operador.asignacionesMes(mes, anio);
        double usuarios = operador.numeroUsuarios();
        double ADivisionEnero = ((float) (AcudieronEnero / usuarios));
        double ADivisionFebrero = ((float) (AcudieronFebrero / usuarios));
        double ADivisionMarzo = ((float) (AcudieronMarzo / usuarios));
        double ADivisionAbril = ((float) (AcudieronAbril / usuarios));
        double ADivisionMayo = ((float) (AcudieronMayo / usuarios));
        double ADivisionJunio = ((float) (AcudieronJunio / usuarios));
        AsistentesEnero = (float) ((ADivisionEnero) * 100);
        AsistentesFebrero = (float) ((ADivisionFebrero) * 100);
        AsistentesMarzo = (float) ((ADivisionMarzo) * 100);
        AsistentesAbril = (float) ((ADivisionAbril) * 100);
        AsistentesMayo = (float) ((ADivisionMayo) * 100);
        AsistentesJunio = (float) ((ADivisionJunio) * 100);
       /* NoAcudieronEnero = (100 - RAsignacionesEnero);
        NoAcudieronFebrero = (100 - RAsignacionesFebrero);
        NoAcudieronMarzo = (100 - RAsignacionesMarzo);
        NoAcudieronAbril = (100 - RAsignacionesAbril);
        NoAcudieronMayo = (100 - RAsignacionesMayo);
        NoAcudieronJunio = (100 - RAsignacionesJunio);
*/

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
        piechart2 = (PieChart) findViewById(R.id.Estadisticas_semestrales_grafica2);
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
        piechart2.setRotationEnabled(true);
        piechart2.setUsePercentValues(true);
        piechart3.setRotationEnabled(true);
        piechart3.setUsePercentValues(true);
        // pieChart.setHoleColor(Color.BLUE);
        pieChart.setCenterTextColor(Color.BLACK);
        pieChart.setHoleRadius(45f);
        pieChart.animateXY(1500, 1500);
        piechart2.setHoleRadius(40f);
        piechart2.animateXY(1500, 1500);
        piechart3.setCenterTextColor(Color.BLACK);
        piechart2.setCenterTextColor(Color.BLACK);
        piechart3.setHoleRadius(40f);
        piechart3.animateXY(1500, 1500);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setDrawEntryLabels(false);
        piechart2.setDrawEntryLabels(false);
        piechart3.setDrawEntryLabels(false);
        pieChart.setCenterText("Despensas");
        piechart2.setTransparentCircleAlpha(0);
        piechart2.setCenterText("Usuarios");
        piechart3.setTransparentCircleAlpha(0);
        piechart3.setCenterText("Sección");
        pieChart.setCenterTextSize(20);
        piechart2.setCenterTextSize(20);
        piechart3.setCenterTextSize(20);

        DatosGrafica1(SumaEntregada,NoEntregada);
        DatosGrafica2(AsistentesEnero, AsistentesFebrero, AsistentesMarzo, AsistentesAbril, AsistentesMayo, AsistentesJunio, NoAcudieronEnero, NoAcudieronFebrero, NoAcudieronMarzo, NoAcudieronAbril, NoAcudieronMayo, NoAcudieronJunio);
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

    private void DatosGrafica2(float asistentesEnero, float asistentesFebrero, float asistentesMarzo, float asistentesAbril, float asistentesMayo, float asistentesJunio, float noAcudieronEnero, float noAcudieronFebrero, float noAcudieronMarzo, float noAcudieronAbril, float noAcudieronMayo, float noAcudieronJunio) {
        Log.d(TAG, "addDataSet started");

        List<PieEntry> entries = new ArrayList<>();

        entries.add(new PieEntry(asistentesEnero, "Enero"));
        entries.add(new PieEntry(asistentesFebrero, "Febrero"));
        entries.add(new PieEntry(asistentesMarzo, "Marzo"));
        entries.add(new PieEntry(asistentesAbril, "Abril"));
        entries.add(new PieEntry(asistentesMayo, "Mayo"));
        entries.add(new PieEntry(asistentesJunio, "Junio"));
        PieDataSet set = new PieDataSet(entries, null);
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.BLUE);
        colors.add(Color.RED);
        colors.add(Color.YELLOW);
        colors.add(Color.MAGENTA);
        colors.add(Color.CYAN);
        colors.add(Color.GREEN);

        //create pie data object
        PieData data1 = new PieData(set);
        set.setColors(colors);
       // piechart2.setData(data1);
        piechart2.highlightValues(null);
        piechart2.invalidate();
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
}




