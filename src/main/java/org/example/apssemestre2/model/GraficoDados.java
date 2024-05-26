package org.example.apssemestre2.model;

import javafx.scene.chart.XYChart;

public class GraficoDados {
    private String[] data;
    private int[] consumo;
    private XYChart.Series<String,Number> grafico;

    public GraficoDados(String[] d,int[] c){
        this.data = d;
        this.consumo = c;

    }

    public GraficoDados(){

    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }

    public int[] getConsumo() {
        return consumo;
    }

    public void setConsumo(int[] consumo) {
        this.consumo = consumo;
    }
}
