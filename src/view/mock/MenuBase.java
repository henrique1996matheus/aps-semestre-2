package view.mock;

public class MenuBase {
    public final String SELECIONE_UMA_OPCAO = "\nSelecione uma opção: ";
    public String display() {
        return "MenuBase";
    }

    public boolean tratarEntrada(String entrada, MenuMestre menuMestre) {
        return false;
    }
}
