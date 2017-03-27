package net.mattelsa.molaya.model;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Marlon Olaya on 24/03/2017.
 */
@Entity
@Table(name="TBL_USUARIO")
public class Usuario {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="IDUSUARIO")
    private Integer id;

    @Column(name="CEDULA", unique = true, nullable = false)
    private String cedula;

    @Column(name="NOMBRES")
    private String nombres;

    @Column(name="APELLIDOS")
    private String apellidos;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "IDROL")
    private Rol rol;

    @OneToMany(mappedBy = "usuario",cascade = {CascadeType.REMOVE, CascadeType.MERGE})
    private List<Registro> registro;

    @JoinTable(name = "TBL_USUARIOS_VEHICULO",
            joinColumns={
                    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "idvehiculo", referencedColumnName = "idvehiculo")
            }
    )
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private List<Vehiculo> vehiculos;

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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(List<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }


    public List<Registro> getRegistro() {
        return registro;
    }

    public void setRegistro(List<Registro> registro) {
        this.registro = registro;
    }
}
