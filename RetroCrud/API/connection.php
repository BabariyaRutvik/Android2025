<?php
header('Content-Type: application/json; charset=UTF-8');
ini_set('display_errors', 1);
error_reporting(E_ALL);

$host = "localhost";
$port = "5432";
$dbname = "Android";
$user = "postgres";
$password = "Rutvik@1013";

$conn = pg_connect("host=$host port=$port dbname=$dbname user=$user password=$password");

if (!$conn) {
    http_response_code(500);
    echo json_encode(['success'=>false, 'message'=>'Database Connection Error.']);
    exit;
}
?>
