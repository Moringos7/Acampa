package com.rogzart.proyecto_interfaces.Splash;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.rogzart.proyecto_interfaces.InterfacesLogin.Inicio;
import com.rogzart.proyecto_interfaces.R;
import com.rogzart.proyecto_interfaces.sqlite.ActualizacionBaseDatos;
import com.rogzart.proyecto_interfaces.sqlite.OperacionesBaseDatos;

import org.w3c.dom.Text;

import static java.lang.Thread.sleep;

public class SplashActivity extends Activity {
    private TextView ivSplash;
    private ImageView IS;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**Creacion Estructura BD*/
        /*******************************************************************/
        OperacionesBaseDatos.obtenerInstancia(getApplicationContext());
        /*******************************************************************/
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);

        ivSplash = (TextView) findViewById(R.id.TS);
        IS = (ImageView) findViewById(R.id.ivSplash);
        Animation myanim = AnimationUtils.loadAnimation(this, R.anim.splash_transicion);
        IS.startAnimation(myanim);
        ivSplash.startAnimation(myanim);
        final Intent intent = new Intent(this, Inicio.class);
        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    startActivity(intent);
                    finish();
                }

            }

        };
        timer.start();
    }
}
