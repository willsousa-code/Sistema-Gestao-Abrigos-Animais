package view;

import java.util.Scanner;

public class MenuVeterinario {

    private Scanner entrada = new Scanner(System.in);

    public void exibir() {

        int opcao = -1;

        while (opcao != 0) {

            System.out.println("\n===== MENU VETERINÁRIO =====");
            System.out.println("1 - Consultar animais");
            System.out.println("2 - Registrar consulta");
            System.out.println("3 - Registrar vacina");
            System.out.println("4 - Consultar histórico médico");
            System.out.println("5 - Atualizar informações de saúde");
            System.out.println("0 - Voltar");

            System.out.print("Escolha: ");

            try {
                opcao = Integer.parseInt(entrada.nextLine());

                switch (opcao) {

                    case 1:
                        System.out.println("Lista de animais");
                        break;

                    case 2:
                        System.out.println("Registro de consulta");
                        break;

                    case 3:
                        System.out.println("Registro de vacina");
                        break;

                    case 4:
                        System.out.println("Histórico médico dos animais");
                        break;

                    case 5:
                        System.out.println("Atualização das informações de saúde");
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