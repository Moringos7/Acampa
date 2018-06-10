package com.rogzart.proyecto_interfaces;

import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.rogzart.proyecto_interfaces.FragmentosBarra.AdaptadorNotificacion;
import com.rogzart.proyecto_interfaces.FragmentosBarra.Administrar.MenuAdministrar;
import com.rogzart.proyecto_interfaces.FragmentosBarra.AsignacionAdultosMayores.Coordinador.AsignacionAdultoMayorCoordinador;
import com.rogzart.proyecto_interfaces.FragmentosBarra.AsignacionAdultosMayores.Usuario.AsignacionAdultoMayorUsuario;
import com.rogzart.proyecto_interfaces.FragmentosBarra.Convivio.Convivio;
import com.rogzart.proyecto_interfaces.FragmentosBarra.Estadisticas.Estadisticas;
import com.rogzart.proyecto_interfaces.FragmentosBarra.Eventos.ListaEventos;
import com.rogzart.proyecto_interfaces.FragmentosBarra.InformacionAdultoMayor.busqueda_informacion_adulto_mayor;
import com.rogzart.proyecto_interfaces.FragmentosBarra.Inventario.Menu_Inventario;
import com.rogzart.proyecto_interfaces.FragmentosBarra.Scouter.Administracion_Scouter;
import com.rogzart.proyecto_interfaces.FragmentosBarra.Sugerencias.menu_sugerencias;
import com.rogzart.proyecto_interfaces.FragmentosBarra.LocalizacionLugares.LocalizacionLugares;
import com.rogzart.proyecto_interfaces.Modelo.Conexion;
import com.rogzart.proyecto_interfaces.Modelo.Evento;
import com.rogzart.proyecto_interfaces.Modelo.Notificacion;
import com.rogzart.proyecto_interfaces.Modelo.TipoEvento;
import com.rogzart.proyecto_interfaces.Modelo.Usuario;
import com.rogzart.proyecto_interfaces.Singleton.LogUser;
import com.rogzart.proyecto_interfaces.sqlite.ActualizacionBaseDatos;
import com.rogzart.proyecto_interfaces.sqlite.OperacionesBaseDatos;

import java.util.ArrayList;
import java.util.Calendar;


