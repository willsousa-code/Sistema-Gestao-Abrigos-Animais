package view;

import java.util.Scanner;

public class MenuPrincipal {

    private Scanner entrada = new Scanner(System.in);

    public void exibir() {

        int opcao = -1;

        while (opcao != 0) {

            System.out.println("\n===== SISTEMA DE ABRIGOS E RESGATES =====");
            System.out.println("1 - Entrar como Administrador");
            System.out.println("2 - Entrar como Veterinário");
            System.out.println("3 - Entrar como Voluntário");
            System.out.println("0 - Sair");

            System.out.print("Escolha uma opção: ");

            try {

                opcao = Integer.parseInt(entrada.nextLine());

                switch (opcao) {

                    case 1:
                        new MenuAdministrador().exibir();
                        break;

                    case 2:
                        new MenuVeterinario().exibir();
                        break;

                    case 3:
                        new MenuVoluntario().exibir();
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