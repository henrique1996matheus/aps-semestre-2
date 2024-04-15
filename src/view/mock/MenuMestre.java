package view.mock;

import java.util.Scanner;

public class MenuMestre {
    MenuBase menu;

    public void rodar() {
        menu = new MenuPrincipal();
        Scanner scanner = new Scanner(System.in);

        String entrada;
        boolean manterPrograma;

        do {
            System.out.print(menu.display());
            entrada = scanner.nextLine();

            manterPrograma = menu.tratarEntrada(entrada, this);
        } while (manterPrograma);

        System.out.println("\nFinalizando Programa...");

        scanner.close();
    }
}
