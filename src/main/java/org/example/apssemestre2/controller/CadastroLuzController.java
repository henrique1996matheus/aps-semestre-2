package org.example.apssemestre2.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.example.apssemestre2.model.ContaLuz;
import org.example.apssemestre2.repository.ContaLuzDao;

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
		void selecionarContaLuz(MouseEvent event) {
			if (event.getClickCount() == 1) {
				ContaLuz ContaSelecionada= (ContaLuz) TableViewContaLuz.getSelectionModel().getSelectedItem();
				if (ContaSelecionada != null) {
					TextFieldBandeira.setText(ContaSelecionada.getBandeira());
					TextFieldReferencia.setText(ContaSelecionada.getReferencia());
					TextFieldVencimento.setText(ContaSelecionada.getVencimento());
					TextFieldConsumo.setText(ContaSelecionada.getConsumo());
					TextFieldValor.setText(ContaSelecionada.getValor());
				}
			}
		}
	    @FXML
	    void alterarCadastro(ActionEvent event) {

	    }

	    @FXML
	    void salvarCadastro(ActionEvent event) {

			String bandeira = TextFieldBandeira.getText();
			String referencia = TextFieldReferencia.getText();
			String vencimento = TextFieldVencimento.getText();
			String consumo = TextFieldConsumo.getText();
			String valor = TextFieldValor.getText();

			ContaLuz novaConta = new ContaLuz(bandeira,referencia,vencimento,consumo,valor);
			contaluz.add(novaConta);
			new ContaLuzDao().cadastrarConta(novaConta);
	    }

	    @FXML
	    void excluirCadastroLuz(ActionEvent event) {
	    }

	private ObservableList<ContaLuz> contaluz = FXCollections.observableArrayList();


	@Override
	public void initialize(URL url,ResourceBundle rb) {
		TextFieldBandeira.setEditable(false);
		TextFieldReferencia.setEditable(false);
		TextFieldVencimento.setEditable(false);
		TextFieldConsumo.setEditable(false);
		TextFieldValor.setEditable(false);

		TableViewContaLuz.setOnMouseClicked(event -> {
			if (event.getClickCount() == 1) {
				selecionarContaLuz(event);
			}
		});

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
