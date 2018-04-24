package com.rogzart.proyecto_interfaces.data;

import android.provider.BaseColumns;

public class BaseDeDatosLocalTablas {
    public static abstract class Adultos implements BaseColumns{
        public static final String Table_Name="adultomayor";
        public static final String IdAdultoMayor= "IdAdultoMayor";
        public static final String Nombre= "Nombre";
        public static final String ApellidoPaterno= "ApellidoPaterno";
        public static final String ApellidoMaterno= "ApellidoMaterno";
        public static final String Fotografia= "Fotografia";
        public static final String Diabetico= "Diabetico";
        public static final String FkDependencia= "FkDependencia";
        public static final String FkDomicilio = "FkDomicilio";
    }
}
