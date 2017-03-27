package net.mattelsa.molaya.controller;

import net.mattelsa.molaya.model.Rol;
import net.mattelsa.molaya.model.TipoVehiculo;
import net.mattelsa.molaya.model.Usuario;
import net.mattelsa.molaya.service.impl.PersistenceServiceImpl;
import org.joda.time.DateTime;
import org.joda.time.DateTimeComparator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Marlon Olaya on 18/03/2017.
 */
@RestController
@RequestMapping("/usuarios")
public class UsuarioRestController {
    private final Logger logger = LoggerFactory.getLogger(UsuarioRestController.class);
    private PersistenceServiceImpl persistenceService;

    @Autowired
    public void setPersistenceService(PersistenceServiceImpl persistenceService) {
        this.persistenceService = persistenceService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Usuario> getUsuarios() {
        List<Usuario> usuarios = persistenceService.getUsuarios();
        return usuarios;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Usuario addItem(@RequestBody Usuario usuario) {
        usuario.setId(null);
        logger.info("Agregando Usuario" + usuario.toString());
        return persistenceService.addUsuario(usuario);
    }

    @RequestMapping(value="search", method = RequestMethod.GET)
    public List<Usuario> search(@RequestParam String word, @RequestParam(required=false) String type ) {
        if(type != null && word != null){
            return persistenceService.search(word, type);
        }
        if(word != null){
            return persistenceService.search(word);
        }
        return persistenceService.getUsuarios();
    }

    @RequestMapping(value="searchByCedula", method = RequestMethod.GET)
    public List<Usuario> searchByCedula(@RequestParam String word) {
        return persistenceService.search(word);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Usuario updateItem(@RequestBody Usuario updatedItem, @PathVariable Integer id) {
        updatedItem.setId(id);
        return persistenceService.updateUsuario(updatedItem);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteItem(@PathVariable Integer id) {
        persistenceService.deleteUsuario(id);
    }

    @RequestMapping("/roles")
    public List<Rol> getRoles(){
        return persistenceService.getRoles();
    }

    @RequestMapping("/tipos")
    public List<TipoVehiculo> getTipos(){
        return persistenceService.getTipoVehiculos();
    }

}
