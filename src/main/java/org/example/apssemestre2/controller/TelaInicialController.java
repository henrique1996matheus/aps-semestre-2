package org.example.apssemestre2.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.example.apssemestre2.model.Aparelho;
import org.example.apssemestre2.model.enums.VoltagemEnum;
import org.example.apssemestre2.service.AparelhoService;

public class TelaInicialController {
    AparelhoService controller;

    public TelaInicialController() {
        controller = new AparelhoService();
    }

    @FXML
    private Label txtOla;

    @FXML
    protected void onHelloButtonClick() {
        var lista = controller.listar();
        Aparelho aparelho = new Aparelho("Sem informação", VoltagemEnum.v110);

        if (!lista.isEmpty()) {
            aparelho = lista.get(0);
        }

        txtOla.setText("Aparelho encontrado: " + aparelho.getNome());
    }
}