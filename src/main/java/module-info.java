module com.example.javagoat {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.javagoat to javafx.fxml;
    exports com.example.javagoat.back;
    exports com.example.javagoat.GUI;
    opens com.example.javagoat.GUI to javafx.fxml;
}