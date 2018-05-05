package com.josemi.basededatos;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.josemi.basededatos.sqlite.BaseDatosPedido;
import com.josemi.basededatos.sqlite.OperacionesBaseDatos;

public class Segunda extends AppCompatActivity {
    OperacionesBaseDatos Con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);
        Con = OperacionesBaseDatos.obtenerInstancia(getApplicationContext());
        final Button Mostrar =  (Button) findViewById(R.id.Show);
        Mostrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Cursor c = Con.LeerProducto();
                if(c.moveToFirst()){
                    do{
                        int id = c.getInt(1);
                        String nombre = c.getString(2);
                        double precio = c.getDouble(3);
                        int exis = c.getInt(4);
                        Toast.makeText(Segunda.this, ""+id+" "+nombre+"-"+String.format("%.1f",precio)+"-"+exis, Toast.LENGTH_SHORT).show();
                    }while(c.moveToNext());
                }

            }
        });
        final Button regresar = (Button)findViewById(R.id.button5);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), ActividadInicial.class);
                startActivityForResult(intent, 0);
            }
        });
    }
}
