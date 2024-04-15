package view.mock;

public class MenuPrincipal extends MenuBase {

    @Override
    public String display() {
        String tela = "";
        tela += "=== Menu Principal ===\n\n";
        tela += "1 - Gráfico de uso\n";
        tela += "2 - Cadastrar dados\n";
        tela += "3 - Histórico\n";

        tela += "0 - Sair\n";

        tela += SELECIONE_UMA_OPCAO;

        return tela;
    }

    @Override
    public boolean tratarEntrada(String entrada, MenuMestre menuMestre) {
        boolean manterPrograma = true;

        switch (entrada) {
            case "1":
                menuMestre.menu = new MenuGrafico();
                break;

            case "0":
                manterPrograma = false;
                break;
        }

        return manterPrograma;
    }
}
