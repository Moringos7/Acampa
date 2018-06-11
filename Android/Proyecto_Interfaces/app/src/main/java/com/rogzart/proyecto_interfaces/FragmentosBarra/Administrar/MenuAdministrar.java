package com.rogzart.proyecto_interfaces.FragmentosBarra.Administrar;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.rogzart.proyecto_interfaces.FragmentosBarra.Administrar.AU.ListaAdministrarUsuario;
import com.rogzart.proyecto_interfaces.Modelo.Conexion;
import com.rogzart.proyecto_interfaces.R;



public class MenuAdministrar extends Fragment {

    public  MenuAdministrar(){

    }
    public static MenuAdministrar newInstance() {
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
        final Conexion conexion = new Conexion(getContext());
        Button Usuarios = (Button) getView().findViewById(R.id.btnUsuarios);

        Usuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                if (conexion.isConnected()){
                    ft.replace(R.id.contenedor, ListaAdministrarUsuario.newInstance());
                    ft.addToBackStack(null);
                    ft.commit();
                }else{
                    Toast.makeText(getContext(), "Verifique su conexion", Toast.LENGTH_SHORT).show();
                }

            }
        });
        /*
        Button AdultosMayores = (Button)getView().findViewById(R.id.btnAdultosMayores);
        AdultosMayores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                if (conexion.isConnected()){
                    ft.replace(R.id.contenedor, ListaAdministrarUsuario.newInstance());
                    ft.addToBackStack(null);
                    ft.commit();
                }else{
                    Toast.makeText(getContext(), "Verifique su conexion", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Button Coordinadores = (Button)getView().findViewById(R.id.btnCoordiadores);
        Coordinadores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                if (conexion.isConnected()){
                    ft.replace(R.id.contenedor, ListaAdministrarUsuario.newInstance());
                    ft.addToBackStack(null);
                    ft.commit();
                }else{
                    Toast.makeText(getContext(), "Verifique su conexion", Toast.LENGTH_SHORT).show();
                }
            }
        });*/

    }


}
