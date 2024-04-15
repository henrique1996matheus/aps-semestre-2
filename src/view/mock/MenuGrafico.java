package view.mock;

import controller.GraficoController;

public class MenuGrafico extends MenuBase {

    GraficoController graficoController;

    public MenuGrafico() {
        graficoController = new GraficoController();
    }

    @Override
    public String display() {
        int mes = 1;

        var lista = graficoController.usoPorMes(mes);

        String tela = "";
        tela += "=== Gráfico de uso ===\n\n";
        tela += "Uso no mês: " + lista.get(0) + "\n";
        tela += "Uso na semana: " + lista.get(1) + "\n";

        tela += "0 - Voltar\n";

        tela += SELECIONE_UMA_OPCAO;

        return tela;
    }

    @Override
    public boolean tratarEntrada(String entrada, MenuMestre menuMestre) {
        switch (entrada) {
            case "0":
                menuMestre.menu = new MenuPrincipal();
                break;
        }

        return true;
    }
}
