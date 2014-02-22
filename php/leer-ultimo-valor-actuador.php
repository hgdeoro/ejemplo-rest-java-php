<?php

require 'vendor/autoload.php';

use Guzzle\Http\Client;

$client = new Client('http://localhost:8080');

$request = $client->get('/tesis/actuador/32/valor');
// echo $request->getUrl();

// You must send a request in order for the transfer to occur
$response = $request->send();

echo $response->getBody() . "\n";

// $data = $response->json();
// echo $data['type'];

?>