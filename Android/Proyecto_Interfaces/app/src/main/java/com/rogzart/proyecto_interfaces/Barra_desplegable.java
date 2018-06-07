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

import com.rogzart.proyecto_interfaces.FragmentosBarra.Administrar.AU.AdministrarUsuario;
import com.rogzart.proyecto_interfaces.FragmentosBarra.Administrar.MenuAdministrar;
import com.rogzart.proyecto_interfaces.FragmentosBarra.AsignacionAdultosMayores.Coordinador.AsignacionAdultoMayorCoordinador;
import com.rogzart.proyecto_interfaces.FragmentosBarra.AsignacionAdultosMayores.Usuario.AsignacionAdultoMayorUsuario;
import com.rogzart.proyecto_interfaces.FragmentosBarra.Convivio.Convivio;
import com.rogzart.proyecto_interfaces.FragmentosBarra.Estadisticas.Estadisticas;
import com.rogzart.proyecto_interfaces.FragmentosBarra.Eventos.ListaEventos;
import com.rogzart.proyecto_interfaces.FragmentosBarra.InformacionAdultoMayor.busqueda_informacion_adulto_mayor;
import com.rogzart.proyecto_interfaces.FragmentosBarra.Inventario.IG.ListaInventario;
import com.rogzart.proyecto_interfaces.FragmentosBarra.Scouter.Administracion_Scouter;
import com.rogzart.proyecto_interfaces.FragmentosBarra.Sugerencias.menu_sugerencias;
import com.rogzart.proyecto_interfaces.FragmentosBarra.LocalizacionLugares.LocalizacionLugares;
import com.rogzart.proyecto_interfaces.Modelo.Conexion;
import com.rogzart.proyecto_interfaces.Modelo.Usuario;
import com.rogzart.proyecto_interfaces.Singleton.LogUser;
import com.rogzart.proyecto_interfaces.sqlite.ActualizacionBaseDatos;


public class Barra_desplegable extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Conexion CONECT;
    private LogUser ControlUser;
    private ActualizacionBaseDatos Act;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ControlUser = LogUser.obtenerInstancia(getApplicationContext());
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
        //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        /*if (drawer.isDrawerOpen(GravityCompat.START)) {
            //drawer.closeDrawer(GravityCompat.START);
        } else {

        }*/
        super.onBackPressed();
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
                if(Act.ActualizarBasedeDatos(getApplicationContext())){
                    Toast.makeText(this, "Actualizacion Completada", Toast.LENGTH_SHORT).show();
                }
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
        boolean CheckConexionCoordinador = true;
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        int id = item.getItemId();
        if(id == R.id.nav_Asignacion){
            if(ControlUser.getCoordinador() > 0) {
                ft.replace(R.id.contenedor, AsignacionAdultoMayorCoordinador.newInstance());
                CheckConexionCoordinador = CONECT.isConnected();
            }else{
                ft.replace(R.id.contenedor, AsignacionAdultoMayorUsuario.newInstance());
            }
            if(CheckConexionCoordinador) {
                ft.addToBackStack(null);
                ft.commit();
            }else {
                Toast.makeText(this, "Verifica tu conexion a Internet", Toast.LENGTH_SHORT).show();
            }
        }else if(id == R.id.nav_Ruta){
            getFragmentManager().beginTransaction().replace(R.id.contenedor,new LocalizacionLugares()).commit();
        }else if(id == R.id.nav_Info){
            ft.replace(R.id.contenedor, busqueda_informacion_adulto_mayor.newInstance());
            ft.addToBackStack(null);
            ft.commit();
        }else if(id == R.id.nav_Sugerencias){
            ft.replace(R.id.contenedor, menu_sugerencias.newInstance());
            ft.addToBackStack(null);
            ft.commit();
        }else if(id == R.id.nav_Convivio){
            ft.replace(R.id.contenedor, Convivio.newInstance());
            ft.addToBackStack(null);
            ft.commit();
        }else if(id == R.id.nav_Inventario){
            ft.replace(R.id.contenedor, ListaInventario.newInstance());
            ft.addToBackStack(null);
            ft.commit();
        }else if(id == R.id.nav_Scouters){
            ft.replace(R.id.contenedor, Administracion_Scouter.newInstance());
            ft.addToBackStack(null);
            ft.commit();
        }else if(id == R.id.nav_Estadisticas){
            //getFragmentManager().beginTransaction().replace(R.id.contenedor,new Fragmento08()).commit();
            ft.replace(R.id.contenedor, Estadisticas.newInstance());
            ft.addToBackStack(null);
            ft.commit();
        }else if(id == R.id.nav_Eventos){
            ft.replace(R.id.contenedor, ListaEventos.newInstance());
            ft.addToBackStack(null);
            ft.commit();

        }else if(id == R.id.nav_Administrar) {
            ft.replace(R.id.contenedor, MenuAdministrar.newInstance());
            ft.addToBackStack(null);
            ft.commit();
        }else if(id == R.id.nav_Salir){
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
