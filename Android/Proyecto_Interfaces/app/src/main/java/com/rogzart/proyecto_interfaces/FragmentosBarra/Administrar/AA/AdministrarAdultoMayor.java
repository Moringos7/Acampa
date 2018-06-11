package com.rogzart.proyecto_interfaces.FragmentosBarra.Administrar.AA;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rogzart.proyecto_interfaces.R;


public class AdministrarAdultoMayor extends Fragment {


    public AdministrarAdultoMayor() {
        // Required empty public constructor
    }


    public static AdministrarAdultoMayor newInstance() {
        AdministrarAdultoMayor fragment = new AdministrarAdultoMayor();
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
        return inflater.inflate(R.layout.fragment_administrar_adulto_mayor, container, false);
    }
    
}
