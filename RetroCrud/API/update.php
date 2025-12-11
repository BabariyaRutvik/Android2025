<?php
header('Content-Type: application/json; charset=UTF-8');
require 'connection.php';

// Read POST values sent by Android
$id      = $_POST['id']      ?? null;
$name    = $_POST['Name']    ?? null;
$adress  = $_POST['Adress']  ?? null;
$email   = $_POST['Email']   ?? null;
$password = $_POST['Password'] ?? null;

// Check required fields
if (empty($id) || empty($name) || empty($adress) || empty($email) || empty($password)) {
    http_response_code(400);
    echo json_encode([
        'success' => false,
        'message' => 'Please fill all fields.'
    ]);
    exit;
}

// Update query
$sql = "UPDATE retrocrud 
        SET name = $1, adress = $2, email = $3, password = $4 
        WHERE id = $5";

$result = pg_query_params($conn, $sql, [$name, $adress, $email, $password, (int)$id]);

if ($result && pg_affected_rows($result) > 0) {
    echo json_encode([
        'success' => true,
        'message' => 'Record Updated Successfully.'
    ]);
} else {
    http_response_code(500);
    echo json_encode([
        'success' => false,
        'message' => 'Update failed or ID not found.',
        'error' => pg_last_error($conn)
    ]);
}
?>
