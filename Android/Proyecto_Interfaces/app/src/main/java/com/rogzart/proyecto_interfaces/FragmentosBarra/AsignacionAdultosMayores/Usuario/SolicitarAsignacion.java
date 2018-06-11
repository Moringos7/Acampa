package com.rogzart.proyecto_interfaces.FragmentosBarra.AsignacionAdultosMayores.Usuario;

import android.annotation.SuppressLint;
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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.rogzart.proyecto_interfaces.Barra_desplegable;
import com.rogzart.proyecto_interfaces.FragmentosBarra.AsignacionAdultosMayores.Coordinador.AsignacionAdultoMayorCoordinador;
import com.rogzart.proyecto_interfaces.InterfacesLogin.NuevaContrasenia;
import com.rogzart.proyecto_interfaces.Modelo.AdultoMayor;
import com.rogzart.proyecto_interfaces.Modelo.Conexion;
import com.rogzart.proyecto_interfaces.Modelo.Usuario;
import com.rogzart.proyecto_interfaces.R;
import com.rogzart.proyecto_interfaces.Singleton.LogUser;
import com.rogzart.proyecto_interfaces.Singleton.VolleySingleton;
import com.rogzart.proyecto_interfaces.sqlite.ActualizacionBaseDatos;
import com.rogzart.proyecto_interfaces.sqlite.OperacionesBaseDatos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.validation.Validator;


@SuppressLint("ValidFragment")
public class SolicitarAsignacion extends Fragment {

    private LinearLayout LayoutPeticion;
    private String FechaActual;
    private Conexion conexion;
    private Usuario usuario;
    private Boolean Activado;
    private HiloPeticion myHiloP;
    private OperacionesBaseDatos operador;
    private ArrayList<AdultoMayor> Asignados;
    private Button BtnSalir;
    private AlertDialog.Builder AlertaSalir;


    @SuppressLint("ValidFragment")
    public SolicitarAsignacion(Bundle bolsa) {
        FechaActual = bolsa.getString("FechaActual");
        usuario = (Usuario) bolsa.getSerializable("Usuario");
    }

    public static SolicitarAsignacion newInstance(Bundle bolsa) {
        SolicitarAsignacion fragment = new SolicitarAsignacion(bolsa);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_solicitar_asignacion, container, false);
    }
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        LayoutPeticion = getView().findViewById(R.id.linearLayoutPeticion);
        //Toast.makeText(getContext(), ""+FechaActual, Toast.LENGTH_LONG).show();
        BtnSalir = getView().findViewById(R.id.btnSalir);
        configurarDialogs();
        conexion = new Conexion(getContext());
        operador =  OperacionesBaseDatos.obtenerInstancia(getContext());
        conexion.setRuta("WebService/Asignacion/wsAsignacionCreate.php");
        myHiloP = new HiloPeticion();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, conexion.getRuta(),
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        if(response.compareTo("Solicitud Enviada") == 0){
                            Toast.makeText(getContext(), ""+response, Toast.LENGTH_SHORT).show();
                            LayoutPeticion.setVisibility(View.VISIBLE);
                            BtnSalir.setVisibility(View.VISIBLE);
                            myHiloP.execute();
                        }else{
                            Toast.makeText(getContext(), "Error: "+response, Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getContext(), Barra_desplegable.class);
                            getActivity().finish();
                            startActivityForResult(intent, 0);
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), ""+error, Toast.LENGTH_LONG).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("status","0");
                params.put("fecha", FechaActual);
                params.put("fkusuario", Integer.toString(usuario.getIdUsuario()));
                return params;
            }
        };
        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);

        BtnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertaSalir.show();
                
            }
        });
    }
    public void configurarDialogs(){
        AlertaSalir = new AlertDialog.Builder(getContext());
        AlertaSalir.setTitle("¿Seguro que desea Salir?");
        AlertaSalir.setMessage("No perderá su asignación");
        AlertaSalir.setCancelable(false);
        AlertaSalir.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                myHiloP.cancel(true);
                Intent intent = new Intent(getContext(), Barra_desplegable.class);
                getActivity().finish();
                startActivityForResult(intent, 0);
            }
        });
        AlertaSalir.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                
            }
        });
    }
    @Override
    public void onPause() {
        super.onPause();
        myHiloP.cancel(true);
    }
    private boolean VerificarAsignaciones(){
        boolean status = false;
        Asignados = operador.ObtenerAsignaciones(FechaActual,usuario.getIdUsuario());
        if(Asignados.size() != 0){
            status = true;
        }
        return status;
    }
    private class HiloPeticion extends AsyncTask<Void, Void, Void> {

        @Override protected void onPreExecute() {


        }
        @Override
        protected Void doInBackground(Void... voids) {
            Boolean Salir = true;
            Boolean Check;
            while (Salir) {
                Check = VerificarAsignaciones();
                if (!Check) {
                    operador.EliminarDatosTabla("recoger");
                    operador.EliminarDatosTabla("asignacion");
                    new ActualizacionBaseDatos(getContext()).ActualizacionAsignacion(getContext());
                    new ActualizacionBaseDatos(getContext()).ActualizacionRecoger(getContext());

                    Salir = true;
                }else{
                    Salir = false;
                }
                if(isCancelled()){
                    break;
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            Bundle bolsa = new Bundle();
            bolsa.putSerializable("AdultosMayores",Asignados);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.contenedor, AdultosMayoresAsignados.newInstance(bolsa));
            ft.addToBackStack(null);
            ft.commit();


        }
    }

}
