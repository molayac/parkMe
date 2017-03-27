package net.mattelsa.molaya.controller;

import net.mattelsa.molaya.model.Usuario;
import net.mattelsa.molaya.service.impl.PersistenceServiceImpl;
import net.mattelsa.molaya.view.ReportView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Marlon Olaya on 26/03/2017.
 */
@RestController
@RequestMapping("reports")
public class ReportesRestController {
    private final Logger logger = LoggerFactory.getLogger(ReportesRestController.class);
    private PersistenceServiceImpl persistenceService;

    @Autowired
    public void setPersistenceService(PersistenceServiceImpl persistenceService) {
        this.persistenceService = persistenceService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<ReportView> getUsuariosXFecha(@RequestParam(required = false) String ingreso) {
        return persistenceService.getUsuariosByFecha(ingreso);

    }
    @RequestMapping(value="{id}",method = RequestMethod.GET)
    public ReportView getUsuariosXFecha(@PathVariable Integer id, @RequestParam(required = false) String ingreso) {
        logger.info(ingreso);
        return persistenceService.getUsuarioByFecha(ingreso, id);

    }
}
