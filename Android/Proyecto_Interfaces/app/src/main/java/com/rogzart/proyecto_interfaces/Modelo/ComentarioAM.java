package com.rogzart.proyecto_interfaces.Modelo;

public class ComentarioAM {
    private int IdComentarioAM;
    private String Nombre;
    private String Fecha;
    private Integer FkAdultoMayor;
    public ComentarioAM(){

    }

    public int getIdComentarioAM() {
        return IdComentarioAM;
    }

    public void setIdComentarioAM(int idComentarioAM) {
        IdComentarioAM = idComentarioAM;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public Integer getFkAdultoMayor() {
        return FkAdultoMayor;
    }

    public void setFkAdultoMayor(Integer fkAdultoMayor) {
        if(fkAdultoMayor == 0){
            fkAdultoMayor = null;
        }
        FkAdultoMayor = fkAdultoMayor;
    }
}
