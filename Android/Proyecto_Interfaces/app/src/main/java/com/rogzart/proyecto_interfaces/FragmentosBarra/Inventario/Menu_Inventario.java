package com.rogzart.proyecto_interfaces.FragmentosBarra.Inventario;

import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.rogzart.proyecto_interfaces.FragmentosBarra.Inventario.IG.ListaInventario;
import com.rogzart.proyecto_interfaces.FragmentosBarra.Inventario.LR.Lista_Rapida;
import com.rogzart.proyecto_interfaces.Modelo.Conexion;
import com.rogzart.proyecto_interfaces.R;

public class Menu_Inventario extends Fragment {

    private Button BtnInventario;
    private Button BtnLista;
    private Conexion conexion;

    public Menu_Inventario() {
        // Required empty public constructor
    }

    public static Menu_Inventario newInstance() {
        Menu_Inventario fragment = new Menu_Inventario();
        return fragment;
    }
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        BtnInventario = getView().findViewById(R.id.btnInventario);
        BtnLista =  getView().findViewById(R.id.btnListaRapida);
        conexion = new Conexion(getContext());

        BtnInventario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.contenedor, ListaInventario.newInstance());
                ft.addToBackStack(null);
                ft.commit();

            }
        });

        BtnLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(conexion.isConnected()){
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.contenedor, Lista_Rapida.newInstance());
                    ft.addToBackStack(null);
                    ft.commit();
                }else{
                    Toast.makeText(getContext(), "Verifica tu Conexion", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu__inventario, container, false);
    }


}
