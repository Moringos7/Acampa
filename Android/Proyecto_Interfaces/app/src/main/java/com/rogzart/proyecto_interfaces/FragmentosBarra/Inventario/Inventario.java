package com.rogzart.proyecto_interfaces.FragmentosBarra.Inventario;

import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.rogzart.proyecto_interfaces.R;

import java.util.ArrayList;


public class Inventario extends Fragment {
    ArrayList<String> listainventario;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.f_inventario, container, false);
    }
    public void onActivityCreated(Bundle state) {
        ListView ListaInventario;
        ArrayAdapter<String> adaptador;
        super.onActivityCreated(state);
        ListaInventario=(ListView) getView().findViewById(R.id.ListaG);
        /*FloatingActionButton FnuevoP = (FloatingActionButton)getView().findViewById(R.id.fab);
        FnuevoP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Correo enviado", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

       // base=new ConexionSQLiteHelper(getApplicationContext(),"bd_Acampa",null,1);
        consultarListaInventario();

     //   ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,listainventario);
      //  ListaInventario.setAdapter(adaptador);


}

    private void consultarListaInventario() {
       // listainventario= new ArrayList<String>();
       /* SQLiteDatabase db=base.getReadableDatabase();
       Inventario inventario=null;



        Cursor cursor=db.rawquery("SELECT * FROM "+inventario.Tabla_Inventario,null);


        */
      // while (cursor.moveToNext()){

       }
    }

