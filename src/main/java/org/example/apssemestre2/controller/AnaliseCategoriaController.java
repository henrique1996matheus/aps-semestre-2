package org.example.apssemestre2.controller;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.controlsfx.control.CheckComboBox;
import org.example.apssemestre2.model.Categoria;
import org.example.apssemestre2.model.GraficoDados;
import org.example.apssemestre2.service.CategoriaService;
import org.example.apssemestre2.service.GraficoService;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AnaliseCategoriaController implements Initializable {

    @FXML
    private LineChart<String, Number> LineChartAnalise;

    @FXML
    private TextField TextFieldAno;

    @FXML
    private AnchorPane AnchorPaneCentral;

    private final CategoriaService categoriaService;
    private final GraficoService graficoService;

    public AnaliseCategoriaController() {
        categoriaService = new CategoriaService();
        graficoService = new GraficoService();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Categoria> categorias = categoriaService.listar(new Categoria());

        ObservableList<Categoria> items = FXCollections.observableArrayList();
        items.addAll(categorias);

        CheckComboBox<Categoria> CheckComboBoxCategorias = new CheckComboBox<>(items);
        VBox Categorias = new VBox();
        AnchorPaneCentral.getChildren().add(Categorias);
        Categorias.getChildren().add(CheckComboBoxCategorias);
        Categorias.setLayoutX(490);
        Categorias.setLayoutY(110);
        CheckComboBoxCategorias.setPrefWidth(100);
        CheckComboBoxCategorias.setPrefHeight(25);

        CheckComboBoxCategorias.getCheckModel().getCheckedItems().addListener((ListChangeListener<Categoria>) change -> {
            updateChart(CheckComboBoxCategorias.getCheckModel().getCheckedItems());
        });
    }

    private void updateChart(ObservableList<Categoria> selectedCategories) {
        // Limpar os dados antigos
        LineChartAnalise.getData().clear();

        if (selectedCategories.isEmpty()) {
            return;
        }

        // Obter os dados das categorias selecionadas
        List<Categoria> categoriasSelecionadas = new ArrayList<>(selectedCategories);

        // todo alterar o anoFiltro fixo
        GraficoDados dados = graficoService.categorias(categoriasSelecionadas, 2024);

        // Obter as categorias e seus consumos
        String[] categorias = dados.getX();
        String[][] consumos = dados.getConsumosCategorias();

        // Iterar sobre as categorias selecionadas
        for (int i = 0; i < categorias.length; i++) {
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName(categorias[i]);

            // Adicionar pontos de dados para cada categoria
            for (int j = 0; j < consumos[i].length; j++) {
                series.getData().add(new XYChart.Data<>(String.valueOf(j + 1), Float.parseFloat(consumos[i][j])));
            }

            // Adicionar a nova série ao gráfico
            LineChartAnalise.getData().add(series);
        }
    }
}