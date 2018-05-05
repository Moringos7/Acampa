package com.rogzart.proyecto_interfaces.Modelo;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;

public class Conexion {
    private Context context;
    private String IpServer;
    private String Ruta;
    public Conexion(Context context) {
        this.context = context;
        IpServer =  "http://35.196.37.188/";
    }
    public boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        Network[] networks = connectivityManager.getAllNetworks();
        NetworkInfo networkInfo;
        for (Network mNetwork : networks) {
            networkInfo = connectivityManager.getNetworkInfo(mNetwork);
            if (networkInfo.getState().equals(NetworkInfo.State.CONNECTED)) {
                return true;
            }
        }
        return false;
    }
    public void setRuta(String comp){
        this.Ruta = comp;
    }
    public String getRuta(){

        return IpServer+Ruta;
    }
}

