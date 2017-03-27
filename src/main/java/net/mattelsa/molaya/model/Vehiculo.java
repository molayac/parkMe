package net.mattelsa.molaya.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Marlon Olaya on 24/03/2017.
 */
@Entity
@Table(name="TBL_VEHICULO")
public class Vehiculo {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="IDVEHICULO")
    private Integer id;

    @ManyToMany(fetch=FetchType.LAZY, mappedBy = "vehiculos", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonBackReference
    private List<Usuario> usuarios;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="IDTIPOV")
    private TipoVehiculo tipo;

    @Column(name="CC_MODELO")
    private String ccModelo;

    @Column(name="TIEMPOS_NPUERTAS")
    private Integer tiemposNPuertas;

    @Column(name="PLACA")
    private String placa;

    @Column(name="RUTA_IMAGEN")
    private String imagen;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public String getCcModelo() {
        return ccModelo;
    }

    public void setCcModelo(String ccModelo) {
        this.ccModelo = ccModelo;
    }

    public Integer getTiemposNPuertas() {
        return tiemposNPuertas;
    }

    public void setTiemposNPuertas(Integer tiemposNPuertas) {
        this.tiemposNPuertas = tiemposNPuertas;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public TipoVehiculo getTipo() {
        return tipo;
    }

    public void setTipo(TipoVehiculo tipo) {
        this.tipo = tipo;
    }

}
