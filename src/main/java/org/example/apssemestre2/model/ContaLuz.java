package org.example.apssemestre2.model;

import java.time.LocalDate;

public class ContaLuz {
    private int id;
    private String bandeira;
    private LocalDate referencia;
    private LocalDate vencimento;
    private float consumo;
    private float valor;

    public ContaLuz() {
    }

    public ContaLuz(int id, LocalDate vencimento, LocalDate referencia) {
        setId(id);
        setVencimento(vencimento);
        setReferencia(referencia);
    }

    public ContaLuz(String bandeira, LocalDate referencia, LocalDate vencimento, float consumo, float valor) {
        super();
        this.bandeira = bandeira;
        this.referencia = referencia;
        this.vencimento = vencimento;
        this.consumo = consumo;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public float getConsumo() {
        return consumo;
    }

    public void setConsumo(float consumo) {
        this.consumo = consumo;
    }

    public LocalDate getVencimento() {
        return vencimento;
    }

    public void setVencimento(LocalDate vencimento) {
        this.vencimento = vencimento;
    }

    public LocalDate getReferencia() {
        return referencia;
    }

    public void setReferencia(LocalDate referencia) {
        this.referencia = referencia;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }
}
