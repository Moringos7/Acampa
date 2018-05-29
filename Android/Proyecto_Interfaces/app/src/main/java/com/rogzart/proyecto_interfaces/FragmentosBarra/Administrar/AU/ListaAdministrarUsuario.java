package com.rogzart.proyecto_interfaces.FragmentosBarra.Administrar.AU;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Layout;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.rogzart.proyecto_interfaces.Barra_desplegable;
import com.rogzart.proyecto_interfaces.FragmentosBarra.Administrar.MenuAdministrar;
import com.rogzart.proyecto_interfaces.Modelo.Conexion;
import com.rogzart.proyecto_interfaces.Modelo.Usuario;
import com.rogzart.proyecto_interfaces.R;
import com.rogzart.proyecto_interfaces.Singleton.VolleySingleton;
import com.rogzart.proyecto_interfaces.sqlite.OperacionesBaseDatos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListaAdministrarUsuario extends Fragment{

    private SearchView mSearchView;
    private ListView listaG;
    private ListView listaNR;
    private OperacionesBaseDatos operador;
    private Conexion conexion;
    private TextView resultados;
    private TextView resultadosNR;
    private int cuenta;
    private int cuentaNR;
    private Button UsuarioGeneral;
    private Button NoRegistrado;
    private LinearLayout layoutR;
    private LinearLayout layoutNR;
    private Boolean Check;
    public ListaAdministrarUsuario() {
    }

    public static ListaAdministrarUsuario newInstance() {
        ListaAdministrarUsuario fragment = new ListaAdministrarUsuario();
      return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_administrar_usuario, container, false);

    }
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        cargarListaUsuariosNoRegistrados();
        layoutR = (LinearLayout) getView().findViewById(R.id.layoutUsuariosGeneral);
        layoutNR = (LinearLayout) getView().findViewById(R.id.layoutNoRegistrados);
        mSearchView=(SearchView) getView().findViewById(R.id.searchUsuario);
        resultados = (TextView) getView().findViewById(R.id.resultados);
        resultadosNR = (TextView) getView().findViewById(R.id.resultadosNoRegistrado);
        listaG = (ListView) getView().findViewById(R.id.lista_administar_usuario);
        listaNR = (ListView) getView().findViewById(R.id.lista_administar_usuario_no_registrados);
        operador = OperacionesBaseDatos.obtenerInstancia(getContext());
        conexion = new Conexion(getContext());
        ListaUsuarios();
        //ListaUsuariosNoRegistrados();
        UsuarioGeneral = getView().findViewById(R.id.btnusuariosGeneral);
        NoRegistrado = getView().findViewById(R.id.btnNOregistrados);
        //Metodo de botón Usuarios Generales
        UsuarioGeneral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutNR.setVisibility(View.GONE);
                layoutR.setVisibility(View.VISIBLE);
                ListaUsuarios();
            }
        });
        //Método de botón No Registrado
        NoRegistrado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               layoutR.setVisibility(View.GONE);
               layoutNR.setVisibility(View.VISIBLE);
               cargarListaUsuariosNoRegistrados();
            }
        });

    }
    private void cargarListaUsuariosNoRegistrados(){
        Conexion x = new Conexion();
        final ArrayList<Usuario> arrayList = new ArrayList<Usuario>();
        x.setRuta("WebService/Usuario/wsUsuariosNoRegistrados.php");
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, x.getRuta(), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    //response.optBoolean((json.length())-1);
                    JSONArray json = response.optJSONArray("Usuario");
                    Check = json.getJSONObject(json.length()-1).optBoolean("Check");
                    if(Check){
                        arrayList.clear();
                        ListaUsuariosNoRegistrados(arrayList);
                    }else{
                        for (int i = 0; i < (json.length()-1); i++) {
                            JSONObject jsonObject = json.getJSONObject(i);
                            Usuario mUsuario = new Usuario();
                            mUsuario.setIdUsuario(jsonObject.optInt("IdUsuario"));
                            mUsuario.setNombre(jsonObject.optString("Nombre"));
                            mUsuario.setApellidoPaterno(jsonObject.optString("ApellidoPaterno"));
                            mUsuario.setApellidoMaterno(jsonObject.optString("ApellidoMaterno"));
                            mUsuario.setCorreo(jsonObject.optString("Correo"));
                            mUsuario.setFotografia(jsonObject.optString("Fotografia"));
                            mUsuario.setFechaNacimiento(jsonObject.optString("FechaNacimiento"));
                            mUsuario.setScout(jsonObject.optInt("Scout"));
                            mUsuario.setFkSeccion(jsonObject.optInt("FkSeccion"));
                            arrayList.add(mUsuario);
                            ListaUsuariosNoRegistrados(arrayList);
                        }
                    }
                } catch (JSONException e) {
                    Toast.makeText(getContext(), "Fallo: "+e, Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), ""+error, Toast.LENGTH_LONG).show();
            }
        });
        VolleySingleton.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);
    }
    private void ListaUsuariosNoRegistrados(ArrayList<Usuario> arrayList){
        //Toast.makeText(getContext(), "-->"+arrayList.size(), Toast.LENGTH_SHORT).show();
        cuentaNR = arrayList.size();
        if(cuenta != 1){
            resultadosNR.setText(cuentaNR + " Resultados");
        }else{
            resultadosNR.setText(cuentaNR + " Resultado");
        }
        final ListaAdaptadorUsuarioNoRegistrado miLista = new ListaAdaptadorUsuarioNoRegistrado(arrayList, getContext());
        listaNR.setAdapter(miLista);

    }
    private void ListaUsuarios() {

        ArrayList<Usuario> arrayList = operador.LeerTablaUsuario(getContext());
        cuenta = arrayList.size();
        resultados.setText(cuenta + " Resultados");
        final ListaAdaptadorUsuario miLista = new ListaAdaptadorUsuario(arrayList, getContext());
        listaG.setAdapter(miLista);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                miLista.getFilter().filter(query);
                return false;
            }
        });
        listaG.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(conexion.isConnected()){
                    Usuario user = (Usuario) adapterView.getItemAtPosition(i);
                    //Toast.makeText(getContext(), user.getNombre() + " " + user.getApellidoPaterno(), Toast.LENGTH_SHORT).show();
                    Bundle packet = new Bundle();
                    packet.putSerializable("Objeto",user);
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.contenedor, AdministrarUsuario.newInstance(packet));
                    ft.addToBackStack(null);
                    ft.commit();
                }else{
                    Toast.makeText(getContext(), "Verifique su conexion", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getContext(), Barra_desplegable.class);
                    startActivityForResult(intent, 0);
                }
            }
        });
    }
}
