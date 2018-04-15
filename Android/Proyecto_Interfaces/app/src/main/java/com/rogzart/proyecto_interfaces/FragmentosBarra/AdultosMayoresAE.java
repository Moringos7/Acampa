package com.rogzart.proyecto_interfaces.FragmentosBarra;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.rogzart.proyecto_interfaces.R;

import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AdultosMayoresAE.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AdultosMayoresAE#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdultosMayoresAE extends Fragment implements com.android.volley.Response.Listener<JSONObject>, com.android.volley.Response.ErrorListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public AdultosMayoresAE() {
        // Required empty public constructor
    }
    EditText nombre,apellidopaterno,apellidomaterno,fotografia;
    Switch diabetico;
    Button btnRegistrar;
    ProgressDialog progreso;

    RequestQueue request;
    JsonObjectRequest Jsonobjectrequest;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AdultosMayoresAE.
     */
    // TODO: Rename and change types and number of parameters
    public static AdultosMayoresAE newInstance(String param1, String param2) {
        AdultosMayoresAE fragment = new AdultosMayoresAE();
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
        View vista=inflater.inflate(R.layout.f_adultos_mayores,container,false);
        nombre= (EditText) vista.findViewById(R.id.nombre);
        apellidopaterno= (EditText) vista.findViewById(R.id.apellidomaterno);
        apellidomaterno= (EditText) vista.findViewById(R.id.apellidopaterno);
        fotografia= (EditText) vista.findViewById(R.id.fotografia);
        btnRegistrar= (Button) vista.findViewById((R.id.btnRegistro));
        request= Volley.newRequestQueue(getContext());
        btnRegistrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                CargarWebService();
            }
        });
        return vista;
    }

    private void CargarWebService() {
        progreso= new ProgressDialog(getContext());
        progreso.setMessage("Cargando");
        progreso.show();

    String url="https://acampa.000webhostapp.com/WebService/AdultoMayor/wsAdultoMayorCreate.php";
    url=url.replace(" ","%20");

    Jsonobjectrequest= new JsonObjectRequest(Request.Method.GET,url,null,this,this);
    request.add(Jsonobjectrequest);


    }


    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(getContext(),"Se ha registrado exitosamente",Toast.LENGTH_SHORT).show();
        progreso.hide();
        nombre.setText("");
        apellidopaterno.setText("");
        apellidomaterno.setText("");
        diabetico.setText("false");
        fotografia.setText("");



    }
    @Override
    public void onErrorResponse(VolleyError error) {
    progreso.hide();
        Toast.makeText(getContext(),"No se pudo registrar"+error.toString(),Toast.LENGTH_SHORT).show();
        Log.i("Error",error.toString());
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



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
