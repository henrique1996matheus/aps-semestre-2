package org.example.apssemestre2.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.example.apssemestre2.model.Aparelho;

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
import org.example.apssemestre2.service.AparelhoService;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;


public class CadastroAparelhosController implements Initializable {

    @FXML
    private Button BtnCancelar;

    @FXML
    private TextField TextFieldPotencia;

    @FXML
    private TableColumn<?, ?> TableColumnModelo;

    @FXML
    private Button BtnSalvar;

    @FXML
    private TextField TextFieldMarca;

    @FXML
    private TextField TextFieldUsoMedio;

    @FXML
    private Label IdAparelho;

    @FXML
    private ChoiceBox<?> ChoiceBoxCategoria;

    @FXML
    private Button BtnLimpar;

    @FXML
    private Button BtnExcluir;

    @FXML
    private ChoiceBox<?> ChoiceBoxFiltro;

    @FXML
    private TextField TextFieldNome;

    @FXML
    private Button BtnAlterar;

    @FXML
    private TableColumn<?, ?> TableColumnNome;

    @FXML
    private TableColumn<?, ?> TableColumnPotencia;

    @FXML
    private TextField TextFieldModelo;

    @FXML
    private TableColumn<?, ?> TableColumnMarca;

    @FXML
    private TableView<?> TableViewAparelhos;

    @FXML
    void CancelarEdicao(ActionEvent event) {
        BtnAlterar.setVisible(true);
        BtnExcluir.setVisible(true);
        BtnCancelar.setVisible(false);
    }

    @FXML
    void SalvarAparelho(ActionEvent event) {
        String nome = TextFieldNome.getText();
        String modelo = TextFieldModelo.getText();
        String marca = TextFieldMarca.getText();
        String potencia = TextFieldPotencia.getText();

        Aparelho aparelho = new Aparelho(nome, modelo, marca, potencia);

        if (service.cadastrar(aparelho)) {
            aparelhos.add(aparelho);
        }
    }

    @FXML
    void LimparCampos(ActionEvent event) {
        TextFieldPotencia.setText("");
        TextFieldMarca.setText("");
        TextFieldModelo.setText("");
        TextFieldNome.setText("");
    }

    @FXML
    void AlterarAparelho(ActionEvent event) {
        BtnAlterar.setVisible(false);
        BtnExcluir.setVisible(false);
        BtnCancelar.setVisible(true);
        IdAparelho.setText("Alterar" );


    }

    @FXML
    void ExcluirAparelho(ActionEvent event) {
        Aparelho aparelho = TableViewAparelhos.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação de Exclusão");
        alert.setHeaderText("Tem certeza que deseja excluir esse aparelho?");
        alert.setContentText("Esta ação não poderá ser desfeita.");

        ButtonType buttonTypeConfirmar = new ButtonType("Confirmar");
        ButtonType buttonTypeCancelar = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeConfirmar, buttonTypeCancelar);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == buttonTypeConfirmar) {

            aparelhos.remove(aparelho);
        }
    }


    private AparelhoService service;

    public CadastroAparelhosController() {
        this.service = new AparelhoService();
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

    private ObservableList<Aparelho> aparelhos = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        BtnCancelar.setVisible(false);

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

        List<Aparelho> aparelhosList = service.listar();
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

