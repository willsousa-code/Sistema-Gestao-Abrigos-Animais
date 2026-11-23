
package view;

import java.util.Scanner;
import main.SistemaContexto;

public class MenuPrincipal {

    private Scanner entrada = new Scanner(System.in);
    private SistemaContexto contexto;

    private MenuAdministrador administrador;
    private MenuVeterinario veterinario;
    private MenuVoluntario voluntario;

    public MenuPrincipal() {
        contexto = new SistemaContexto();

        administrador = new MenuAdministrador(contexto);
        veterinario = new MenuVeterinario(contexto);
        voluntario = new MenuVoluntario(contexto);
    }

    public void exibir() {

        int opcao = -1;

        while (opcao != 0) {

            System.out.println("\n===== SISTEMA DE ABRIGOS E RESGATES =====");
            System.out.println("1 - Administrador");
            System.out.println("2 - Veterinário");
            System.out.println("3 - Voluntário");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(entrada.nextLine());

                switch (opcao) {
                    case 1:
                        administrador.exibir();
                        break;

                    case 2:
                        veterinario.exibir();
                        break;

                    case 3:
                        voluntario.exibir();
                        break;

                    case 0:
                        System.out.println("Sistema encerrado!");
                        break;

                    default:
                        System.out.println("Opção inválida!");
                }

            } catch (NumberFormatException e) {
                System.out.println("Digite apenas números!");
            }
        }
    }
}

