package net.mattelsa.molaya.controller;

import net.mattelsa.molaya.model.Registro;
import net.mattelsa.molaya.model.Usuario;
import net.mattelsa.molaya.service.impl.PersistenceServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Marlon Olaya on 25/03/2017.
 */
@RestController
@RequestMapping("registros")
public class RegistroRestController {
    private final Logger logger = LoggerFactory.getLogger(RegistroRestController.class);
    private PersistenceServiceImpl persistenceService;

    @Autowired
    public void setPersistenceService(PersistenceServiceImpl persistenceService) {
        this.persistenceService = persistenceService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Registro addItem(@RequestBody Registro registro) {
        registro.setId(null);
        logger.info("Agregando Registro" + registro.toString());
        persistenceService.addRegistro(registro);
        return registro;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Registro updateItem(@RequestBody Registro updatedItem, @PathVariable Integer id) {
        updatedItem.setId(id);
        persistenceService.updateRegistro(updatedItem);
        return updatedItem;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Registro getItem(@PathVariable Integer id) {
        return persistenceService.searchRegistro(id);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<Registro> search(@RequestParam String word, @RequestParam(required = false) String type) {
        return persistenceService.searchRegistro(word, type);
    }
}
