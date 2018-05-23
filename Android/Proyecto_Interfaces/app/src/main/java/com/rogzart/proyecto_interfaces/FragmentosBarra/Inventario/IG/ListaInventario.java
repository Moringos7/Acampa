package com.rogzart.proyecto_interfaces.FragmentosBarra.Inventario.IG;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.rogzart.proyecto_interfaces.Modelo.Conexion;

import com.rogzart.proyecto_interfaces.Modelo.Inventario;
import com.rogzart.proyecto_interfaces.R;
import com.rogzart.proyecto_interfaces.sqlite.OperacionesBaseDatos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class ListaInventario extends Fragment {
    ArrayList<String> listainventario;
    SearchView buscador;
    ListView listaInventario;
    Conexion conexion;
    OperacionesBaseDatos operador;

    public static ListaInventario newInstance(){
        ListaInventario fragmento = new ListaInventario();
        return fragmento;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_inventario_general, container, false);
    }
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        listaInventario = (ListView) getView().findViewById(R.id.list_inventario_general_lista);
        buscador = getView().findViewById(R.id.list_inventario_general_buscador);
        operador = OperacionesBaseDatos.obtenerInstancia(getContext());
        conexion =  new Conexion(getContext());
        ArrayList <Inventario> arrayList = operador.LeerTablaInventario();
        final ListaAdaptadorInventario miLista = new ListaAdaptadorInventario(arrayList, getContext());
        listaInventario.setAdapter(miLista);
        listaInventario.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int x,  long l) {
                Inventario inventario1 =  (Inventario) adapterView.getItemAtPosition(x);
                Bundle bolsa = new  Bundle();
                bolsa.putSerializable("articulo", (Serializable) inventario1);

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.contenedor, ListaInventarioMain.newInstance(bolsa));
                transaction.addToBackStack(null);
                transaction.commit();





            }
        });

        }
        ArrayAdapter<String> adaptador; //Bugle
        //Bundle c;


    }


