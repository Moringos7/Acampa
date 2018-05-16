package com.rogzart.proyecto_interfaces.ActivitysConexion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.rogzart.proyecto_interfaces.InterfacesLogin.Inicio;
import com.rogzart.proyecto_interfaces.R;

public class Conexion_Exitosa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conexion__exitosa);
        final Button BtnExito =  findViewById(R.id.btnExito);
        BtnExito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), Inicio.class);
                startActivityForResult(intent, 0);
            }
        });
    }
}
