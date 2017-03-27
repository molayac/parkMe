package net.mattelsa.molaya.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.mattelsa.molaya.util.ArchivoExterno;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("file")
public class FileController {

    private final Logger logger = LoggerFactory.getLogger(FileController.class);
    // The Environment object will be used to read parameters from the
    // application.properties configuration file
    private Environment env;

    private ArchivoExterno archivoExterno;

    @Autowired
    public void setArchivoExterno(ArchivoExterno archivoExterno) {
        this.archivoExterno = archivoExterno;
    }

    @Autowired
    public void setEnv(Environment env) {
        this.env = env;
    }

    /**
     * Muestra la pagina index con el formulario de subir
     * Show the index page containing the form for uploading a file.
     */
    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "test.html";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String uploadFile(
            @RequestParam("file") MultipartFile file) {

        try {
            archivoExterno.setFilename(file.getOriginalFilename());
            String filepath = Paths.get(archivoExterno.getRuta(), archivoExterno.getFilename()).toString();
            logger.info("Cargando archivo en external path: " + filepath);
            // Save the file locally
            BufferedOutputStream stream =
                    new BufferedOutputStream(new FileOutputStream(new File(filepath)));
            stream.write(file.getBytes());
            stream.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "";
        }
        return archivoExterno.getFilename();
    } // method uploadFile

} // class MainController

