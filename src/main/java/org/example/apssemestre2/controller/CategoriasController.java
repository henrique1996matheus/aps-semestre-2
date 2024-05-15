package org.example.apssemestre2.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.example.apssemestre2.model.Categoria;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.fxml.Initializable;
import org.example.apssemestre2.service.AparelhoService;
import org.example.apssemestre2.service.CategoriaService;

public class CategoriasController implements Initializable {

    @FXML
    private Button BtnSalvar;

    @FXML
    private Button BtnNovo;

    @FXML
    private Button BtnExcluir;

    @FXML
    private TableView<Categoria> TableViewCategorias;

    @FXML
    private TableColumn<Categoria, String> TableColumnCategorias;

    @FXML
    private TextField TextFieldNome;

    @FXML
    private Button BtnAlterar;

    private CategoriaService service;

    public CategoriasController() {this.service = new CategoriaService(); }

    @FXML
    void adicionarCategoria(ActionEvent event) {
        TextFieldNome.setEditable(true);
        TextFieldNome.setText("");
    }

    @FXML
    void selecionarCategoria(MouseEvent event) {

        TextFieldNome.setEditable(true);

        if (event.getClickCount() == 1) {
            Categoria CategoriaSelecionada = (Categoria) TableViewCategorias.getSelectionModel().getSelectedItem();
            if (CategoriaSelecionada != null) {
                TextFieldNome.setText(CategoriaSelecionada.getNome());
            }
        }
    }

    @FXML
    void alterarCategoria(ActionEvent event) {
        Categoria categoriaSelecionada = TableViewCategorias.getSelectionModel().getSelectedItem();
        if (categoriaSelecionada != null) {
            String nome = TextFieldNome.getText();

            if (service.atualizar(categoriaSelecionada, new Categoria(nome))) {
            }
            TableViewCategorias.refresh();
        }
    }

    @FXML
    void salvarCategoria(ActionEvent event) {
        String nome = TextFieldNome.getText();

        Categoria novaCategoria = new Categoria(nome);

        if (service.cadastrar(novaCategoria)) {
            categorias.add(novaCategoria);
        }
    }

    @FXML
    void excluirCategoria(ActionEvent event) {
        Categoria excluirCategoria = TableViewCategorias.getSelectionModel().getSelectedItem();

        if (excluirCategoria != null && service.excluir(excluirCategoria)) {
            categorias.remove(excluirCategoria);
        }
    }

    private ObservableList<Categoria> categorias = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TextFieldNome.setEditable(false);

        TableViewCategorias.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                selecionarCategoria(event);
            }
        });
        TableColumnCategorias.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNome()));

        List<Categoria> aparelhosList = service.listar();
        categorias.addAll(aparelhosList);
        TableViewCategorias.setItems(categorias);

        Image salvar = new Image(getClass().getResource("/org/example/apssemestre2/icons/salvar.png").toExternalForm());
        ImageView Salvar = new ImageView(salvar);
        Salvar.setFitHeight(13);
        Salvar.setFitWidth(13);
        BtnSalvar.setGraphic(Salvar);

        Image excluir = new Image(getClass().getResource("/org/example/apssemestre2/icons/excluir.png").toExternalForm());
        ImageView excl = new ImageView(excluir);
        BtnExcluir.setGraphic(excl);

        Image nov = new Image(getClass().getResource("/org/example/apssemestre2/icons/novoarq.png").toExternalForm());
        ImageView novo = new ImageView(nov);
        novo.setFitWidth(13);
        novo.setFitHeight(13);
        BtnNovo.setGraphic(novo);

        Image alt = new Image(getClass().getResource("/org/example/apssemestre2/icons/setas-flechas.png").toExternalForm());
        ImageView alterar = new ImageView(alt);
        alterar.setFitWidth(16);
        alterar.setFitHeight(16);
        BtnAlterar.setGraphic(alterar);
    }
}
