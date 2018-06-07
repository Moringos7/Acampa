package com.rogzart.proyecto_interfaces.FragmentosBarra.Scouter;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.rogzart.proyecto_interfaces.Modelo.Usuario;
import com.rogzart.proyecto_interfaces.R;
import com.rogzart.proyecto_interfaces.sqlite.OperacionesBaseDatos;

import java.util.ArrayList;
import java.util.Calendar;


public class AgregarScouter extends Fragment implements View.OnClickListener {

    private String FechaActual;
    private OperacionesBaseDatos operador;
    private ListView ListG;
    private Usuario user;
    private View afterV;
    private Button Btn;
    public AgregarScouter() {
        // Required empty public constructor
    }

    public static AgregarScouter newInstance() {
        AgregarScouter fragment = new AgregarScouter();
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
        return inflater.inflate(R.layout.fragment_agregar_scouter, container, false);
    }
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        FechaActual = generarFecha();
        operador = OperacionesBaseDatos.obtenerInstancia(getContext());
        Btn = getView().findViewById(R.id.btnAsignarScouter);
        Btn.setOnClickListener(this);
        ArrayList<Usuario> usuarios = operador.obtenerUsuariosnoScouters();
        ListG = getView().findViewById(R.id.lista_scouters);
        Toast.makeText(getContext(), ""+usuarios.size(), Toast.LENGTH_SHORT).show();
        AdaptadorAgregarScouter miLista = new AdaptadorAgregarScouter(usuarios,getContext());
        ListG.setAdapter(miLista);
        ListG.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(user == null){
                    user = (Usuario) adapterView.getItemAtPosition(i);
                    user.setCheck(true);
                    //view.setBackgroundColor(Color.rgb(45, 115, 191));
                }else{
                    user.setCheck(false);
                    Usuario userTemp = (Usuario) adapterView.getItemAtPosition(i);
                    if(user.getIdUsuario() == userTemp.getIdUsuario()){
                        user = null;
                        //view.setBackgroundColor(Color.WHITE);
                    }else{
                        user = null;
                        user = (Usuario)adapterView.getItemAtPosition(i);
                        user.setCheck(true);
                        //view.setBackgroundColor(Color.WHITE);
                    }
                }

                if(afterV == null){
                    afterV = view;
                    view.setBackgroundColor(Color.rgb(45, 115, 191));

                }else{
                    if(afterV == view){
                        afterV = null;
                        view.setBackgroundColor(Color.WHITE);
                    }else{
                        afterV.setBackgroundColor(Color.WHITE);
                        view.setBackgroundColor(Color.rgb(45, 115, 191));
                        afterV = view;
                    }
                }
                /*
                if(afterV == null){
                    afterV.setBackgroundColor(Color.WHITE);
                    view.setBackgroundColor(Color.rgb(45, 115, 191));
                }
                if(afterV == view){
                    view.setBackgroundColor(Color.WHITE);
                    afterV = null;
                }else{
                    view.setBackgroundColor(Color.rgb(45, 115, 191));
                    afterV = view;
                }*/

                if(user != null){
                    Toast.makeText(getContext(), "Seleccionado: "+user.getNombre(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "-> No seleccion", Toast.LENGTH_SHORT).show();
                }
                //Toast.makeText(getContext(), "P: "+l, Toast.LENGTH_SHORT).show();
            }
        });

    }
    private String generarFecha(){
        String Fecha;
        Calendar c = Calendar.getInstance();
        int Dia = c.get(Calendar.DAY_OF_MONTH);
        int Mes = c.get(Calendar.MONTH)+1;
        int Anio = c.get(Calendar.YEAR);
        String decenaD = "";
        String decenaM = "";
        if(Mes < 10){
            decenaM = "0";
        }
        if(Dia < 10){
            decenaD = "0";
        }
        Fecha = String.valueOf(Anio)+"-"+decenaM+String.valueOf(Mes)+"-"+decenaD+String.valueOf(Dia);
        return Fecha;
    }


    @Override
    public void onClick(View v) {
        if(user != null){
            Toast.makeText(getContext(), ""+user.getNombre(), Toast.LENGTH_SHORT).show();

        }else{
        Toast.makeText(getContext(), "Selecciona un Usuario", Toast.LENGTH_SHORT).show();
        }
    }
}
