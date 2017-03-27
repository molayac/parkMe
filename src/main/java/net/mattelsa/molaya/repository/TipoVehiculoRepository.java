package net.mattelsa.molaya.repository;

import net.mattelsa.molaya.model.Rol;
import net.mattelsa.molaya.model.TipoVehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Marlon Olaya on 24/03/2017.
 */
public interface TipoVehiculoRepository extends JpaRepository<TipoVehiculo, Integer> {
}
