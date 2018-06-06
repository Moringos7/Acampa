package com.rogzart.proyecto_interfaces.FragmentosBarra.InformacionAdultoMayor;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.rogzart.proyecto_interfaces.FragmentosBarra.Administrar.MenuAdministrar;
import com.rogzart.proyecto_interfaces.FragmentosBarra.Inventario.IG.ListaInventario;
import com.rogzart.proyecto_interfaces.FragmentosBarra.VoluntarioFrecuente.AsignarVoluntarioFrecuente;
import com.rogzart.proyecto_interfaces.Modelo.AdultoMayor;
import com.rogzart.proyecto_interfaces.Modelo.Conexion;
import com.rogzart.proyecto_interfaces.Modelo.Domicilio;
import com.rogzart.proyecto_interfaces.Modelo.FotoAlrededores;
import com.rogzart.proyecto_interfaces.Modelo.Ubicacion;
import com.rogzart.proyecto_interfaces.Modelo.Usuario;
import com.rogzart.proyecto_interfaces.R;
import com.rogzart.proyecto_interfaces.Singleton.LogUser;
import com.rogzart.proyecto_interfaces.Singleton.VolleySingleton;
import com.rogzart.proyecto_interfaces.sqlite.OperacionesBaseDatos;

import java.util.ArrayList;

@SuppressLint("ValidFragment")
public class InformacionAdultoMayor extends Fragment implements OnMapReadyCallback {

    private AdultoMayor AdultoMayor;
    private AlertDialog.Builder Alerta;
    private Domicilio Domicilio;
    private Ubicacion Ubicacion;
    private OperacionesBaseDatos operador;
    private MapView mMapView;
    private GoogleMap mGoogleMap;
    private LatLng latLng;
    private TextView InfoDependencia,Info,Nombre,Apellidos,Diabetico,Calle,Colonia,VoluntarioFrecuente;
    private TextView Nivel1,Nivel2,Nivel3;
    private ImageView Fotografia,FotografiaDomicilio;
    private Boolean Visible = false;
    private Conexion conexion;
    private Button btnVoluntarioFrecuente;
    private LinearLayout Cercanos;

    @SuppressLint("ValidFragment")
    public InformacionAdultoMayor(Bundle paquete) {
        AdultoMayor = (AdultoMayor) paquete.getSerializable("AdultoMayor");
    }
    public static InformacionAdultoMayor newInstance(Bundle paquete){
        InformacionAdultoMayor fragment = new InformacionAdultoMayor(paquete);
        return fragment;
    }

    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        conexion = new Conexion(getContext());
        operador = OperacionesBaseDatos.obtenerInstancia(getContext());
        configuracionPrincipalAdultoMayor();
        configuracionDependencia();
        configurarDomicilio();
        configurarLugaresCercanos();
        configurarVoluntarioFrecuente();
        configurarDialogs();

        //

