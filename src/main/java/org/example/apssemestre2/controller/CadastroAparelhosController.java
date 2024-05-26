package org.example.apssemestre2.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.scene.control.*;
import org.example.apssemestre2.model.Aparelho;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.fxml.Initializable;
import org.example.apssemestre2.model.Categoria;
import org.example.apssemestre2.service.AparelhoService;
import org.example.apssemestre2.service.CategoriaService;


public class CadastroAparelhosController implements Initializable {


    @FXML
    private TextField TextFieldPotencia;

    @FXML
    private TableColumn<Aparelho, String> TableColumnModelo;

    @FXML
    private Button BtnSalvar;

    @FXML
    private TextField TextFieldMarca;

    @FXML
    private TextField TextFieldUsoMedio;

    @FXML
    private Label IdAparelho;

    @FXML
    private ChoiceBox<Categoria> ChoiceBoxCategoria;

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
    private TableColumn<Aparelho, String> TableColumnNome;

    @FXML
    private TableColumn<Aparelho, String> TableColumnPotencia;

    @FXML
    private TextField TextFieldModelo;

    @FXML
    private TableColumn<Aparelho, String> TableColumnMarca;

    @FXML
    private TableView<Aparelho> TableViewAparelhos;

    private ObservableList<Aparelho> aparelhos = FXCollections.observableArrayList();

    private boolean novoCadastro = true;

    private AparelhoService service;

    private CategoriaService categoriaService;

    public CadastroAparelhosController() {
        service = new AparelhoService();
        categoriaService = new CategoriaService();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //add infos ao choicebox de categoria
        var categorias = categoriaService.listar(new Categoria());

        TableViewAparelhos.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                selecionarAparelho(event);
            }
        });

        ChoiceBoxCategoria.getItems().addAll(categorias);

        ChoiceBoxCategoria.setOnAction(event -> abrirCategoria());

        TableColumnNome.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNome()));
        TableColumnModelo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getModelo()));
        TableColumnMarca.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMarca()));
        TableColumnPotencia.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPotencia()));

        List<Aparelho> aparelhosList = service.listar(new Aparelho());
        aparelhos.addAll(aparelhosList);
        TableViewAparelhos.setItems(aparelhos);

        Image Salvar = new Image(getClass().getResource("/org/example/apssemestre2/icons/salvar.png").toExternalForm());
        ImageView salvo = new ImageView(Salvar);
        BtnSalvar.setGraphic(salvo);


        Image Limpar = new Image(getClass().getResource("/org/example/apssemestre2/icons/limpar-limpo.png").toExternalForm());
        ImageView limpo = new ImageView(Limpar);
        BtnLimpar.setGraphic(limpo);

        Image Alterar = new Image(getClass().getResource("/org/example/apssemestre2/icons/setas-flechas.png").toExternalForm());
        ImageView alterado = new ImageView(Alterar);
        alterado.setFitWidth(16);
        alterado.setFitHeight(16);
        BtnAlterar.setGraphic(alterado);

        Image Excluir = new Image(getClass().getResource("/org/example/apssemestre2/icons/excluir.png").toExternalForm());
        ImageView excluido = new ImageView(Excluir);
        BtnExcluir.setGraphic(excluido);
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

            LimparCampos(new ActionEvent());
        }
    }

    @FXML
    void LimparCampos(ActionEvent event) {
        TextFieldPotencia.setText("");
        TextFieldMarca.setText("");
        TextFieldModelo.setText("");
        TextFieldNome.setText("");

        if (!novoCadastro) {
            novoCadastro = true;

            Image Limpar = new Image(getClass().getResource("/org/example/apssemestre2/icons/limpar-limpo.png").toExternalForm());
            ImageView limpo = new ImageView(Limpar);
            BtnLimpar.setGraphic(limpo);

            BtnLimpar.setText("Limpar");
            IdAparelho.setText("Novo Aparelho");

            BtnExcluir.setDisable(false);
            BtnAlterar.setDisable(false);
        }
    }

    @FXML
    void AlterarAparelho(ActionEvent event) {
        novoCadastro = false;

        BtnAlterar.setDisable(true);
        BtnExcluir.setDisable(true);
        IdAparelho.setText("Alterando Aparelho");

        BtnLimpar.setText("Cancelar");

        Image Cancelar = new Image(getClass().getResource("/org/example/apssemestre2/icons/cancelar.png").toExternalForm());
        ImageView cancelado = new ImageView(Cancelar);
        BtnLimpar.setGraphic(cancelado);
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


    private void abrirCategoria() {
        Categoria selecCat = ChoiceBoxCategoria.getValue();

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


    private void selecionarAparelho(MouseEvent event) {
    }
}

