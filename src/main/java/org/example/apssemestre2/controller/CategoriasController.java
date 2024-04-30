package org.example.apssemestre2.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
public class CategoriasController implements Initializable{

	@FXML
    private Button BtnSalvar;

    @FXML
    private Button BtnNovo;

    @FXML
    private Button BtnExcluir;

    @FXML
    private TableView<?> TableViewCategorias;

    @FXML
    private TextField TextFieldNome;

    @FXML
    private Button BtnAlterar;

    @FXML
    void adicionarCategoria(ActionEvent event) {
		TextFieldNome.setEditable(true);
		TextFieldNome.setText("");
    }

    @FXML
    void alterarCategoria(ActionEvent event) {

    }

    @FXML
    void salvarCategoria(ActionEvent event) {

    }

    @FXML
    void excluirCategoria(ActionEvent event) {

    }

	@Override
	public void initialize(URL url,ResourceBundle rb) {
		TextFieldNome.setEditable(false);
		
		Image salvar = new Image(getClass().getResource("/icons/salvar.png").toExternalForm());
		ImageView Salvar = new ImageView(salvar);
		Salvar.setFitHeight(13);
		Salvar.setFitWidth(13);
		BtnSalvar.setGraphic(Salvar);
		
		Image excluir = new Image(getClass().getResource("/icons/excluir.png").toExternalForm());
		ImageView excl = new ImageView(excluir);
		BtnExcluir.setGraphic(excl);
		
		Image nov = new Image(getClass().getResource("/icons/novoarq.png").toExternalForm());
		ImageView novo = new ImageView(nov);
		novo.setFitWidth(13);
		novo.setFitHeight(13);
		BtnNovo.setGraphic(novo);
		
		Image alt = new Image(getClass().getResource("/icons/setas-flechas.png").toExternalForm());
		ImageView alterar = new ImageView(alt);
		alterar.setFitWidth(16);
		alterar.setFitHeight(16);
		BtnAlterar.setGraphic(alterar);
	}
}
