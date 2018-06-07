package com.rogzart.proyecto_interfaces.FragmentosBarra.Inventario.IG;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.rogzart.proyecto_interfaces.FragmentosBarra.Administrar.AU.ListaAdministrarUsuario;
import com.rogzart.proyecto_interfaces.Modelo.Conexion;
import com.rogzart.proyecto_interfaces.Modelo.Inventario;
import com.rogzart.proyecto_interfaces.R;
import com.rogzart.proyecto_interfaces.Singleton.LogUser;
import com.rogzart.proyecto_interfaces.Singleton.VolleySingleton;
import com.rogzart.proyecto_interfaces.sqlite.ActualizacionBaseDatos;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import static android.app.Activity.RESULT_OK;

public class ListaInventarioMain extends Fragment {
    private Bundle packet;
    private Conexion conexion;
    private Inventario inventario;
    private EditText Nombre, existencia, cantidad, descripcion, comentario;
    private ImageView imagenarticulo;
    private Button btnEliminar, btnGuardar;
    Bitmap bitmap;
    Uri ImageUrl;
    String Imagen;
    private static final int PICK_IMAGE = 100;
    ImageRequest imageRequest;

    public ListaInventarioMain() {

    }

    @SuppressLint("ValidFragment")
    public ListaInventarioMain(Bundle bundle) {
        packet = bundle;
        inventario = (Inventario) packet.getSerializable("articulo");


    }

    public static ListaInventarioMain newInstance(Bundle bundle) {
        ListaInventarioMain fragmento = new ListaInventarioMain(bundle);
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
        return inflater.inflate(R.layout.detalle_inventario_general, container, false);
    }

    public void onActivityCreated(Bundle state) {

        super.onActivityCreated(state);
        Nombre = getView().findViewById(R.id.detalles_inventario_nombre);
        existencia = getView().findViewById(R.id.detalles_inventario_existencia);
        cantidad = getView().findViewById(R.id.detalles_inventario_cantidad);
        descripcion = getView().findViewById(R.id.detalles_inventario_descripcion);
        imagenarticulo = getView().findViewById(R.id.detalles_inventario_imagen);
        btnEliminar = getView().findViewById(R.id.detalles_inventario_boton_eliminar);
        btnGuardar = getView().findViewById(R.id.detalles_inventario_boton_guardar);
        conexion = new Conexion(getContext());
        Nombre.setText(inventario.getProducto());
        existencia.setText(String.valueOf(inventario.getExistencia()));
        cantidad.setText(String.valueOf(inventario.getCantidad()));
        descripcion.setText(inventario.getDescripcion());

        btnEliminar.setOnClickListener(new View.OnClickListener() {

                                           @Override
                                           public void onClick(View v) {
                                               EliminarProducto();
                                           }
                                       }
        );
        imagenarticulo.setOnClickListener(new View.OnClickListener() {

                                              @Override
                                              public void onClick(View v) {
                                                  cargarimagen();
                                              }
                                          }
        );
        btnGuardar.setOnClickListener(new View.OnClickListener() {

                                          @Override
                                          public void onClick(View v) {
                                              Toast.makeText(getContext(),  "Guardar producto", Toast.LENGTH_SHORT).show();
                                              UpdateProducto();
                                          }
                                      }
        );

        if (LogUser.obtenerInstancia(getContext()).getCoordinador() == 0) {
            Nombre.setEnabled(false);
            existencia.setEnabled(false);
            cantidad.setEnabled(false);
            descripcion.setEnabled(false);
        }


        conexion.setRuta("WebService/" + inventario.getImagen());
        imageRequest = new ImageRequest(conexion.getRuta(), new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                imagenarticulo.setImageBitmap(response);
                Imagen = BitMaptoString(response);
            }
        }, 0, 0, ImageView.ScaleType.CENTER, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        VolleySingleton.getInstance(getContext()).addToRequestQueue(imageRequest);

    }

    private void cargarimagen() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            ImageUrl = data.getData();
            imagenarticulo.setImageURI(ImageUrl);
            imagenarticulo.buildDrawingCache();
            bitmap = imagenarticulo.getDrawingCache();
            Imagen = BitMaptoString(bitmap);
        }
    }

    private String BitMaptoString(Bitmap bitmap) {
        ByteArrayOutputStream array = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, array);
        byte[] imagenByte = array.toByteArray();
        String imagenString = Base64.encodeToString(imagenByte, Base64.DEFAULT);
        return imagenString;
    }


    private void EliminarProducto() {
        conexion.setRuta("WebService/Inventario/wsInventarioDelete.php");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, conexion.getRuta(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Toast.makeText(getContext(), "" + error, Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                String IdInventario = Integer.toString(inventario.getIdInventario());
                String Imagen = inventario.getImagen();


                Map<String, String> params = new HashMap<String, String>();
                params.put("idinventario", IdInventario);
                params.put("ruta", Imagen);
                return params;

            }
        };
        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.contenedor, ListaInventario.newInstance());
        ft.addToBackStack(null);
        ft.commit();

    }

    private void UpdateProducto() {
        conexion.setRuta("WebService/Inventario/wsInventarioUpdate.php");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, conexion.getRuta(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Toast.makeText(getContext(), "" + error, Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                String IdInventario = Integer.toString(inventario.getIdInventario());
                String producto = Nombre.getText().toString();
                String Existencia = existencia.getText().toString();
                String Cantidad = cantidad.getText().toString();
                String Descripcion = descripcion.getText().toString();
                String Comentario = "Hola";


                Map<String, String> params = new HashMap<String, String>();
                params.put("idinventario", IdInventario);
                params.put("producto", producto);
                params.put("cantidad", Cantidad);
                params.put("existencia", Existencia);
                params.put("descripcion", Descripcion);
                params.put("comentario", Comentario);
                params.put("imagen", Imagen);
                return params;

            }
        };
        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.contenedor, ListaInventario.newInstance());
        ft.addToBackStack(null);
        ft.commit();

    }
}
