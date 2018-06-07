package com.rogzart.proyecto_interfaces.FragmentosBarra.Scouter;

import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.rogzart.proyecto_interfaces.FragmentosBarra.InformacionAdultoMayor.InformacionAdultoMayor;
import com.rogzart.proyecto_interfaces.FragmentosBarra.VoluntarioFrecuente.AsignarVoluntarioFrecuente;
import com.rogzart.proyecto_interfaces.Modelo.Conexion;
import com.rogzart.proyecto_interfaces.Modelo.Usuario;
import com.rogzart.proyecto_interfaces.R;
import com.rogzart.proyecto_interfaces.Singleton.LogUser;
import com.rogzart.proyecto_interfaces.Singleton.VolleySingleton;
import com.rogzart.proyecto_interfaces.sqlite.ActualizacionBaseDatos;
import com.rogzart.proyecto_interfaces.sqlite.OperacionesBaseDatos;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class AgregarScouter extends Fragment implements View.OnClickListener {

    private String FechaActual;
    private OperacionesBaseDatos operador;
    private ListView ListG;
    private Usuario user;
    private View afterV;
    private Button Btn,btnPrueba;
    private String FechaFinal;
    private Conexion conexion;
    private  Boolean verificarSeleccion;
    //////////////////
    private ActualizacionBaseDatos Act;
    private LinearLayout Cargando, General;
    ///////////////////
    public AgregarScouter() {
        // Required empty public constructor
    }

    public static AgregarScouter newInstance() {
        AgregarScouter fragment = new AgregarScouter();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_agregar_scouter, container, false);
    }
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

        btnPrueba = getView().findViewById(R.id.btnPrueba);
        btnPrueba.setVisibility(View.GONE);
        /*btnPrueba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               FechaFinal = generarFechaFinal();
                Toast.makeText(getContext(), FechaFinal, Toast.LENGTH_SHORT).show();
            }
        });*/


        FechaActual = generarFecha();
        verificarSeleccion = false;
        /////////////7
        Act = new ActualizacionBaseDatos(getContext());
        Cargando = getView().findViewById(R.id.layoutActualizandoScouter);
        General = getView().findViewById(R.id.LayoutScouter);
        ///////////////////
        conexion = new Conexion(getContext());
        operador = OperacionesBaseDatos.obtenerInstancia(getContext());
        Btn = getView().findViewById(R.id.btnAsignarScouter);
        Btn.setOnClickListener(this);
        ArrayList<Usuario> usuarios = operador.obtenerUsuariosnoScouters();
        ListG = getView().findViewById(R.id.lista_scouters);
        AdaptadorAgregarScouter miLista = new AdaptadorAgregarScouter(usuarios,getContext());
        ListG.setAdapter(miLista);
        ListG.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(user == null){
                    user = (Usuario) adapterView.getItemAtPosition(i);
                    user.setCheck(true);
                    verificarSeleccion = true;
                    //view.setBackgroundColor(Color.rgb(45, 115, 191));
                }else{

                    user.setCheck(false);
                    Usuario userTemp = (Usuario) adapterView.getItemAtPosition(i);
                    if(user.getIdUsuario() == userTemp.getIdUsuario()){
                        user = null;
                        //view.setBackgroundColor(Color.WHITE);
                        verificarSeleccion = false;
                    }else{
                        user = null;
                        user = (Usuario)adapterView.getItemAtPosition(i);
                        user.setCheck(true);
                        verificarSeleccion = true;
                        //view.setBackgroundColor(Color.WHITE);
                    }
                }

                if(afterV == null){
                    afterV = view;
                    view.setBackgroundColor(Color.rgb(45, 115, 191));

                }else{
                    if(afterV == view){
                        afterV = null;
                        view.setBackgroundColor(Color.WHITE);
                    }else{
                        afterV.setBackgroundColor(Color.WHITE);
                        view.setBackgroundColor(Color.rgb(45, 115, 191));
                        afterV = view;
                    }
                }
                /*
                if(afterV == null){
                    afterV.setBackgroundColor(Color.WHITE);
                    view.setBackgroundColor(Color.rgb(45, 115, 191));
                }
                if(afterV == view){
                    view.setBackgroundColor(Color.WHITE);
                    afterV = null;
                }else{
                    view.setBackgroundColor(Color.rgb(45, 115, 191));
                    afterV = view;
                }*/

                if(user != null){
                    Toast.makeText(getContext(), "Seleccionado: "+user.getNombre(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "Sin seleccion", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private String  generarFechaFinal(){
        if(LogUser.obtenerInstancia(getContext()).getCoordinador() > 0 ){
            return "3018-10-25";
        }else{

            //Obtencion de Fecha Actual
            Date date = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            //Suma de 30 dias
            calendar.add(Calendar.DAY_OF_YEAR, 30);
            //Asignar Formato
            int Dia = calendar.get(Calendar.DAY_OF_MONTH);
            int Mes = calendar.get(Calendar.MONTH)+1;
            int Anio = calendar.get(Calendar.YEAR);
            String decenaD = "";
            String decenaM = "";
            if(Mes < 10){
                decenaM = "0";
            }
            if(Dia < 10){
                decenaD = "0";
            }
            ////
            return String.valueOf(Anio)+"-"+decenaM+String.valueOf(Mes)+"-"+decenaD+String.valueOf(Dia);
        }
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
        Act.ActualizacionScouter(getContext());
        Act.ActualizacionFotoAlrededores(getContext());
        Act.ActualizacionProblematica(getContext());
        Act.ActualizacionAdultoMayor(getContext());
        Act.ActualizacionAsignacion(getContext());
        Act.ActualizacionComentarioAM(getContext());
        Act.ActualizacionGestionInventario(getContext());
        Act.ActualizacionRecoger(getContext());
        Act.ActualizacionVoluntarioFrecuente(getContext());
        Act.ActualizacionScouter(getContext());
    }

    @Override
    public void onClick(View v) {
        if(verificarSeleccion){
            //Toast.makeText(getContext(), ""+user.getNombre(), Toast.LENGTH_SHORT).show();
            if(conexion.isConnected()){
                conexion.setRuta("WebService/Scouter/wsScouterCreate.php");
                FechaFinal = generarFechaFinal();
                StringRequest stringRequest = new StringRequest(Request.Method.POST, conexion.getRuta(),
                        new Response.Listener<String>()
                        {
                            @Override
                            public void onResponse(String response) {
                                //Toast.makeText(getContext(), ""+response, Toast.LENGTH_SHORT).show();
                                if(response.compareTo("Asignado") == 0){
                                    HiloCargaLista x = new HiloCargaLista();
                                    x.execute();
                                }else {
                                    Toast.makeText(getContext(), ""+response, Toast.LENGTH_SHORT).show();
                                }
                            }
                        },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // error
                                Toast.makeText(getContext(), ""+error, Toast.LENGTH_SHORT).show();
                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams()
                    {
                        Map<String, String>  params = new HashMap<String, String>();
                        params.put("fkusuario",Integer.toString(user.getIdUsuario()));
                        params.put("fechainicio",generarFecha());
                        params.put("fechafinal",FechaFinal);
                        return params;
                    }
                };
                VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
            }else{
                Toast.makeText(getContext(), "Verifique su conexion a Internet", Toast.LENGTH_SHORT).show();
            }

        }else{
        Toast.makeText(getContext(), "Selecciona un Usuario", Toast.LENGTH_SHORT).show();
        }
    }
    private class HiloCargaLista extends AsyncTask<Void, Void, Void> {

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
            if(LogUser.obtenerInstancia(getContext()).getCoordinador() > 0){
                Toast.makeText(getContext(), "Asignado", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getContext(), "Asignado Hasta: "+ FechaFinal, Toast.LENGTH_SHORT).show();
            }

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.contenedor, Administracion_Scouter.newInstance());
            ft.addToBackStack(null);
            ft.commit();


        }
    }
}
