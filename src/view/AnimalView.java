
package view;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import model.Animal;
import service.AnimalService;

public class AnimalView {

    private Scanner entrada = new Scanner(System.in);
    private AnimalService animalService;

    public AnimalView(AnimalService animalService) {
        this.animalService = animalService;
    }

    public void exibir() {

        int opcao = -1;

        while (opcao != 0) {

            System.out.println("\n===== GERENCIAMENTO DE ANIMAIS =====");
            System.out.println("1 - Cadastrar animal");
            System.out.println("2 - Listar animais");
            System.out.println("3 - Buscar animal por ID");
            System.out.println("4 - Excluir animal");
            System.out.println("5 - Disponibilizar para adoção");
            System.out.println("0 - Voltar");

            System.out.print("Escolha: ");

            try {
                opcao = Integer.parseInt(entrada.nextLine());

                switch (opcao) {

                    case 1:
                        cadastrar();
                        break;

                    case 2:
                        listar();
                        break;

                    case 3:
                        buscar();
                        break;

                    case 4:
                        excluir();
                        break;

                    case 5:
                        disponibilizar();
                        break;

                    case 0:
                        System.out.println("Voltando ao menu...");
                        break;

                    default:
                        System.out.println("Opção inválida!");
                }

            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido!");
            }
        }
    }

    private void cadastrar() {

        try {
            System.out.println("\n===== CADASTRAR ANIMAL =====");

            System.out.print("Nome: ");
            String nome = entrada.nextLine();

            System.out.print("Espécie: ");
            String especie = entrada.nextLine();

            System.out.print("Raça: ");
            String raca = entrada.nextLine();

            System.out.print("Idade: ");
            int idade = Integer.parseInt(entrada.nextLine());

            System.out.print("Porte: ");
            String porte = entrada.nextLine();

            System.out.print("Estado de saúde: ");
            String saude = entrada.nextLine();

            System.out.print("Data do resgate (AAAA-MM-DD): ");
            LocalDate data = LocalDate.parse(entrada.nextLine());

            Animal animal = new Animal(
                nome, especie, raca, idade, porte, saude, data
            );

            animalService.cadastrar(animal);

            System.out.println("Animal cadastrado com sucesso!");

        } catch (NumberFormatException e) {
            System.out.println("Idade inválida!");

        } catch (DateTimeParseException e) {
            System.out.println("Data inválida! Utilize AAAA-MM-DD.");
        }
    }

    private void listar() {

        List<Animal> animais = animalService.listarAnimais();

        System.out.println("\n===== ANIMAIS CADASTRADOS =====");

        if (animais.isEmpty()) {
            System.out.println("Nenhum animal cadastrado.");
            return;
        }

        for (Animal animal : animais) {
            mostrarAnimal(animal);
        }
    }

    private void buscar() {

        try {
            System.out.print("Digite o ID do animal: ");
            int id = Integer.parseInt(entrada.nextLine());

            Animal animal = animalService.buscar(id);

            mostrarAnimal(animal);

        } catch (NumberFormatException e) {
            System.out.println("ID inválido!");

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void excluir() {

        try {
            System.out.print("Digite o ID do animal: ");
            int id = Integer.parseInt(entrada.nextLine());

            animalService.excluir(id);

            System.out.println("Animal excluído com sucesso!");

        } catch (NumberFormatException e) {
            System.out.println("ID inválido!");

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void disponibilizar() {

        try {
            System.out.print("Digite o ID do animal: ");
            int id = Integer.parseInt(entrada.nextLine());

            animalService.disponibilizar(id);

            System.out.println("Animal disponibilizado para adoção!");

        } catch (NumberFormatException e) {
            System.out.println("ID inválido!");

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void mostrarAnimal(Animal animal) {

        System.out.println("\n------------------------------");
        System.out.println("ID: " + animal.getId());
        System.out.println("Nome: " + animal.getNome());
        System.out.println("Espécie: " + animal.getEspecie());
        System.out.println("Raça: " + animal.getRaca());
        System.out.println("Idade: " + animal.getIdade());
        System.out.println("Porte: " + animal.getPorte());
        System.out.println("Saúde: " + animal.getEstadoSaude());
        System.out.println("Data do resgate: " + animal.getDataResgate());
        System.out.println("Status: " + animal.getStatusAdocao());
        System.out.println("------------------------------");
    }
}
