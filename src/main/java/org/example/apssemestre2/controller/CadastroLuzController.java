package org.example.apssemestre2.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.example.apssemestre2.model.ContaLuz;
import org.example.apssemestre2.service.ContaLuzService;

public class CadastroLuzController implements Initializable {

    @FXML
    private TextField TextFieldBandeira;

    @FXML
    private TextField TextFieldReferencia;

    @FXML
    private Button BtnSalvar;

    @FXML
    private ChoiceBox<?> ChoiceBoxAno;

    @FXML
    private TextField TextFieldVencimento;

    @FXML
    private TextField TextFieldConsumo;

    @FXML
    private Button BtnLimpar;

    @FXML
    private Label LabelCadastro;

    @FXML
    private Button BtnExcluir;

    @FXML
    private TableView<?> TableViewContaLuz;

    @FXML
    private TextField TextFieldValor;

    @FXML
    private Button BtnAlterar;

    @FXML
    void salvarCadastro(ActionEvent event) {

        String bandeira = TextFieldBandeira.getText();
        String referencia = TextFieldReferencia.getText();
        String vencimento = TextFieldVencimento.getText();
        String consumo = TextFieldConsumo.getText();
        String valor = TextFieldValor.getText();

        ContaLuz novaConta = new ContaLuz(bandeira, referencia, vencimento, consumo, valor);

        service.cadastrar(novaConta);
        contaluz.add(novaConta);
    }

    @FXML
    void LimparDados(ActionEvent event) {

        TextFieldBandeira.setText("");
        TextFieldReferencia.setText("");
        TextFieldVencimento.setText("");
        TextFieldConsumo.setText("");
        TextFieldValor.setText("");

        Image Limpar = new Image(getClass().getResource("/org/example/apssemestre2/icons/limpar-limpo.png").toExternalForm());
        ImageView limpo = new ImageView(Limpar);
        BtnLimpar.setGraphic(limpo);

        BtnLimpar.setText("Limpar");
        LabelCadastro.setText("Nova Conta de Luz");

        BtnAlterar.setVisible(true);
        BtnExcluir.setVisible(true);

        BtnAlterar.setOpacity(1);
        BtnExcluir.setOpacity(1);
    }

    @FXML
    void alterarCadastro(ActionEvent event) {

        BtnAlterar.setOpacity(0.25);
        BtnExcluir.setOpacity(0.25);
        LabelCadastro.setText("Editando Conta de Luz");

        BtnLimpar.setText("Cancelar");

        Image Cancelar = new Image(getClass().getResource("/org/example/apssemestre2/icons/cancelar.png").toExternalForm());
        ImageView cancelado = new ImageView(Cancelar);
        BtnLimpar.setGraphic(cancelado);

    }

    @FXML
    void excluirCadastroLuz(ActionEvent event) {

    }


    private ObservableList<ContaLuz> contaluz = FXCollections.observableArrayList();

    private final ContaLuzService service;

    public CadastroLuzController() {
        this.service = new ContaLuzService();
    }


    @FXML
    void selecionarContaLuz(MouseEvent event) {
        if (event.getClickCount() == 1) {
            ContaLuz ContaSelecionada = (ContaLuz) TableViewContaLuz.getSelectionModel().getSelectedItem();
            if (ContaSelecionada != null) {
                TextFieldBandeira.setText(ContaSelecionada.getBandeira());
                TextFieldReferencia.setText(ContaSelecionada.getReferencia());
                TextFieldVencimento.setText(ContaSelecionada.getVencimento());
                TextFieldConsumo.setText(ContaSelecionada.getConsumo());
                TextFieldValor.setText(ContaSelecionada.getValor());
            }
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TextFieldBandeira.setEditable(false);
        TextFieldReferencia.setEditable(false);
        TextFieldVencimento.setEditable(false);
        TextFieldConsumo.setEditable(false);
        TextFieldValor.setEditable(false);

        TableViewContaLuz.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                selecionarContaLuz(event);
            }
        });

        Image Salvar = new Image(getClass().getResource("/org/example/apssemestre2/icons/salvar.png").toExternalForm());
        ImageView salvo = new ImageView(Salvar);
        salvo.setFitWidth(14);
        salvo.setFitHeight(14);
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
