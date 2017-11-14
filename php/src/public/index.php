<?php
use \Psr\Http\Message\ServerRequestInterface as Request;
use \Psr\Http\Message\ResponseInterface as Response;

require '../vendor/autoload.php';

$config['displayErrorDetails'] = true;
$config['addContentLengthHeader'] = false;

$config['db']['host'] = "localhost";
$config['db']['user'] = "dm107";
$config['db']['pass'] = "dm107";
$config['db']['dbname'] = "trabalhoDM107";

$app = new \Slim\App(["config" => $config]);
$container = $app->getContainer();
//$username = $_SERVER["PHP_AUTH_USER"];
//$password = $_SERVER["PHP_AUTH_PW"];

$container['db'] = function ($c) {
    $dbConfig = $c['config']['db'];
    $pdo = new PDO("mysql:host=" . $dbConfig['host'] . ";dbname=" . $dbConfig['dbname'], $dbConfig['user'], $dbConfig['pass']);
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    $pdo->setAttribute(PDO::ATTR_DEFAULT_FETCH_MODE, PDO::FETCH_ASSOC);
    $db = new NotORM($pdo); return $db;
};

$usuarios = $container['db']->usuario();
$usersAuth = array();

foreach ($usuarios as $usuario) {
    $usersAuth[$usuario["username"]] = $usuario["password"];
}

$app->add(new Tuupola\Middleware\HttpBasicAuthentication(
    [ "users" => $usersAuth])
);

$app->get("/api/entregas", function (Request $request, Response $response) {
    $response->getBody()->write("Bem vindo a API de entregas com Basic Auth.");
    return $response;
});

$app->put('/api/entregas/{id}', function (Request $request, Response $response) {
    $id = $request->getAttribute('id');
    $json = $request->getParsedBody();

    $entrega = $this->db->entrega()->where('id', $id);
    if($entrega->fetch()){
        $result =  $entrega->update($json);
        echo json_encode(array(
            'status' => (bool)$result,
            'message' => 'Entrega '.$id.' atualizada com sucesso'
        ));
    }else{
        echo json_encode(array(
            'status' => false,
            'message' => 'Entrega '.$id.' nao encontrada'
        ));
    }
});

$app->delete('/api/entregas/{id}', function (Request $request, Response $response) {
    $id = $request->getAttribute('id');
    $entrega = $this->db->entrega()->where('id',$id);
    if($entrega->fetch()){
        $result =  $entrega->delete();
        echo json_encode(array(
            'status' => (bool)$result,
            'message' => "Entrega $id removida com sucesso"
        ));
    }else{
        echo json_encode(array(
            'status' => false,
            'message' => "Entrega $id nao encontrada"
        ));
    }
});

$app->run();

?>