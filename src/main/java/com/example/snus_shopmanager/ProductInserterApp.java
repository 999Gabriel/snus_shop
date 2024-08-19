import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductInserterApp extends Application {

    // Datenbankkonfiguration
    private static final String DB_URL = "jdbc:mysql://snus_shop-db-1:3306/snus_shop?useSSL=false&serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "macintosh";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Product Inserter");

        // Erstellen Sie die Formularelemente
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label nameLabel = new Label("Produktname:");
        GridPane.setConstraints(nameLabel, 0, 0);
        TextField nameInput = new TextField();
        GridPane.setConstraints(nameInput, 1, 0);

        Label descriptionLabel = new Label("Produktbeschreibung:");
        GridPane.setConstraints(descriptionLabel, 0, 1);
        TextField descriptionInput = new TextField();
        GridPane.setConstraints(descriptionInput, 1, 1);

        Label priceLabel = new Label("Produktpreis:");
        GridPane.setConstraints(priceLabel, 0, 2);
        TextField priceInput = new TextField();
        GridPane.setConstraints(priceInput, 1, 2);

        Label imageUrlLabel = new Label("Bild-URL:");
        GridPane.setConstraints(imageUrlLabel, 0, 3);
        TextField imageUrlInput = new TextField();
        GridPane.setConstraints(imageUrlInput, 1, 3);

        Label stockQuantityLabel = new Label("Lagerbestandsmenge:");
        GridPane.setConstraints(stockQuantityLabel, 0, 4);
        TextField stockQuantityInput = new TextField();
        GridPane.setConstraints(stockQuantityInput, 1, 4);

        Button submitButton = new Button("Produkt hinzufügen");
        GridPane.setConstraints(submitButton, 1, 5);

        Label messageLabel = new Label();
        GridPane.setConstraints(messageLabel, 1, 6);

        submitButton.setOnAction(e -> {
            String name = nameInput.getText();
            String description = descriptionInput.getText();
            double price;
            int stockQuantity;

            try {
                price = Double.parseDouble(priceInput.getText());
                stockQuantity = Integer.parseInt(stockQuantityInput.getText());
            } catch (NumberFormatException ex) {
                messageLabel.setText("Preis oder Lagerbestandsmenge ist ungültig.");
                return;
            }

            String imageUrl = imageUrlInput.getText();
            try {
                insertProduct(name, description, price, imageUrl, stockQuantity);
                messageLabel.setText("Produkt erfolgreich eingefügt!");
            } catch (SQLException ex) {
                messageLabel.setText("Fehler beim Einfügen des Produkts: " + ex.getMessage());
            }
        });

        grid.getChildren().addAll(nameLabel, nameInput, descriptionLabel, descriptionInput, priceLabel, priceInput,
                imageUrlLabel, imageUrlInput, stockQuantityLabel, stockQuantityInput, submitButton, messageLabel);

        Scene scene = new Scene(grid, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void insertProduct(String name, String description, double price, String imageUrl, int stockQuantity) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Verbindung zur Datenbank herstellen
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // SQL-Insert-Anweisung vorbereiten
            String sql = "INSERT INTO products (name, description, price, image_url, stock_quantity, created_at, " +
                    "updated_at) " +
                    "VALUES (?, ?, ?, ?, ?, current_timestamp(), current_timestamp())";
            stmt = conn.prepareStatement(sql);

            // Parameter setzen
            stmt.setString(1, name);
            stmt.setString(2, description);
            stmt.setDouble(3, price);
            stmt.setString(4, imageUrl);
            stmt.setInt(5, stockQuantity);

            // SQL-Anweisung ausführen
            stmt.executeUpdate();
        } finally {
            // Ressourcen schließen
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }
}