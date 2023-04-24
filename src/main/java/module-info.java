module com.example.javagoat {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires org.controlsfx.controls;


    opens com.example.javagoat to javafx.fxml;
    exports com.example.javagoat.back;
    exports com.example.javagoat;
    exports com.example.javagoat.back.names;

}