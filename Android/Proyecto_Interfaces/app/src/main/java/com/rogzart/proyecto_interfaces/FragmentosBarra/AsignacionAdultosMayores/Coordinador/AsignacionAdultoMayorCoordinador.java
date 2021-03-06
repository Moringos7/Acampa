package com.rogzart.proyecto_interfaces.FragmentosBarra.AsignacionAdultosMayores.Coordinador;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.rogzart.proyecto_interfaces.Barra_desplegable;
import com.rogzart.proyecto_interfaces.FragmentosBarra.Eventos.ListaEventos;
import com.rogzart.proyecto_interfaces.Modelo.AdultoMayor;
import com.rogzart.proyecto_interfaces.Modelo.Conexion;
import com.rogzart.proyecto_interfaces.Modelo.SeleccionAM;
import com.rogzart.proyecto_interfaces.Modelo.UsuarioAsignacion;
import com.rogzart.proyecto_interfaces.R;
import com.rogzart.proyecto_interfaces.Singleton.VolleySingleton;
import com.rogzart.proyecto_interfaces.sqlite.ActualizacionBaseDatos;
import com.rogzart.proyecto_interfaces.sqlite.OperacionesBaseDatos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AsignacionAdultoMayorCoordinador extends Fragment {

    private AlertDialog.Builder AlertaEvento;
    private AlertDialog.Builder AlertaFin;
    private AlertDialog.Builder AlertaSalir;
    private AlertDialog.Builder AlertaSalir2;
    private boolean EventoDisponible;
    private boolean Activado;
    private OperacionesBaseDatos operador;
    private String FechaActual;
    private ListView listaG;
    private Boolean A;
    private HiloCargaLista myHiloC;
    public int NumeroPeticiones;
    private ArrayList<SeleccionAM> Selecciones;
    private LinearLayout LinearprogressBar;
    private TextView ContadorAsignados;
    private Button BtnSalir;
    private int Size;



    public  AsignacionAdultoMayorCoordinador(){
        Selecciones = new ArrayList<SeleccionAM>();
    }
    @SuppressLint("ValidFragment")
    public  AsignacionAdultoMayorCoordinador(Bundle bolsa){
        Selecciones = (ArrayList<SeleccionAM>) bolsa.getSerializable("Selecciones");
    }
    public static AsignacionAdultoMayorCoordinador newInstance() {
        AsignacionAdultoMayorCoordinador fragment = new AsignacionAdultoMayorCoordinador();
        return fragment;
    }
    public static AsignacionAdultoMayorCoordinador newInstance(Bundle bolsa) {
        AsignacionAdultoMayorCoordinador fragment = new AsignacionAdultoMayorCoordinador(bolsa);
        return fragment;
    }

    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

        configurarDialogs();
        LinearprogressBar = getView().findViewById(R.id.progressBarAsignacion);
        ContadorAsignados = getView().findViewById(R.id.ContadorAsignados);
        BtnSalir = getView().findViewById(R.id.btnSalir);
        operador = OperacionesBaseDatos.obtenerInstancia(getContext());
        FechaActual = generarFecha();
        NumeroPeticiones = 0;

        EventoDisponible = operador.verificarEventoServicio(FechaActual);
        if(!EventoDisponible){
            AlertaEvento.show();
        }else {
            cargarLista();
            configurarHilos();

        }
        //LinearprogressBar.setVisibility(View.VISIBLE);

        BtnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Size > 0){
                    AlertaSalir.show();
                }else{
                    DetenerHilos();
                    Intent intent = new Intent(getContext(), Barra_desplegable.class);
                    getActivity().finish();
                    startActivityForResult(intent, 0);
                }
            }
        });

        ////Botones de Administracion
        LinearLayout Botones = getView().findViewById(R.id.layoutBotones);
        Botones.setVisibility(View.GONE);
        Button btn = getView().findViewById(R.id.btntemporal);
        btn.setText("Recargar");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetenerHilos();
                configurarHilos();

            }
        });
        Button BTN = getView().findViewById(R.id.btnActivar);
        BTN.setText("Status");
        BTN.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "Peticiones: "+NumeroPeticiones, Toast.LENGTH_SHORT).show();
                        Toast.makeText(getContext(), "Estado Hilo1: "+Activado, Toast.LENGTH_SHORT).show();
                        Toast.makeText(getContext(), "Estado Hilo2: "+myHiloC.getStatus(), Toast.LENGTH_SHORT).show();
                    }
                });
        //Botones de Administracion
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_asignacion_adulto_mayor_coordinador, container, false);

    }
    @Override
    public void onPause() {
        super.onPause();
        DetenerHilos();
    }

    private void configurarDialogs(){
        AlertaEvento = new AlertDialog.Builder(getContext());
        AlertaEvento.setTitle("Servicio No agendado");
        AlertaEvento.setMessage("Necesita agendar un Servicio antes de Asignar Adultos Mayores, ¿Desea agendar uno?");
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
        AlertaFin = new AlertDialog.Builder(getContext());
        AlertaFin.setTitle("Fin de Asignaciones");
        AlertaFin.setMessage("Ha terminado de Asignar a todos los adultos mayores");
        AlertaFin.setCancelable(false);
        AlertaFin.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                Intent intent = new Intent(getContext(), Barra_desplegable.class);
                getActivity().finish();
                startActivityForResult(intent, 0);
            }
        });
        AlertaSalir = new AlertDialog.Builder(getContext());
        AlertaSalir.setTitle("Asignaciones Pendientes");
        AlertaSalir.setMessage("Aun quedan Adultos Mayores por Asignar ¿Estas seguro? ");
        AlertaSalir.setCancelable(false);
        AlertaSalir.setPositiveButton("Salir", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                AlertaSalir2.show();
            }
        });
        AlertaSalir.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {

            }
        });
        AlertaSalir2 = new AlertDialog.Builder(getContext());
        AlertaSalir2.setTitle("¿Esta Seguro?");
        AlertaSalir2.setCancelable(false);
        AlertaSalir2.setPositiveButton("Salir", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                DetenerHilos();
                Intent intent = new Intent(getContext(), Barra_desplegable.class);
                getActivity().finish();
                startActivityForResult(intent, 0);
            }
        });
        AlertaSalir2.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {

            }
        });

    }

    private void agendar(){
        //Toast.makeText(getContext(), "Agendame", Toast.LENGTH_SHORT).show();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.contenedor, ListaEventos.newInstance());
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
    public void cargarLista(){
        listaG = getView().findViewById(R.id.listaAsigancionCoordinador);
        ArrayList<UsuarioAsignacion> arrayList = operador.LeerUsuariosAsignacion(FechaActual);
        Size = arrayList.size();
        //Toast.makeText(getContext(), ""+arrayList.size(), Toast.LENGTH_SHORT).show();
        //Toast.makeText(getContext(), ""+FechaActual, Toast.LENGTH_SHORT).show();
        for(UsuarioAsignacion usuario : arrayList){
            for(SeleccionAM regirsto : Selecciones){
                if(regirsto.getIdentificador() == usuario.getIdUsuario()){
                    usuario.setAdultosMayores(regirsto.getListaAsigandos());
                }
            }
        }
        ListaAdaptadorAsignacionAdultoMayor miLista = new ListaAdaptadorAsignacionAdultoMayor(arrayList, getContext(),FechaActual,Selecciones);
        LinearprogressBar.setVisibility(View.GONE);
        listaG.setAdapter(miLista);
        Asignaciones();

    }
    private void Asignaciones(){
        int NumAdultosMayores;
        //Adultos Mayores en el sistema
        ArrayList<AdultoMayor> AdultosMayores = operador.LeerTablaAdultoMayor();
        //Adultos Mayores Asignados Actualmente
        ArrayList<AdultoMayor> Asignados = operador.obtenerAdultosMayoresAsignados(FechaActual);
        NumAdultosMayores = AdultosMayores.size() - Asignados.size();
        ContadorAsignados.setText(String.valueOf(NumAdultosMayores));
        ContadorAsignados.setVisibility(View.VISIBLE);
        if(NumAdultosMayores == 0){
            AlertaFin.show();
            DetenerHilos();
        }
    }
    private void DetenerHilos(){
        Activado = false;
        if(myHiloC != null){
            myHiloC.cancel(true);
        }
    }
    private void configurarHilos(){
        NumeroPeticiones = 0;
        Activado = true;
        myHiloC = new HiloCargaLista();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(Activado){
                    if (myHiloC.getStatus() == AsyncTask.Status.RUNNING){

                    }else{
                        myHiloC = new HiloCargaLista();
                        myHiloC.execute();
                    }
                }
            }
        }).start();
    }


    private class HiloCargaLista extends AsyncTask<Void, Void, Void> {

        public Conexion conexion;
        public JSONObject obj;
        public JsonObjectRequest jsonObjectRequest;
        @Override protected void onPreExecute() {
            conexion = new Conexion(getContext());
            conexion.setRuta("WebService/Asignacion/contarRegistros.php");
            A = false;
            Map<String, String> params = new HashMap();
            params.put("fecha", FechaActual);
            obj = new JSONObject(params);
            jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, conexion.getRuta(), obj, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    JSONArray json = response.optJSONArray("Respuesta");
                    try {
                        JSONObject jsonObject = json.getJSONObject(0);
                        int cuenta = jsonObject.optInt("Cuenta");
                        if(cuenta != NumeroPeticiones){
                            A = true;
                            NumeroPeticiones = cuenta;
                        }else{
                            LinearprogressBar.setVisibility(View.VISIBLE);

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getContext(), "Fallo Conexion", Toast.LENGTH_SHORT).show();
                }
            });
        }
        @Override
        protected Void doInBackground(Void... voids) {
            boolean Salir = true;

            while(Salir){
                VolleySingleton.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);
                if(A){
                    operador.EliminarDatosTabla("recoger");
                    operador.EliminarDatosTabla("asignacion");
                    new ActualizacionBaseDatos(getContext()).ActualizacionAsignacion(getContext());
                    new ActualizacionBaseDatos(getContext()).ActualizacionRecoger(getContext());

                    Salir = false;
                    A = false;
                }
                if(isCancelled()){
                    break;
                }
                try{
                    Thread.sleep(2000);}
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            cargarLista();
        }
        @Override
        protected void onCancelled() {
            super.onCancelled();
            //Toast.makeText(getContext(), "Cancelado", Toast.LENGTH_SHORT).show();
        }

    }
}

