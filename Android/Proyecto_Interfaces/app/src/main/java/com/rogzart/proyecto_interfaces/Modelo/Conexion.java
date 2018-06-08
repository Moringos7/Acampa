package com.rogzart.proyecto_interfaces.Modelo;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;

import java.io.IOException;

public class Conexion {
    private Context context;
    private String IpServer;
    private String Ruta;
    public Conexion(){
        IpServer =  "http://35.196.37.188/";
    }
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
                ///Codigo añadido
                ////
                /*try {
                    Process p = Runtime.getRuntime().exec("ping -c 1 www.google.com");
                    int val = p.waitFor();
                    boolean reachable = (val == 0);
                    return reachable;
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return false;*/
                ////

                //
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

