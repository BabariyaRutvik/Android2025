<?php
header('Content-Type: application/json; charset=UTF-8');
require 'connection.php';

// Query
$sql = "SELECT id, name, adress, email, password FROM retrocrud ORDER BY id DESC";
$result = pg_query($conn, $sql);

// Error check
if (!$result) {
    http_response_code(500);
    echo json_encode([
        'success' => false,
        'message' => 'Query failed',
        'error' => pg_last_error($conn)
    ]);
    exit;
}

$rows = [];
while ($r = pg_fetch_assoc($result)) {
    // Return values matching your Android Model keys
    $rows[] = [
        "id"       => (int)$r['id'],
        "Name"     => $r['name'],
        "Adress"   => $r['adress'],
        "Email"    => $r['email'],
        "Password" => $r['password']
    ];
}

echo json_encode($rows);
?>
