package net.mattelsa.molaya.service;

import net.mattelsa.molaya.model.Registro;
import net.mattelsa.molaya.model.Rol;
import net.mattelsa.molaya.model.TipoVehiculo;
import net.mattelsa.molaya.model.Usuario;
import net.mattelsa.molaya.view.ReportView;

import java.util.List;

/**
 * Created by Marlon Olaya on 20/03/2017.
 */
public interface PersistenceService {

    List<TipoVehiculo> getTipoVehiculos();
    List<Rol> getRoles();
    List<Usuario> getUsuarios();
    List<Usuario> search(String search, String type);
    List<Usuario> search(String search);
    Usuario addUsuario(Usuario ave);
    Usuario updateUsuario(Usuario ave);
    void deleteUsuario(Integer id);
    Registro addRegistro(Registro registro);
    Registro updateRegistro(Registro registro);
    Registro searchRegistro(Integer id);
    List<Registro> searchRegistro(String search, String type);
    List<ReportView> getUsuariosByFecha(String ingreso);
    ReportView getUsuarioByFecha(String ingreso, Integer id);
}
