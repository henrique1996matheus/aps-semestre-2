package org.example.apssemestre2.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.util.Duration;

public class JanelaPrincipalController implements Initializable {

    @FXML
    private MenuBar BarraMenu;

    @FXML
    private TextField TextFieldFat;

    @FXML
    private TextField TextFieldBandeira;

    @FXML
    private Menu MenuConsumoDetalhado;

    @FXML
    private MenuItem menuitemMonitoramentoMetas;

    @FXML
    private MenuItem menuitemMonitoramentoAnaliseCategoria;

    @FXML
    private MenuItem menuitemConsumoContaLuz;

    @FXML
    private MenuItem menuitemCategorias;

    @FXML
    private MenuItem menuitemListaAparelhos;

    @FXML
    private BarChart<String,Number> GraficoBarra;

    @FXML
    private MenuItem menuitemConsumoConsumoporDia;

    @FXML
    private MenuItem menuitemConsumoConsumoporMes;

    @FXML
    private NumberAxis GraficoVoltagem;

    @FXML
    private TextField TextFieldAnt;

    @FXML
    private MenuItem MenuitemSobreSistema;

    @FXML
    private Label LabelTitulo;

    @FXML
    private Menu MenuMonitoramento;

    @FXML
    private Menu MenuAparelhos;

    @FXML
    private MenuItem menuitemConsumoAparelho;

    @FXML
    private Menu MenuSobre;

    @FXML
    private CategoryAxis GraficoDias;

    @FXML
    void acessarListaAparelhos(ActionEvent event) {
        try {
            abrirTelas("CadastroAparelhos", "Aparelhos");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    void acessarConsumoAparelho(ActionEvent event) {
        try {
            abrirTelas("consumo", "Consumo");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    void acessarCategorias(ActionEvent event) {
        try {
            abrirTelas("categorias", "Categorias");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    void acessarMetas(ActionEvent event) {

    }

    @FXML
    void acessarAnaliseCategoria(ActionEvent event) {
        try {
            abrirTelas("Analise_Categoria", "Análise por Categoria");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    void acessarConsumoDia(ActionEvent event) {
        try {
            abrirTelas("Consumo_Dia", "Consumo Diário");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    void acessarConsumoMes(ActionEvent event) {
        try {
            abrirTelas("Consumo_Mes", "Consumo Mensal");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    void acessarContaLuz(ActionEvent event) {
        try {
            abrirTelas("Cadastro_Luz", "Conta de Luz");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    void acessarSistema(ActionEvent event) {

    }

    public void abrirTelas(String tela, String NomeTela) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/org/example/apssemestre2/view/" + tela + ".fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle(NomeTela);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    private void timerGrafico() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.minutes(10), event -> {

            // Exemplo de atualização de dados do gráfico
            String[] dias = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"};
            int[] consum = {2900, 2698, 2600, 2400, 2000, 3200, 3102, 2345, 2735, 2144, 2234};

            XYChart.Series<String, Number> serie1 = new XYChart.Series<>();
            for (int i = 0; i < dias.length; i++) {
                // Simulando mudança nos dados
                consum[i] += (int) (Math.random() * 100 - 50); // Alterar valores aleatoriamente
                serie1.getData().add(new XYChart.Data<>(dias[i], consum[i]));
            }
            GraficoBarra.getData().clear();
            GraficoBarra.getData().add(serie1);
            GraficoBarra.setLegendVisible(false);
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        timerGrafico();
        //aparelhos

        /*Image é uma classe que serve para carregar e armazenar imagens,enquanto ImageView serve para renderizar uma imagem carregada pela
         * classe Image.
         * */
        Image aparel = new Image(getClass().getResourceAsStream("/org/example/apssemestre2/icons/aparelhos.png"));
        ImageView aparelh = new ImageView(aparel);
        MenuAparelhos.setGraphic(aparelh);

        //monitoramento
        Image monitoramento = new Image(getClass().getResourceAsStream("/org/example/apssemestre2/icons/monitoramento (1).png"));
        ImageView monitor = new ImageView(monitoramento);
        MenuMonitoramento.setGraphic(monitor);

        //Consumo detalhado
        Image consumo = new Image(getClass().getResourceAsStream("/org/example/apssemestre2/icons/consumo_detalhado.png"));
        ImageView detalhado = new ImageView(consumo);
        MenuConsumoDetalhado.setGraphic(detalhado);

        //sobre
        Image sobre = new Image(getClass().getResourceAsStream("/org/example/apssemestre2/icons/sobre.png"));
        ImageView sobre_1 = new ImageView(sobre);
        MenuSobre.setGraphic(sobre_1);

        //metodo para bandeira


        //Grafico de Barras



        //Preenchimento Campo de Texto
        TextFieldAnt.setText("R$ 142,90");
        TextFieldAnt.setEditable(false);
        TextFieldAnt.setAlignment(Pos.CENTER);

        TextFieldFat.setText("30/07");
        TextFieldFat.setEditable(false);
        TextFieldFat.setAlignment(Pos.CENTER);

        TextFieldBandeira.setEditable(false);
        TextFieldBandeira.setStyle("-fx-background-color: rgb(50,205,50);");
    }
}