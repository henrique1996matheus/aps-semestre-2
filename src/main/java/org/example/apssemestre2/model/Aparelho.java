package org.example.apssemestre2.model;

public class Aparelho {
    private int id;
    private Integer idCategoria;
    private String nome;
    private String modelo;
    private String marca;
    private String potencia;
    private int usoMedio;

    public Aparelho(String nome, String modelo, String marca, String potencia, int idCategoria) {
        super();

        this.nome = nome;
        this.modelo = modelo;
        this.marca = marca;
        this.potencia = potencia;
        this.idCategoria = idCategoria;
    }

    public Aparelho() {
    }

    public Aparelho(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Aparelho(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
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

    public int getUsoMedio() {
        return usoMedio;
    }

    public void setUsoMedio(int usoMedio) {
        this.usoMedio = usoMedio;
    }
}
