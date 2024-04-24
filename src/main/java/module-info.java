module org.example.apssemestre {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    exports org.example.apssemestre2.view;
    opens org.example.apssemestre2.view to javafx.fxml;
}