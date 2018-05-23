package com.rogzart.proyecto_interfaces.FragmentosBarra.Inventario.IG;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.rogzart.proyecto_interfaces.Modelo.Conexion;
import com.rogzart.proyecto_interfaces.Modelo.Inventario;
import com.rogzart.proyecto_interfaces.R;
import com.rogzart.proyecto_interfaces.Singleton.VolleySingleton;

public class ListaInventarioMain extends Fragment {
    private Bundle packet;
    private Inventario inventario;
    private EditText Nombre, existencia,cantidad,Descripcion;
    private ImageView imagenarticulo;
    private Button btnEliminar, btnGuardar;
    ImageRequest imageRequest;
    public ListaInventarioMain(){

    }
    @SuppressLint("ValidFragment")
    public  ListaInventarioMain(Bundle bundle){
        packet=bundle;
        inventario = (Inventario) packet.getSerializable("articulo");


    }
    public static ListaInventarioMain newInstance(Bundle bundle){
        ListaInventarioMain fragmento =  new ListaInventarioMain(bundle);
        return fragmento;
    }
    @Override
    public void onCreate(Bundle savedInstanceState){ super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.detalle_inventario_general, container, false);
    }
    public void onActivityCreated (Bundle state) {

        super.onActivityCreated(state);
        Nombre= getView().findViewById(R.id.detalles_inventario_nombre);
        existencia =  getView().findViewById(R.id.detalles_inventario_existencia);
        cantidad = getView().findViewById(R.id.detalles_inventario_cantidad);
        Descripcion = getView().findViewById(R.id.detalles_inventario_descripcion);
        imagenarticulo = getView().findViewById(R.id.detalles_inventario_imagen);
        btnEliminar = getView().findViewById(R.id.detalles_inventario_boton_eliminar);
        btnGuardar = getView().findViewById(R.id.detalles_inventario_boton_guardar);
        Conexion conexion = new Conexion(getContext());

        Nombre.setText(inventario.getProducto());
        existencia.setText(inventario.getExistencia());
        cantidad.setText(String.valueOf(inventario.getCantidad()));
        Descripcion.setText(inventario.getDescripcion());

        conexion.setRuta("WebService/"+inventario.getImagen());
        imageRequest =  new ImageRequest(conexion.getRuta(), new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                imagenarticulo.setImageBitmap(response);
            }
        }, 0, 0, ImageView.ScaleType.CENTER, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        VolleySingleton.getInstance(getContext()).addToRequestQueue(imageRequest);

    }

}
