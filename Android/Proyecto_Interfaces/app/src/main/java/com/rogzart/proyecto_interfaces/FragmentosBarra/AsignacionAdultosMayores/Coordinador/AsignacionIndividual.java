package com.rogzart.proyecto_interfaces.FragmentosBarra.AsignacionAdultosMayores.Coordinador;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.rogzart.proyecto_interfaces.Modelo.AdultoMayor;
import com.rogzart.proyecto_interfaces.Modelo.Asignacion;
import com.rogzart.proyecto_interfaces.Modelo.Conexion;
import com.rogzart.proyecto_interfaces.Modelo.SeleccionAM;
import com.rogzart.proyecto_interfaces.Modelo.Usuario;
import com.rogzart.proyecto_interfaces.Modelo.UsuarioAsignacion;
import com.rogzart.proyecto_interfaces.R;
import com.rogzart.proyecto_interfaces.Singleton.VolleySingleton;
import com.rogzart.proyecto_interfaces.sqlite.OperacionesBaseDatos;

import java.util.ArrayList;
import java.util.LinkedList;


public class AsignacionIndividual extends Fragment implements View.OnClickListener {

    private static UsuarioAsignacion usuario;
    private static String Fecha;
    private ImageView Imagen;
    private TextView Nombre;
    private ListView Lista;
    private SearchView Busqueda;
    private OperacionesBaseDatos operador;
    private Conexion conexion;
    private Button Btn;
    ////
    private ArrayList<SeleccionAM> Selecciones;
    private ArrayList<AdultoMayor> AdultosMayores;
    private ArrayList<AdultoMayor> Frecuentes;
    private ArrayList<AdultoMayor> Asignados;
    private ArrayList<AdultoMayor> noFrecuentes;

    ///

    //
    public AsignacionIndividual() {

    }

    @SuppressLint("ValidFragment")
    public AsignacionIndividual(Bundle bolsa) {
        usuario = (UsuarioAsignacion) bolsa.getSerializable("Usuario");
        Fecha = (String) bolsa.get("Fecha");
        Selecciones = (ArrayList<SeleccionAM>) bolsa.getSerializable("Selecciones");
    }


