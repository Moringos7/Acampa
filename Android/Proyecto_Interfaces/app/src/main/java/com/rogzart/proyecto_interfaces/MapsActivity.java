package com.rogzart.proyecto_interfaces;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;

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

import org.joda.time.DateTime;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private final LatLng Plaza_Principal = new LatLng(20.139004271838623, -103.7265372);
    private final LatLng Patricia_Gonzalez_Martinez  = new LatLng(20.142085712792106, -103.72972363168708);
    private final LatLng Manuel_Alvarez_Figueroa = new LatLng(20.142386141478696, -103.73019386684086);
    private final LatLng Ramiro_Perez_Chavez = new LatLng(20.142131421109774, -103.72994536046644);
    private final LatLng Facundo_Cabral_Ramiro = new LatLng(20.14085736092522, -103.73204441725716);
    private final LatLng Emiliano_Sanchez = new LatLng(20.14300087813121, -103.72702715194674);
    private final LatLng Luis_Reyes_Martinez = new LatLng(20.134919736573494, -103.72940407548322);
    private final LatLng Joel_Partida_Dominguez = new LatLng(20.135362833793494, -103.73079345975293);
    private final LatLng Daniel_Rodriguez_Olmos = new LatLng(20.134010855768565, -103.72865686742534);
    private final LatLng Juan_Hernandez_Meza = new LatLng(20.141900196836566, -103.72355458357544);
    private final LatLng Carlos_Meza_Rolfos = new LatLng(20.14014967111393, -103.72413166982767);

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private boolean mPermissionDenied = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        MarkerOptions markerOptions = new MarkerOptions();
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Plaza_Principal, 15));
        // mapa.addPolyline(new PolylineOptions().add(atemajac,san_emiliano).width(5).color(Color.RED));
        mMap.addMarker(new MarkerOptions()
                .position(Plaza_Principal)
                .title("Atemajac")
                .snippet("Pueblo de Atemajac de Brizuela")
                .icon(BitmapDescriptorFactory
                        .fromResource(android.R.drawable.ic_menu_compass))
                .anchor(0.5f, 0.5f));
        mMap.addMarker(new MarkerOptions()
                .position(Patricia_Gonzalez_Martinez)
                .title("juan")
                .snippet("Adulto Mayor")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

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

        String str_org = "origin=" + Plaza_Principal.latitude +","+Plaza_Principal.longitude;
        String str_dest = "destination=" + Emiliano_Sanchez.latitude+","+Emiliano_Sanchez.longitude;
        enableMyLocation();
        DateTime now = new DateTime();
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

    public void animateCamera(View view) {
        if (mMap.getMyLocation() != null)
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                    new LatLng(mMap.getMyLocation().getLatitude(),
                            mMap.getMyLocation().getLongitude()), 15));
    }
    private GeoApiContext getGeoContext() {
        GeoApiContext geoApiContext = new GeoApiContext();
        return geoApiContext.setQueryRateLimit(3).setApiKey(getString(R.string.google_maps_key)).setConnectTimeout(1, TimeUnit.SECONDS).setReadTimeout(1, TimeUnit.SECONDS).setWriteTimeout(1, TimeUnit.SECONDS);
    }


    private void addMarkersToMap(DirectionsResult results, GoogleMap nMap) {
        nMap.addMarker(new MarkerOptions().position(new LatLng(results.routes[0].legs[0].startLocation.lat,results.routes[0].legs[0].startLocation.lng)).title(results.routes[0].legs[0].startAddress));
        nMap.addMarker(new MarkerOptions().position(new LatLng(results.routes[0].legs[0].endLocation.lat,results.routes[0].legs[0].endLocation.lng)).title(results.routes[0].legs[0].startAddress).snippet(getEndLocationTitle(results)));
    }

    private String getEndLocationTitle(DirectionsResult results) {
        return  "Time :"+ results.routes[0].legs[0].duration.humanReadable + " Distance :" + results.routes[0].legs[0].distance.humanReadable;
    }
    private void addPolyline(DirectionsResult results, GoogleMap mapa) {
        List<LatLng> decodedPath = PolyUtil.decode(results.routes[0].overviewPolyline.getEncodedPath());
        mapa.addPolyline(new PolylineOptions().addAll(decodedPath));
    }

}
