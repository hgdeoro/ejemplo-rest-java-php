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

## URLs para acceder con el navegador

#### Para leer valor: GET

+ http://localhost:8080/tesis/sensor/32/valor

#### Para escribir valor: POST

    wget -q --no-proxy --post-data '' -S -O - 'http://localhost:8080/tesis/actuador/32/valor?value=77' ; echo ""

