package org.example.apssemestre2.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.controlsfx.control.CheckComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class AnaliseCategoriaController implements Initializable {


    @FXML
    private LineChart<?, ?> LineChartAnalise;

    @FXML
    private ComboBox<?> ComboBoxCategorias;

    @FXML
    private Button BtnFiltro;

    @FXML
    private AnchorPane AnchorPaneCentral;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        ObservableList<String> items = FXCollections.observableArrayList("Sala", "Cozinha", "Quarto 1","Escritorio","Quarto 2");
        CheckComboBox<String> CheckComboBoxCategorias = new CheckComboBox<>(items);
        VBox Categorias = new VBox();
        AnchorPaneCentral.getChildren().add(Categorias);
        Categorias.getChildren().add(CheckComboBoxCategorias);
        Categorias.setLayoutX(490);
        Categorias.setLayoutY(110);
        CheckComboBoxCategorias.setPrefWidth(100);
        CheckComboBoxCategorias.setPrefHeight(25);


        Image Filtrar = new Image(getClass().getResource("/org/example/apssemestre2/icons/filtro.png").toExternalForm());
        ImageView Filtrado = new ImageView(Filtrar);
        BtnFiltro.setGraphic(Filtrado);
    }
}
