package com.rogzart.prueba_maps;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainFragment extends Fragment {
    private static String APP_TAG = "APIRutasGoogle";
    private TextView _txtPointA;
    private TextView _txtPointB;
    private Button _btnCalcRoute;
    private MainActivity mainAct;

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_maps, container,
                false);
        setupUI(rootView);
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        //Obtenemos la referencia a la Actividad principal
        this.mainAct = (MainActivity) mainAct;
    }

    public void setupUI(View rootView) {
        //Se mapea los componentes UI en el fragment_main.xml con variables para su manipulación.
        _txtPointA = (TextView) rootView.findViewById(R.id.textPointA);
        _txtPointA.setText("Barcelona, Spain");
        _txtPointB = (TextView) rootView.findViewById(R.id.textPointB);
        _txtPointB.setText("Tarragona, Spain");
        _btnCalcRoute = (Button) rootView.findViewById(R.id.btnCalcRoute);

        //Se define un OnClickListener al botón de Calcular Ruta:
        _btnCalcRoute.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(APP_TAG, "_btnCalcRoute fue pulsado");
                //Obtenemos la dirección A y B obtenida por el usuario.
                String pointA = _txtPointA.getText().toString();
                String pointB = _txtPointB.getText().toString();

                if (pointA != null && pointA != "" && pointB != null && pointB != "") {
                    //Disparamos la tarea asíncrona definida en la clase ManageGoogleRoutes
                    //pasando los puntos A y B para el calculo de la ruta y la obtención de
                    //las coordenadas que nos permitirán dibujar la ruta a seguir.
                    new ManageGoogleRoutes((OnTaskCompleted) MainFragment.this.mainAct).execute(pointA, pointB);
                } else {
                    Toast.makeText(MainActivity.this.mainAct, "Todos los campos son obligatorios", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}