package net.mattelsa.molaya.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Marlon Olaya on 25/03/2017.
 */
@Entity
@Table(name="TBL_REGISTRO")
public class Registro {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="IDREGISTRO")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE})
    @JoinColumn(name="IDUSUARIO")
    private Usuario usuario;

    @Column(name="FECHA", columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private String fecha;

    @Column(name="FINGRESO")
    private Date ingreso;

    @Column(name="FSALIDA")
    private Date salida;

    @Column(name="CELDA")
    private String celda;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonBackReference
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getIngreso() {
        return ingreso;
    }

    public void setIngreso(Date ingreso) {
        this.ingreso = ingreso;
    }

    public Date getSalida() {
        return salida;
    }

    public void setSalida(Date salida) {
        this.salida = salida;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCelda() {
        return celda;
    }

    public void setCelda(String celda) {
        this.celda = celda;
    }
}
