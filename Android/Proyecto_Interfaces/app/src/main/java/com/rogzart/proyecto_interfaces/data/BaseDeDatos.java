package com.rogzart.proyecto_interfaces.data;

import android.content.ContentValues;
import android.icu.text.IDNA;
import android.net.UrlQuerySanitizer;

import java.util.UUID;

import static com.rogzart.proyecto_interfaces.data.BaseDeDatosLocalTablas.*;

public class BaseDeDatos {
    private String IdAdultoMayor;
    private String Nombre;
    private String ApellidoPaterno;
    private String ApellidoMaterno;
    private String Fotografia;
    private String Diabetico;
    private String FkDependencia;
    private String FkDomicilio;
    private String IdAsignacion;
    private String Status;
    private String Fecha;
    private String FkUsuario;
    private String FkAdultoMayor;
    private String IdComentarioAM;
    private String NombreC;
    private String FechaC;
    private String IdCoordinador;
    private String FkScouter;
    private String IdDependencia;
    private String NombreD;
    private String IdDomicilio;
    private String Numero;
    private String Calle;
    private String Colonia;
    private String FotoD;
    private String FkUbicacion;
    private String IdEvento;
    private String FechaE;
    private String Hora;
    private String Lugar;
    private String Informacion;
    private String FkTipoEvento;


    public BaseDeDatos(String Nombre,
                       String ApellidoPaterno,
                       String ApellidoMaterno,
                       String Fotografia,
                       String DIabetico,
                       String FkDependencia,
                       String FkDomicilio,
                       String IdAsignacion,
                       String Status,
                       String Fecha,
                       String FkUsuario,
                       String FkAdultoMayor,
                       String IdComentarioAM,
                       String NombreC,
                       String FechaC,
                       String IdCoordinador,
                       String FkScouter,
                       String IdDependencia,
                       String NombreD,
                       String IdDomicilio,
                       String Numero,
                       String Calle,
                       String Colonia,
                       String FotoD,
                       String FkUbicacion,
                       String IdEvento,
                       String FechaE,
                       String Hora,
                       String Lugar,
                       String Informacion,
                       String FkTipoEvento;
                       ) {
        this.IdAdultoMayor = UUID.randomUUID().toString();
        this.Nombre = Nombre;
        this.ApellidoPaterno = ApellidoPaterno;
        this.ApellidoMaterno = ApellidoMaterno;
        this.Fotografia = Fotografia;
        this.Diabetico = DIabetico;
        this.FkDependencia = FkDependencia;
        this.FkDomicilio = FkDomicilio;
        this.IdAsignacion= IdAsignacion;
        this.Status= Status;
        this.Fecha= Fecha;
        this.FkUsuario= FkUsuario;
        this.FkAdultoMayor= FkAdultoMayor;
        this.IdComentarioAM=IdComentarioAM;
        this.NombreC=NombreC;
        this.FechaC=FechaC;
        this.FkAdultoMayor=FkAdultoMayor;
        this.IdCoordinador=IdCoordinador;
        this.FkScouter=FkScouter;
        this.IdDependencia=IdDependencia;
        this.NombreD=NombreD;
        this.IdDomicilio=IdDomicilio;
        this.Numero=Numero;
        this.Calle=Calle;
        this.Colonia=Colonia;
        this.FotoD=FotoD;
        this.FkUbicacion=FkUbicacion;
        this.IdEvento=IdEvento;
        this.FechaE=FechaE;
        this.Hora=Hora;
        this.Lugar=Lugar;
        this.Informacion=Informacion;
        this.FkTipoEvento=FkTipoEvento;



    }

    public String getIdAdultoMayor() {
        return IdAdultoMayor;

    }

    public String getNombre() {
        return Nombre;

    }

    public String getApellidoPaterno() {
        return ApellidoPaterno;

    }

    public String getApellidoMaterno() {
        return ApellidoMaterno;

    }

    public String getFotografia() {
        return Fotografia;

    }

    public String getDiabetico() {
        return Diabetico;

    }

    public String getFkDependencia() {
        return FkDependencia;

    }

    public String getFkDomicilio() {
        return FkDomicilio;

    }
    public String getIdAsignacion() {
        return IdAsignacion;

    }
    public String getStatus() {
        return Status;

    }
    public String getFecha() {
        return Fecha;

    }
    public String getFkUsuario() {
        return FkUsuario;

    }
    public String getFkAdultoMayor() {
        return FkAdultoMayor;

    }
    public String getIdComentarioAM() {
        return IdComentarioAM;

    }
    public String NombreC() {
        return NombreC;

    }
    public String getFechaC() {
        return FechaC;

    }
    public String getIdCoordinador(){
        return IdCoordinador;
    }
    public String getFkScouter() {
        return FkScouter;

    }
    public String getIdDependencia() {
        return IdDependencia;

    }
    public String getNombreD() {
        return NombreD;

    }
    public String getIdDomicilio() {
        return IdDomicilio;

    }
    public String getNumero() {
        return Numero;

    }
    public String getCalle() {
        return Calle;

    }
    public String Colonia() {
        return Colonia;

    }
    public String getFotoD() {
        return FotoD;

    }
    public String getFkUbicacion() {
        return FkUbicacion;

    }
    public String getIdEvento() {
        return IdEvento;

    }
    public String Fecha() {
        return Fecha;

    }
    public String getLugar() {
        return Lugar;

    }
    public String getInformacion() {
        return Informacion;

    }
    public String getFkTipoEvento() {
        return FkTipoEvento;

    }






    public ContentValues toContentValues() {
        ContentValues values= new ContentValues();
        values.put(Adultos.IdAdultoMayor, IdAdultoMayor);
        values.put(Adultos.Nombre, Nombre);
        values.put(Adultos.ApellidoPaterno, ApellidoPaterno);
        values.put(Adultos.ApellidoMaterno, ApellidoMaterno);
        values.put(Adultos.Fotografia, Fotografia);
        values.put(Adultos.Diabetico, Diabetico);
        values.put(Adultos.FkDependencia, FkDependencia);
        values.put(Adultos.FkDomicilio, FkDomicilio);
        return values;
    }
}
