package net.mattelsa.molaya.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Marlon Olaya on 24/03/2017.
 */
@Entity
@Table(name="TBL_ROL")
public class Rol {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="IDROL")
    private Integer id;

    @Column(name="NOMBRE")
    private String nombre;

    @OneToMany(mappedBy = "rol")
    private List<Usuario> usuarios;

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
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuario) {
        this.usuarios = usuario;
    }
}
