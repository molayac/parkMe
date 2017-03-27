package net.mattelsa.molaya.service.impl;

import net.mattelsa.molaya.model.*;
import net.mattelsa.molaya.repository.*;
import net.mattelsa.molaya.service.PersistenceService;
import net.mattelsa.molaya.view.ReportView;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import org.slf4j.Logger;

/**
 * Created by Marlon Olaya on 24/03/2017.
 */
@Service
public class PersistenceServiceImpl implements PersistenceService {
    private final Logger logger = LoggerFactory.getLogger(PersistenceServiceImpl.class);
    private UsuarioRepository usuarioRepository;
    private RolRepository rolRepository;
    private TipoVehiculoRepository tipoVehiculoRepository;
    private VehiculoRepository vehiculoRepository;
    private RegistroRepository registroRepository;


    @Autowired
    public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Autowired
    public void setRolRepository(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    @Autowired
    public void setTipoVehiculoRepository(TipoVehiculoRepository tipoVehiculoRepository) {
        this.tipoVehiculoRepository = tipoVehiculoRepository;
    }

    @Autowired
    public void setVehiculoRepository(VehiculoRepository vehiculoRepository) {
        this.vehiculoRepository = vehiculoRepository;
    }

    @Autowired
    public void setRegistroRepository(RegistroRepository registroRepository) {
        this.registroRepository = registroRepository;
    }

    @Override
    public List<TipoVehiculo> getTipoVehiculos() {
        return tipoVehiculoRepository.findAll();
    }

    @Override
    public List<Rol> getRoles() {
        return rolRepository.findAll();
    }

    @Override
    public List<ReportView> getUsuariosByFecha(String ingreso) {
        if(ingreso == null || ingreso.isEmpty()){
            ingreso = getDateString("YYYY-MM") + "%";
        }else{
            ingreso += "%";
        }
        List<ReportView> reports = new ArrayList<ReportView>();
        List<Registro> registros = registroRepository.findByFechaLike(ingreso);
        String finalIngreso = ingreso;
        registros.stream().forEach(p->{
                    Usuario user = usuarioRepository.findOne(p.getUsuario().getId());
                    Integer count = registroRepository.countByFechaLikeAndUserId(finalIngreso, user.getId());
                    reports.add(new ReportView(user.getId(), count, user.getNombres(), user.getRol().getNombre(),user.getApellidos(), user.getCedula()));
                }
        );
        return reports;
    }

    @Override
    public ReportView getUsuarioByFecha(String ingreso, Integer id) {
        if(ingreso == null || ingreso.isEmpty()){
            ingreso = getDateString("YYYY-MM") + "%";
        }
        else{
            ingreso += "%";
        }
        List<Registro> registros = registroRepository.findByFechaLikeAndUserIdGroup(ingreso, id);
        List<ReportView> reports = new ArrayList<ReportView>();
        String finalIngreso = ingreso;
        registros.stream().forEach(p->{
                    Usuario user = usuarioRepository.findOne(p.getUsuario().getId());
                    Integer count = registroRepository.countByFechaLikeAndUserId(finalIngreso, user.getId());
                    reports.add(new ReportView(user.getId(), count, user.getNombres(), user.getRol().getNombre(),user.getApellidos(), user.getCedula()));
                }
        );
        if(reports.size() == 0){
            reports.add(null);
        }
        return reports.get(0);
    }

    @Override
    public List<Usuario> getUsuarios() {
        return usuarioRepository.findAllByOrderByIdDesc();
    }

    @Override
    public List<Usuario> search(String search, String type) {
        if (type.equalsIgnoreCase("cedula")) {
            return usuarioRepository.findByCedula(search);
        }
        return usuarioRepository.findByVehiculos_PlacaLike(search);
    }

    @Override
    public Usuario addUsuario(Usuario usuario) {
        List<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario updateUsuario(Usuario usuario) {
        return usuarioRepository.saveAndFlush(usuario);
    }

    @Override
    public void deleteUsuario(Integer id) {
        usuarioRepository.delete(id);
    }

    @Override
    public List<Usuario> search(String search) {
        return usuarioRepository.findByCedula(search);
    }

    @Override
    public Registro addRegistro(Registro registro) {
        registro.setIngreso(getDateTime());
        registro.setFecha(getDateString("YYYY-MM-dd"));
        return registroRepository.save(registro);
    }

    @Override
    public Registro updateRegistro(Registro registro) {
        //registro = registroRepository.getOne(registro.getId());
        logger.info("Registro: " + registro.getIngreso());
        registro.setSalida(getDateTime());
        return registroRepository.saveAndFlush(registro);
    }

    @Override
    public Registro searchRegistro(Integer id) {
        return registroRepository.findById(id);
    }

    @Override
    public List<Registro> searchRegistro(String search, String type) {
        if (type.equalsIgnoreCase("cedula")) {
            return registroRepository.findByUsuario_Cedula(search);
        }
        return registroRepository.findByUsuario_Vehiculos_PlacaLike(search);
    }

    private Date getDateTime() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(
                    "yyyy/MM/dd HH:mm:ss");
            return dateFormat.parse(dateFormat.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getDateString(String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                pattern);
        return dateFormat.format(new Date());
    }
}
