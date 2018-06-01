package com.rogzart.proyecto_interfaces.FragmentosBarra.Eventos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rogzart.proyecto_interfaces.R;


public class Eventos extends Fragment {
    public  Eventos(){

    }

    public static Eventos newInstance() {
        Eventos fragment = new Eventos();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_eventos, container, false);

    }

    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
    }
}
