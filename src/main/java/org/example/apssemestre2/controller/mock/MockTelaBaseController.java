package org.example.apssemestre2.controller.mock;

import java.util.List;
import java.util.Scanner;

public abstract class MockTelaBaseController {
    private String titulo = "Título Tela";
    private String textoEscolhaOpcao = "Escolha uma das opções acima: ";
    private List<String> opcoes = List.of();
    protected Scanner scanner = new Scanner(System.in);

    public abstract void display();

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTextoEscolhaOpcao() {
        return textoEscolhaOpcao;
    }

    public void setTextoEscolhaOpcao(String textoEscolhaOpcao) {
        this.textoEscolhaOpcao = textoEscolhaOpcao;
    }

    public List<String> getOpcoes() {
        return opcoes;
    }

    public void setOpcoes(List<String> opcoes) {
        this.opcoes = opcoes;
    }
}
