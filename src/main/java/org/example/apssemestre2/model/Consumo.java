package org.example.apssemestre2.model;

import java.time.LocalDate;

public class Consumo {
    private int id;
    private LocalDate data;
    private int gastoHora;
    private int idAparelho;

    public Consumo() {}

    public Consumo(LocalDate data, int idAparelho, int gastoHora) {
        setData(data);
        setIdAparelho(idAparelho);
        setGastoHora(gastoHora);
    }

    public Consumo(int id, int idAparelho, LocalDate data) {
        setId(id);
        setIdAparelho(idAparelho);
        setData(data);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public int getGastoHora() {
        return gastoHora;
    }

    public void setGastoHora(int gastoHora) {
        this.gastoHora = gastoHora;
    }

    public int getIdAparelho() {
        return idAparelho;
    }

    public void setIdAparelho(int idAparelho) {
        this.idAparelho = idAparelho;
    }
}
