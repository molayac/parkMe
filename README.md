#ParkMe!
Este es un proyecto realizado con:

- Spring Boot
- Spring MVC
- Spring Data JPA
- AngularJS 
- Bootstrap
- Base de datos SQLITE Embebida

El objetvo: una prueba técnica, trata de 
cumplir con los requisitos solicitados.

#Requisitos para ejecutar:
- Java 8 instalado.


    Para ejecutarlo por consola, debe:
        mvn clean package
        java -jar parkme-0.0.1-SNAPSHOT.jar [opciones]
    
    Dentro de las opciones esta:
        --port   Se utiliza para especificar el puerto del servicio
               Ej: --port=9090 por defecto (8080)
        --folder Se utiliza para especificar la carpeta donde se van a guardar las imagenes
        		Ej: --folder=uploads
     
    También se puede ejecutar dando doble click al Jar; por defecto corre por el puerto 8080
    
Accede a la url: 
    http://localhost:8080/
    http://localhost:8080/reportes
    http://localhost:8080/acerca

    
    