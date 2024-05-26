package org.example.apssemestre2.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.example.apssemestre2.model.ContaLuz;
import org.example.apssemestre2.service.ContaLuzService;

public class CadastroLuzController implements Initializable {

    @FXML
    private TextField textFieldBandeira;

    @FXML
    private DatePicker datePickerReferencia;

    @FXML
    private Button btnSalvar;

    @FXML
    private TextField textFieldAno;

    @FXML
    private DatePicker datePickerVencimento;

    @FXML
    private TextField textFieldConsumo;

    @FXML
    private Button btnLimpar;

    @FXML
    private Label lblTitulo;

    @FXML
    private Button btnExcluir;

    @FXML
    private TableView<ContaLuz> tableView;

    @FXML
    private TableColumn<ContaLuz, String> tableColumnVencimento;

    @FXML
    private TableColumn<ContaLuz, String> tableColumnValor;

    @FXML
    private TableColumn<ContaLuz, String> tableColumnReferencia;

    @FXML
    private TableColumn<ContaLuz, String> tableColumnBandeira;

    @FXML
    private TableColumn<ContaLuz, String> tableColumnConsumo;

    @FXML
    private TextField textFieldValor;

    @FXML
    private Button btnAlterar;

    private ObservableList<ContaLuz> contas = FXCollections.observableArrayList();

    private final ContaLuzService service;

    private Alert alert;

    private ContaLuz contaLuzAlterar;

    public CadastroLuzController() {
        this.service = new ContaLuzService();
    }

    @FXML
    void salvar(ActionEvent event) {
        try {
            String bandeira = textFieldBandeira.getText();
            LocalDate referencia = datePickerReferencia.getValue();
            LocalDate vencimento = datePickerVencimento.getValue();
            float consumo = Float.parseFloat(textFieldConsumo.getText());
            float valor = Float.parseFloat(textFieldValor.getText());

            ContaLuz novaConta = new ContaLuz(bandeira, referencia, vencimento, consumo, valor);

            service.cadastrar(novaConta);

            filtrar();
            limpar(new ActionEvent());
        } catch (Exception e) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro ao salvar a Conta de Luz!");
            alert.setHeaderText(e.getMessage());
            alert.show();
        }
    }

    @FXML
    void limpar(ActionEvent event) {
        textFieldBandeira.setText("");
        datePickerReferencia.setValue(null);
        datePickerVencimento.setValue(null);
        textFieldConsumo.setText("");
        textFieldValor.setText("");

        contaLuzAlterar = null;

        Image Limpar = new Image(getClass().getResource("/org/example/apssemestre2/icons/limpar-limpo.png").toExternalForm());
        ImageView limpo = new ImageView(Limpar);
        btnLimpar.setGraphic(limpo);

        btnLimpar.setText("Limpar");
        lblTitulo.setText("Nova Conta de Luz");

        btnAlterar.setDisable(false);
        btnExcluir.setDisable(false);
    }

    @FXML
    void alterarCadastro(ActionEvent event) {
        ContaLuz model = tableView.getSelectionModel().getSelectedItem();

        if (Objects.nonNull(model)) {
            contaLuzAlterar = model;

            textFieldBandeira.setText(String.valueOf(model.getBandeira()));
            datePickerReferencia.setValue(model.getReferencia());
            datePickerVencimento.setValue(model.getVencimento());
            textFieldConsumo.setText(String.valueOf(model.getConsumo()));
            textFieldValor.setText(String.valueOf(model.getValor()));

            btnAlterar.setDisable(true);
            btnExcluir.setDisable(true);

            lblTitulo.setText("Alterando Conta de Luz");

            Image Cancelar = new Image(getClass().getResource("/org/example/apssemestre2/icons/cancelar.png").toExternalForm());
            ImageView cancelado = new ImageView(Cancelar);
            btnLimpar.setGraphic(cancelado);
        }
    }

    @FXML
    void excluirCadastroLuz(ActionEvent event) {
        ContaLuz model = tableView.getSelectionModel().getSelectedItem();

        if (Objects.nonNull(model)) {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmação de Exclusão");
            alert.setHeaderText("Tem certeza que deseja excluir essa Conta?");
            alert.setContentText("Esta ação não poderá ser desfeita.");

            ButtonType buttonTypeConfirmar = new ButtonType("Confirmar");
            ButtonType buttonTypeCancelar = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(buttonTypeConfirmar, buttonTypeCancelar);

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == buttonTypeConfirmar) {
                try {
                    service.excluir(model);
                    filtrar();
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
        ContaLuz filtro = new ContaLuz();

        int ano = 0;

        if (!Objects.equals("", textFieldAno.getText())) {
            ano = Integer.parseInt(textFieldAno.getText());
        }

        filtro.setReferencia(LocalDate.of(ano, 1, 1));

        var lista = service.listar(filtro);

        contas.clear();
        contas.addAll(lista);

        tableView.setItems(contas);
        tableView.refresh();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        textFieldAno.setOnAction(event -> filtrar());

        tableColumnVencimento.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getVencimento().toString()));
        tableColumnValor.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getValor())));
        tableColumnReferencia.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getReferencia().toString()));
        tableColumnBandeira.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBandeira()));
        tableColumnConsumo.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getConsumo())));

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
