package view;

import java.util.Scanner;
import java.time.LocalDate;
import model.Animal;
import service.AnimalService;

public class AnimalView {

    private Scanner entrada = new Scanner(System.in);
    private AnimalService service;

    public AnimalView(AnimalService service) {
        this.service = service;
    }

    public void cadastrar() {

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

        Animal animal = new Animal(
            nome, especie, raca, idade,
            porte, saude, LocalDate.now()
        );

        service.cadastrar(animal);

        System.out.println("Animal cadastrado com sucesso!");
    }

    public void listar() {

        System.out.println("\n===== ANIMAIS CADASTRADOS =====");

        for (Animal a : service.listarAnimais()) {

            System.out.println("ID: " + a.getId());
            System.out.println("Nome: " + a.getNome());
            System.out.println("Espécie: " + a.getEspecie());
            System.out.println("Raça: " + a.getRaca());
            System.out.println("Idade: " + a.getIdade());
            System.out.println("Saúde: " + a.getEstadoSaude());
            System.out.println("Status: " + a.getStatusAdocao());

            System.out.println("-------------------------");
        }
    }

    public void buscar() {

        System.out.print("Digite o ID do animal: ");
        int id = Integer.parseInt(entrada.nextLine());

        Animal animal = service.buscar(id);

        System.out.println("Nome: " + animal.getNome());
        System.out.println("Espécie: " + animal.getEspecie());
        System.out.println("Raça: " + animal.getRaca());
        System.out.println("Estado de saúde: " + animal.getEstadoSaude());
        System.out.println("Status: " + animal.getStatusAdocao());
    }
}