package net.mattelsa.molaya.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Marlon Olaya on 25/03/2017.
 */
@Controller
public class MainController {

    @RequestMapping(value="/")
    public String index() {
        return "pages/index";
    }

    @RequestMapping(value="/reportes")
    public String registro() {
        return "pages/reportes";
    }

    @RequestMapping(value="/acerca")
    public String acerca() {
        return "pages/acerca";
    }

}
