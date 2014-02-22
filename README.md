# Ejemplo Java-PHP usando REST

## Maven

Para facilitar el tema de las dependencias, uso Maven. Para que Maven instale todas las dependencias, hay que ejecutar:

    mvn install

Si se agregan dependencias, para que Maven agregue las librerias en Eclipse, hay que ejecutar:

    mvn eclipse:eclipse

Y para ejecutar los tests:

    mvn test

## Recursos

+ http://www.vogella.com/tutorials/REST/article.html
+ https://jersey.java.net/documentation/latest/getting-started.html

## Para ejecutar scripts PHP

    $ php -f leer-sensor.php 
    Respuesta del servidor: {"idSensor":32,"valor":12}
     - Sensor: 32
     - Valor: 12
    
    $ php -f escribir-actuador.php 
    Respuesta del servidor: {"idActuador":"32","valor":"44"}
     - Actuador: 32
     - Valor escrito: 44
    
    $ php -f leer-ultimo-valor-actuador.php 
    Respuesta del servidor: {"idActuador":"32","valor":"44"}
     - Actuador: 32
     - Valor: 44

