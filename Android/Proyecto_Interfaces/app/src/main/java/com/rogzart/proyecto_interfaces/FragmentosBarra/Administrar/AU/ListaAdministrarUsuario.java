package com.rogzart.proyecto_interfaces.FragmentosBarra.Administrar.AU;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.rogzart.proyecto_interfaces.Barra_desplegable;
import com.rogzart.proyecto_interfaces.FragmentosBarra.Administrar.MenuAdministrar;
import com.rogzart.proyecto_interfaces.Modelo.Conexion;
import com.rogzart.proyecto_interfaces.Modelo.Usuario;
import com.rogzart.proyecto_interfaces.R;
import com.rogzart.proyecto_interfaces.sqlite.OperacionesBaseDatos;

import java.util.ArrayList;

public class ListaAdministrarUsuario extends Fragment {
    EditText buscador;
    ListView lista;
    OperacionesBaseDatos operador;
    Conexion conexion;
    public ListaAdministrarUsuario() {
    }

    public static ListaAdministrarUsuario newInstance() {
        ListaAdministrarUsuario fragment = new ListaAdministrarUsuario();
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
        return inflater.inflate(R.layout.fragment_administrar_usuario, container, false);

    }
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        buscador = (EditText) getView().findViewById(R.id.busqueda_administar_usuario);
        lista = (ListView) getView().findViewById(R.id.lista_administar_usuario);
        operador = OperacionesBaseDatos.obtenerInstancia(getContext());
        conexion = new Conexion(getContext());
        ArrayList<Usuario> arrayList = operador.LeerTablaUsuario(getContext());
        final ListaAdaptadorUsuario miLista = new ListaAdaptadorUsuario(arrayList, getContext());
        lista.setAdapter(miLista);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(conexion.isConnected()){
                    Usuario user = (Usuario) adapterView.getItemAtPosition(i);
                    //Toast.makeText(getContext(), user.getNombre() + " " + user.getApellidoPaterno(), Toast.LENGTH_SHORT).show();
                    Bundle packet = new Bundle();
                    packet.putSerializable("Objeto",user);

                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.contenedor, AdministrarUsuario.newInstance(packet));
                    ft.addToBackStack(null);
                    ft.commit();
                }else{
                    Toast.makeText(getContext(), "Verifique su conexion", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getContext(), Barra_desplegable.class);
                    startActivityForResult(intent, 0);
                }
            }
        });
    }
}
