package org.example.apssemestre2.model;

public class Categoria {

    private int id;
    private String nome;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public Categoria(String nome) {
        super();
        this.nome = nome;
    }

    public Categoria() {}
}
