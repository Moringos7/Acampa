package com.rogzart.proyecto_interfaces.FragmentosBarra.Inventario.IG;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.rogzart.proyecto_interfaces.FragmentosBarra.Administrar.AU.ListaAdaptadorUsuarioNoRegistrado;
import com.rogzart.proyecto_interfaces.InterfacesLogin.Inicio;
import com.rogzart.proyecto_interfaces.InterfacesLogin.signup;
import com.rogzart.proyecto_interfaces.Modelo.Conexion;

import com.rogzart.proyecto_interfaces.Modelo.Inventario;
import com.rogzart.proyecto_interfaces.Modelo.Usuario;
import com.rogzart.proyecto_interfaces.R;
import com.rogzart.proyecto_interfaces.Singleton.VolleySingleton;
import com.rogzart.proyecto_interfaces.sqlite.OperacionesBaseDatos;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.app.Activity.RESULT_OK;


public class ListaInventario extends Fragment {
    ArrayList<String> listainventario;
    Uri ImageUrl;
    SearchView buscador, buscadorE;
    ListView listaInventario, listaInventarioE;
    ScrollView scroll;
    ImageView ImagenProducto;
    Button BtnGeneral, BtnExtras, ABtnGuardar;
    EditText ANombre,AExistencia,ACantidad,ADescripcion;
    Bitmap bitmap;
    CheckBox AExtra;
    FloatingActionButton botonAgregar, botonRegresar;
    TextView resultados,resultadosE;
    Conexion conexion, conexionE;
    String Imagen;
    private JsonObjectRequest jsonObjectRequest;
    private int cuenta, cuentaE;
    private static final int PICK_IMAGE = 100;
    LinearLayout layoutG, layoutE, layoutT, layoutA;
    OperacionesBaseDatos operador;
    public static int ValorExtra = 1;

