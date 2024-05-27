package org.example.apssemestre2.controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;
import org.example.apssemestre2.model.GraficoDados;
import org.example.apssemestre2.service.GraficoService;

public class ConsumoDiaController implements Initializable  {

	 @FXML
	 private DatePicker DatePcikerFim;

	 @FXML
	 private DatePicker DatePickerInicio;

	 @FXML
	 private AnchorPane DatePickerFiltro;

	 @FXML
	 private LineChart<String, Number> LineChartUsoDia;

	private final GraficoService graficoService;

    public ConsumoDiaController() {
		graficoService = new GraficoService();
    }


    public void initialize(URL location, ResourceBundle resources) {

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
		GraficoDados dados = graficoService.dias(startDate, endDate);

		// Limpar todas as séries existentes
		LineChartUsoDia.getData().clear();

		// Criar uma nova série
		XYChart.Series<String, Number> series = new XYChart.Series<>();


		LineChartUsoDia.setLegendVisible(false);

		// Iterar sobre os dados e adicionar ao gráfico
		String[] dias = dados.getX();
		String[] consumo = dados.getY();
		for (int i = 0; i < dias.length; i++) {
			series.getData().add(new XYChart.Data<>(dias[i], Integer.parseInt(consumo[i])));
		}

		// Adicionar a nova série ao gráfico
		LineChartUsoDia.getData().add(series);
	}


		private class DayMonthStringConverter extends StringConverter<LocalDate> {
	        private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM");

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

	                return LocalDate.parse(string + "/2000", dateFormatter);
	            } else {
	                return null;
	            }
	        }
	 }

}
