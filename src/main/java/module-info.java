module com.example.enigmagui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.enigmagui to javafx.fxml;
    exports com.example.enigmagui;
}