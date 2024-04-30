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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;

public class ConsumoDiaController implements Initializable  {	
	
	 @FXML
	 private DatePicker DatePcikerFim;

	 @FXML
	 private DatePicker DatePickerInicio;

	 @FXML
	 private AnchorPane DatePickerFiltro;

	 @FXML
	 private LineChart<?, ?> LineChartUsoDia;
	 
	    
	 
	 public void initialize(URL location, ResourceBundle resources) {
		 
		 configDatePicker(DatePcikerFim);
	        configDatePicker(DatePickerInicio);
	    }

	    private void configDatePicker(DatePicker datePicker) {
	        datePicker.setConverter(new DayMonthStringConverter());
	        
	        
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
