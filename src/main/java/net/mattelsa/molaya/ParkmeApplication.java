package net.mattelsa.molaya;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.TimeZone;

@SpringBootApplication
public class ParkmeApplication {

    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    public static void main(String[] args) {
        try {
			/*Create Directory By default*/
            Files.createDirectories(Paths.get("db"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        SpringApplication.run(ParkmeApplication.class, args);
    }

}
