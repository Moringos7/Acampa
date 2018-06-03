package com.rogzart.proyecto_interfaces.Modelo;

import java.io.Serializable;
import java.util.ArrayList;

public class UsuarioAsignacion extends Usuario implements Serializable {
    private ArrayList<AdultoMayor> AdultosMayores;
    public UsuarioAsignacion(){

    }
    public UsuarioAsignacion(Usuario user){
            this.setIdUsuario(user.getIdUsuario());
            this.setNombre(user.getNombre());
            this.setApellidoPaterno(user.getApellidoPaterno());
            this.setApellidoMaterno(user.getApellidoMaterno());
            this.setCorreo(user.getCorreo());
            this.setScout(user.getScout());
            this.setFechaNacimiento(user.getFechaNacimiento());
            this.setFotografia(user.getFotografia());
            this.setFkSeccion(user.getFkSeccion());
            this.AdultosMayores = new ArrayList<AdultoMayor>();
    }
    public ArrayList<AdultoMayor> getAdultosMayores() {
        return AdultosMayores;
    }

    public void setAdultosMayores(ArrayList<AdultoMayor> adultosMayores) {
        AdultosMayores = adultosMayores;
    }
}
