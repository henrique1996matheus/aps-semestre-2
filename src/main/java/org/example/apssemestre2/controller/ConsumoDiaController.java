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

		// Lógica para atualizar o gráfico com base nas datas selecionadas

		GraficoDados dados = graficoService.dias(LocalDate.now(), LocalDate.now());

		XYChart.Series<String, Number> serie1 = new XYChart.Series<>();

		// Iterando sobre os dados e adicionando ao gráfico
		String[] dias = dados.getX();
		String[] consumo = dados.getY();
		for (int i = 0; i < dias.length; i++) {
			serie1.getData().add(new XYChart.Data<>(dias[i], Integer.parseInt(consumo[i])));
		}

		// Limpando os dados antigos e adicionando a nova série
		LineChartUsoDia.getData().clear();
		LineChartUsoDia.getData().add(serie1);



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
