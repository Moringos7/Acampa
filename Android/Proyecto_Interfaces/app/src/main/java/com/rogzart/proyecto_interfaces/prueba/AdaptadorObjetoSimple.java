package com.rogzart.proyecto_interfaces.prueba;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.rogzart.proyecto_interfaces.R;

import java.util.LinkedList;
import java.util.List;

public class AdaptadorObjetoSimple extends RecyclerView.Adapter<AdaptadorObjetoSimple.ViewHolder> {
    private List<ObjetoSimple> datos;
    private Context context;
    private int resource;
    private boolean modoSeleccion;
    private SparseBooleanArray seleccionados;

    public AdaptadorObjetoSimple(Context context, LinkedList<ObjetoSimple> datos) {
        this.context = context;
        this.datos = datos;
        seleccionados = new SparseBooleanArray();
        modoSeleccion = false;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ObjetoSimple os = datos.get(position);
        //Toast.makeText(context, ""+position, Toast.LENGTH_SHORT).show();
        holder.bindView(os);
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    /**VIEWHOLDER*/
    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_texto;
        private View item;

        public ViewHolder(View itemView) {
            super(itemView);
            this.item = itemView;
        }

        public void bindView(ObjetoSimple os){

            tv_texto = (TextView) item.findViewById(R.id.tv_texto);
            tv_texto.setText(os.getTexto());

            //Selecciona el objeto si estaba seleccionado
            if (seleccionados.get(getAdapterPosition())){
                item.setSelected(true);
            } else
                item.setSelected(false);

            /**Activa el modo de selección*/
            item.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (!modoSeleccion){
                        modoSeleccion = true;
                        v.setSelected(true);
                        seleccionados.put(getAdapterPosition(), true);
                    }
                    return true;
                }
            });

            /**Selecciona/deselecciona un ítem si está activado el modo selección*/
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (modoSeleccion) {
                        if (!v.isSelected()) {
                            v.setSelected(true);
                            seleccionados.put(getAdapterPosition(), true);
                        } else {
                            v.setSelected(false);
                            seleccionados.put(getAdapterPosition(), false);
                            if (!haySeleccionados())
                                modoSeleccion = false;
                        }
                    }
                }
            });
        }
    }

    public boolean haySeleccionados() {
        for (int i = 0; i <= datos.size(); i++) {
            if (seleccionados.get(i))
                return true;
        }
        return false;
    }

    /**Devuelve aquellos objetos marcados.*/
    public LinkedList<ObjetoSimple> obtenerSeleccionados(){
        LinkedList<ObjetoSimple> marcados = new LinkedList<ObjetoSimple>();
        for (int i = 0; i < datos.size(); i++) {
            if (seleccionados.get(i)){
                marcados.add(datos.get(i));
            }
        }
        return marcados;
    }
}
