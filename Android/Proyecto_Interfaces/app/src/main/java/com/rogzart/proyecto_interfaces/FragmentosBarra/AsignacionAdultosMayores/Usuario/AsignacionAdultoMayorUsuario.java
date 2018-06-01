package com.rogzart.proyecto_interfaces.FragmentosBarra.AsignacionAdultosMayores.Usuario;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rogzart.proyecto_interfaces.R;


public class AsignacionAdultoMayorUsuario extends Fragment {
    public AsignacionAdultoMayorUsuario(){

    }
    public static AsignacionAdultoMayorUsuario newInstance() {
        AsignacionAdultoMayorUsuario fragment = new AsignacionAdultoMayorUsuario();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_asignacion_adulto_mayor_usuario, container, false);

    }

    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

    }
}
