package net.mattelsa.molaya.repository;

import net.mattelsa.molaya.model.Usuario;
import net.mattelsa.molaya.view.ReportView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Marlon Olaya on 24/03/2017.
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    List<Usuario> findByVehiculos_PlacaLike(String placa);
    List<Usuario> findByCedula(String cedula);
    List<Usuario> findAllByOrderByIdDesc();

}
