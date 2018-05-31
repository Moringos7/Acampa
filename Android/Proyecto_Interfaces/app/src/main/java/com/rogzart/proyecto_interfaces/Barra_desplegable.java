package com.rogzart.proyecto_interfaces;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.rogzart.proyecto_interfaces.FragmentosBarra.Administrar.MenuAdministrar;
import com.rogzart.proyecto_interfaces.FragmentosBarra.Fragmento01;
import com.rogzart.proyecto_interfaces.FragmentosBarra.Fragmento02;
import com.rogzart.proyecto_interfaces.FragmentosBarra.Inventario.IG.ListaInventario;
import com.rogzart.proyecto_interfaces.FragmentosBarra.Inventario.IG.ListaInventarioMain;
import com.rogzart.proyecto_interfaces.FragmentosBarra.TrazadoRuta.Trazado_de_ruta;
import com.rogzart.proyecto_interfaces.FragmentosBarra.Fragmento04;
import com.rogzart.proyecto_interfaces.FragmentosBarra.Fragmento06;
import com.rogzart.proyecto_interfaces.FragmentosBarra.Fragmento07;
import com.rogzart.proyecto_interfaces.FragmentosBarra.Fragmento08;
import com.rogzart.proyecto_interfaces.FragmentosBarra.Fragmento09;
import com.rogzart.proyecto_interfaces.Modelo.Conexion;
import com.rogzart.proyecto_interfaces.Modelo.Usuario;
import com.rogzart.proyecto_interfaces.Singleton.LogUser;
import com.rogzart.proyecto_interfaces.sqlite.ActualizacionBaseDatos;


public class Barra_desplegable extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Conexion CONECT;
    private ActualizacionBaseDatos Act;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUser ControlUser = LogUser.obtenerInstancia(getApplicationContext());
        Usuario mUsuario = ControlUser.getUser();
        CONECT = new Conexion(getApplicationContext());



        setContentView(R.layout.activity_barra_desplegable);

        /*ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle("Canteen Home");
        actionBar.setHomeButtonEnabled(true);
        actionBar.setIcon(R.drawable.back);*/

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(mUsuario.getNombre());
        if(ControlUser.getCoordinador() > 0){
            getSupportActionBar().setSubtitle("Coordinador");
        }else if(ControlUser.getScouter() > 0){
            getSupportActionBar().setSubtitle("Scouter");
        }


        ///Este código genera la Hamburguesa///
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        ////

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View hView = navigationView.getHeaderView(0);

        TextView UsuarioBarra = (TextView) hView.findViewById(R.id.NombreUsuarioBarra);
        UsuarioBarra.setText(""+mUsuario.getNombre() +" "+mUsuario.getApellidoPaterno()+ " " + mUsuario.getApellidoMaterno());
        TextView CorreoBarra = (TextView) hView.findViewById(R.id.CorreoUsuarioBarra);
        CorreoBarra.setText(mUsuario.getCorreo());
        navigationView.getMenu().setGroupVisible(R.id.menuVoluntario, true);
        if(ControlUser.getScouter() > 0) {
            navigationView.getMenu().setGroupVisible(R.id.menuScouter, true);
        }
        if(ControlUser.getCoordinador() > 0) {
            navigationView.getMenu().setGroupVisible(R.id.menuCoordinador, true);
        }
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.barra_desplegable, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.BarraMiPerfil) {
            
        }else if(id == R.id.BarraActualizar){
            if(CONECT.isConnected()) {
                Act = new ActualizacionBaseDatos(getApplicationContext());
                Act.VolcarBasedeDatos();
                Act.ActualizarBasedeDatos(getApplicationContext());
            }else{
                Toast.makeText(this, "Error: Actualización, Verifique su conexión", Toast.LENGTH_SHORT).show();
            }
        } else if(id == R.id.BarraCerrarSesion){
            finish();
            LogUser.obtenerInstancia(getApplicationContext()).logout();
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        int id = item.getItemId();
        if(id == R.id.nav_Asignacion){
            ft.replace(R.id.contenedor, Fragmento04.newInstance());
            ft.addToBackStack(null);
            ft.commit();
        }else if(id == R.id.nav_Ruta){
            getFragmentManager().beginTransaction().replace(R.id.contenedor,new Trazado_de_ruta()).commit();
        }else if(id == R.id.nav_Info){
            getFragmentManager().beginTransaction().replace(R.id.contenedor,new Fragmento01()).commit();
        }else if(id == R.id.nav_Sugerencias){
            
        }else if(id == R.id.nav_Convivio){
            getFragmentManager().beginTransaction().replace(R.id.contenedor,new Fragmento06()).commit();
        }else if(id == R.id.nav_Inventario){
            ft.replace(R.id.contenedor, ListaInventario.newInstance());
            ft.addToBackStack(null);
            ft.commit();
        }else if(id == R.id.nav_Scouters){
            //getFragmentManager().beginTransaction().replace(R.id.contenedor,new Fragmento07()).commit();
            Bundle bolsa = new Bundle();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.contenedor, ListaInventarioMain.newInstance(bolsa));
            transaction.addToBackStack(null);
            transaction.commit();

        }else if(id == R.id.nav_Estadisticas){
            getFragmentManager().beginTransaction().replace(R.id.contenedor,new Fragmento08()).commit();
        }else if(id == R.id.nav_Eventos){
            getFragmentManager().beginTransaction().replace(R.id.contenedor,new Fragmento09()).addToBackStack(null).commit();
        }else if(id == R.id.nav_Administrar) {
            ft.replace(R.id.contenedor, MenuAdministrar.newInstance());
            ft.addToBackStack(null);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
