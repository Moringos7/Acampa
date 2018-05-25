package com.rogzart.proyecto_interfaces.InterfacesLogin;

import android.content.Context;
import android.widget.Toast;

import com.rogzart.proyecto_interfaces.Modelo.Usuario;

public class ControladorCorreos {
    Usuario user;
    String Asunto;
    Context contexto;
    public ControladorCorreos(Usuario user, String Asunto, Context contexto){
        this.user = user;
        this.Asunto = Asunto;
        this.contexto = contexto;
    }
    public void EnviarCorreoInicio(){
        Toast.makeText(contexto, ""+user.getIdUsuario(), Toast.LENGTH_SHORT).show();
    }
}
