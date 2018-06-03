package com.rogzart.proyecto_interfaces.FragmentosBarra.AsignacionAdultosMayores.Usuario;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rogzart.proyecto_interfaces.Modelo.AdultoMayor;
import com.rogzart.proyecto_interfaces.R;

import java.util.ArrayList;


@SuppressLint("ValidFragment")
public class AdultosMayoresAsignados extends Fragment {

    private ArrayList<AdultoMayor> Asignados;
    @SuppressLint("ValidFragment")
    public AdultosMayoresAsignados(Bundle bolsa) {
        Asignados = (ArrayList<AdultoMayor>) bolsa.getSerializable("AdultosMayores");
    }

    public static AdultosMayoresAsignados newInstance(Bundle bolsa) {
        AdultosMayoresAsignados fragment = new AdultosMayoresAsignados(bolsa);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_adultos_mayores_asignados, container, false);
    }
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
    }
}
