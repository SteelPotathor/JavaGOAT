module com.example.javagoat {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires org.controlsfx.controls;
    requires com.calendarfx.view;
    requires AnimateFX;


    opens com.example.javagoat to javafx.fxml;
    exports com.example.javagoat.back;
    exports com.example.javagoat;
    exports com.example.javagoat.back.names;
}