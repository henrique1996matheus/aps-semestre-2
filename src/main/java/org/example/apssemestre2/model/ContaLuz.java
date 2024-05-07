package org.example.apssemestre2.model;

public class ContaLuz {
    public String bandeira;
    public String referencia;
    public String vencimento;
    public String consumo;
    public String valor;

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

    public ContaLuz(String bandeira, String referencia, String vencimento, String consumo, String valor) {
        super();
        this.bandeira = bandeira;
        this.referencia = referencia;
        this.vencimento = vencimento;
        this.consumo = consumo;
        this.valor = valor;
    }

    public ContaLuz() {}
}
