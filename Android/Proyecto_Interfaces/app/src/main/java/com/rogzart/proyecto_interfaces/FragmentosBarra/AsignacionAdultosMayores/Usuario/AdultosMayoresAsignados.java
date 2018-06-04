package com.rogzart.proyecto_interfaces.FragmentosBarra.AsignacionAdultosMayores.Usuario;

import android.annotation.SuppressLint;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.rogzart.proyecto_interfaces.FragmentosBarra.Administrar.AU.AdministrarUsuario;
import com.rogzart.proyecto_interfaces.FragmentosBarra.Administrar.MenuAdministrar;
import com.rogzart.proyecto_interfaces.FragmentosBarra.InformacionAdultoMayor.InformacionAdultoMayor;
import com.rogzart.proyecto_interfaces.Modelo.AdultoMayor;
import com.rogzart.proyecto_interfaces.R;

import java.util.ArrayList;


@SuppressLint("ValidFragment")
public class AdultosMayoresAsignados extends Fragment implements View.OnClickListener {

    private ArrayList<AdultoMayor> Asignados;
    private ListView Lista;
    private Button btn;
    private TextView numero;

    @SuppressLint("ValidFragment")
    public AdultosMayoresAsignados(Bundle bolsa) {
        Asignados = (ArrayList<AdultoMayor>) bolsa.getSerializable("AdultosMayores");
    }

    public static AdultosMayoresAsignados newInstance(Bundle bolsa) {
        AdultosMayoresAsignados fragment = new AdultosMayoresAsignados(bolsa);
        return fragment;
    }
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        Lista = getView().findViewById(R.id.listAsignados);
        numero = getView().findViewById(R.id.numeroAdultos);
        numero.setText(Integer.toString(Asignados.size()));
        configurarLista();
        btn = getView().findViewById(R.id.btnTrazaRutaAsignacion);
        btn.setOnClickListener(this);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_adultos_mayores_asignados, container, false);
    }
    void configurarLista(){
        ListaAdaptadorAsignados ListaAdaptador = new ListaAdaptadorAsignados(Asignados,getContext());
        Lista.setAdapter(ListaAdaptador);
        Lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AdultoMayor miAdultoMayor = (AdultoMayor) parent.getItemAtPosition(position);
                Bundle miPaquete = new Bundle();
                miPaquete.putSerializable("AdultoMayor",miAdultoMayor);

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.contenedor, InformacionAdultoMayor.newInstance(miPaquete));
                ft.addToBackStack(null);
                ft.commit();
            }
        });

    }
    @Override
    public void onClick(View v) {
        //Obtener Ubicacion por Id

    }
}
