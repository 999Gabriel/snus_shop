package com.example.snus_shopmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductInserter {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/snus_shop?useSSL=false&serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "macintosh";

    public static void insertProduct(String name, String description, double price, String imageUrl, int stockQuantity) throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO products (name, description, price, image_url, stock_quantity, created_at, updated_at) VALUES (?, ?, ?, ?, ?, current_timestamp(), current_timestamp())")) {

            stmt.setString(1, name);
            stmt.setString(2, description);
            stmt.setDouble(3, price);
            stmt.setString(4, imageUrl);
            stmt.setInt(5, stockQuantity);

            stmt.executeUpdate();
        }
    }
}