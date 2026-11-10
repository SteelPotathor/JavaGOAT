module com.example.javagoat {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires org.controlsfx.controls;
    requires com.calendarfx.view;
    requires AnimateFX;
    requires com.google.gson;


    opens com.example.javagoat to javafx.fxml;
    // Gson reads and writes the JSON records by reflection
    opens com.example.javagoat.Model.storage to com.google.gson;
    exports com.example.javagoat.Model;
    exports com.example.javagoat.Model.matching;
    exports com.example.javagoat;
    exports com.example.javagoat.Model.names;
}
