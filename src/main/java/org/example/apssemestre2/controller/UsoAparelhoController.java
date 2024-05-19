package org.example.apssemestre2.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;



public class UsoAparelhoController implements Initializable{


	@FXML
	private TextField TextFieldGastoHora;

	@FXML
	private ChoiceBox<?> ChoiceBoxAparelho;

	@FXML
	private Button BtnSalvar;

	@FXML
	private ChoiceBox<?> ChoiceBoxData;

	@FXML
	private DatePicker DatePickerData;

	@FXML
	private Button BtnAlterar;

	@FXML
	void salvarUso(ActionEvent event) {

	}

	@FXML
	void alterarUso(ActionEvent event) {

	}


	@Override
	public void initialize(URL url,ResourceBundle rb) {


		Image Alterar = new Image(getClass().getResource("/org/example/apssemestre2/icons/setas-flechas.png").toExternalForm());
		ImageView alterado = new ImageView(Alterar);
		alterado.setFitWidth(16);
		alterado.setFitHeight(16);
		BtnAlterar.setGraphic(alterado);


		Image Salvar = new Image(getClass().getResource("/org/example/apssemestre2/icons/salvar.png").toExternalForm());
		ImageView salvo = new ImageView(Salvar);
		BtnSalvar.setGraphic(salvo);

	}




}

