package org.example.apssemestre2.controller;

import java.net.URL;
import javafx.scene.control.TableColumn;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.example.apssemestre2.model.Aparelho;
import org.example.apssemestre2.model.ContaLuz;
import org.example.apssemestre2.repository.ContaLuzRepository;
import org.example.apssemestre2.service.ContaLuzService;

public class CadastroLuzController implements Initializable {

    @FXML
    private TableColumn<ContaLuz,String> TableColumnVencimento;

    @FXML
    private TableColumn<ContaLuz,String> TableColumnValor;

    @FXML
    private TableColumn<ContaLuz,String> TableColumnReferencia;

    @FXML
    private TableColumn<ContaLuz,String> TableColumnBandeira;

    @FXML
    private TableColumn<ContaLuz,String> TableColumnConsumo;

    @FXML
    private TableColumn<ContaLuz,String> TableColumnAno;

    @FXML
    private TextField TextFieldBandeira;

    @FXML
    private TextField TextFieldReferencia;

    @FXML
    private Button BtnSalvar;

    @FXML
    private ChoiceBox<String> ChoiceBoxAno;

    @FXML
    private Button BtnNovo;

    @FXML
    private TextField TextFieldVencimento;

    @FXML
    private TextField TextFieldConsumo;

    @FXML
    private TextField TextFieldAno;

    @FXML
    private Button BtnExcluir;

    @FXML
    private TableView<ContaLuz> TableViewContaLuz;

    @FXML
    private TextField TextFieldValor;

    @FXML
    private Button BtnAlterar;
    private ObservableList<ContaLuz> contaluz = FXCollections.observableArrayList();

    private final ContaLuzService service;

    public CadastroLuzController() {
        this.service = new ContaLuzService();
    }

    @FXML
    void novoCadastro(ActionEvent event) {
        TextFieldBandeira.setEditable(true);
        TextFieldBandeira.setText("");
        TextFieldConsumo.setEditable(true);
        TextFieldConsumo.setText("");
        TextFieldValor.setEditable(true);
        TextFieldValor.setText("");
        TextFieldReferencia.setEditable(true);
        TextFieldReferencia.setText("");
        TextFieldVencimento.setEditable(true);
        TextFieldVencimento.setText("");
        TextFieldAno.setEditable(true);
        TextFieldAno.setText("");
    }

    @FXML
    void selecionarContaLuz(MouseEvent event) {

        TextFieldBandeira.setEditable(true);
        TextFieldConsumo.setEditable(true);
        TextFieldValor.setEditable(true);
        TextFieldReferencia.setEditable(true);
        TextFieldVencimento.setEditable(true);
        TextFieldAno.setEditable(true);

        if (event.getClickCount() == 1) {
            ContaLuz ContaSelecionada = (ContaLuz) TableViewContaLuz.getSelectionModel().getSelectedItem();
            if (ContaSelecionada != null) {
                TextFieldBandeira.setText(ContaSelecionada.getBandeira());
                TextFieldConsumo.setText(ContaSelecionada.getConsumo());
                TextFieldValor.setText(ContaSelecionada.getValor());
                TextFieldReferencia.setText(ContaSelecionada.getReferencia());
                TextFieldVencimento.setText(ContaSelecionada.getVencimento());
                TextFieldAno.setText(ContaSelecionada.getAno());
            }
        }
    }

    @FXML
    void alterarCadastro(ActionEvent event) {
        ContaLuz ContaSelecionada = (ContaLuz) TableViewContaLuz.getSelectionModel().getSelectedItem();
        if (ContaSelecionada != null) {
            String bandeira = TextFieldBandeira.getText();
            String consumo = TextFieldConsumo.getText();
            String valor = TextFieldValor.getText();
            String referencia = TextFieldReferencia.getText();
            String vencimento = TextFieldVencimento.getText();
            String ano = TextFieldAno.getText();

            if (ContaSelecionada != null && service.atualizar(ContaSelecionada, new ContaLuz(bandeira, referencia, vencimento, consumo, valor, ano))) {
            }

            atualizarTabela();
        }
    }

