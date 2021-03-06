<?php

require 'vendor/autoload.php';

use Guzzle\Http\Client;

$client = new Client('http://localhost:8080');

$request = $client->post('/tesis/actuador/32/valor');
$request->setBody(json_encode(array('valor' => '44')), 'application/json');

// You must send a request in order for the transfer to occur
$response = $request->send();

echo "Respuesta del servidor: " . $response->getBody() . "\n";
$obj = $response->json();
echo " - Actuador: " . $obj['idActuador'] . "\n";
echo " - Valor escrito: " . $obj['valor'] . "\n";

?>