        configurarComentarios();



    }
    private void configurarDialogs(){
        Alerta = new AlertDialog.Builder(getContext());
        Alerta.setTitle("Modificar");
        Alerta.setMessage("¿Deseas modificar el Voluntario Encragado?");
        Alerta.setCancelable(false);
        Alerta.setPositiveButton("Modificar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                Bundle bolsa = new Bundle();
                bolsa.putSerializable("AdultoMayor",AdultoMayor);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.contenedor, AsignarVoluntarioFrecuente.newInstance(bolsa));
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        Alerta.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {

            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_informacion_adulto_mayor, container, false);

        mMapView = (MapView) v.findViewById(R.id.map2);
        mMapView.onCreate(savedInstanceState);
        mMapView.getMapAsync(this);

        return v;
    }
    void configuracionPrincipalAdultoMayor(){
        String apellidos;
        Nombre = getView().findViewById(R.id.Nombre);
        Apellidos = getView().findViewById(R.id.Apellidos);
        Fotografia = getView().findViewById(R.id.ImagenAdultoMayor);
        Diabetico = getView().findViewById(R.id.Diabetico);
        Nombre.setText(AdultoMayor.getNombre());
        apellidos = AdultoMayor.getApellidoPaterno() +" "+AdultoMayor.getApellidoMaterno();
        Apellidos.setText(apellidos);

        conexion.setRuta("WebService/"+AdultoMayor.getFotografia());
        ImageRequest imageRequest = new ImageRequest(conexion.getRuta(), new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                Fotografia.setImageBitmap(response);
            }
        }, 0, 0, ImageView.ScaleType.CENTER, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //imagen local
            }
        });
        VolleySingleton.getInstance(getContext()).addToRequestQueue(imageRequest);

        if(AdultoMayor.getDiabetico() == 0){
            Diabetico.setVisibility(View.GONE);
        }
    }
    void configuracionDependencia(){
        String Definicion =
                "Nivel 1: El Adulto Mayor puede realizar todas sus actividades cotidianas sin ayuda de otros. \n \n"+
                        "Nivel 2: El Adulto Mayor necesita ayuda en algunas de sus actividades cotianas. \n \n"+
                        "Nivel 3: El Adulto Mayor necesita ayuda en todas o casi todas sus actividades cotidianas.";
        InfoDependencia = getView().findViewById(R.id.InfoDependencia);
        Info = getView().findViewById(R.id.TextQuestion);
        InfoDependencia.setText(Definicion);
        Info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Visible = !Visible;
                if(Visible){
                    InfoDependencia.setVisibility(View.VISIBLE);
                }else{
                    InfoDependencia.setVisibility(View.GONE);
                }

            }
        });
        int NivelDependencia = AdultoMayor.getFkDependencia();
        Nivel1 = getView().findViewById(R.id.Nivel1);
        Nivel2 = getView().findViewById(R.id.Nivel2);
        Nivel3 = getView().findViewById(R.id.Nivel3);

        if(NivelDependencia > 0){
            Nivel1.setBackgroundColor(Color.argb(255, 26, 206, 246));
        }
        if(NivelDependencia > 1){
            Nivel2.setBackgroundColor(Color.argb(255, 26, 206, 246));
        }
        if(NivelDependencia > 2){
            Nivel3.setBackgroundColor(Color.argb(255, 26, 206, 246));
        }

    }
    void configurarDomicilio(){
        Calle = getView().findViewById(R.id.CalleAdultoMayor);
        Colonia = getView().findViewById(R.id.ColoniaAdultoMayor);
        FotografiaDomicilio = getView().findViewById(R.id.ImagenDomicilioAdultoMayor);
        String calle;
        String colonia;

        Domicilio = operador.obtenerDomicilio(AdultoMayor.getFkDomicilio());
        calle = Domicilio.getCalle()+" #"+Domicilio.getNumero();
        colonia ="Colonia "+Domicilio.getColonia();
        Calle.setText(calle);
        Colonia.setText(colonia);
        conexion.setRuta("WebService/"+Domicilio.getFoto());
        ImageRequest imageRequest = new ImageRequest(conexion.getRuta(), new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                FotografiaDomicilio.setImageBitmap(response);
            }
        }, 0, 0, ImageView.ScaleType.CENTER, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //imagen local
            }
        });
        VolleySingleton.getInstance(getContext()).addToRequestQueue(imageRequest);
    }
    void configurarLugaresCercanos(){
        Cercanos = getView().findViewById(R.id.LayoutCercanos);
        ArrayList<FotoAlrededores> fotos = operador.obtenerFotoAlredoresporIdDomicilio(Domicilio.getIdDomicilio());
        if(fotos.size() < 1){
            Cercanos.setVisibility(View.GONE);
        }else{
            RecyclerView recyclerView =  getView().findViewById(R.id.my_recycler_view);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
            recyclerView.setAdapter(new LugaresCercanosAdapter(fotos,getContext()));
        }
    }
    void configurarVoluntarioFrecuente(){
        VoluntarioFrecuente = getView().findViewById(R.id.VoluntarioFrecuente);
        btnVoluntarioFrecuente = getView().findViewById(R.id.btnAsignarVoluntarioFrecuente);
        Usuario Frecuente = operador.obtenerVoluntarioFrecuente(AdultoMayor.getIdAdultoMayor());
        if(Frecuente.getIdUsuario() == 0){
            if(LogUser.obtenerInstancia(getContext()).getCoordinador() > 0){
                VoluntarioFrecuente.setVisibility(View.GONE);
            }else{
                btnVoluntarioFrecuente.setVisibility(View.GONE);
            }
        }else{
            btnVoluntarioFrecuente.setVisibility(View.GONE);
            String NombreCompleto  = Frecuente.getNombre()+" "+Frecuente.getApellidoPaterno()+" "+Frecuente.getApellidoMaterno();
            VoluntarioFrecuente.setText(NombreCompleto);
            VoluntarioFrecuente.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(LogUser.obtenerInstancia(getContext()).getCoordinador() > 0){
                        Alerta.show();
                    }
                }
            });
        }


        btnVoluntarioFrecuente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bolsa = new Bundle();
                bolsa.putSerializable("AdultoMayor",AdultoMayor);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.contenedor, AsignarVoluntarioFrecuente.newInstance(bolsa));
                ft.addToBackStack(null);
                ft.commit();
            }
        });
    }
    void configurarComentarios(){

    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        Ubicacion = operador.obtenerUbicacion(Domicilio.getFkUbicacion());
        latLng = new LatLng(Ubicacion.getLatitud(), Ubicacion.getLongitud());
        //latLng = new LatLng(0, 0);
        //Toast.makeText(getContext(), "Latitud: "+latLng.latitude+" Longitud: "+latLng.longitude, Toast.LENGTH_SHORT).show();
        mGoogleMap = googleMap;
        mGoogleMap.getUiSettings().setZoomControlsEnabled(true);
        mGoogleMap.addMarker(new MarkerOptions().position(latLng));
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}
