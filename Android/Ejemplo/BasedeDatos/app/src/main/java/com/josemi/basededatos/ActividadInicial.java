package com.josemi.basededatos;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.josemi.basededatos.modelo.Producto;
import com.josemi.basededatos.sqlite.ActualizacionBaseDatos;
import com.josemi.basededatos.sqlite.OperacionesBaseDatos;

import java.util.Calendar;

public class ActividadInicial extends AppCompatActivity {

    OperacionesBaseDatos operador;
    Producto Mer;
    ActualizacionBaseDatos Act;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final String fechaActual = Calendar.getInstance().getTime().toString();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_inicial);
        Act = ActualizacionBaseDatos.CreacionBaseDatos(getApplicationContext());
        operador = OperacionesBaseDatos.obtenerInstancia(getApplicationContext());
        Mer = new Producto();
        final Button mEmailSignInButton = (Button) findViewById(R.id.button1);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Mer = new Producto(4,"Dogo", (float) 4.5,35);
                operador.InsertarProducto(Mer);*/
                Act.ActualizacionProducto(ActividadInicial.this);
                //Toast.makeText(ActividadInicial.this, "Hola", Toast.LENGTH_SHORT).show();
            }
        });
        final Button Leer = (Button) findViewById(R.id.button2);
        Leer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Cursor c = operador.LeerProducto();
                if(c.moveToFirst()){
                    do{
                        int id = c.getInt(1);
                        String nombre = c.getString(2);
                        double precio = c.getDouble(3);
                        int exis = c.getInt(4);
                        Toast.makeText(ActividadInicial.this, ""+id+" "+nombre+"-"+String.format("%.1f",precio)+"-"+exis, Toast.LENGTH_SHORT).show();
                    }while(c.moveToNext());
                }
                //query.close();
            }
        });
         Button Segundon = (Button) findViewById(R.id.boton_segundon);
        Segundon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ActividadInicial.this, fechaActual, Toast.LENGTH_LONG).show();
                Intent intent = new Intent (view.getContext(), Segunda.class);
                startActivityForResult(intent, 0);
            }
        });

    }
}











