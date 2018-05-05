package com.rogzart.proyecto_interfaces.sqlite;

public class EstructuraBaseDatos {
    interface Columnasadultomayor {
        String Id = "IdAdultoMayor";
        String Nombre = "Nombre";
        String ApellidoPaterno = "ApellidoPaterno";
        String ApellidoMaterno = "ApellidoMaterno";
        String Fotografia = "Fotografia";
        String Diabetico = "Diabetico";
        String FkDependencia = "FkDependencia";
        String FkDomicilio = "FkDomicilio";
    }

    interface Columnasasignacion {
        String Id = "IdAsignacion";
        String Status = "Status";
        String Fecha = "Fecha";
        String FkUsuario = "FkUsuario";
        String FkAdultoMayor = "FkAdultoMayor";
    }

    interface Columnascomentarioam {
        String Id = "IdComentarioAM";
        String Nombre = "Nombre";
        String Fecha = "Fecha";
        String FkAdultoMayor = "FkAdultoMayor";
    }

    interface Columnasdependencia {
        String Id = "IdDependencia";
        String Nombre = "Nombre";
    }

    interface Columnasdomicilio {
        String Id = "IdDomicilio";
        String Numero = "Numero";
        String Calle = "Calle";
        String Colonia = "Colonia";
        String Foto = "Foto";
        String FkUbicacion = "FkUbicacion";
    }

    interface Columnasevento {
        String Id = "IdEvento";
        String Fecha = "Fecha";
        String Hora = "Hora";
        String Lugar = "Lugar";
        String Informacion = "Informacion";
        String FkTipoEveto = "FkTipoEvento";
    }

    interface Columnasfotoalrededores {
        String Id = "IdFotoAlrededores";
        String Foto = "Foto";
        String FkDomiilio = "FkDomicilio";
    }

    interface Columnasgestioninventario {
        String Id = "IdGestionInventario";
        String Fecha = "Fecha";
        String FkScouter = "FkScouter";
        String FkInventario = "FkInventario";
    }

    interface Columnasinventario {
        String Id = "IdInventario";
        String Producto = "Producto";
        String Cantidad = "Cantidad";
        String Existencia = "Existencia";
        String Descripcion = "Descripcion";
        String Imagen = "Imagen";
        String Comentario = "Comentario";
        String Extra = "Extra";
    }

    interface Columnasproblematica {
        String Id = "IdProblematica";
        String Fecha = "Fecha";
        String Nombre = "Nombre";
        String Sugerencia = "Sugerencia";
        String FkUsuario ="FkUsuario";
        String FkTipoProblematica = "FkTipoProblematica";
    }
    interface Columnasrecoger{
        String Id = "IdRecoger";
        String FkRScouter = "FkScouter";
        String FkAsignacion = "FkAsignacion";
    }
    interface Columnasscouter{
        String Id = "IdScouter";
        String FechaInicio = "FechaInicio";
        String FechaFinal = "FechaFinal";
        String FkUsuario = "FkUsuario";
    }
    interface Columnasseccion{
        String Id = "IdSeccion";
        String Nombre = "Nombre";
    }
    interface Columnastipoevento{
        String Id = "IdTipoEvento";
        String Nombre = "Nombre";
    }
    interface Columnastipoproblematica{
        String Id = "IdProblematica";
        String Nombre = "Nombre";
    }
    interface Columnasubicacion{
        String Id = "IdUbicacion";
        String Longitud = "Longitud";
        String Latitud = "Latitud";
    }
    interface Columnasusuario{
        String Id= "IdUsuario";
        String Nombre = "Nombre";
        String ApellidoPaterno = "ApellidoPaterno";
        String ApellidoMaterno = "ApellidoMaterno";
        String Correo = "Correo";
        String Fotografia = "Fotografia";
        String FechaNacimiento = "FechaNacimiento";
        String Scout = "Scout";
        String FkSeccion = "FkSeccion";
    }
    interface Columnasvoluntariofrecuente{
        String Id = "IdVoluntarioFrecuente";
        String FkUsuario = "FkUsuario";
        String FkAdultoMayor = "FkAdultoMayor";
    }
    public static class adultomayor implements Columnasadultomayor {

    }
    public static class asignacion implements Columnasasignacion {

    }
    public static class comentarioam implements Columnascomentarioam {

    }
    public static class dependencia  implements Columnasdependencia {

    }
    public static class domicilio implements Columnasdomicilio {

    }
    public static class evento implements Columnasevento {

    }
    public static class fotoalrededores implements Columnasfotoalrededores {

    }
    public static class gestioninventario implements Columnasgestioninventario {

    }public static class inventario implements Columnasinventario {

    }public static class problematica implements Columnasproblematica {

    }public static class recoger implements Columnasrecoger {

    }public static class scouter implements Columnasscouter {

    }
    public static class seccion implements Columnasseccion {

    }public static class tipoevento implements Columnastipoevento {

    }public static class tipoproblematica implements Columnastipoproblematica {

    }public static class ubicacion implements Columnasubicacion {

    }
    public static class usuario implements Columnasusuario {

    }
    public static class voluntariofrecuente implements Columnasvoluntariofrecuente {

    }







}
