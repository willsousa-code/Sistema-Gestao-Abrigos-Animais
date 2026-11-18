package view;

import java.util.Scanner;

public class MenuAdministrador {

    private Scanner entrada = new Scanner(System.in);

    public void exibir() {

        int opcao = -1;

        while (opcao != 0) {

            System.out.println("\n===== MENU ADMINISTRADOR =====");
            System.out.println("1 - Gerenciar animais");
            System.out.println("2 - Gerenciar usuários");
            System.out.println("3 - Aprovar adoções");
            System.out.println("4 - Gerenciar eventos");
            System.out.println("5 - Gerenciar atividades");
            System.out.println("6 - Consultar relatórios");
            System.out.println("0 - Voltar");

            System.out.print("Escolha: ");

            try {
                opcao = Integer.parseInt(entrada.nextLine());

                switch (opcao) {

                    case 1:
                        System.out.println("Gerenciamento de animais");
                        break;

                    case 2:
                        System.out.println("Gerenciamento de usuários");
                        break;

                    case 3:
                        System.out.println("Área de aprovação de adoções");
                        break;

                    case 4:
                        System.out.println("Gerenciamento de eventos");
                        break;

                    case 5:
                        System.out.println("Gerenciamento de atividades");
                        break;

                    case 6:
                        System.out.println("Relatórios do sistema");
                        break;

                    case 0:
                        System.out.println("Voltando ao menu principal...");
                        break;

                    default:
                        System.out.println("Opção inválida!");
                }

            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido!");
            }
        }
    }
}