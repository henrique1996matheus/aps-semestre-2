package org.example.apssemestre2.controller.mock;

import java.util.List;

public class MockTelaInicialController extends MockTelaBaseController {

    public MockTelaInicialController() {
        setTitulo("Tela Inicial");
        setOpcoes(List.of("Aparelhos", "Categorias"));
    }

    @Override
    public void display() {
        System.out.println(getTitulo() + "\n");

        var opcoes = getOpcoes();

        for (int i = 0; i < opcoes.size(); i++) {
            var opcao = opcoes.get(i);
            var valor = i + 1;

            System.out.println(valor + ") - " + opcao);
        }

        System.out.println("0) - Sair\n");
        System.out.println(getTextoEscolhaOpcao());

        String entrada = scanner.nextLine();


    }
}
