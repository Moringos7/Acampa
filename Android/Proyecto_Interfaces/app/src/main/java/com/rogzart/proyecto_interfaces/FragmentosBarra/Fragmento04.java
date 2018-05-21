package com.rogzart.proyecto_interfaces.FragmentosBarra;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rogzart.proyecto_interfaces.R;


public class Fragmento04 extends Fragment {

    public Fragmento04() {
        // Required empty public constructor
    }
    public static Fragmento04 newInstance() {
        Fragmento04 fragment = new Fragmento04();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.f_asignacion_adultos_m, container, false);
    }

}
