module org.example.apssemestre2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    exports org.example.apssemestre2;
    opens org.example.apssemestre2 to javafx.fxml;
}