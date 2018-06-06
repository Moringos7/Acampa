package com.rogzart.proyecto_interfaces.FragmentosBarra.Eventos;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.rogzart.proyecto_interfaces.Modelo.Conexion;
import com.rogzart.proyecto_interfaces.Modelo.Evento;
import com.rogzart.proyecto_interfaces.Modelo.TipoEvento;
import com.rogzart.proyecto_interfaces.R;
import com.rogzart.proyecto_interfaces.sqlite.OperacionesBaseDatos;

public class ListaEventosMain extends Fragment {
    private Bundle packet;
    private Evento evento;
    private OperacionesBaseDatos operador;
    private Context contexto;
    private EditText ETipoEvento, ELugar,EHora,EFecha,EInformacion;
    private Button btnGuardar;

    public ListaEventosMain() {

    }
    @SuppressLint("ValidFragment")
    public  ListaEventosMain(Bundle bundle){
        packet=bundle;
        evento = (Evento) packet.getSerializable("casos");


    }
    public static ListaEventosMain newInstance(Bundle bundle){
        ListaEventosMain fragmento =  new ListaEventosMain(bundle);
        return fragmento;
    }
    @Override
    public void onCreate(Bundle savedInstanceState){ super.onCreate(savedInstanceState);}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.detalle_eventos, container, false);
    }
    public void onActivityCreated (Bundle state) {
        super.onActivityCreated(state);
        ETipoEvento = (EditText) getView().findViewById(R.id.detalles_eventos_tipoeventos);
        EFecha = (EditText) getView().findViewById(R.id.detalles_evento_fecha);
        EInformacion = (EditText) getView().findViewById(R.id.detalles_evento_informacion);
        ELugar = (EditText) getView().findViewById(R.id.detalles_evento_lugar);
        EHora = (EditText) getView().findViewById(R.id.detalles_evento_hora);
        Conexion conexion= new Conexion(getContext());
        operador= OperacionesBaseDatos.obtenerInstancia(contexto);

        EFecha.setText(String.valueOf(evento.getFecha()));
        EHora.setText(String.valueOf(evento.getHora()));
        EInformacion.setText(evento.getInformacion());
        ELugar.setText(evento.getLugar());
        ETipoEvento.setText(String.valueOf(evento.getFkTipoEvento()));




    }


}