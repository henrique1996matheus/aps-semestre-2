package org.example.apssemestre2.controller;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.controlsfx.control.CheckComboBox;
import org.example.apssemestre2.model.GraficoDados;
import org.example.apssemestre2.service.GraficoService;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AnaliseCategoriaController implements Initializable {

    @FXML
    private LineChart<String, Number> LineChartAnalise;

    @FXML
    private AnchorPane AnchorPaneCentral;

    private final GraficoService graficoService;

    public AnaliseCategoriaController() {
        graficoService = new GraficoService();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> items = FXCollections.observableArrayList("Sala", "Cozinha", "Quarto 1", "Escritorio", "Quarto 2");
        CheckComboBox<String> CheckComboBoxCategorias = new CheckComboBox<>(items);
        VBox Categorias = new VBox();
        AnchorPaneCentral.getChildren().add(Categorias);
        Categorias.getChildren().add(CheckComboBoxCategorias);
        Categorias.setLayoutX(490);
        Categorias.setLayoutY(110);
        CheckComboBoxCategorias.setPrefWidth(100);
        CheckComboBoxCategorias.setPrefHeight(25);

        CheckComboBoxCategorias.getCheckModel().getCheckedItems().addListener((ListChangeListener<String>) change -> {
            // Atualizando o gráfico toda vez que um item é selecionado ou desselecionado
            updateChart(CheckComboBoxCategorias.getCheckModel().getCheckedItems());
        });
    }

    private void updateChart(ObservableList<String> selectedCategories) {
        // Limpar os dados antigos
        LineChartAnalise.getData().clear();

        // Adicionar uma série para cada categoria selecionada
        for (String categoria : selectedCategories) {
            GraficoDados dados = graficoService.inicial(LocalDate.now());
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName(categoria);

            // Iterando sobre os dados e adicionando ao gráfico
            String[] dias = dados.getX();
            String[] consumo = dados.getY();
            for (int i = 0; i < dias.length; i++) {
                series.getData().add(new XYChart.Data<>(dias[i], Integer.parseInt(consumo[i])));
            }

            // Adicionar a nova série ao gráfico
            LineChartAnalise.getData().add(series);
        }
    }
}