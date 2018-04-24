package com.rogzart.proyecto_interfaces.FragmentosBarra;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.rogzart.proyecto_interfaces.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Editar_AdultosM.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Editar_AdultosM#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Editar_AdultosM extends Fragment  {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Editar_AdultosM() {
        // Required empty public constructor
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Editar_AdultosM.
     */
    // TODO: Rename and change types and number of parameters
    EditText enombre;
    EditText eapellidopaterno;
    EditText eapellidomaterno;
    Switch ediabetico;
    Button btnactualizar;
    Button btneliminar;
    ProgressDialog  pDialog;
    JsonObjectRequest jsonobjectrequest1;
    StringRequest stringRequest;
    public static Editar_AdultosM newInstance(String param1, String param2) {
        Editar_AdultosM fragment = new Editar_AdultosM();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista= inflater.inflate(R.layout.f_editar_adultos,container,false);
        enombre= (EditText) vista.findViewById(R.id.enombre);
        eapellidopaterno= (EditText) vista.findViewById(R.id.eapellidopaterno);
        eapellidomaterno= (EditText) vista.findViewById(R.id.apellidomaterno);
        ediabetico= (Switch) vista.findViewById(R.id.ediabetico);
        btnactualizar= (Button) vista.findViewById(R.id.btnactualizar);
        btneliminar= (Button) vista.findViewById(R.id.btneliminar);

        btnactualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webServiceActualizar();
            }
        });
        btneliminar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                webServiceEliminar();
            }
        });



        return inflater.inflate(R.layout.f_editar_adultos, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    /*actualizar.setonClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view){
            webServiceActualizar();
        }
    });
    //para el boton  de eliminar.
    eliminar.setonClickListener(new View.OnClickListener(){
        public void onClick(View view){
            webServiceEliminar();
        }
    )};
*/
    private void webServiceActualizar(){
        pDialog = new ProgressDialog(getContext());
        pDialog.setMessage("Cargando...");
        pDialog.show();
        String ip="https://acampa.000webhostapp.com/";
        String url =ip+"http://<192.168.100.5>/WebService/wsAdultoMayorUpdate.php?idadultomayor=&nombre="+enombre.getText().toString()+"&apellidopaterno="+eapellidopaterno.getText().toString()+"&apellidomaterno="+eapellidomaterno.getText().toString()+"&fotografia="+null+"&diabetico="+ediabetico.getSwitchPadding();
        stringRequest= new StringRequest(Request.Method.GET,url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pDialog.hide();
            if(response.trim().equalsIgnoreCase("Actualizado")){
                Toast.makeText(getContext(),"Se ha Actualizado con exito",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getContext(),"No se ha Actualizado ",Toast.LENGTH_SHORT).show();
                Log.i("Respuesta: ",""+response);
            }
            }

	}, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"No se ha podido conectar",Toast.LENGTH_SHORT).show();
                pDialog.hide();
            }
        });
    }


    private void webServiceEliminar(){
        pDialog = new ProgressDialog(getContext());
        pDialog.setMessage("Cargando...");
        pDialog.show();
        String ip="https://acampa.000webhostapp.com/";
        String url =ip+"http://<ip>/WebService/AdultoMayor/wsAdultoMayorDelete.php?nombre="+enombre.getText().toString();
        stringRequest=new StringRequest(Request.Method.GET, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pDialog.hide();

                if (response.trim().equalsIgnoreCase("eliminado")) {
                    enombre.setText("");
                    eapellidopaterno.setText("");
                    eapellidomaterno.setText("");
                    ediabetico.setTextOff("False");
                    Toast.makeText(getContext(), "Se ha Eliminado con exito", Toast.LENGTH_SHORT).show();
                } else {
                    if (response.trim().equalsIgnoreCase("NoExiste")) {
                        Toast.makeText(getContext(), "No se encuentra el Adulto Mayor", Toast.LENGTH_SHORT).show();
                        Log.i("Respuesta: ", "" + response);
                    } else {
                        Toast.makeText(getContext(), "No se ha eliminado", Toast.LENGTH_SHORT).show();
                        Log.i("Respuesta: ", "" + response);
                    }
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "No se ha podido conectar", Toast.LENGTH_SHORT).show();
                pDialog.hide();
            }
        });

        }



    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}