package com.rogzart.proyecto_interfaces.ActivitysConexion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.rogzart.proyecto_interfaces.R;

public class ErrorPeticion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_peticion);
        TextView textoError1 = findViewById(R.id.textViewE1);
        TextView textoError2 = findViewById(R.id.textViewE2);
        final Button volver =  findViewById(R.id.btnVolver);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
