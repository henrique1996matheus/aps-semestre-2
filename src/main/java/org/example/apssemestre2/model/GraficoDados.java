package org.example.apssemestre2.model;

import java.util.List;

public class GraficoDados {
    private String[] x;
    private String[] y;
    private String[][] consumosCategorias;

    public GraficoDados() {
    }
    
    public GraficoDados(String[] x, String[] y) {
        this.x = x;
        this.y = y;
    }

    public GraficoDados(String[] x, String[][] consumosCategorias) {
        this.x = x;
        this.consumosCategorias = consumosCategorias;
    }

    public String[] getX() {
        return x;
    }

    public void setX(String[] x) {
        this.x = x;
    }

    public String[] getY() {
        return y;
    }

    public void setY(String[] y) {
        this.y = y;
    }

    public String[][] getConsumosCategorias() {
        return consumosCategorias;
    }

    public void setConsumosCategorias(String[][] consumosCategorias) {
        this.consumosCategorias = consumosCategorias;
    }
}
