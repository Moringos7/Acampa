package com.rogzart.proyecto_interfaces.FragmentosBarra.AsignacionAdultosMayores.Usuario;

import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.rogzart.proyecto_interfaces.Barra_desplegable;
import com.rogzart.proyecto_interfaces.FragmentosBarra.Administrar.MenuAdministrar;
import com.rogzart.proyecto_interfaces.FragmentosBarra.AsignacionAdultosMayores.Coordinador.AsignacionAdultoMayorCoordinador;
import com.rogzart.proyecto_interfaces.Modelo.AdultoMayor;
import com.rogzart.proyecto_interfaces.Modelo.Conexion;
import com.rogzart.proyecto_interfaces.Modelo.Usuario;
import com.rogzart.proyecto_interfaces.R;
import com.rogzart.proyecto_interfaces.Singleton.LogUser;
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

import javax.xml.transform.ErrorListener;


public class AsignacionAdultoMayorUsuario extends Fragment {

    private StringRequest stringRequest;
    private AlertDialog.Builder AlertaEvento;
    private Conexion conexion;
    private String FechaActual;
    private LogUser ControlUser;
    private Calendar c = Calendar.getInstance();
    private OperacionesBaseDatos operador;
    private HiloRecargarAsignacion myHiloC;
    private LinearLayout LayoutPrincipal;

    private ArrayList<AdultoMayor> Asignados;
    private Usuario mUsuario;
    public AsignacionAdultoMayorUsuario(){

    }
    public static AsignacionAdultoMayorUsuario newInstance() {
        AsignacionAdultoMayorUsuario fragment = new AsignacionAdultoMayorUsuario();
        return fragment;
    }
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        operador = OperacionesBaseDatos.obtenerInstancia(getContext());
        conexion = new Conexion(getContext());
        Asignados = new ArrayList<AdultoMayor>();
        LayoutPrincipal = getView().findViewById(R.id.linearLayoutCargando);
        FechaActual = generarFecha();
        ControlUser = LogUser.obtenerInstancia(getContext());
        mUsuario = ControlUser.getUser();
        if(operador.verificarEventoServicio(generarFecha())){
            if(VerificarAsignaciones()){
                Bundle bolsa = new Bundle();
                bolsa.putSerializable("AdultosMayores",Asignados);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.contenedor, AdultosMayoresAsignados.newInstance(bolsa));
                ft.addToBackStack(null);
                ft.commit();
            }else{
                if(!conexion.isConnected()){
                    Toast.makeText(getContext(), "Sin Asignaciones,Conectate a Internet", Toast.LENGTH_SHORT).show();
                }else{
                    myHiloC = new HiloRecargarAsignacion();
                    myHiloC.execute();
                }
            }
        }else{
            configurarDialogs();
            AlertaEvento.show();
        }
    }
    private void configurarDialogs() {
        AlertaEvento = new AlertDialog.Builder(getContext());
        AlertaEvento.setTitle("Servicio No Agendado");
        AlertaEvento.setMessage("Contacte al Coordinador del Servicio");
        AlertaEvento.setCancelable(false);
        AlertaEvento.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                getActivity().finish();
                Intent intent = new Intent(getContext(), Barra_desplegable.class);
                startActivityForResult(intent, 0);
            }
        });
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
    @Override
    public void onPause() {
        super.onPause();
        if(myHiloC != null){
            myHiloC.cancel(true);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_asignacion_adulto_mayor_usuario, container, false);

    }
    private boolean VerificarAsignaciones(){
        boolean status = false;
        Asignados = operador.ObtenerAsignaciones(FechaActual,mUsuario.getIdUsuario());
        if(Asignados.size() != 0){
            status = true;
        }
        return status;
    }

    private class HiloRecargarAsignacion extends AsyncTask<Void, Void, Void> {

        @Override protected void onPreExecute() {


        }
        @Override
        protected Void doInBackground(Void... voids) {
            operador.EliminarDatosTabla("recoger");
            operador.EliminarDatosTabla("asignacion");
            new ActualizacionBaseDatos(getContext()).ActualizacionAsignacion(getContext());
            new ActualizacionBaseDatos(getContext()).ActualizacionRecoger(getContext());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if(VerificarAsignaciones()){
                Bundle bolsa = new Bundle();
                bolsa.putSerializable("AdultosMayores",Asignados);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.contenedor, AdultosMayoresAsignados.newInstance(bolsa));
                ft.addToBackStack(null);
                ft.commit();
            }else{
                Bundle bolsa = new Bundle();
                bolsa.putString("FechaActual",FechaActual);
                bolsa.putSerializable("Usuario",mUsuario);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.contenedor, SolicitarAsignacion.newInstance(bolsa));
                ft.addToBackStack(null);
                ft.commit();
            }

        }
    }

}
