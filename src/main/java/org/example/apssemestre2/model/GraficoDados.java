package org.example.apssemestre2.model;

import java.util.List;

public class GraficoDados {
    private List<String> x;
    private List<String> y;

    public GraficoDados() {
    }
    
    public GraficoDados(List<String> x, List<String> y) {
        this.x = x;
        this.y = y;
    }

    public List<String> getX() {
        return x;
    }

    public void setX(List<String> x) {
        this.x = x;
    }

    public List<String> getY() {
        return y;
    }

    public void setY(List<String> y) {
        this.y = y;
    }
}
