package org.example.apssemestre2.model;

public class ContaLuz {
    public int id;
    public String bandeira;
    public String referencia;
    public String vencimento;
    public String consumo;
    public String valor;
    public String ano;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getValor() {
        return valor;
    }
    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getConsumo() {
        return consumo;
    }
    public void setConsumo(String consumo) {
        this.consumo = consumo;
    }

    public String getVencimento() {
        return vencimento;
    }
    public void setVencimento(String vencimento) {
        this.vencimento = vencimento;
    }

    public String getReferencia() {
        return referencia;
    }
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getBandeira() {
        return bandeira;
    }
    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public String getAno() {
        return ano;
    }
    public void setAno(String ano) {
        this.ano = ano;
    }

    public ContaLuz(String bandeira, String referencia, String vencimento, String consumo, String valor, String ano) {
        super();
        this.bandeira = bandeira;
        this.referencia = referencia;
        this.vencimento = vencimento;
        this.consumo = consumo;
        this.valor = valor;
        this.ano = ano;
    }

    public ContaLuz() {}
}
