package org.example.apssemestre2.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.SimpleStyleableStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.util.StringConverter;
import org.example.apssemestre2.model.Aparelho;
import org.example.apssemestre2.model.Consumo;
import org.example.apssemestre2.service.AparelhoService;
import org.example.apssemestre2.service.ConsumoService;


public class ConsumoController implements Initializable {

    @FXML
    private TextField textFieldGastoHora;

    @FXML
    private ChoiceBox<Aparelho> choiceBoxAparelhoCadastro;

    @FXML
    private ChoiceBox<Aparelho> choiceBoxAparelhoFiltro;

    @FXML
    private DatePicker datePickerDataFiltro;

    @FXML
    private DatePicker datePickerData;

    @FXML
    private Button btnSalvar;

    @FXML
    private Button btnLimpar;

    @FXML
    private Button btnAlterar;

    @FXML
    private Button btnExcluir;

    @FXML
    private Label lblTitulo;

    @FXML
    private TableView<Consumo> tableView;

    @FXML
    public TableColumn<Consumo, String> tableColumnAparelho;

    @FXML
    public TableColumn<Consumo, String> tableColumnData;

    @FXML
    public TableColumn<Consumo, String> tableColumnGastoHora;

    private final ObservableList<Consumo> consumos = FXCollections.observableArrayList();

    private Alert alert;

    private final ConsumoService service;

    private final AparelhoService aparelhoService;

    private Consumo consumoAlterar = null;

    public ConsumoController() {
        service = new ConsumoService();
        aparelhoService = new AparelhoService();
    }

