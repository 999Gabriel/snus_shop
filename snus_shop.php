<?php
//include 'db.php'; // Stellen Sie sicher, dass der Pfad korrekt ist

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
$sql = "SELECT id, name, description, price, image FROM products";
$result = $conn->query($sql);
?>

<!DOCTYPE html>
<html lang="de">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Nikotinbeutel Lieferdienst</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<header>
    <nav>
        <img src="images/logo.png" alt="Logo" class="logo">
        <ul>
            <li><a href="#home">Home</a></li>
            <li><a href="#products">Produkte</a></li>
            <li><a href="#about">Über uns</a></li>
            <li><a href="#contact">Kontakt</a></li>
            <li><a href="#cart">Warenkorb</a></li>
        </ul>
    </nav>
</header>

<main id="home">
    <section class="hero">
        <h1>Nikotinbeutel direkt zu dir nach Hause</h1>
        <p>Schnell, einfach und zuverlässig</p>
        <a href="#products" class="cta-button">Jetzt bestellen</a>
    </section>

    <section id="products" class="products">
        <h2>Unsere Produkte</h2>
        <div class="product-list">
            <?php
            if ($result->num_rows > 0) {
                while($row = $result->fetch_assoc()) {
                    echo "<div class='product-item'>";
                    echo "<img src='images/" . $row["image"] . "' alt='" . $row["name"] . "'>";
                    echo "<h3>" . $row["name"] . "</h3>";
                    echo "<p>" . $row["description"] . "</p>";
                    echo "<p>€" . $row["price"] . "</p>";
                    echo "<button onclick='addToCart(" . $row["id"] . ")'>In den Warenkorb</button>";
                    echo "</div>";
                }
            } else {
                echo "Keine Produkte gefunden";
            }
            $conn->close();
            ?>
        </div>
    </section>

    <section id="about" class="about">
        <h2>Über uns</h2>
        <p>Wir sind ein engagiertes Team, das sich darauf spezialisiert hat, hochwertige Nikotinbeutel schnell und zuverlässig zu liefern.</p>
    </section>

    <section id="contact" class="contact">
        <h2>Kontakt</h2>
        <form>
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" required>
            <label for="email">E-Mail:</label>
            <input type="email" id="email" name="email" required>
            <label for="message">Nachricht:</label>
            <textarea id="message" name="message" required></textarea>
            <button type="submit">Senden</button>
        </form>
    </section>
</main>

<footer>
    <p>&copy; 2024 Nikotinbeutel Lieferdienst. Alle Rechte vorbehalten.</p>
</footer>

<script src="/Users/gabriel/Developer/business/snus_shop/src/scripts.js"></script>
</body>
</html>