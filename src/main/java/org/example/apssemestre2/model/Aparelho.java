package org.example.apssemestre2.model;

import org.example.apssemestre2.model.enums.VoltagemEnum;

public class Aparelho {
    private int id;
    private String nome;
    private String modelo;
    private String marca;
    private String potencia;
    private VoltagemEnum voltagem;

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

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPotencia() {
        return potencia;
    }

    public void setPotencia(String potencia) {
        this.potencia = potencia;
    }

    public Aparelho(String nome, String modelo, String marca, String potencia) {
        super();
        this.nome = nome;
        this.modelo = modelo;
        this.marca = marca;
        this.potencia = potencia;
    }

    public Aparelho() {
    }

    public Aparelho(String nome, VoltagemEnum voltagem) {
        this.nome = nome;
        this.voltagem = voltagem;
    }
}
