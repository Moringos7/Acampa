package com.rogzart.proyecto_interfaces.FragmentosBarra.InformacionAdultoMayor;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.rogzart.proyecto_interfaces.Modelo.AdultoMayor;
import com.rogzart.proyecto_interfaces.R;

@SuppressLint("ValidFragment")
public class InformacionAdultoMayor extends Fragment implements OnMapReadyCallback {

    private AdultoMayor AdultoMayor;
    private GoogleMap mGoogleMap;
    private MapView mMapView;
    private View nView;
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
        Toast.makeText(getContext(), ""+AdultoMayor.getNombre(), Toast.LENGTH_SHORT).show();
        //getMapAsync(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        nView = inflater.inflate(R.layout.fragment_informacion_adulto_mayor, container, false);
        return nView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
            super.onViewCreated(view,savedInstanceState);

            mMapView =(MapView) nView.findViewById(R.id.map2);
            if(mMapView == null){
                mMapView.onCreate(null);
                mMapView.onResume();
                mMapView.getMapAsync(this);
            }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        mGoogleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.addMarker(new MarkerOptions().position(new LatLng(40.609247,-74.644502)).title("State").snippet("Hola Mundo"));
        CameraPosition Liberty = CameraPosition.builder().target(new LatLng(40.609247,-74.644502)).zoom(16).bearing(0).tilt(45).build();
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(Liberty));
    }
}