    @FXML
    void salvarCadastro(ActionEvent event) {
        String bandeira = TextFieldBandeira.getText();
        String consumo = TextFieldConsumo.getText();
        String valor = TextFieldValor.getText();
        String vencimento = TextFieldVencimento.getText();
        String referencia = TextFieldReferencia.getText();
        String ano = TextFieldAno.getText();

        ContaLuz novaConta = new ContaLuz(bandeira, referencia, vencimento, consumo, valor, ano);

        if (service.cadastrar(novaConta)) {
            contaluz.add(novaConta);
        }

        atualizarTabela();
    }

    @FXML
    void excluirCadastroLuz(ActionEvent event) {
        ContaLuz excluirConta = (ContaLuz) TableViewContaLuz.getSelectionModel().getSelectedItem();

        if (excluirConta != null && service.excluir(excluirConta)){
            contaluz.remove(excluirConta);
        }
    }

    @FXML
    void filtroAnos(ActionEvent event) {
        String anoSelecionado = ChoiceBoxAno.getValue();
        List<ContaLuz> anosFiltrados = new ArrayList<>();

        if (anoSelecionado.equals("Todos Anos")) {
            anosFiltrados.addAll(service.listar());
        } else {
            for (ContaLuz ano : service.listar()) {
                if (ano.getAno().equals(anoSelecionado)) {
                    anosFiltrados.add(ano);
                }
            }
        }
        TableViewContaLuz.setItems(FXCollections.observableArrayList(anosFiltrados));
    }

    private void atualizarTabela() {
        contaluz.clear(); // Limpa a lista antes de adicionar os novos dados
        List<ContaLuz> contaList = service.listar();
        contaluz.addAll(contaList);
        TableViewContaLuz.setItems(contaluz);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TextFieldBandeira.setEditable(false);
        TextFieldValor.setEditable(false);
        TextFieldConsumo.setEditable(false);
        TextFieldReferencia.setEditable(false);
        TextFieldVencimento.setEditable(false);
        TextFieldAno.setEditable(false);

        TableViewContaLuz.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                selecionarContaLuz(event);
            }
        });

        TableColumnBandeira.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBandeira()));
        TableColumnConsumo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getConsumo()));
        TableColumnValor.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getValor()));
        TableColumnReferencia.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getReferencia()));
        TableColumnVencimento.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getVencimento()));
        TableColumnAno.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAno()));

        atualizarTabela();

        ChoiceBoxAno.setValue("Todos Anos");
        ChoiceBoxAno.setOnAction(this::filtroAnos);
        ObservableList<String> anos = FXCollections.observableArrayList();
        anos.add("Todos Anos");
        anos.addAll(service.listarString());
        ChoiceBoxAno.setItems(anos);

        TextFieldVencimento.setPromptText("dd / mm");
        TextFieldValor.setPromptText("R$ ");

        Image salvar = new Image(getClass().getResource("/org/example/apssemestre2/icons/salvar.png").toExternalForm());
        ImageView Salvar = new ImageView(salvar);
        BtnSalvar.setGraphic(Salvar);

        Image excluir = new Image(getClass().getResource("/org/example/apssemestre2/icons/excluir.png").toExternalForm());
        ImageView excl = new ImageView(excluir);
        BtnExcluir.setGraphic(excl);

        Image nov = new Image(getClass().getResource("/org/example/apssemestre2/icons/novoarq.png").toExternalForm());
        ImageView novo = new ImageView(nov);
        novo.setFitWidth(14);
        novo.setFitHeight(14);
        BtnNovo.setGraphic(novo);

        Image alt = new Image(getClass().getResource("/org/example/apssemestre2/icons/setas-flechas.png").toExternalForm());
        ImageView alterar = new ImageView(alt);
        alterar.setFitWidth(14);
        alterar.setFitHeight(14);
        BtnAlterar.setGraphic(alterar);
    }
}
