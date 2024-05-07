package org.example.apssemestre2.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import org.example.apssemestre2.model.Aparelho;
import org.example.apssemestre2.repository.AparelhoDAO;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.fxml.Initializable;


public class CadastroAparelhosController implements Initializable {

	@FXML
    private TextField TextFieldPotencia;

    @FXML
    private TableColumn<Aparelho,String> TableColumnModelo;

    @FXML
    private Button BtnSalvar;

    @FXML
    private TextField TextFieldMarca;

    @FXML
    private ChoiceBox<String> ChoiceBoxCategoria;

    @FXML
    private Button BtnExcluir;

    @FXML
    private ChoiceBox<?> ChoiceBoxFiltro;

    @FXML
    private Button BtnAlterar;

    @FXML
    private TextField TextFieldNome;

    @FXML
    private TableColumn<Aparelho,String> TableColumnNome;

    @FXML
    private Button BtnNovo;

    @FXML
    private TableColumn<Aparelho,String> TableColumnPotencia;

    @FXML
    private TextField TextFieldModelo;

    @FXML
    private TableColumn<Aparelho,String> TableColumnMarca;

    @FXML
    private TableView<Aparelho> TableViewAparelhos;


    @FXML
    void adicionarAparelho(ActionEvent event) {
		TextFieldPotencia.setEditable(true);
		TextFieldPotencia.setText("");
		TextFieldMarca.setEditable(true);
		TextFieldMarca.setText("");
		TextFieldModelo.setEditable(true);
		TextFieldModelo.setText("");
		TextFieldNome.setEditable(true);
		TextFieldNome.setText("");
    }

    private void abrirCategoria() {
        String selecCat = ChoiceBoxCategoria.getValue();
        if (selecCat.equals("Nova Categoria...")) {
            abrirJanelaCategoria();
        }
    }

    private void abrirJanelaCategoria() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/apssemestre2/view/categorias.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Categorias");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void selecionarAparelho(MouseEvent event) {

        if (event.getClickCount() == 1) {

            Aparelho aparelhoSelecionado = TableViewAparelhos.getSelectionModel().getSelectedItem();
            if (aparelhoSelecionado != null) {
                TextFieldNome.setText(aparelhoSelecionado.getNome());
                TextFieldModelo.setText(aparelhoSelecionado.getModelo());
                TextFieldMarca.setText(aparelhoSelecionado.getMarca());
                TextFieldPotencia.setText(aparelhoSelecionado.getPotencia());
            }
        }
    }

    @FXML
    void alterarAparelho(ActionEvent event) {

        Aparelho aparelhoSelecionado = TableViewAparelhos.getSelectionModel().getSelectedItem();
        if (aparelhoSelecionado != null) {
            String novoNome = TextFieldNome.getText();
            String novoModelo = TextFieldModelo.getText();
            String novaMarca = TextFieldMarca.getText();
            String novaPotencia = TextFieldPotencia.getText();

            aparelhoSelecionado.setNome(novoNome);
            aparelhoSelecionado.setModelo(novoModelo);
            aparelhoSelecionado.setMarca(novaMarca);
            aparelhoSelecionado.setPotencia(novaPotencia);

            new AparelhoDAO().alterarAparelhos(aparelhoSelecionado);

            TableViewAparelhos.refresh();
        }
    }

    @FXML
    void salvarAparelho(ActionEvent event) {
    	String nome = TextFieldNome.getText();
    	String modelo = TextFieldModelo.getText();
    	String marca = TextFieldMarca.getText();
    	String potencia = TextFieldPotencia.getText();

        Aparelho novoAparelho = new Aparelho(nome, modelo, marca, potencia);
        aparelhos.add(novoAparelho);

        new AparelhoDAO().cadastraraAparelho(novoAparelho);
    }

    @FXML
    void excluirAparelho(ActionEvent event) {
        AparelhoDAO aparelhoDAO = new AparelhoDAO();
        Aparelho excluirAparelho = TableViewAparelhos.getSelectionModel().getSelectedItem();

        if (excluirAparelho != null) {
            aparelhos.remove(excluirAparelho);
            aparelhoDAO.excluirAparelho(excluirAparelho);
        }
    }

    private ObservableList<Aparelho> aparelhos = FXCollections.observableArrayList();

    @Override
	public void initialize(URL url,ResourceBundle rb) {

        TextFieldPotencia.setEditable(false);
        TextFieldMarca.setEditable(false);
        TextFieldModelo.setEditable(false);
        TextFieldNome.setEditable(false);

        //add infos ao choicebox de categoria
        ObservableList<String> items = FXCollections.observableArrayList();

        TableViewAparelhos.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                selecionarAparelho(event);
            }
        });

        ChoiceBoxCategoria.setItems(items);

        ChoiceBoxCategoria.setOnAction(event -> abrirCategoria());

        TableColumnNome.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNome()));
        TableColumnModelo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getModelo()));
        TableColumnMarca.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMarca()));
        TableColumnPotencia.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPotencia()));

        List<Aparelho> aparelhosList = AparelhoDAO.ListaAparelhos();
        aparelhos.addAll(aparelhosList);
        TableViewAparelhos.setItems(aparelhos);

		Image salvar = new Image(getClass().getResource("/org/example/apssemestre2/icons/salvar.png").toExternalForm());
		ImageView Salvar = new ImageView(salvar);
		BtnSalvar.setGraphic(Salvar);

		Image excluir = new Image(getClass().getResource("/org/example/apssemestre2/icons/excluir.png").toExternalForm());
		ImageView excl = new ImageView(excluir);
		BtnExcluir.setGraphic(excl);

		Image nov = new Image(getClass().getResource("/org/example/apssemestre2/icons/novoarq.png").toExternalForm());
		ImageView novo = new ImageView(nov);
		novo.setFitWidth(16);
		novo.setFitHeight(16);
		BtnNovo.setGraphic(novo);

		Image alt = new Image(getClass().getResource("/org/example/apssemestre2/icons/setas-flechas.png").toExternalForm());
		ImageView alterar = new ImageView(alt);
		alterar.setFitWidth(16);
		alterar.setFitHeight(16);
		BtnAlterar.setGraphic(alterar);
	}
}
