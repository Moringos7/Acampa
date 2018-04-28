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
        public static String FkAdultoMayor="FkAdultoMayor";
    }
    public static abstract class GestionInventario implements BaseColumns{
        public static String Table_NameGI="GestionInventario";
        public static String IdGestionInventario="IdGestionInventario";
        public static String FechaGT="FechaGT";
        public static String FkScouter="FkScouter";
        public static String Fkinventario="FkInventario";

    }
    public static abstract class Inventario implements  BaseColumns{
        public static String Table_NameIN ="Inventario";
        public static String IdInventario= "IdInventario";
        public static String Producto="Producto";
        public static String Cantidad= "Cantidad";
        public static String Existencia= "Existencia";
        public static String Descripcion="Descripcion";
        public static String Imagen="Imagen";
        public static String Comentario="Comentario";
        public static String Extra="Extra";
    }
    public static abstract class Password implements  BaseColumns{
        public static String Table_NameP="Password";
        public static String IdPassword="IdPassword";
        public static String Password="Password";
        public static String Intentos="Intentos";
        public static String FkUsuario="FkUsuario";
    }
    public static abstract class Problematica implements  BaseColumns{
        public static String Table_NamePr="Problematica";
        public static String IdProblematica="IdProblematica";
        public static String Fecha="Fecha";
        public static String Nombre="Nombre";
        public static String Sugerencia="Sugerencia";
        public static String FkUsuario="FkUsuario";
        public static String FkTipoProblematica="FkTipoProblematica";
    }
    public static abstract class Recoger implements  BaseColumns{
        public static String Table_NameR="Recoger";
        public static String IdRecoger="IdRecoger";
        public static String FkScouter="FkScouter";
        public static String FkAsignacion="FkAsignacion";
    }
    public static abstract class Scouter implements BaseColumns{
        public static String Table_NameS= "Scouter";
        public static String IdScouter="IdScouter";
        public static String FechaInicio="FechaInicio";
        public static String FechaFinal="FechaFinal";
        public static String FkUsuario="FkUsuario";
    }
    public static abstract class Seccion implements BaseColumns{
        public static String Table_NameSe="Seccion";
        public static String IdSeccion="IdSeccion";
        public static String Nombre="Nombre";
    }
    public static abstract class TipoEvento implements BaseColumns{
        public static String Table_NameTE="TipoEvento";
        public static String IdTipoEvento="IdTipoEvento";
        public static String Nombre="Nombre";
    }
    public static abstract class TipoProblematica implements BaseColumns{
        public static String Table_NameTP="TipoProblematica";
        public static String IdTipoProblematica="IdTipoProblematica";
        public static String Nombre="Nombre";
    }
    public static abstract class Ubicacion implements BaseColumns{
        public static String Table_NameU="Ubicacion";
        public static String IdUbicacion="IdUbicacion";
        public static String Longitud="Longitud";
        public static String Latitud="Latitud";
    }
    public static abstract class Usuario implements BaseColumns{
        public static String Table_NameUs="Usuario";
        public static String IdUsuario="IdUsuario";
        public static String Nombre="Nombre";
        public static String ApellidoPaterno="ApellidoPaterno";
        public static String ApellidoMaterno="ApellidoMaterno";
        public static String Correo="Correo";
        public static String Fotografia="Fotografia";
        public static String FechaNacimiento="FechaNacimiento";
        public static String Scout="Scout";
        public static String FkSeccion="FkSeccion";
    }
    public static abstract class VoluntarioFrecuente implements BaseColumns{
        public static String Table_NameVF="VoluntarioFrecuente";
        public static String IdVoluntarioFrecuente="IdVoluntarioFrecuente";
        public static String FkUsuario="FkUsuario";
        public static String FkAdultoMayor="FkAdultoMayor";
    }
}
