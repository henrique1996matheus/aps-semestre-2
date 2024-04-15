package org.example.apssemestre2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AparelhoApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AparelhoApplication.class.getResource("aparelho-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Aparelhos!");
        stage.setScene(scene);
        stage.show();

        System.out.println("TelaInicial");
    }

    public static void main(String[] args) {
        launch();
    }
}