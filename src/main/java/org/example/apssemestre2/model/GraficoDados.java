package org.example.apssemestre2.model;

import java.util.List;

public class GraficoDados {
    private String[] x;
    private String[] y;

    public GraficoDados() {
    }
    
    public GraficoDados(String[] x, String[] y) {
        this.x = x;
        this.y = y;
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
}
