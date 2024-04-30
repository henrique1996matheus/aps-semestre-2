package org.example.apssemestre2.controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;

public class ConsumoMesController implements Initializable {

    @FXML
    private DatePicker DatePcikerFim;

    @FXML
    private DatePicker DatePickerInicio;

    @FXML
    private AnchorPane DatePickerFiltro;

    @FXML
    private LineChart<?, ?> LineChartUsoMes;

    public void initialize(URL location, ResourceBundle resources) {
        configDatePicker(DatePcikerFim);
        configDatePicker(DatePickerInicio);
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