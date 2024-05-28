package org.example.apssemestre2.controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javafx.util.StringConverter;
import org.example.apssemestre2.model.GraficoDados;
import org.example.apssemestre2.service.GraficoService;

public class ConsumoMesController implements Initializable {

    @FXML
    private DatePicker DatePcikerFim;

    @FXML
    private DatePicker DatePickerInicio;

    @FXML
    private AnchorPane DatePickerFiltro;

    @FXML
    private LineChart<String, Number> LineChartUsoMes;

    private final GraficoService graficoService;

    public ConsumoMesController() {
        graficoService = new GraficoService();
    }

    private void FiltragemMes(DatePicker datePicker) {
        // Define o formato de exibição para mês/ano
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");

        // Converte a data para exibir apenas mês/ano
        datePicker.setConverter(new StringConverter<>() {
            @Override
            public String toString(LocalDate date) {
                return (date != null) ? formatter.format(date) : "";
            }

            @Override
            public LocalDate fromString(String string) {
                return (string != null && !string.isEmpty())
                        ? LocalDate.parse(string + "-01", DateTimeFormatter.ofPattern("MM/yyyy-dd"))
                        : null;
            }
        });

        // Define a célula personalizada para exibir apenas mês/ano
        datePicker.setDayCellFactory(new Callback<>() {
            @Override
            public DateCell call(DatePicker param) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item.format(formatter));
                        }
                    }
                };
            }
        });
    }

    public void initialize(URL location, ResourceBundle resources) {

        FiltragemMes(DatePickerInicio);
        FiltragemMes(DatePcikerFim);
        configDatePicker(DatePcikerFim);
        configDatePicker(DatePickerInicio);

        DatePickerInicio.valueProperty().addListener((observable, oldValue, newValue) -> {

            updateChart();
        });

        DatePcikerFim.valueProperty().addListener((observable, oldValue, newValue) -> {

            updateChart();
        });
    }

    private void updateChart() {

        LocalDate startDate = DatePickerInicio.getValue();
        LocalDate endDate = DatePcikerFim.getValue();

        // Verificar se endDate é nulo
        if (endDate == null) {
            // Se endDate for nulo, não fazer nada e retornar
            return;
        }

        // Lógica para atualizar o gráfico com base nas datas selecionadas
        GraficoDados dados = graficoService.mes(startDate, endDate);

        // Limpar todas as séries existentes
        LineChartUsoMes.getData().clear();

        // Criar uma nova série
        XYChart.Series<String, Number> series = new XYChart.Series<>();


        LineChartUsoMes.setLegendVisible(false);

        // Iterar sobre os dados e adicionar ao gráfico
        String[] dias = dados.getX();
        String[] consumo = dados.getY();
        for (int i = 0; i < dias.length; i++) {
            series.getData().add(new XYChart.Data<>(dias[i], Float.parseFloat(consumo[i])));
        }

        // Adicionar a nova série ao gráfico
        LineChartUsoMes.getData().add(series);
    }
    private void configDatePicker(DatePicker datePicker) {

        datePicker.setConverter(new YearMonthStringConverter());
        

        datePicker.getEditor().setOnMouseClicked(event -> datePicker.getEditor().clear());
    }




    private class YearMonthStringConverter extends StringConverter<LocalDate> {
        private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/yyyy");

        @Override
        public String toString(LocalDate date) {
            if (date != null) {
                return dateFormatter.format(date);
            } else {
                return "";
            }
        }

        @Override
        public LocalDate fromString(String string) {
            if (string != null && !string.isEmpty()) {
                return YearMonth.parse(string, dateFormatter).atDay(1);
            } else {
                return null;
            }
        }
    }
}