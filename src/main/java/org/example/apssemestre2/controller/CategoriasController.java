package org.example.apssemestre2.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.control.*;
import org.example.apssemestre2.model.Aparelho;
import org.example.apssemestre2.model.Categoria;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.fxml.Initializable;
import org.example.apssemestre2.service.CategoriaService;

public class CategoriasController implements Initializable {

    @FXML
    private Button BtnSalvar;

    @FXML
    private Label LabelCategoria;

    @FXML
    private TableColumn<Categoria, String> TableColumnCategorias;

    @FXML
    private Button BtnLimpar;

    @FXML
    private Button BtnExcluir;

    @FXML
    private TableView<Categoria> TableViewCategorias;

    @FXML
    private TextField TextFieldNome;

    @FXML
    private Button BtnAlterar;

    private CategoriaService service;

    private ObservableList<Categoria> categorias = FXCollections.observableArrayList();

    public CategoriasController() {
        this.service = new CategoriaService();
    }

    @FXML
    void LimparDados(ActionEvent event) {
        TextFieldNome.setText("");

        Image Limpar = new Image(getClass().getResource("/org/example/apssemestre2/icons/limpar-limpo.png").toExternalForm());
        ImageView limpo = new ImageView(Limpar);
        BtnLimpar.setGraphic(limpo);

        BtnLimpar.setText("Limpar");
        LabelCategoria.setText("Nova Categoria");

        BtnAlterar.setVisible(true);
        BtnExcluir.setVisible(true);

        BtnAlterar.setOpacity(1);
        BtnExcluir.setOpacity(1);


    }

    @FXML
    void selecionarCategoria(MouseEvent event) {

        if (event.getClickCount() == 1) {
            Categoria CategoriaSelecionada = (Categoria) TableViewCategorias.getSelectionModel().getSelectedItem();

            if (CategoriaSelecionada != null) {
                TextFieldNome.setText(CategoriaSelecionada.getNome());
            }
        }
    }

    @FXML
    void alterarCategoria(ActionEvent event) {
        BtnAlterar.setOpacity(0.25);
        BtnExcluir.setOpacity(0.25);
        LabelCategoria.setText("Alterando Categoria");

        BtnLimpar.setText("Cancelar");

        Image Cancelar = new Image(getClass().getResource("/org/example/apssemestre2/icons/cancelar.png").toExternalForm());
        ImageView cancelado = new ImageView(Cancelar);
        BtnLimpar.setGraphic(cancelado);
        Categoria categoriaSelecionada = TableViewCategorias.getSelectionModel().getSelectedItem();

        if (categoriaSelecionada != null) {
            String nome = TextFieldNome.getText();

            if (service.atualizar(categoriaSelecionada, new Categoria(nome))) {
                TableViewCategorias.refresh();
            }
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TableViewCategorias.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                selecionarCategoria(event);
            }
        });
        TableColumnCategorias.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNome()));

        List<Categoria> categoriaList = service.listar(new Categoria());
        categorias.addAll(categoriaList);
        TableViewCategorias.setItems(categorias);

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
}
