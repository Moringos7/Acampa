package com.rogzart.proyecto_interfaces.InterfacesLogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.rogzart.proyecto_interfaces.R;

public class RecuperacionPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperacion_password);
        Spinner Dias = (Spinner) findViewById(R.id.spinnerDia);
        Spinner Meses = (Spinner) findViewById(R.id.spinnerMes);
        String[]dia = new String[31];
        String[]mes = new String[12];
        for(int i=0; i<31;i++){
            dia[i] = String.valueOf(i+1);
        }
        for(int j=0; j<12; j++){
            mes[j]= String.valueOf(j+1);
        }

        ArrayAdapter<String> adapterD = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dia);
        Dias.setAdapter(adapterD);
        ArrayAdapter<String> adapterM = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mes);
        Meses.setAdapter(adapterM);

    }
}