public class Barra_desplegable extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Conexion CONECT;
    private LogUser ControlUser;
    private ActualizacionBaseDatos Act;
    private MenuItem ITEM;
    private HiloActualizacion myHilo;
    //private HiloConexion hiloConexion;
    private AlertDialog.Builder AlertaConexion;
    private LinearLayout LayoutPrincipal;
    private ListView ListaG;
    private TextView textNovedad;
    private OperacionesBaseDatos operador;
    private boolean Conectado = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ControlUser = LogUser.obtenerInstancia(getApplicationContext());
        verificarVigencia();
        operador = OperacionesBaseDatos.obtenerInstancia(getApplicationContext());
        Usuario mUsuario = ControlUser.getUser();
        CONECT = new Conexion(getApplicationContext());
        setContentView(R.layout.activity_barra_desplegable);
        configurarDialogs();
        LayoutPrincipal = findViewById(R.id.LayoutPrincipal);
        ListaG = findViewById(R.id.ListaNovedades);
        textNovedad = findViewById(R.id.SIN_NOVEDAD);
        verificarNovedades();
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
    private void configurarDialogs(){
        AlertaConexion = new AlertDialog.Builder(getApplicationContext());
        AlertaConexion.setTitle("Conexion Detectada");
        AlertaConexion.setMessage("¿Desea Actualizar la aplicacion?");
        AlertaConexion.setCancelable(false);
        AlertaConexion.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                actualizar();
            }
        });
        AlertaConexion.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
            }
        });
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

        if(id == R.id.BarraActualizar){
            actualizar();
        } else if(id == R.id.BarraCerrarSesion){
            finish();
            LogUser.obtenerInstancia(getApplicationContext()).logout();
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        ITEM = item;
        if(item.getItemId() == R.id.nav_Asignacion){
            seleccionFragments(ITEM);
        }else{
            if(myHilo ==  null){
                myHilo = new HiloActualizacion();
                myHilo.execute();
            }else{
                myHilo.cancel(true);
                myHilo = null;
                myHilo = new HiloActualizacion();
                myHilo.execute();
            }
        }

        return true;
    }
    public void actualizar(){
        if(CONECT.isConnected()) {
            Act = new ActualizacionBaseDatos(getApplicationContext());
            Act.VolcarBasedeDatos();
            if(Act.ActualizarBasedeDatos(getApplicationContext())){
                Toast.makeText(this, "Actualizacion Completada", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Error: Actualización, Verifique su conexión", Toast.LENGTH_SHORT).show();
        }
    }

    public void verificarNovedades(){

        ArrayList<Evento> eventos = operador.verificarEventos(generarFecha());
        ArrayList<TipoEvento> tipoEventos = operador.obtenerTiposEventos(eventos);
        ArrayList<Notificacion> notificaciones = new ArrayList<Notificacion>();
        Notificacion mNotificacion;
        for(int i = 0;i<eventos.size();i++){
            mNotificacion = new Notificacion();
            mNotificacion.setEvento(eventos.get(i));
            mNotificacion.setTipoEvento(tipoEventos.get(i));
            notificaciones.add(mNotificacion);
        }
        if(notificaciones.size() == 0){
            textNovedad.setVisibility(View.VISIBLE);
        }else{
            AdaptadorNotificacion miAdaptador = new AdaptadorNotificacion(notificaciones,getApplicationContext());
            ListaG.setAdapter(miAdaptador);
        }
    }

    public void seleccionFragments(MenuItem item){
        boolean CheckConexionCoordinador = true;
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        int id = item.getItemId();
        LayoutPrincipal.setVisibility(View.GONE);
        if(id == R.id.nav_Principal){
            Intent intent = new Intent(getApplicationContext(), Barra_desplegable.class);
            finish();
            startActivityForResult(intent,0);
        }else if(id == R.id.nav_Asignacion){
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
                Intent intent = new Intent(getApplicationContext(), Barra_desplegable.class);
                finish();
                startActivityForResult(intent,0);
            }
        }else if(id == R.id.nav_Ruta){
            getFragmentManager().beginTransaction().replace(R.id.contenedor,new LocalizacionLugares()).commit();
        }else if(id == R.id.nav_Info){
            ft.replace(R.id.contenedor, busqueda_informacion_adulto_mayor.newInstance());
            ft.addToBackStack(null);
            ft.commit();
        }//else if(id == R.id.nav_Sugerencias){
           // ft.replace(R.id.contenedor, menu_sugerencias.newInstance());
            //ft.addToBackStack(null);
            //ft.commit();
        else if(id == R.id.nav_Convivio){
            ft.replace(R.id.contenedor, Convivio.newInstance());
            ft.addToBackStack(null);
            ft.commit();
        }else if(id == R.id.nav_Inventario){
            ft.replace(R.id.contenedor, Menu_Inventario.newInstance());
            ft.addToBackStack(null);
            ft.commit();
        }else if(id == R.id.nav_Scouters){
            ft.replace(R.id.contenedor, Administracion_Scouter.newInstance());
            ft.addToBackStack(null);
            ft.commit();
        }else if(id == R.id.nav_Estadisticas){
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
    }
    void verificarVigencia(){

        String FechaActual;
        String FechaLogin;
        FechaLogin = LogUser.obtenerInstancia(getApplicationContext()).getFechaLogin();
        String []parteLogin = FechaLogin.split("-");
        //Toast.makeText(this, ""+parte[1], Toast.LENGTH_SHORT).show();
        FechaActual = generarFecha();
        String []parteActual = FechaActual.split("-");

        if(parteLogin[1].compareTo(parteActual[1]) == 0){

        }else{
            finish();
            LogUser.obtenerInstancia(getApplicationContext()).logout();
        }

    }
    private String generarFecha(){
        String Fecha;
        Calendar c = Calendar.getInstance();
        int Dia = c.get(Calendar.DAY_OF_MONTH);
        int Mes = c.get(Calendar.MONTH)+1;
        int Anio = c.get(Calendar.YEAR);
        String decenaD = "";
        String decenaM = "";
        if(Mes < 10){
            decenaM = "0";
        }
        if(Dia < 10){
            decenaD = "0";
        }
        Fecha = String.valueOf(Anio)+"-"+decenaM+String.valueOf(Mes)+"-"+decenaD+String.valueOf(Dia);
        return Fecha;
    }
    private class HiloActualizacion extends AsyncTask<Void, Void, Void> {

        Boolean Salir;
        @Override protected void onPreExecute() {

             Salir = true;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            if(CONECT.isConnected()){
                    Conectado = true;
                    Act = new ActualizacionBaseDatos(getApplicationContext());
                    Act.VolcarBasedeDatos();
                    Act.ActualizarBasedeDatos(getApplicationContext());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                Conectado = false;
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {

            /*if(!Conectado){
               if(hiloConexion != null){
                    hiloConexion = new HiloConexion();
                    hiloConexion.execute();
                }
                Toast.makeText(Barra_desplegable.this, "No conectado", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(Barra_desplegable.this, "Conectado", Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(Barra_desplegable.this, "Actualizado2", Toast.LENGTH_SHORT).show();*/
            seleccionFragments(ITEM);
        }
    }
    /*private class HiloConexion extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            while(!CONECT.isConnected()){

            }
            return null;
        }
        protected void onPostExecute(Void result) {
            Toast.makeText(Barra_desplegable.this, "Hola", Toast.LENGTH_SHORT).show();
            hiloConexion = null;
        }
    }*/

}
