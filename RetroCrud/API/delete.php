<?php
header('Content-Type: application/json; charset=UTF-8');
require 'connection.php';

$id = $_POST['id'] ?? null;

if (empty($id)) {
    http_response_code(400);
    echo json_encode([
        'success' => false,
        'message' => 'ID is required.'
    ]);
    exit;
}

$sql = "DELETE FROM retrocrud WHERE id = $1";
$result = pg_query_params($conn, $sql, [(int)$id]);

if ($result && pg_affected_rows($result) > 0) {
    echo json_encode([
        'success' => true,
        'message' => 'Record Deleted Successfully.'
    ]);
} else {
    http_response_code(500);
    echo json_encode([
        'success' => false,
        'message' => 'Delete failed or ID not found.',
        'error' => pg_last_error($conn)
    ]);
}
?>
