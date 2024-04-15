package org.example.apssemestre2.model;

import org.example.apssemestre2.model.enums.VoltagemEnum;

public class Aparelho {
    public String nome;
    public VoltagemEnum voltagem;

    public Aparelho() {}

    public Aparelho(String nome, VoltagemEnum voltagem) {
        this.nome = nome;
        this.voltagem = voltagem;
    }
}
