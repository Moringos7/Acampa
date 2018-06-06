package com.rogzart.proyecto_interfaces.FragmentosBarra.Sugerencias;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rogzart.proyecto_interfaces.R;

public class menu_sugerencias extends Fragment {

    public menu_sugerencias() {
        // Required empty public constructor
    }

    public static menu_sugerencias newInstance() {
        menu_sugerencias fragment = new menu_sugerencias();
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
        return inflater.inflate(R.layout.fragment_menu_sugerencias, container, false);
    }
}
