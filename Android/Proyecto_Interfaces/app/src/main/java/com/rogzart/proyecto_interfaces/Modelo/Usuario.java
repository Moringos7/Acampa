package com.rogzart.proyecto_interfaces.Modelo;

import java.io.Serializable;

public class Usuario implements Serializable {
    private int IdUsuario;
    private String Nombre;
    private String ApellidoPaterno;
    private String ApellidoMaterno;
    private String Correo;
    private String Fotografia;
    private String FechaNacimiento;
    private int Scout;
    private Integer FkSeccion;

    private Boolean Check;
    public Usuario(){
        this.Check = false;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        IdUsuario = idUsuario;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellidoPaterno() {
        return ApellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        ApellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return ApellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        ApellidoMaterno = apellidoMaterno;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getFotografia() {
        return Fotografia;
    }

    public void setFotografia(String fotografia) {
        Fotografia = fotografia;
    }

    public String getFechaNacimiento() {
        return FechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        FechaNacimiento = fechaNacimiento;
    }

    public int getScout() {
        return Scout;
    }

    public void setScout(int scout) {
        Scout = scout;
    }

    public Integer getFkSeccion() {
        return FkSeccion;
    }

    public void setFkSeccion(Integer fkSeccion) {
        if(fkSeccion == 0){
            fkSeccion = null;
        }
        FkSeccion = fkSeccion;
    }
    public Boolean getCheck() {
        return Check;
    }

    public void setCheck(Boolean check) {
        Check = check;
    }
}