    public static ListaInventario newInstance() {
        ListaInventario fragmento = new ListaInventario();
        return fragmento;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_inventario_general, container, false);
    }

    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        ABtnGuardar = (Button) getView().findViewById(R.id.agregar_producto_btnguardar);
        ACantidad = (EditText) getView().findViewById(R.id.agregar_producto_cantidad);
        ADescripcion = (EditText) getView().findViewById(R.id.agregar_producto_descripcion);
        AExistencia = (EditText) getView().findViewById(R.id.agregar_producto_existencia);
        AExtra = (CheckBox) getView().findViewById(R.id.agregar_producto_extra);
        ImagenProducto= (ImageView) getView().findViewById(R.id.agregar_producto_imagen);
        ANombre = (EditText) getView().findViewById(R.id.agregar_producto_nombre);
        botonRegresar = (FloatingActionButton) getView().findViewById(R.id.boton_regresar);
        BtnGeneral = (Button) getView().findViewById(R.id.list_inventario_general_btnG);
        BtnExtras = (Button) getView().findViewById(R.id.list_inventario_general_btnE);
        resultados = (TextView) getView().findViewById(R.id.list_Inventario_general_resultados);
        resultadosE = (TextView) getView().findViewById(R.id.resultadosE);
        listaInventario = (ListView) getView().findViewById(R.id.listaGeneral);
        listaInventarioE = (ListView) getView().findViewById(R.id.listaExtras);
        buscador = (SearchView) getView().findViewById(R.id.list_inventario_general_buscador);
        buscadorE= (SearchView) getView().findViewById(R.id.buscadorExtras);
        botonAgregar = (FloatingActionButton) getView().findViewById(R.id.list_inventario_general_agregar);
        operador = OperacionesBaseDatos.obtenerInstancia(getContext());
        layoutG = getView().findViewById(R.id.LinearLGeneral);
        layoutT = getView().findViewById(R.id.inventario_general_layout_general);
        layoutA = getView().findViewById(R.id.inventario_general_layout_agregar);
        scroll = getView().findViewById(R.id.Scroll_agregar);
        layoutE = getView().findViewById(R.id.LinearLExtra);

        conexion = new Conexion(getContext());
        conexion.setRuta("WebService/Inventario/wsInventarioCreate.php");

        ListaGeneral();
        botonRegresar.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View v) {
                                                 layoutT.setVisibility(View.VISIBLE);
                                                 scroll.setVisibility(View.GONE);
                                                 botonAgregar.setVisibility(View.VISIBLE);
                                             }
                                         }

        );

        botonAgregar.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                if(conexion.isConnected()) {
                                                    layoutT.setVisibility(View.GONE);
                                                    scroll.setVisibility(View.VISIBLE);
                                                    botonAgregar.setVisibility(View.GONE);

                                                } else {
                                                    Toast.makeText(getContext(), "Verifique su conexion a Internet", Toast.LENGTH_SHORT).show();
                                                }

                                            }
                                        }

        );

        BtnExtras.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             layoutE.setVisibility(View.VISIBLE);
                                             layoutG.setVisibility(View.GONE);
                                             cargarlistainventarioExtras();


                                         }
                                     }


        );
        BtnGeneral.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              layoutG.setVisibility(View.VISIBLE);
                                              layoutE.setVisibility(View.GONE);
                                              ListaGeneral();
                                          }
                                      }


        );
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.agregar_producto_imagen:
                cargarimagen();
                break;
        }

    }

    private void cargarimagen() {
        Intent intent= new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && requestCode == PICK_IMAGE){
            ImageUrl= data.getData();
            ImagenProducto.setImageURI(ImageUrl);
            ImagenProducto.buildDrawingCache();
            bitmap =  ImagenProducto.getDrawingCache();
            Imagen = BitMaptoString(bitmap);
        }
    }

    private String BitMaptoString(Bitmap bitmap) {
        ByteArrayOutputStream array = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,array);
        byte[] imagenByte = array.toByteArray();
        String imagenString = Base64.encodeToString(imagenByte,Base64.DEFAULT);
        return imagenString;
    }

    private void NuevoProducto() {
        String producto = ANombre.getText().toString();
        String Existencia = AExistencia.getText().toString();
        String Cantidad = ACantidad.getText().toString();
        String Descripcion = ADescripcion.getText().toString();
        Map<String, String> params = new HashMap<>();
        params.put("producto", producto);
        params.put("Existencia", Existencia);
        params.put("Cantidad", Cantidad);
        params.put("Descripcion", Descripcion);
        params.put("Extra", String.valueOf(AExtra.isChecked()));
        JSONObject obj = new JSONObject(params);

        jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, conexion.getRuta(), obj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONArray json = response.optJSONArray("Inventario");

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(signup.this, ""+error, Toast.LENGTH_LONG).show();
                Toast.makeText(getContext(), "Fallo Conexion al Servidor", Toast.LENGTH_SHORT).show();
            }

    });
        VolleySingleton.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);
    }
    private void cargarlistainventarioExtras() {

         ArrayList<Inventario> arrayList2 = operador.LeerTablaInventarioExtras();
         listainventarioGeneralExtras(arrayList2);




    }

    private void listainventarioGeneralExtras(ArrayList<Inventario> arrayList) {
        cuentaE = arrayList.size();
        if(cuenta != 1){
            resultadosE.setText(cuentaE + " Resultados");
        }else{
            resultadosE.setText(cuentaE + " Resultado");
        }
        final ListaAdaptadorInventario miLista = new ListaAdaptadorInventario(arrayList, getContext());
        listaInventarioE.setAdapter(miLista);
        buscadorE.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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
        listaInventarioE.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                   @Override
                                                   public void onItemClick(AdapterView<?> adapterView, View view, int x, long l) {
                                                       Inventario inventario1 = (Inventario) adapterView.getItemAtPosition(x);
                                                       Bundle bolsa = new Bundle();
                                                       bolsa.putSerializable("articulo", inventario1);

                                                       FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                                       transaction.replace(R.id.contenedor, ListaInventarioMain.newInstance(bolsa));
                                                       transaction.addToBackStack(null);
                                                       transaction.commit();
                                                   }


                                               }
        );

    }

    private void ListaGeneral() {
            ArrayList<Inventario> arrayList = operador.LeerTablaInventarioSinExtras();
             cuenta = arrayList.size();
             resultados.setText(cuenta + " Resultados");
            // Toast.makeText(getContext(), "" + arrayList.size(), Toast.LENGTH_SHORT).show();
            final ListaAdaptadorInventario miLista = new ListaAdaptadorInventario(arrayList, getContext());
            listaInventario.setAdapter(miLista);
            buscador.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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
            listaInventario.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                       @Override
                                                       public void onItemClick(AdapterView<?> adapterView, View view, int x, long l) {
                                                           Inventario inventario1 = (Inventario) adapterView.getItemAtPosition(x);
                                                           Bundle bolsa = new Bundle();
                                                           bolsa.putSerializable("articulo", inventario1);

                                                           FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                                           transaction.replace(R.id.contenedor, ListaInventarioMain.newInstance(bolsa));
                                                           transaction.addToBackStack(null);
                                                           transaction.commit();
                                                       }


                                                   }
            );
        }
    }



