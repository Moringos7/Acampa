package com.rogzart.proyecto_interfaces.FragmentosBarra.Inventario;

import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.rogzart.proyecto_interfaces.R;
import com.rogzart.proyecto_interfaces.sqlite.OperacionesBaseDatos;

import java.util.ArrayList;
import java.util.List;


public class Inventario extends Fragment {
    ArrayList<String> listainventario;
    OperacionesBaseDatos operador;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.f_inventario, container, false);
    }
    public void onActivityCreated(Bundle state) {
        ListView ListaInventario = (ListView) getView().findViewById(R.id.ListaG);
        operador = OperacionesBaseDatos.obtenerInstancia(getContext());
        List<com.rogzart.proyecto_interfaces.Modelo.Inventario> list =  operador.LeerTablaInventario();
        ArrayAdapter<String> adaptador; //Bugle
        //Bundle c;
        super.onActivityCreated(state);

    }
 }

