package org.example.apssemestre2;

import org.example.apssemestre2.controller.mock.MockTelaBaseController;
import org.example.apssemestre2.controller.mock.MockTelaInicialController;

public class MainMockApplication {
    public static void main(String[] args) {
        MockTelaBaseController tela = new MockTelaInicialController();
        tela.display();
    }
}
