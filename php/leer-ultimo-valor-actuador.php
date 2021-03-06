<?php

require 'vendor/autoload.php';

use Guzzle\Http\Client;

$client = new Client('http://localhost:8080');

$request = $client->get('/tesis/actuador/32/valor');
// echo $request->getUrl();

// You must send a request in order for the transfer to occur
$response = $request->send();

echo "Respuesta del servidor: " . $response->getBody() . "\n";
$obj = $response->json();
echo " - Actuador: " . $obj['idActuador'] . "\n";
echo " - Valor: " . $obj['valor'] . "\n";

?>
