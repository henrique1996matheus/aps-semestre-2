package org.example.apssemestre2.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

public class UsoAparelhoController implements Initializable{

	@FXML
    private TextField TextFieldGastoHora;

    @FXML
    private Button BtnSalvar;

    @FXML
    private TextField TextFieldData;

    @FXML
    private TextField TextFieldAparelho;

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
		TextFieldData.setEditable(false);
		TextFieldGastoHora.setEditable(false);
		TextFieldAparelho.setEditable(false);
		
		Image salvar = new Image(getClass().getResource("/icons/salvar.png").toExternalForm());
		ImageView Salvar = new ImageView(salvar);
		Salvar.setFitHeight(13);
		Salvar.setFitWidth(13);
		BtnSalvar.setGraphic(Salvar);
		
		Image alt = new Image(getClass().getResource("/icons/setas-flechas.png").toExternalForm());
		ImageView alterar = new ImageView(alt);
		alterar.setFitWidth(16);
		alterar.setFitHeight(16);
		BtnAlterar.setGraphic(alterar);

	}




}

