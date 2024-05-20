module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires itextpdf;


    requires org.apache.poi.ooxml;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;


    opens com.example.demo to javafx.fxml;
    exports com.example.demo;

}