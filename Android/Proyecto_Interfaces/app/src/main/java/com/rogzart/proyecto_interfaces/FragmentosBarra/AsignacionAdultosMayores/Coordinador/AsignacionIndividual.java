package com.rogzart.proyecto_interfaces.FragmentosBarra.AsignacionAdultosMayores.Coordinador;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rogzart.proyecto_interfaces.R;



public class AsignacionIndividual extends Fragment {


    public AsignacionIndividual() {
        // Required empty public constructor
    }


    public static AsignacionIndividual newInstance() {
        AsignacionIndividual fragment = new AsignacionIndividual();
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
        return inflater.inflate(R.layout.fragment_asignacion_individual, container, false);
    }


}
