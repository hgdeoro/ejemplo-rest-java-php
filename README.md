# Ejemplo Java-PHP usando REST

## Recursos

+ http://www.vogella.com/tutorials/REST/article.html
+ https://jersey.java.net/documentation/latest/getting-started.html

## URLs para acceder con el navegador

#### Para leer valor: GET

+ http://localhost:8080/tesis/sensor/32/leer

#### Para escribir valor: POST

wget --no-proxy --post-data '' -S -q -O - 'http://localhost:8080/tesis/sensor/32/escribir?value=77' ; echo ""


