package org.example.apssemestre2.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
public class CadastroLuzController implements Initializable{

	 @FXML
	    private TextField TextFieldBandeira;

	    @FXML
	    private TextField TextFieldReferencia;

	    @FXML
	    private Button BtnSalvar;

	    @FXML
	    private ChoiceBox<?> ChoiceBoxAno;

	    @FXML
	    private Button BtnNovo;

	    @FXML
	    private TextField TextFieldVencimento;

	    @FXML
	    private TextField TextFieldConsumo;

	    @FXML
	    private Button BtnExcluir;

	    @FXML
	    private TableView<?> TableViewContaLuz;

	    @FXML
	    private TextField TextFieldValor;

	    @FXML
	    private Button BtnAlterar;

	    @FXML
	    void novoCadastro(ActionEvent event) {
	    	TextFieldBandeira.setEditable(true);
			TextFieldBandeira.setText("");
			TextFieldReferencia.setEditable(true);
			TextFieldReferencia.setText("");
			TextFieldVencimento.setEditable(true);
			TextFieldVencimento.setText("");
			TextFieldConsumo.setEditable(true);
			TextFieldConsumo.setText("");
			TextFieldValor.setEditable(true);
			TextFieldValor.setText("");
	    }

	    @FXML
	    void alterarCadastro(ActionEvent event) {

	    }

	    @FXML
	    void salvarCadastro(ActionEvent event) {

	    }

	    @FXML
	    void excluirCadastroLuz(ActionEvent event) {

	    }

	@Override
	public void initialize(URL url,ResourceBundle rb) {
		TextFieldBandeira.setEditable(false);
		TextFieldReferencia.setEditable(false);
		TextFieldVencimento.setEditable(false);
		TextFieldConsumo.setEditable(false);
		TextFieldValor.setEditable(false);
		
		Image salvar = new Image(getClass().getResource("/org/example/apssemestre2/icons/salvar.png").toExternalForm());
		ImageView Salvar = new ImageView(salvar);
		BtnSalvar.setGraphic(Salvar);
		
		Image excluir = new Image(getClass().getResource("/org/example/apssemestre2/icons/excluir.png").toExternalForm());
		ImageView excl = new ImageView(excluir);
		BtnExcluir.setGraphic(excl);
		
		Image nov = new Image(getClass().getResource("/org/example/apssemestre2/icons/novoarq.png").toExternalForm());
		ImageView novo = new ImageView(nov);
		novo.setFitWidth(14);
		novo.setFitHeight(14);
		BtnNovo.setGraphic(novo);
		
		Image alt = new Image(getClass().getResource("/org/example/apssemestre2/icons/setas-flechas.png").toExternalForm());
		ImageView alterar = new ImageView(alt);
		alterar.setFitWidth(14);
		alterar.setFitHeight(14);
		BtnAlterar.setGraphic(alterar);
		
	}
}
