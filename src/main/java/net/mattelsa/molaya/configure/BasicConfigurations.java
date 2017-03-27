package net.mattelsa.molaya.configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Marlon Olaya on 24/03/2017.
 */
@Configuration
public class BasicConfigurations extends WebMvcConfigurerAdapter {
    @Autowired
    private Environment env;

    /**
     * Crea carpeta en raíz de aplicación para almacenar imagenes cargadas y enruta el servicio REST.
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        String uploads = env.getProperty("application.external.folder");

        registry.addResourceHandler("/external/**").addResourceLocations("file:" + uploads + "/")
                .setCachePeriod(0);

        try {
            /*Create Directory By default*/
            Files.createDirectories(Paths.get(uploads));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
