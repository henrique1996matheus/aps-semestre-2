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
import org.example.apssemestre2.model.Categoria;
import org.example.apssemestre2.model.GraficoDados;
import org.example.apssemestre2.service.GraficoService;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

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


            updateChart(CheckComboBoxCategorias.getCheckModel().getCheckedItems());
        });
    }

    private void updateChart(ObservableList<String> selectedCategories) {
        // Limpar os dados antigos
        LineChartAnalise.getData().clear();

        if (selectedCategories.isEmpty()) {
            return;
        }

        // Obter os dados das categorias selecionadas
        List<Categoria> categoriasSelecionadas = selectedCategories.stream()
                .map(nome -> new Categoria(nome))
                .collect(Collectors.toList());

        GraficoDados dados = graficoService.categorias(categoriasSelecionadas);

        // Obter as categorias e seus consumos
        String[] categorias = dados.getX();
        String[][] consumos = dados.getConsumosCategorias();

        // Iterar sobre as categorias selecionadas
        for (int i = 0; i < categorias.length; i++) {
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName(categorias[i]);

            // Adicionar pontos de dados para cada categoria
            for (int j = 0; j < consumos[i].length; j++) {
                series.getData().add(new XYChart.Data<>(String.valueOf(j + 1), Integer.parseInt(consumos[i][j])));
            }

            // Adicionar a nova série ao gráfico
            LineChartAnalise.getData().add(series);
        }
    }
}