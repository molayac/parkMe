package net.mattelsa.molaya.repository;

import net.mattelsa.molaya.model.Registro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Marlon Olaya on 25/03/2017.
 */

public interface RegistroRepository extends JpaRepository<Registro, Integer> {
    static final String countByFechaLikeAndUserId="SELECT COUNT(DISTINCT fecha) from tbl_registro where " +
            "fecha like ?1% and idusuario = ?2";
    Registro findById(Integer id);
    List<Registro> findByUsuario_Cedula(String cedula);
    List<Registro> findByUsuario_Vehiculos_PlacaLike(String placa);

    @Query(value="SELECT * FROM TBL_REGISTRO WHERE fecha LIKE ?1% GROUP BY idusuario", nativeQuery = true)
    List<Registro> findByFechaLike(String ingreso);

    @Query(value="SELECT * FROM TBL_REGISTRO WHERE fecha LIKE ?1% AND idusuario = ?2 GROUP BY idusuario", nativeQuery = true)
    List<Registro> findByFechaLikeAndUserIdGroup(String ingreso, Integer id);

    @Query(value=countByFechaLikeAndUserId, nativeQuery = true)
    Integer countByFechaLikeAndUserId(String fecha, Integer id);
}