    @FXML
    void salvar() {
        try {
            LocalDate data = datePickerData.getValue();
            Aparelho aparelho = choiceBoxAparelhoCadastro.getValue();
            int gastoHora = 0;

            if (Objects.nonNull(textFieldGastoHora.getText()) && !textFieldGastoHora.getText().isEmpty()) {
                try {
                    gastoHora = Integer.parseInt(textFieldGastoHora.getText());
                } catch (Exception e) {
                    throw new Exception("Informe uma hora válida!");
                }
            }

            int idAparelho = Objects.nonNull(aparelho) ? aparelho.getId() : 0;

            Consumo consumo = new Consumo(data, idAparelho, gastoHora);

            if (Objects.nonNull(consumoAlterar)) {
                consumo.setId(consumoAlterar.getId());
            }

            service.cadastrar(consumo);

            filtrar();
            limpar();
        } catch (Exception e) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro ao salvar o uso!");
            alert.setHeaderText(e.getMessage());
            alert.show();
        }
    }

    @FXML
    void limpar() {
        textFieldGastoHora.setText("");
        choiceBoxAparelhoCadastro.setValue(null);
        datePickerData.setValue(null);

        consumoAlterar = null;

        Image Limpar = new Image(getClass().getResource("/org/example/apssemestre2/icons/limpar-limpo.png").toExternalForm());
        ImageView limpo = new ImageView(Limpar);
        btnLimpar.setGraphic(limpo);

        btnLimpar.setText("Limpar");
        lblTitulo.setText("Novo Consumo");

        btnAlterar.setDisable(false);
        btnExcluir.setDisable(false);
    }

    @FXML
    void alterar() {
        Consumo consumo = tableView.getSelectionModel().getSelectedItem();

        if (Objects.nonNull(consumo)) {
            consumoAlterar = consumo;

            textFieldGastoHora.setText(String.valueOf(consumo.getGastoHora()));
            datePickerData.setValue(consumo.getData());

            for (Aparelho aparelho : choiceBoxAparelhoCadastro.getItems()) {
                if (aparelho.getId() == consumo.getId()) {
                    choiceBoxAparelhoCadastro.setValue(aparelho);
                    break;
                }
            }

            btnAlterar.setDisable(true);
            btnExcluir.setDisable(true);

            lblTitulo.setText("Alterando Consumo");

            Image Cancelar = new Image(getClass().getResource("/org/example/apssemestre2/icons/cancelar.png").toExternalForm());
            ImageView cancelado = new ImageView(Cancelar);
            btnLimpar.setGraphic(cancelado);
        }
    }

    @FXML
    void excluir() {
        Consumo consumo = tableView.getSelectionModel().getSelectedItem();

        if (Objects.nonNull(consumo)) {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmação de Exclusão");
            alert.setHeaderText("Tem certeza que deseja excluir esse uso?");
            alert.setContentText("Esta ação não poderá ser desfeita.");

            ButtonType buttonTypeConfirmar = new ButtonType("Confirmar");
            ButtonType buttonTypeCancelar = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(buttonTypeConfirmar, buttonTypeCancelar);

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == buttonTypeConfirmar) {
                try {
                    service.excluir(consumo);
                    consumos.remove(consumo);
                } catch (Exception e) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erro ao excluir o consumo!");
                    alert.setHeaderText(e.getMessage());
                    alert.show();
                }
            }
        }
    }

    private void filtrar() {
        Consumo filtro = new Consumo();

        var aparelho = choiceBoxAparelhoFiltro.getValue();
        var data = datePickerDataFiltro.getValue();

        filtro.setIdAparelho(Objects.nonNull(aparelho) ? aparelho.getId() : 0);
        filtro.setData(data);

        var usos = service.listar(filtro);

        consumos.clear();
        consumos.addAll(usos);

        tableView.setItems(consumos);
        tableView.refresh();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        var aparelhos = aparelhoService.listar(new Aparelho());

        choiceBoxAparelhoCadastro.getItems().addAll(aparelhos);
        choiceBoxAparelhoFiltro.getItems().addAll(new Aparelho(""));
        choiceBoxAparelhoFiltro.getItems().addAll(aparelhos);

        choiceBoxAparelhoCadastro.setConverter(new StringConverter<>() {
            @Override
            public String toString(Aparelho aparelho) {
                return aparelho != null ? aparelho.getNome() : "";
            }

            @Override
            public Aparelho fromString(String s) {
                return null;
            }
        });
        choiceBoxAparelhoFiltro.setConverter(new StringConverter<>() {
            @Override
            public String toString(Aparelho aparelho) {
                return aparelho != null ? aparelho.getNome() : "";
            }

            @Override
            public Aparelho fromString(String s) {
                return null;
            }
        });

        choiceBoxAparelhoFiltro.setOnAction(event -> filtrar());
        datePickerDataFiltro.setOnAction(event -> filtrar());

        tableColumnAparelho.setCellValueFactory(cellData -> {
            var aparelho = aparelhoService.buscar(cellData.getValue().getIdAparelho());

            return new SimpleStringProperty(String.valueOf(aparelho.getNome()));
        });
        tableColumnData.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getData())));
        tableColumnGastoHora.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getGastoHora())));

        filtrar();

        Image Salvar = new Image(getClass().getResource("/org/example/apssemestre2/icons/salvar.png").toExternalForm());
        ImageView salvo = new ImageView(Salvar);
        btnSalvar.setGraphic(salvo);

        Image Limpar = new Image(getClass().getResource("/org/example/apssemestre2/icons/limpar-limpo.png").toExternalForm());
        ImageView limpo = new ImageView(Limpar);
        btnLimpar.setGraphic(limpo);

        Image Alterar = new Image(getClass().getResource("/org/example/apssemestre2/icons/setas-flechas.png").toExternalForm());
        ImageView alterado = new ImageView(Alterar);
        alterado.setFitWidth(16);
        alterado.setFitHeight(16);
        btnAlterar.setGraphic(alterado);

        Image Excluir = new Image(getClass().getResource("/org/example/apssemestre2/icons/excluir.png").toExternalForm());
        ImageView excluido = new ImageView(Excluir);
        btnExcluir.setGraphic(excluido);
    }
}