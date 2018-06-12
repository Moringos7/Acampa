package com.rogzart.proyecto_interfaces.FragmentosBarra.Convivio;

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
import android.widget.Adapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.rogzart.proyecto_interfaces.Barra_desplegable;
import com.rogzart.proyecto_interfaces.FragmentosBarra.Eventos.ListaEventos;
import com.rogzart.proyecto_interfaces.Modelo.AdultoMayor;
import com.rogzart.proyecto_interfaces.Modelo.Conexion;
import com.rogzart.proyecto_interfaces.Modelo.Mapa;
import com.rogzart.proyecto_interfaces.Modelo.Usuario;
import com.rogzart.proyecto_interfaces.R;
import com.rogzart.proyecto_interfaces.Singleton.LogUser;
import com.rogzart.proyecto_interfaces.Singleton.VolleySingleton;
import com.rogzart.proyecto_interfaces.sqlite.ActualizacionBaseDatos;
import com.rogzart.proyecto_interfaces.sqlite.OperacionesBaseDatos;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class Convivio extends Fragment {

    private String FechaActual;
    private OperacionesBaseDatos operador;
    private AlertDialog.Builder AlertaEvento;
    private AlertDialog.Builder AlertaScouter;
    private SearchView buscador;
    private LogUser ControlUser;
    private ListView ListaG;
    private Button Btn,btnAsigandos;
    private ArrayList<AdultoMayor> adultosSeleccionados;
    private Conexion conexion;
    private ActualizacionBaseDatos Act;
    private LinearLayout General,Cargando;

    public Convivio() {

    }

    public static Convivio newInstance() {
        Convivio fragment = new Convivio();
        return fragment;
    }

    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        FechaActual = generarFechaActual();
        ControlUser = LogUser.obtenerInstancia(getContext());
        Act = new ActualizacionBaseDatos(getContext());
        conexion = new Conexion(getContext());
        General = getView().findViewById(R.id.InterfazConvivio);
        Cargando = getView().findViewById(R.id.layoutActualizandoConvivio);
        buscador = getView().findViewById(R.id.BusquedaConvivio);
        configurarDialogs();
        operador = OperacionesBaseDatos.obtenerInstancia(getContext());
        if(operador.verificarEventoConvivio(FechaActual)){
            //Toast.makeText(getContext(), "Existe", Toast.LENGTH_SHORT).show();
            ArrayList<AdultoMayor> adultos = operador.obtenerAdultosMayoresConvivio(FechaActual);
            ListaG = getView().findViewById(R.id.idListaConvivio);
            final AdaptadorConvivio miLista = new AdaptadorConvivio(adultos,getContext());
            ListaG.setAdapter(miLista);
            buscador.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String query) {
                    miLista.getFilter().filter(query);
                    return false;
                }
            });
            configurarBoton();
        }else{
            //Toast.makeText(getContext(), "No Existe", Toast.LENGTH_SHORT).show();
            if(ControlUser.getCoordinador() > 0){
                AlertaEvento.show();
            }else{
                AlertaScouter.show();
            }
        }
    }
    private void configurarDialogs(){
        AlertaEvento = new AlertDialog.Builder(getContext());
        AlertaEvento.setTitle("Convivio No agendado");
        AlertaEvento.setMessage("Necesita agendar un Convivio antes de Asignar Adultos Mayores, ¿Desea agendar uno?");
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

        AlertaScouter = new AlertDialog.Builder(getContext());
        AlertaScouter.setTitle("Convivio No agendado");
        AlertaScouter.setMessage("Contacte al Coordinador del Servicio");
        AlertaScouter.setCancelable(false);
        AlertaScouter.setPositiveButton("Aceptar",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                cancelar();
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_convivio, container, false);
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
    private String generarFechaActual(){
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
    void configurarBoton(){
        btnAsigandos = getView().findViewById(R.id.BAsignados);
        btnAsigandos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.contenedor, ConvivioAsignados.newInstance());
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        Btn =  getView().findViewById(R.id.BRecoger);
        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Adapter adapter = (Adapter)ListaG.getAdapter();
                AdultoMayor adulto;
                adultosSeleccionados = new ArrayList<AdultoMayor>();
                for(int i = 0; i<adapter.getCount(); i++){
                    adulto = (AdultoMayor) adapter.getItem(i);
                    //Toast.makeText(getContext(), ""+adultoMayor.getNombre()+""+adultoMayor.getCheck(), Toast.LENGTH_SHORT).show();
                    if(adulto.getCheck()){
                        adultosSeleccionados.add(adulto);
                    }
                }
                //Toast.makeText(getContext(), ""+adultosSeleccionados.size(), Toast.LENGTH_SHORT).show();
                asignarAdultosaRecoger();
            }
        });
    }
    void asignarAdultosaRecoger() {
        //Toast.makeText(getContext(), ""+adultosSeleccionados.size(), Toast.LENGTH_SHORT).show();
        if (conexion.isConnected()) {
            if (adultosSeleccionados.size() == 0) {
                Toast.makeText(getContext(), "Asignar Adulto Mayor Primero", Toast.LENGTH_SHORT).show();
            } else {

                final String Id = operador.obtenerIdentificadoresAsignaciones(FechaActual,adultosSeleccionados);
                //Toast.makeText(getContext(), ""+Id, Toast.LENGTH_SHORT).show();
                conexion.setRuta("WebService/Recoger/wsRecogerUpdate.php");
                StringRequest stringRequest = new StringRequest(Request.Method.POST, conexion.getRuta(),
                        new Response.Listener<String>()
                        {
                            @Override
                            public void onResponse(String response) {
                                //Toast.makeText(getContext(), "Exito: "+response, Toast.LENGTH_SHORT).show();
                                if(response.compareTo("Insertado") == 0){
                                    General.setVisibility(View.GONE);
                                    Cargando.setVisibility(View.VISIBLE);
                                    Toast.makeText(getContext(), "Agregando...", Toast.LENGTH_SHORT).show();
                                    HiloRecargar Hilo = new HiloRecargar();
                                    Hilo.execute();
                                }else{
                                    Toast.makeText(getContext(), ""+response, Toast.LENGTH_SHORT).show();
                                }
                            }
                        },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                                Toast.makeText(getContext(), "Error: "+error, Toast.LENGTH_SHORT).show();
                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams()
                    {
                        Map<String, String>  params = new HashMap<String, String>();
                        params.put("Id", Id);
                        params.put("Scouter",Integer.toString(ControlUser.getScouter()));
                        return params;
                    }
                };
                VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
            }
        }else{
            Toast.makeText(getContext(), "Verifica tu conexion a Internet", Toast.LENGTH_SHORT).show();
        }
    }
    private class HiloRecargar extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            operador.EliminarDatosTabla("recoger");
            Act.ActualizacionRecoger(getContext());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.contenedor, Convivio.newInstance());
            ft.addToBackStack(null);
            ft.commit();
        }
    }
}
