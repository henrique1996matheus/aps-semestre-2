package org.example.apssemestre2.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.scene.control.*;
import javafx.util.StringConverter;
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
    private Label TituloAparelho;

    @FXML
    private ChoiceBox<Categoria> ChoiceBoxCategoria;

    @FXML
    private ChoiceBox<Categoria> ChoiceBoxFiltro;

    @FXML
    private Button BtnLimpar;

    @FXML
    private Button BtnExcluir;

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

    private final ObservableList<Aparelho> aparelhos = FXCollections.observableArrayList();

    private final AparelhoService service;

    private final CategoriaService categoriaService;

    private boolean novoCadastro = true;

    private Aparelho aparelhoAlterar = null;

    private Alert alert;

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

        // Choice Cadastro
        ChoiceBoxCategoria.getItems().addAll(categorias);
        ChoiceBoxCategoria.setConverter(new StringConverter<>() {
            @Override
            public String toString(Categoria categoria) {
                return categoria != null ? categoria.getNome() : "";
            }

            @Override
            public Categoria fromString(String string) {
                return null;
            }
        });

        ChoiceBoxCategoria.setOnAction(event -> abrirCategoria());

        // Choice Filtro
        ChoiceBoxFiltro.getItems().addAll(new Categoria(""));
        ChoiceBoxFiltro.getItems().addAll(categorias);
        ChoiceBoxFiltro.setConverter(new StringConverter<>() {
            @Override
            public String toString(Categoria categoria) {
                return categoria != null ? categoria.getNome() : "";
            }

            @Override
            public Categoria fromString(String string) {
                return null;
            }
        });
        ChoiceBoxFiltro.setOnAction(event -> {
            Aparelho aparelho = new Aparelho();
            Categoria categoria = ChoiceBoxFiltro.getValue();

            aparelho.setIdCategoria(Objects.nonNull(categoria) ? categoria.getId() : 0);

            filtrarAparelhos(aparelho);
        });

        TableColumnNome.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNome()));
        TableColumnModelo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getModelo()));
        TableColumnMarca.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMarca()));
        TableColumnPotencia.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPotencia()));

        filtrarAparelhos(new Aparelho());

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

    private void filtrarAparelhos(Aparelho aparelho) {
        List<Aparelho> lista = service.listar(aparelho);

        aparelhos.clear();
        aparelhos.addAll(lista);

        TableViewAparelhos.setItems(aparelhos);
        TableViewAparelhos.refresh();
    }

    @FXML
    void SalvarAparelho(ActionEvent event) {
        String nome = TextFieldNome.getText();
        String modelo = TextFieldModelo.getText();
        String marca = TextFieldMarca.getText();
        String potencia = TextFieldPotencia.getText();
        Categoria categoria = ChoiceBoxCategoria.getValue();

        int idCategoria = Objects.nonNull(categoria) ? categoria.getId() : 0;

        Aparelho aparelho = new Aparelho(nome, modelo, marca, potencia, idCategoria);

        if (Objects.nonNull(aparelhoAlterar)) {
            aparelho.setId(aparelhoAlterar.getId());
        }

        try {
            service.cadastrar(aparelho);

            if (Objects.isNull(aparelhoAlterar)) {
                aparelhos.add(aparelho);
            } else {
                for (Aparelho a : TableViewAparelhos.getItems()) {
                    if (a.getId() == aparelho.getId()) {
                        a.setIdCategoria(aparelho.getIdCategoria());
                        a.setNome(aparelho.getNome());
                        a.setModelo(aparelho.getModelo());
                        a.setMarca(aparelho.getMarca());
                        a.setPotencia(aparelho.getPotencia());
                        a.setUsoMedio(aparelho.getUsoMedio());

                        TableViewAparelhos.refresh();
                        break;
                    }
                }

                aparelhoAlterar = null;
            }

            LimparCampos(new ActionEvent());
        } catch (Exception e) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro ao salvar o aparelho!");
            alert.setHeaderText(e.getMessage());
            alert.show();
        }
    }

    @FXML
    void LimparCampos(ActionEvent event) {
        TextFieldPotencia.setText("");
        TextFieldMarca.setText("");
        TextFieldModelo.setText("");
        TextFieldNome.setText("");
        ChoiceBoxCategoria.setValue(null);


        if (!novoCadastro) {
            aparelhoAlterar = null;
            novoCadastro = true;

            Image Limpar = new Image(getClass().getResource("/org/example/apssemestre2/icons/limpar-limpo.png").toExternalForm());
            ImageView limpo = new ImageView(Limpar);
            BtnLimpar.setGraphic(limpo);

            BtnLimpar.setText("Limpar");
            TituloAparelho.setText("Novo Aparelho");

            BtnExcluir.setDisable(false);
            BtnAlterar.setDisable(false);
        }
    }

    @FXML
    void AlterarAparelho(ActionEvent event) {
        Aparelho aparelhoSelecionado = TableViewAparelhos.getSelectionModel().getSelectedItem();

        if (Objects.nonNull(aparelhoSelecionado)) {
            LimparCampos(new ActionEvent());

            novoCadastro = false;

            aparelhoAlterar = aparelhoSelecionado;

            TextFieldNome.setText(aparelhoSelecionado.getNome());
            TextFieldMarca.setText(aparelhoSelecionado.getMarca());
            TextFieldPotencia.setText(aparelhoSelecionado.getPotencia());
            TextFieldModelo.setText(aparelhoSelecionado.getModelo());
            TextFieldUsoMedio.setText(String.valueOf(aparelhoSelecionado.getUsoMedio()));

            for (Categoria categoria : ChoiceBoxCategoria.getItems()) {
                if (categoria.getId() == aparelhoSelecionado.getIdCategoria()) {
                    ChoiceBoxCategoria.setValue(categoria);
                    break;
                }
            }

            BtnAlterar.setDisable(true);
            BtnExcluir.setDisable(true);

            TituloAparelho.setText("Alterando Aparelho");

            BtnLimpar.setText("Cancelar");

            Image Cancelar = new Image(getClass().getResource("/org/example/apssemestre2/icons/cancelar.png").toExternalForm());
            ImageView cancelado = new ImageView(Cancelar);
            BtnLimpar.setGraphic(cancelado);
        }
    }

    @FXML
    void ExcluirAparelho(ActionEvent event) {
        Aparelho aparelho = TableViewAparelhos.getSelectionModel().getSelectedItem();

        if (Objects.nonNull(aparelho)) {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
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
    }

    private void abrirCategoria() {
        Categoria selecCat = ChoiceBoxCategoria.getValue();

        if (Objects.nonNull(selecCat) && selecCat.equals("Nova Categoria...")) {
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

