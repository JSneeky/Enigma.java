module com.example.enigmagui {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jetbrains.annotations;


    opens com.example.enigmagui to javafx.fxml;
    exports com.example.enigmagui;
}