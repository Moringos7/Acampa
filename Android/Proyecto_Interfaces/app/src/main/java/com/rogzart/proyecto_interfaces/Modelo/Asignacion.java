package com.rogzart.proyecto_interfaces.Modelo;

public class Asignacion {
    private int IdAsignacion;
    private int Status;
    private String Fecha;
    private Integer FkUsuario;
    private Integer FkAdultoMayor;
    public Asignacion() {

    }

    public int getIdAsignacion() {
        return IdAsignacion;
    }

    public void setIdAsignacion(int idAsignacion) {
        IdAsignacion = idAsignacion;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public Integer getFkUsuario() {
        return FkUsuario;
    }

    public void setFkUsuario(Integer fkUsuario) {
        if(fkUsuario == 0){
            fkUsuario = null;
        }
        FkUsuario = fkUsuario;
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
