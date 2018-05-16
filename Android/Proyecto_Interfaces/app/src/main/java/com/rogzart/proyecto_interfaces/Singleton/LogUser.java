package com.rogzart.proyecto_interfaces.Singleton;

import com.rogzart.proyecto_interfaces.Modelo.Usuario;

public class LogUser {
    private static LogUser mInstancia = null;
    private static Usuario User;
    private static Integer FkScouter;
    private static Integer FkCoordinador;
    private LogUser() { }
    public static LogUser obtenerInstancia(){
        CrearInstancia();
        return mInstancia;
    }
    private synchronized static void CrearInstancia(){
        if(mInstancia == null){
            mInstancia = new LogUser();
        }
    }
    public Usuario getUser() {
        return User;
    }
    public void setUser(Usuario user) {
        User = user;
    }
    public Integer getFkScouter(){
        return FkScouter;
    }
    public void setFkScouter(Integer fkScouter){
        this.FkScouter = fkScouter;
    }
    public Integer getFkCoordinador(){
        return FkScouter;
    }
    public void setFkCoordinador(Integer fkCoordinador){
        this.FkCoordinador = fkCoordinador;
    }


}
