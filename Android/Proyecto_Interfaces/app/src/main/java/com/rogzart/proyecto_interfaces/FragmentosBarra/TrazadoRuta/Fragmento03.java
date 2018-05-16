package com.rogzart.proyecto_interfaces.FragmentosBarra.TrazadoRuta;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.rogzart.proyecto_interfaces.MapsActivity;
import com.rogzart.proyecto_interfaces.R;


public class Fragmento03 extends Fragment {
    Button mapa;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.f_trazado_de_ruta, container, false);
        View view = inflater.inflate(R.layout.f_trazado_de_ruta, container, false);






        return view;
    }
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        mapa = (Button) getView().findViewById(R.id.BMapa);
        mapa.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent mapa = new Intent(getActivity(), MapsActivity.class);
                                        startActivity(mapa);

                                    }
                                }


        );
    }
}

