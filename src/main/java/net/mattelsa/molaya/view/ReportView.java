package net.mattelsa.molaya.view;

import org.springframework.stereotype.Component;

import javax.persistence.Embedded;

/**
 * Created by Marlon Olaya on 26/03/2017.
 */
public class ReportView {

    private Integer id;
    private Integer cantdias;
    private String nombres;
    private String cedula;
    private String rol;
    private String apellidos;

    public ReportView(Integer id, Integer cantdias, String nombres, String rol, String apellidos, String cedula) {
        this.id = id;
        this.cantdias = cantdias;
        this.nombres = nombres;
        this.rol = rol;
        this.apellidos = apellidos;
        this.cedula = cedula;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public Integer getCantdias() {
        return cantdias;
    }

    public void setCantdias(Integer cantdias) {
        this.cantdias = cantdias;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
}
