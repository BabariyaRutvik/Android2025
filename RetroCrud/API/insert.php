<?php
header('Content-Type: application/json; charset=UTF-8');
ini_set('display_errors', 1);
error_reporting(E_ALL);

// Include DB connection
require 'connection.php';

// Read POST values (fields EXACTLY as Android sends)
$name   = $_POST['Name']   ?? null;
$adress = $_POST['Adress'] ?? null;
$email  = $_POST['Email']  ?? null;
$password = $_POST['Password'] ?? null;

// Check required fields
if (empty($name) || empty($adress) || empty($email) || empty($password)) {
    http_response_code(400);
    echo json_encode([
        'success' => false,
        'message' => 'Please fill all fields.'
    ]);
    exit;
}

// Insert query (PostgreSQL safe query)
$sql = "INSERT INTO retrocrud (name, adress, email, password)
        VALUES ($1, $2, $3, $4) RETURNING id";

$result = pg_query_params($conn, $sql, [$name, $adress, $email, $password]);

if ($result) {
    $row = pg_fetch_assoc($result);
    echo json_encode([
        'success' => true,
        'message' => 'Data Inserted Successfully.',
        'id' => (int)$row['id']
    ]);
} else {
    http_response_code(500);
    echo json_encode([
        'success' => false,
        'message' => 'Insertion Failed',
        'error' => pg_last_error($conn)
    ]);
}
?>
