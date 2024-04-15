package org.example.apssemestre2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.example.apssemestre2.model.Aparelho;
import org.example.apssemestre2.model.enums.VoltagemEnum;

public class AparelhoController {
    org.example.apssemestre2.controller.AparelhoController controller;

    public AparelhoController() {
        controller = new org.example.apssemestre2.controller.AparelhoController();
    }

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        var lista = controller.listar();
        Aparelho aparelho = new Aparelho("Sem informação", VoltagemEnum.v110);

        if (!lista.isEmpty()) {
            aparelho = lista.get(0);
        }

        welcomeText.setText("Aparelho encontrado: " + aparelho.nome);
    }
}