package org.example.apssemestre2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.apssemestre2.service.MigracaoDBService;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            MigracaoDBService.getInstancia().migrar();

            primaryStage.setTitle("Controle de energia");
            //FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("tela-inicial-view.fxml"));
            AnchorPane root = FXMLLoader.load(getClass().getResource("/org/example/apssemestre2/view/JanelaPrincipal.fxml"));
            Image applicationIcon = new Image(getClass().getResourceAsStream("/org/example/apssemestre2/icons/light-bulb_1778126.png"));
            primaryStage.getIcons().add(applicationIcon);
            Scene scene = new Scene(root, 750, 550);
            //scene.getStylesheets().add(getClass().getResource("/styles/application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}