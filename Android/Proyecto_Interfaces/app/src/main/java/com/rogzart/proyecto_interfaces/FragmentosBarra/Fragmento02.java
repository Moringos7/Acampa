package com.rogzart.proyecto_interfaces.FragmentosBarra;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rogzart.proyecto_interfaces.R;


public class Fragmento02 extends Fragment {
    public Fragmento02() {
        // Required empty public constructor
    }
    public static Fragmento02 newInstance() {
        Fragmento02 fragment = new Fragmento02();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.f_lugares, container, false);
    }

}