    public static AsignacionIndividual newInstance(Bundle bolsa) {
        AsignacionIndividual fragment = new AsignacionIndividual(bolsa);
        return fragment;
    }

    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        Imagen = getView().findViewById(R.id.imagenUsuarioAsignacionIndividual);
        Nombre = getView().findViewById(R.id.nombreUsuarioAsignacionIndividual);
        Busqueda = getView().findViewById(R.id.busquedaAsignacionIndividual);
        operador = OperacionesBaseDatos.obtenerInstancia(getContext());
        conexion = new Conexion(getContext());
        Btn = getView().findViewById(R.id.btnAsignar);
        Btn.setOnClickListener(this);
        Nombre.setText(usuario.getNombre() + " " + usuario.getApellidoPaterno() + " " + usuario.getApellidoMaterno());
        cargarImagenUsuario();
        cargarListaAdultosMayores();




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_asignacion_individual, container, false);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void cargarImagenUsuario() {
        conexion.setRuta("WebService/" + usuario.getFotografia());
        ImageRequest imageRequest = new ImageRequest(conexion.getRuta(), new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                Imagen.setImageBitmap(response);
            }
        }, 0, 0, ImageView.ScaleType.CENTER, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        VolleySingleton.getInstance(getContext()).addToRequestQueue(imageRequest);
    }

    private void cargarListaAdultosMayores() {
        Boolean Verificador;
        Lista = getView().findViewById(R.id.listaAdultosMayores);
        //Adultos Mayores Frecuentes del Usuario//
        Frecuentes = operador.obtenerAdultosMayoresPorVoluntarioFrecuente(usuario.getIdUsuario());
        //Adultos Mayores en el sistema//
        AdultosMayores = operador.LeerTablaAdultoMayor();
        //Adultos Mayores ya asignados previamente//
        Asignados = operador.obtenerAdultosMayoresAsignados(Fecha);
        //Lista para diferenciar Adultos Frecuentes de No frecuentes despues de los filtos//
        noFrecuentes = new ArrayList<AdultoMayor>();
        //Lista para realizar filtros con Adultos Mayores Pre-seleccionados en el sistema, NO Asignados//
        ArrayList<AdultoMayor> arrayListPre = new ArrayList<AdultoMayor>();
        //Lista con Adultos Mayores Finales//
        ArrayList<AdultoMayor> arrayListFinal = new ArrayList<AdultoMayor>();
        //Listas para acomodar primero los Adultos Mayores frecuentes existentes
        ArrayList<AdultoMayor> arrayListNoFrecuentesVerificados = new ArrayList<AdultoMayor>();
        ArrayList<AdultoMayor> arrayListFrecuentesVerificados = new ArrayList<AdultoMayor>();

        ///Adultos Mayores disponibles
        for(int i=0; i<AdultosMayores.size();i++){
            Verificador = true;
            for(int j=0; j < Asignados.size();j++){
                if(AdultosMayores.get(i).getIdAdultoMayor() == Asignados.get(j).getIdAdultoMayor()){
                    Verificador = false;
                }
            }
            if(Verificador){
                arrayListPre.add(AdultosMayores.get(i));
            }
        }

        for(SeleccionAM registro : Selecciones){
            if(registro.getIdentificador() == usuario.getIdUsuario()) {
                arrayListFinal.addAll(registro.getListaAsigandos());
            }
        }
        Boolean Check = false;
        for( AdultoMayor registroPre : arrayListPre){
            for(SeleccionAM registro : Selecciones){
                for(AdultoMayor registrosAM :registro.getListaAsigandos()){
                    if(registrosAM.getIdAdultoMayor() == registroPre.getIdAdultoMayor()){
                        Check = true;
                    }
                }
            }
            if(!Check){
                arrayListFinal.add(registroPre);
            }
            Check = false;
        }

        for(AdultoMayor AdultoFrecuente : Frecuentes){
            for (int j = 0; j < arrayListFinal.size(); j++) {
                if(arrayListFinal.get(j).getIdAdultoMayor() == AdultoFrecuente.getIdAdultoMayor()) {
                    arrayListFrecuentesVerificados.add(arrayListFinal.get(j));
                }
            }
        }

        boolean Igual;
        for(int i=0; i<arrayListFinal.size();i++){
            Igual = false;
            for(int j=0; j<arrayListFrecuentesVerificados.size();j++){
                if(arrayListFrecuentesVerificados.get(j).getIdAdultoMayor() == arrayListFinal.get(i).getIdAdultoMayor()){
                    Igual = true;
                }
            }
            if(!Igual){
                arrayListNoFrecuentesVerificados.add(arrayListFinal.get(i));
            }
        }
        arrayListFinal.clear();
        arrayListFinal = arrayListFrecuentesVerificados;
        arrayListFinal.addAll(arrayListNoFrecuentesVerificados);
        ListaAdaptadorAsignacionIndividual miLista = new ListaAdaptadorAsignacionIndividual(arrayListFinal,Frecuentes,getContext());
        Lista.setAdapter(miLista);
    }

    @Override
    public void onClick(View v) {
        ArrayList<AdultoMayor> adultoMayorArrayList = new ArrayList<AdultoMayor>();
        try{
            if(Lista != null){
                Adapter adapter = (Adapter)Lista.getAdapter();
                AdultoMayor adultoMayor;
                for(int i = 0; i<adapter.getCount(); i++){
                    adultoMayor = (AdultoMayor) adapter.getItem(i);
                    //Toast.makeText(getContext(), ""+adultoMayor.getNombre()+""+adultoMayor.getCheck(), Toast.LENGTH_SHORT).show();
                    if(adultoMayor.getCheck()){
                      adultoMayorArrayList.add(adultoMayor);
                    }
                }
            }
        }catch (Exception e){

        }
        Boolean Encontrado = false;
        for(int j=0; j<Selecciones.size();j++){
            if(Selecciones.get(j).getIdentificador() == usuario.getIdUsuario()){
                Selecciones.get(j).setListaAsigandos(adultoMayorArrayList);
                Encontrado = true;
                //Toast.makeText(getContext(), "Encontrado ;)", Toast.LENGTH_SHORT).show();
            }
        }
        if(!Encontrado){
            SeleccionAM nuevoregistro = new SeleccionAM();
            nuevoregistro.setIdentificador(usuario.getIdUsuario());
            nuevoregistro.setListaAsigandos(adultoMayorArrayList);
            Selecciones.add(nuevoregistro);
        }
        Bundle bolsa = new Bundle();
        bolsa.putSerializable("Selecciones",Selecciones);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.contenedor, AsignacionAdultoMayorCoordinador.newInstance(bolsa));
        //ft.addToBackStack(null);
        ft.commit();


    }
}
