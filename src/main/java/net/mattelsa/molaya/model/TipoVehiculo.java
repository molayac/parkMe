package net.mattelsa.molaya.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Marlon Olaya on 24/03/2017.
 */
@Entity
@Table(name="TBL_TIPO_VEHICULO")
public class TipoVehiculo {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="IDTIPOV")
    private Integer id;

    @Column(name="NOMBRE")
    private String nombre;

    @OneToMany(mappedBy = "tipo")
    private List<Vehiculo> vehiculos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @JsonBackReference
    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculo(List<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }
}
