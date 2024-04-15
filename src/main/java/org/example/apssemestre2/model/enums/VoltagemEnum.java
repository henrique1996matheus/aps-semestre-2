package org.example.apssemestre2.model.enums;

public enum VoltagemEnum {
    v110(110),
    v220(220);

    private int voltagem;

    VoltagemEnum(int voltagem) {
        this.voltagem = voltagem;
    }
}
