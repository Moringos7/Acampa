package com.rogzart.proyecto_interfaces.Singleton;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.rogzart.proyecto_interfaces.InterfacesLogin.Inicio;
import com.rogzart.proyecto_interfaces.Modelo.Usuario;

public class LogUser {
    //Constantes
    private static  final String UserData = "DatosUsuario";
    private static final String UserId = "IdUsuario";
    private static final String UserName = "UserName";
    private static final String UserEmail = "Correo";
    private static final String UserLastNameP = "ApellidoPaterno";
    private static final String UserLastNameM = "ApellidoMaterno";
    private static final String UserFotografia = "Fotografia";
    private static final String UserSeccion = "FkSeccion";
    private static final String IdScouter = "IdScouter";
    private static final String IdCoordinador = "IdCoordinador";
    //
    private static LogUser mInstancia = null;
    private static  Context ctx;
    private LogUser(Context context) {
        ctx = context;
    }
    public static LogUser obtenerInstancia(Context contexto){
        CrearInstancia(contexto);
        return mInstancia;
    }
    private synchronized static void CrearInstancia(Context contexto){
        if(mInstancia == null){
            mInstancia = new LogUser(contexto);
        }
    }
    public void userLogin(Usuario User,int Scouter, int Coordinador) {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(UserData, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(UserId,User.getIdUsuario());
        editor.putString(UserName, User.getNombre());
        editor.putString(UserEmail, User.getCorreo());
        editor.putString(UserLastNameP, User.getApellidoPaterno());
        editor.putString(UserLastNameM, User.getApellidoMaterno());
        editor.putString(UserFotografia, User.getFotografia());
        editor.putInt(UserSeccion,User.getFkSeccion());
        editor.putInt(IdScouter, Scouter);
        editor.putInt(IdCoordinador,Coordinador);
        editor.apply();
    }
    public Usuario getUser(){
        Usuario User = new  Usuario();
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(UserData,Context.MODE_PRIVATE);
        User.setIdUsuario(sharedPreferences.getInt(UserId,0));
        User.setNombre(sharedPreferences.getString(UserName,null));
        User.setCorreo(sharedPreferences.getString(UserEmail,null));
        User.setApellidoPaterno(sharedPreferences.getString(UserLastNameP,null));
        User.setApellidoMaterno(sharedPreferences.getString(UserLastNameM,null));
        User.setFotografia(sharedPreferences.getString(UserFotografia,null));
        User.setFkSeccion(sharedPreferences.getInt(UserSeccion,0));
        return  User;
    }
    public int getScouter(){
        int scouter;
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(UserData,Context.MODE_PRIVATE);
        scouter = sharedPreferences.getInt(IdScouter,0);
        return scouter;
    }
    public int getCoordinador(){
        int coordinador;
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(UserData,Context.MODE_PRIVATE);
        coordinador = sharedPreferences.getInt(IdCoordinador,0);
        return coordinador;
    }
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(UserData, Context.MODE_PRIVATE);
        return sharedPreferences.getString(UserEmail, null) != null;
    }
    public void logout() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(UserData, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        ctx.startActivity(new Intent(ctx, Inicio.class));
    }
}
