package com.rogzart.proyecto_interfaces;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.GeoApiContext;
import com.google.maps.android.PolyUtil;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.rogzart.proyecto_interfaces.Modelo.Mapa;

import org.joda.time.DateTime;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private final LatLng Plaza_Principal = new LatLng(20.141053, -103.728672);
    private ArrayList<Mapa> ListaPrincipal;

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private boolean mPermissionDenied = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Toast.makeText(this, ""+getIntent().getSerializableExtra("Lista").getClass().toString(), Toast.LENGTH_SHORT).show();
        ListaPrincipal = (ArrayList<Mapa>) getIntent().getSerializableExtra("Lista");
        //Toast.makeText(this, ""+ListaPrincipal.size(), Toast.LENGTH_SHORT).show();
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    public void generarMarcadores(){

        /*ArrayList<LatLng> x = new ArrayList<LatLng>();
        ListaPrincipal
        x.add(Patricia_Gonzalez_Martinez);
        x.add(Manuel_Alvarez_Figueroa);
        x.add(Ramiro_Perez_Chavez);*/

        for(int i = 0;i<ListaPrincipal.size();i++){
            String NombreCompleto = ListaPrincipal.get(i).getAdultoMayor().getNombre()+" "+ListaPrincipal.get(i).getAdultoMayor().getApellidoPaterno()+" "+ListaPrincipal.get(i).getAdultoMayor().getApellidoMaterno();
            mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(ListaPrincipal.get(i).getUbicacion().getLatitud(),ListaPrincipal.get(i).getUbicacion().getLongitud()))
                    .title(NombreCompleto)
                    .snippet("Adulto Mayor")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)))
                    .showInfoWindow();
            //Toast.makeText(this, ""+ListaPrincipal.get(i).getUbicacion().getIdUbicacion()+"<--->"+ ListaPrincipal.get(i).getUbicacion().getLatitud(), Toast.LENGTH_SHORT).show();
        }

        /*
        mMap.addMarker(new MarkerOptions()
                .position(Manuel_Alvarez_Figueroa)
                .title("jose Arreola")
                .snippet("Adulto Mayor")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        mMap.addMarker(new MarkerOptions()
                .position(Ramiro_Perez_Chavez)
                .title("Martha Berenice")
                .snippet("Adulto Mayor")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

        mMap.addMarker(new MarkerOptions()
                .position(Facundo_Cabral_Ramiro)
                .title("Ivan Garcia")
                .snippet("Adulto Mayor")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        mMap.addMarker(new MarkerOptions()
                .position(Emiliano_Sanchez)
                .title("Emiliano Sanchez")
                .snippet("Adulto Mayor")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        mMap.addMarker(new MarkerOptions()
                .position(Luis_Reyes_Martinez)
                .title("Luis Reyes Martinez")
                .snippet("Adulto Mayor")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));
        mMap.addMarker(new MarkerOptions()
                .position(Joel_Partida_Dominguez)
                .title("Joel Partida Dominguez")
                .snippet("Adulto Mayor")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));
        mMap.addMarker(new MarkerOptions()
                .position(Daniel_Rodriguez_Olmos)
                .title("Daniel Rodriguez Olmos")
                .snippet("Adulto Mayor")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));
        mMap.addMarker(new MarkerOptions()
                .position(Juan_Hernandez_Meza)
                .title("Juan Hernandez Meza")
                .snippet("Adulto Mayor")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        mMap.addMarker(new MarkerOptions()
                .position(Carlos_Meza_Rolfos)
                .title("Carlos Meza Rolfos")
                .snippet("Adulto Mayor")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
*/

    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Plaza_Principal, 15));
        mMap.addMarker(new MarkerOptions()
                .position(Plaza_Principal)
                .title("Pueblo de Atemajac de Brizuela")
                .snippet("Kiosco")
                .icon(BitmapDescriptorFactory
                        .fromResource(android.R.drawable.ic_menu_compass))
                .anchor(0.5f, 0.5f));
        generarMarcadores();
        enableMyLocation();
    }

    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission to access the location is missing.
            PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE,
                    Manifest.permission.ACCESS_FINE_LOCATION, true);
        } else if (mMap != null) {
            // Access to the location has been granted to the app.
            mMap.setMyLocationEnabled(true);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            return;
        }

        if (PermissionUtils.isPermissionGranted(permissions, grantResults,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            // Enable the my location layer if the permission has been granted.
            enableMyLocation();
        } else {
            // Display the missing permission error dialog when the fragments resume.
            mPermissionDenied = true;
        }
    }

    public void moveCamera(View view) {
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Plaza_Principal));
    }
    public void volver(View view){
        Intent intent = new Intent(getApplicationContext(), Barra_desplegable.class);
        finish();
        startActivityForResult(intent, 0);
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), Barra_desplegable.class);
        finish();
        startActivityForResult(intent, 0);
    }
}
