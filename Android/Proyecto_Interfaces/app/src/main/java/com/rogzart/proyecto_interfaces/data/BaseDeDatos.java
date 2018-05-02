package com.rogzart.proyecto_interfaces.data;

import android.content.ContentValues;
import android.content.Context;
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
    private String IdFotoAlrededores;
    private String Foto;
    private String IdGestionInventario;
    private String FechaGI;
    private String FkInventario;
    private String IdInventario;
    private String Producto;
    private String Cantidad;
    private String Existencia;
    private String Descripcion;
    private String Imagen;
    private String Comentario;
    private String Extra;
    private String IdPassword;
    private String Password;
    private String Intentos;
    private String IdProblematica;
    private String FechaP;
    private String NombreP;
    private String Sugerencia;
    private String FkTipoProblematica;
    private String IdRecoger;
    private String FkAsignacion;
    private String IdScouter;
    private String FechaInicio;
    private String FechaFinal;
    private String IdSeccion;
    private String NombreS;
    private String IdTipoEvento;
    private String NombreTE;
    private String IdTipoProblematico;
    private String NombreTP;
    private String IdUbicacion;
    private String Longitud;
    private String Latitud;
    private String IdUsuario;
    private String NombreU;
    private String ApellidoPatenoU;
    private String ApellidoMaternoU;
    private String Correo;
    private String FotografiaU;
    private String FechaNacimiento;
    private String Scout;
    private String FkSeccion;
    private String IdVoluntarioFrecuente;











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
                       String FkTipoEvento,
                       String IdFotoAlrededores,
                       String Foto,
                       String IdGestionInventario,
                       String FechaGI,
                       String FkInventario,
                       String IdInventario,
                       String Producto,
                       String Cantidad,
                       String Existencia,
                       String Descripcion,
                       String Imagen,
                       String Comentario,
                       String Extra,
                       String IdPassword,
                       String Password,
                       String Intentos,
                       String IdProblematica,
                       String FechaP,
                       String NombreP,
                       String Sugerencia,
                       String FkTipoProblematica,
                       String IdRecoger,
                       String FkAsignacion,
                       String IdScouter,
                       String FechaInicio,
                       String FechaFinal,
                       String IdSeccion,
                       String NombreS,
                       String IdTipoEvento,
                       String NombreTE,
                       String IdTipoProblematico,
                       String NombreTP,
                       String IdUbicacion,
                       String Longitud,
                       String Latitud,
                       String IdUsuario,
                       String NombreU,
                       String ApellidoPatenoU,
                       String ApellidoMaternoU,
                       String Correo,
                       String FotografiaU,
                       String FechaNacimiento,
                       String Scout,
                       String FkSeccion,
                       String IdVoluntarioFrecuente
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
        this.IdFotoAlrededores=IdFotoAlrededores;
        this.Foto=Foto;
        this.IdGestionInventario=IdGestionInventario;
        this.FechaGI=FechaGI;
        this.FkInventario=FkInventario;
        this.IdInventario=IdInventario;
        this.Producto=Producto;
        this.Cantidad=Cantidad;
        this.Existencia=Existencia;
        this.Descripcion=Descripcion;
        this.Imagen=Imagen;
        this.Comentario=Comentario;
        this.Extra=Extra;
        this.IdPassword=IdPassword;
        this.Password=Password;
        this.Intentos=Intentos;
        this.IdProblematica=IdProblematica;
        this.FechaP=FechaP;
        this.NombreP=NombreP;
        this.Sugerencia=Sugerencia;
        this.FkTipoProblematica=FkTipoProblematica;
        this.IdRecoger=IdRecoger;
        this.FkAsignacion=FkAsignacion;
        this.IdScouter=IdScouter;
        this.FechaInicio=FechaInicio;
        this.FechaFinal=FechaFinal;
        this.IdSeccion=IdSeccion;
        this.NombreS=NombreS;
        this.IdTipoEvento=IdTipoEvento;
        this.NombreTE=NombreTE;
        this.IdTipoProblematico=IdTipoProblematico;
        this.NombreTP=NombreTP;
        this.IdUbicacion=IdUbicacion;
        this.Longitud=Longitud;
        this.Latitud=Latitud;
        this.IdUsuario=IdUsuario;
        this.NombreU=NombreU;
        this.ApellidoPatenoU=ApellidoPatenoU;
        this.ApellidoMaternoU=ApellidoMaternoU;
        this.Correo=Correo;
        this.FotografiaU=FotografiaU;
        this.FechaNacimiento=FechaNacimiento;
        this.Scout=Scout;
        this.FkSeccion=FkSeccion;
        this.IdVoluntarioFrecuente=IdVoluntarioFrecuente;


    }

    public BaseDeDatos(Context applicationContext) {
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






    public ContentValues toContentValuesAM() {
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
    public ContentValues toContentValuesAS(){
        ContentValues values=new ContentValues();
        values.put(Asignacion.IdAsignacion, IdAsignacion);
        values.put(Asignacion.Status, Status);
        values.put(Asignacion.Fecha, Fecha);
        values.put(Asignacion.FkUsuario, FkUsuario);
        values.put(Asignacion.FkAdultoMayor, FkAdultoMayor);
        return values;
    }
    public ContentValues toContentValuesCAM(){
        ContentValues values= new ContentValues();
        values.put(ComentarioAM.IdComentarioAM, IdComentarioAM);
        values.put(ComentarioAM.NombreC, NombreC );
        values.put(ComentarioAM.FechaC, FechaC);
        values.put(ComentarioAM.FkAdultoMayor,FkAdultoMayor);
        return values;
    }
    public ContentValues toContentValuesCo(){
        ContentValues values= new ContentValues();
        values.put(Coordinador.IdCoordinador, IdCoordinador);
        values.put(Coordinador.FkScouter, FkScouter);
        return values;
    }
    public ContentValues toContentDependencia(){
        ContentValues values= new ContentValues();
        values.put(Dependencia.IdDependencia, IdDependencia);
        values.put(Dependencia.NombreD, NombreD);
        return values;
    }
    public ContentValues toContentDomicilio(){
        ContentValues values= new ContentValues();
        values.put(Domicilio.IdDomicilio,IdDomicilio);
        values.put(Domicilio.Numero,Numero);
        values.put(Domicilio.Calle,Calle);
        values.put(Domicilio.Colonia,Colonia);
        values.put(Domicilio.FotoD,FotoD);
        values.put(Domicilio.FkUbicacion,FkUbicacion);
        return values;
    }
    public ContentValues toContentEvento(){
        ContentValues values= new ContentValues();
        values.put(Evento.IdEvento,IdEvento);
        values.put(Evento.FechaC,FechaC);
        values.put(Evento.Hora,Hora);
        values.put(Evento.Lugar,Lugar);
        values.put(Evento.Informacion,Informacion);
        values.put(FkTipoEvento,FkTipoEvento);
        return values;

    }

}
