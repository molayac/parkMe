package net.mattelsa.molaya.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Marlon Olaya on 25/03/2017.
 */
@Component
public class ArchivoExterno {
    @Value("${application.external.folder}")
    private String ruta;
    private String filename;
    private String base;
    private String id;

    @Autowired
    public ArchivoExterno() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        this.base = "Ext_img_";
        this.id = sdf.format(new Date());
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        String extension = filename.substring(filename.indexOf("."), filename.length());
        this.filename = base + id + extension;
    }

    public String getBase() {
        return base;
    }

    public String getId() {
        return id;
    }

}
