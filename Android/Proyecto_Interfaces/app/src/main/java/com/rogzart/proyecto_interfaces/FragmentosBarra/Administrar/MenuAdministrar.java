package com.rogzart.proyecto_interfaces.FragmentosBarra.Administrar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.rogzart.proyecto_interfaces.R;



public class MenuAdministrar extends Fragment {

    public static MenuAdministrar newInstance(String param1, String param2) {
        MenuAdministrar fragment = new MenuAdministrar();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu_administrar, container, false);

    }

    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        Button Usuarios = (Button) getView().findViewById(R.id.btnUsuarios);
        Usuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        Button AdultosMayores = (Button)getView().findViewById(R.id.btnAdultosMayores);
        AdultosMayores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        Button Coordinadores = (Button)getView().findViewById(R.id.btnCoordiadores);
        Coordinadores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


}
