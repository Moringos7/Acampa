package com.rogzart.proyecto_interfaces.data;

import android.provider.BaseColumns;

public class BaseDeDatosLocalTablas {
    public static abstract class Adultos implements BaseColumns{
        public static final String Table_NameAM="adultomayor";
        public static final String IdAdultoMayor= "IdAdultoMayor";
        public static final String Nombre= "Nombre";
        public static final String ApellidoPaterno= "ApellidoPaterno";
        public static final String ApellidoMaterno= "ApellidoMaterno";
        public static final String Fotografia= "Fotografia";
        public static final String Diabetico= "Diabetico";
        public static final String FkDependencia= "FkDependencia";
        public static final String FkDomicilio = "FkDomicilio";
    }
    public static abstract class Asignacion implements BaseColumns{
        public static String Table_NameAs="asignacion";
        public static String IdAsignacion= "IdAsignacion";
        public static String Status="Status";
        public static String Fecha="Fecha";
        public static String FkUsuario="FkUsuario";
        public static String FkAdultoMayor="FkAdultoMayor";
    }
    public static abstract class ComentarioAM implements BaseColumns{
        public static String Table_NameCAM="ComentarioAM";
        public static String IdComentarioAM="IdComenatioAM";
        public static String NombreC="NombreC";
        public static String FechaC="FechaC";
        public static String FkAdultoMayor="FkAdultoMayor";

    }
    public static abstract class Coordinador implements BaseColumns{
        public static String Table_NameCo="Coordinador";
        public static String IdCoordinador="IdCoordinador";
        public static String FkScouter="FjSCouter";
    }
    public static abstract class Dependencia implements  BaseColumns{
        public static String Table_NameD="Dependencia";
        public static String IdDependencia="IdDeoendencia";
        public static String NombreD="NombreD";
    }
    public static abstract class Domicilio implements BaseColumns{
        public static String Table_NameDom="Domicilio";
        public static String IdDomicilio="IdDomicilio";
        public static String Numero="Numero";
        public static String Calle="Calle";
        public static String Colonia="Colonia";
        public static String FotoD="FotoD";
        public static String FkUbicacion="FkUbicacion";
    }
    public static abstract class Evento implements  BaseColumns{
        public static String Table_NameE="Evento";
        public static String IdEvento="IdEvento";
        public static String FechaC="FechaC";
        public static String Hora="Hora";
        public static String Lugar="Lugar";
        public static String Informacion="Informacion";
        public static String FkTipoEvento="FkTipoEvento";
    }
    public static abstract class FotoAlrededores implements BaseColumns{
        public static String Table_NameFA="FotoAlrededores";
        public static String IdEventosAlrededores="IdEventoAlrededores";
        public static String Foto="Foto";
    }
    public static abstract class GestionInventario implements BaseColumns{
        public static String Table_NameGI="GestionInventario";
        public static String IdGestionInventario="IdGestionInventario";
        public static String FechaGT="FechaGT";
        public static String Fkinventario="FkInventario";
    }
}
