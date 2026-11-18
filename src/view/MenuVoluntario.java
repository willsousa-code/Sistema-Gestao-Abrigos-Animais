package view;

import java.util.Scanner;

public class MenuVoluntario {

    private Scanner entrada = new Scanner(System.in);

    public void exibir() {

        int opcao = -1;

        while (opcao != 0) {

            System.out.println("\n===== MENU VOLUNTÁRIO =====");
            System.out.println("1 - Ver animais disponíveis");
            System.out.println("2 - Consultar eventos");
            System.out.println("3 - Ver atividades");
            System.out.println("4 - Participar de atividade");
            System.out.println("5 - Consultar notificações");
            System.out.println("0 - Voltar");

            System.out.print("Escolha: ");

            try {
                opcao = Integer.parseInt(entrada.nextLine());

                switch (opcao) {

                    case 1:
                        System.out.println("Animais disponíveis para adoção");
                        break;

                    case 2:
                        System.out.println("Eventos cadastrados");
                        break;

                    case 3:
                        System.out.println("Atividades disponíveis");
                        break;

                    case 4:
                        System.out.println("Participação em atividade");
                        break;

                    case 5:
                        System.out.println("Notificações recebidas");
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