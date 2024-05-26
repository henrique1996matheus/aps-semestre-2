package org.example.apssemestre2.controller;

import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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

    private Categoria categoriaAlterar = null;

    private Alert alert;

    public CategoriasController() {
        this.service = new CategoriaService();
    }

    @FXML
    void LimparDados(ActionEvent event) {
        TextFieldNome.setText("");

        categoriaAlterar = null;

        Image Limpar = new Image(getClass().getResource("/org/example/apssemestre2/icons/limpar-limpo.png").toExternalForm());
        ImageView limpo = new ImageView(Limpar);
        BtnLimpar.setGraphic(limpo);

        BtnLimpar.setText("Limpar");
        LabelCategoria.setText("Nova Categoria");

        BtnAlterar.setDisable(false);
        BtnExcluir.setDisable(false);
    }

    @FXML
    void alterarCategoria(ActionEvent event) {
        Categoria categoriaSelecionada = (Categoria) TableViewCategorias.getSelectionModel().getSelectedItem();

        if (Objects.nonNull(categoriaSelecionada)) {
            categoriaAlterar = categoriaSelecionada;

            TextFieldNome.setText(categoriaSelecionada.getNome());

            BtnAlterar.setDisable(true);
            BtnExcluir.setDisable(true);

            LabelCategoria.setText("Alterando Categoria");

            BtnLimpar.setText("Cancelar");

            Image Cancelar = new Image(getClass().getResource("/org/example/apssemestre2/icons/cancelar.png").toExternalForm());
            ImageView cancelado = new ImageView(Cancelar);
            BtnLimpar.setGraphic(cancelado);
        }
    }

    @FXML
    void salvarCategoria(ActionEvent event) {
        String nome = TextFieldNome.getText();

        Categoria categoria = new Categoria(nome);

        if (Objects.nonNull(categoriaAlterar)) {
            categoria.setId(categoriaAlterar.getId());
        }

        try {
            service.cadastrar(categoria);

            if (Objects.isNull(categoriaAlterar)) {
                categorias.add(categoria);
            } else {
                for (Categoria c : TableViewCategorias.getItems()) {
                    if (c.getId() == categoria.getId()) {
                        c.setNome(categoria.getNome());

                        TableViewCategorias.refresh();
                        break;
                    }
                }

                categoriaAlterar = null;
            }

            LimparDados(new ActionEvent());
        } catch (Exception e) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro ao salvar a categoria!");
            alert.setHeaderText(e.getMessage());
            alert.show();
        }
    }

    @FXML
    void excluirCategoria(ActionEvent event) {
        Categoria excluirCategoria = TableViewCategorias.getSelectionModel().getSelectedItem();

        if (Objects.nonNull(excluirCategoria)) {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmação de Exclusão");
            alert.setHeaderText("Tem certeza que deseja excluir esse aparelho?");
            alert.setContentText("Esta ação não poderá ser desfeita.");

            ButtonType buttonTypeConfirmar = new ButtonType("Confirmar");
            ButtonType buttonTypeCancelar = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(buttonTypeConfirmar, buttonTypeCancelar);

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == buttonTypeConfirmar) {
                try {
                    service.excluir(excluirCategoria);
                    categorias.remove(excluirCategoria);
                } catch (Exception e) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erro ao excluir a categoria!");
                    alert.setHeaderText(e.getMessage());
                    alert.show();
                }
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
