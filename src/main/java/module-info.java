module org.example.apssemestre {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    exports org.example.apssemestre2.controller;
    opens org.example.apssemestre2.controller to javafx.fxml;
    exports org.example.apssemestre2.service;
    opens org.example.apssemestre2.service to javafx.fxml;
    exports org.example.apssemestre2;
    opens org.example.apssemestre2 to javafx.fxml;
}