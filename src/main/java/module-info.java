module com.example.snus_shopmanager {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.snus_shopmanager to javafx.fxml;
    exports com.example.snus_shopmanager;
}