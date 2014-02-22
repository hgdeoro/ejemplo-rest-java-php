<?php

require 'vendor/autoload.php';

use Guzzle\Http\Client;

$client = new Client('http://localhost:8080');

$request = $client->post('/tesis/actuador/32/valor?value=77');
// echo $request->getUrl();

// You must send a request in order for the transfer to occur
$response = $request->send();

echo $response->getBody();

// $data = $response->json();
// echo $data['type'];

?>
