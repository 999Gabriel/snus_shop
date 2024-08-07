<?php
// Fehleranzeige aktivieren
ini_set('display_errors', 1);
ini_set('display_startup_errors', 1);
error_reporting(E_ALL);

// Datenbankverbindung herstellen
$servername = "localhost";
$username = "root"; // MySQL-Benutzername aus docker-compose.yml
$password = "macintosh"; // MySQL-Passwort aus docker-compose.yml
$dbname = "snus_shop";

$conn = new mysqli($servername, $username, $password, $dbname);

if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
?>