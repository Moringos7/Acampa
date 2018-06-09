package com.rogzart.proyecto_interfaces.FragmentosBarra.Eventos;

import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.rogzart.proyecto_interfaces.FragmentosBarra.Scouter.Administracion_Scouter;
import com.rogzart.proyecto_interfaces.FragmentosBarra.Scouter.AgregarScouter;
import com.rogzart.proyecto_interfaces.Modelo.Conexion;
import com.rogzart.proyecto_interfaces.R;
import com.rogzart.proyecto_interfaces.Singleton.LogUser;
import com.rogzart.proyecto_interfaces.Singleton.VolleySingleton;
import com.rogzart.proyecto_interfaces.sqlite.ActualizacionBaseDatos;
import com.rogzart.proyecto_interfaces.sqlite.OperacionesBaseDatos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class Agregar_Evento extends Fragment {

    private EditText Fecha;
    private EditText Hora;
    private EditText Lugar;
    private EditText Informacion;
    private Spinner TipoEvento;
    private Conexion conexion;
    private OperacionesBaseDatos operador;
    private Button BtnAgregar;
    private String FechaCorrecta,HoraCorrecta;
    private int FkTipoEvento;
    private LinearLayout General,Cargando;
    private ActualizacionBaseDatos Act;

    public Agregar_Evento() {
        // Required empty public constructor
    }


    public static Agregar_Evento newInstance() {
        Agregar_Evento fragment = new Agregar_Evento();
        return fragment;
    }

    void llenarSpinner(){
        String []Tipos = new String[2];
        Tipos[0] = "Servicio";
        Tipos[1] = "Convivio";
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,Tipos);
        TipoEvento.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_agregar__evento, container, false);
    }
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        operador = OperacionesBaseDatos.obtenerInstancia(getContext());
        Act = new ActualizacionBaseDatos(getContext());
        conexion = new Conexion(getContext());

        Fecha = (EditText) getView().findViewById(R.id.IdFechaEvento);
        Hora = (EditText) getView().findViewById(R.id.IdHoraEvento);
        Lugar = (EditText) getView().findViewById(R.id.IdLugarEvento);
        Informacion = (EditText) getView().findViewById(R.id.IdInformacionEvento);
        TipoEvento = getView().findViewById(R.id.spinnerTipoEvento);
        BtnAgregar = getView().findViewById(R.id.btnAgregarEvento);
        General = getView().findViewById(R.id.layoutEventoGeneral);
        Cargando = getView().findViewById(R.id.layoutActualizandoEvento) ;
        llenarSpinner();
        BtnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verificaciones()){
                    if(conexion.isConnected()){
                        conexion.setRuta("WebService/Evento/wsEventoCreate.php");
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, conexion.getRuta(),
                                new Response.Listener<String>()
                                {
                                    @Override
                                    public void onResponse(String response) {
                                        if(response.compareTo("Creado") == 0){
                                            HiloCargaEventos x = new HiloCargaEventos();
                                            x.execute();
                                        }else{
                                            Toast.makeText(getContext(), ""+response, Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                },
                                new Response.ErrorListener()
                                {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                        Toast.makeText(getContext(), ""+error, Toast.LENGTH_SHORT).show();
                                    }
                                }
                        ) {
                            @Override
                            protected Map<String, String> getParams()
                            {
                                Map<String, String>  params = new HashMap<String, String>();
                                params.put("fecha",FechaCorrecta);
                                params.put("hora",HoraCorrecta);
                                params.put("lugar",String.valueOf(Lugar.getText()));
                                params.put("informacion",String.valueOf(Informacion.getText()));
                                params.put("fktipoevento",String.valueOf(FkTipoEvento));

                                return params;
                            }
                        };
                        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
                    }else{
                        Toast.makeText(getContext(), "Verifica tu conexion a Internet", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    //Toast.makeText(getContext(), "Datos Incorrectos", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    public boolean verificaciones(){
        boolean CheckVerificacion = true,CheckDiaPosterior = true;
        Boolean CheckFecha = true;
        String[] ArregloFecha,ArregloFechaActual;
        String Dia,Mes,Anio;
        int diaActual,mesActual,anioActual;
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            formatoFecha.setLenient(false);
            formatoFecha.parse(String.valueOf(Fecha.getText()));

        } catch (ParseException e) {
            CheckFecha = false;
            CheckVerificacion = false;
            Toast.makeText(getContext(), "Fecha No valida", Toast.LENGTH_SHORT).show();
        }

        if(CheckFecha) {
            ArregloFecha = String.valueOf(Fecha.getText()).split("/");
            Dia = ArregloFecha[0];
            Mes = ArregloFecha[1];
            Anio = ArregloFecha[2];
            int dia = Integer.parseInt(Dia);
            int mes = Integer.parseInt(Mes);
            int anio = Integer.parseInt(Anio);

            ArregloFechaActual = String.valueOf(generarFecha()).split("-");
            diaActual = Integer.parseInt(ArregloFechaActual[2]);
            mesActual = Integer.parseInt(ArregloFechaActual[1]);
            anioActual = Integer.parseInt(ArregloFechaActual[0]);

            if(anio < anioActual){
                CheckVerificacion = false;
                CheckDiaPosterior = false;
            }else if(mes == mesActual){
                if(dia < diaActual ){
                    CheckVerificacion = false;
                    CheckDiaPosterior = false;
                }else{
                    FechaCorrecta = "" + Anio + "-" + Mes + "-" + Dia;
                }
            }else if(mes < mesActual){
                CheckVerificacion = false;
                CheckDiaPosterior = false;
            }else{
                FechaCorrecta = "" + Anio + "-" + Mes + "-" + Dia;
            }
            if(!CheckDiaPosterior){
                Toast.makeText(getContext(), "Ingrese Fecha Posterior a la actual", Toast.LENGTH_LONG).show();
            }
        }

        String hora = String.valueOf(Hora.getText());
        if(hora.length() < 4){
            Toast.makeText(getContext(), "Formato Incorreto Hora", Toast.LENGTH_SHORT).show();
            CheckVerificacion = false;
        }else{
            String []partesHora = hora.split(":");
            int Horas = Integer.parseInt(partesHora[0]);
            int Minutos = Integer.parseInt(partesHora[1]);
            if(Horas > 23 || Horas <0){
                Toast.makeText(getContext(), "Formato Incorreto Hora [Hora]", Toast.LENGTH_SHORT).show();
                CheckVerificacion = false;
            }else if(Minutos > 59 || Minutos < 0){
                Toast.makeText(getContext(), "Formato Incorreto Hora [Minutos]", Toast.LENGTH_SHORT).show();
                CheckVerificacion = false;
            }else {
                HoraCorrecta = hora;
            }
        }
        String lugar = String.valueOf(Lugar.getText());
        if(lugar.length() == 0){
            CheckVerificacion = false;
            Toast.makeText(getContext(), "Ingrese Lugar", Toast.LENGTH_SHORT).show();
        }

        if(TipoEvento.getSelectedItem().toString().compareTo("Servicio") == 0){
            FkTipoEvento = 1;
        }else if (TipoEvento.getSelectedItem().toString().compareTo("Convivio") == 0){
            FkTipoEvento = 2;
        }else{
            CheckVerificacion = false;
        }
        return CheckVerificacion;
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


    public void actualizaciones(){
        operador.EliminarDatosTabla("voluntariofrecuente");
        operador.EliminarDatosTabla("recoger");
        operador.EliminarDatosTabla("gestioninventario");
        operador.EliminarDatosTabla("comentarioam");
        operador.EliminarDatosTabla("asignacion");
        operador.EliminarDatosTabla("adultomayor");
        operador.EliminarDatosTabla("problematica");
        operador.EliminarDatosTabla("fotoalrededores");
        operador.EliminarDatosTabla("scouter");
        operador.EliminarDatosTabla("domicilio");
        operador.EliminarDatosTabla("evento");
        Act.ActualizacionEvento(getContext());
        Act.ActualizacionDomicilio(getContext());
        Act.ActualizacionScouter(getContext());
        Act.ActualizacionFotoAlrededores(getContext());
        Act.ActualizacionProblematica(getContext());
        Act.ActualizacionAdultoMayor(getContext());
        Act.ActualizacionAsignacion(getContext());
        Act.ActualizacionComentarioAM(getContext());
        Act.ActualizacionGestionInventario(getContext());
        Act.ActualizacionRecoger(getContext());
        Act.ActualizacionVoluntarioFrecuente(getContext());
    }
    private class HiloCargaEventos extends AsyncTask<Void, Void, Void> {

        @Override protected void onPreExecute() {
            General.setVisibility(View.GONE);
            Cargando.setVisibility(View.VISIBLE);
        }
        @Override
        protected Void doInBackground(Void... voids) {

            actualizaciones();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.contenedor, ListaEventos.newInstance());
            ft.addToBackStack(null);
            ft.commit();


        }
    }
}
