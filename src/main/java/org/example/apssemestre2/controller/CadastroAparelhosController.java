package org.example.apssemestre2.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import org.example.apssemestre2.repository.AparelhoRepository;
import org.example.apssemestre2.service.AparelhoService;


public class CadastroAparelhosController implements Initializable {

    @FXML
    private TextField TextFieldPotencia;

    @FXML
    private TableColumn<Aparelho,String> TableColumnModelo;

    @FXML
    private TableColumn<Aparelho,String> TableColumnCategoria;

    @FXML
    private Button BtnSalvar;

    @FXML
    private TextField TextFieldMarca;

    @FXML
    private ChoiceBox<String> ChoiceBoxCategoria;

    @FXML
    private Button BtnExcluir;

    @FXML
    private ChoiceBox<String> ChoiceBoxFiltro;

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

    private AparelhoService service;

    public CadastroAparelhosController() {
        this.service = new AparelhoService();
    }

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

        if (selecCat != null && selecCat.equals("Nova Categoria...")) {
            abrirJanelaCategoria();
        }
    }

    private void abrirJanelaCategoria() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/apssemestre2/view/Categorias.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Categorias");
            stage.setOnHiding(event -> atualizarCategorias());
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void atualizarCategorias() {
        ChoiceBoxCategoria.getItems().clear();

        List<String> categorias = service.listarString();
        categorias.add("Nova Categoria...");
        ChoiceBoxCategoria.setItems(FXCollections.observableArrayList(categorias));
    }

    @FXML
    void selecionarAparelho(MouseEvent event) {

        TextFieldPotencia.setEditable(true);
        TextFieldMarca.setEditable(true);
        TextFieldModelo.setEditable(true);
        TextFieldNome.setEditable(true);

        if (event.getClickCount() == 1) {

            Aparelho aparelhoSelecionado = TableViewAparelhos.getSelectionModel().getSelectedItem();
            if (aparelhoSelecionado != null) {
                TextFieldNome.setText(aparelhoSelecionado.getNome());
                TextFieldModelo.setText(aparelhoSelecionado.getModelo());
                TextFieldMarca.setText(aparelhoSelecionado.getMarca());
                TextFieldPotencia.setText(aparelhoSelecionado.getPotencia());
                ChoiceBoxCategoria.setValue(aparelhoSelecionado.getNomeCategoria());
            }
        }
    }

    @FXML
    void alterarAparelho(ActionEvent event) {
        Aparelho aparelhoSelecionado = TableViewAparelhos.getSelectionModel().getSelectedItem();

        String nome = TextFieldNome.getText();
        String modelo = TextFieldModelo.getText();
        String marca = TextFieldMarca.getText();
        String potencia = TextFieldPotencia.getText();
        String novaCategoria = ChoiceBoxCategoria.getValue();
        int idCategoria = obterIdCategoria(novaCategoria);

        if (aparelhoSelecionado != null && service.atualizar(aparelhoSelecionado, new Aparelho(nome, modelo, marca, potencia, idCategoria))) {
        }
        atualizarTabela();
    }

    @FXML
    void salvarAparelho(ActionEvent event) {
        String nome = TextFieldNome.getText();
        String modelo = TextFieldModelo.getText();
        String marca = TextFieldMarca.getText();
        String potencia = TextFieldPotencia.getText();
        String nomeCategoria = ChoiceBoxCategoria.getValue();
        int idCategoria = obterIdCategoria(nomeCategoria);

        Aparelho aparelho = new Aparelho(nome, modelo, marca, potencia, idCategoria);

        if (service.cadastrar(aparelho)) {
            aparelhos.add(aparelho);
        }
        atualizarTabela();
    }

    private int obterIdCategoria(String nomeCategoria) {
        int idCategoria = 0;
        if (nomeCategoria != null) {
            idCategoria = new AparelhoRepository().buscarCategoriaPeloId(nomeCategoria);
        }
        return idCategoria;
    }

    @FXML
    void excluirAparelho(ActionEvent event) {
        Aparelho aparelho = TableViewAparelhos.getSelectionModel().getSelectedItem();

        if (aparelho != null && service.excluir(aparelho)) {
            aparelhos.remove(aparelho);
        }
    }

    @FXML
    void filtroCategoria(ActionEvent event) {
        String categoriaSelecionada = (String) ChoiceBoxFiltro.getValue();
        List<Aparelho> aparelhosFiltrados = new ArrayList<>();

        if (!categoriaSelecionada.equals("Todas as Categorias")) {
            for (Aparelho aparelho : service.listar()) {
                if (aparelho.getNomeCategoria().equals(categoriaSelecionada)) {
                    aparelhosFiltrados.add(aparelho);
                }
            }
            TableViewAparelhos.setItems(FXCollections.observableArrayList(aparelhosFiltrados));
        } else {
            atualizarTabela();
        }
    }

    private void atualizarTabela() {
        List<Aparelho> aparelhosList = service.listar();
        aparelhos.setAll(aparelhosList);
        TableViewAparelhos.setItems(aparelhos);
    }

    private ObservableList<Aparelho> aparelhos = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        TextFieldPotencia.setEditable(false);
        TextFieldMarca.setEditable(false);
        TextFieldModelo.setEditable(false);
        TextFieldNome.setEditable(false);

        TableViewAparelhos.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                selecionarAparelho(event);
            }
        });

        ChoiceBoxCategoria.setOnAction(event -> abrirCategoria());

        TableColumnNome.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNome()));
        TableColumnModelo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getModelo()));
        TableColumnMarca.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMarca()));
        TableColumnPotencia.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPotencia()));
        TableColumnCategoria.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNomeCategoria()));

        atualizarTabela();
        atualizarCategorias();

        ChoiceBoxFiltro.setValue("Todas as Categorias");
        ChoiceBoxFiltro.setOnAction(this::filtroCategoria);
        ObservableList<String> categorias = FXCollections.observableArrayList();
        categorias.add("Todas as Categorias");
        categorias.addAll(service.listarString());
        ChoiceBoxFiltro.setItems(categorias);

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
