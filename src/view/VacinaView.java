package view;

import java.util.Scanner;
import java.util.List;
import java.time.LocalDate;

import model.Vacina;
import model.Animal;
import model.Veterinario;

import service.VacinaService;
import service.AnimalService;

public class VacinaView {

    private Scanner entrada = new Scanner(System.in);

    private VacinaService vacinaService;
    private AnimalService animalService;

    public VacinaView(VacinaService vacinaService, AnimalService animalService) {
        this.vacinaService = vacinaService;
        this.animalService = animalService;
    }

    public void cadastrar(Veterinario veterinario) {

        try {
            System.out.println("\n===== REGISTRAR VACINA =====");

            System.out.print("ID do animal: ");
            int id = Integer.parseInt(entrada.nextLine());

            Animal animal = animalService.buscar(id);

            System.out.print("Nome da vacina: ");
            String nome = entrada.nextLine();

            System.out.print("Data da aplicação (AAAA-MM-DD): ");
            LocalDate data = LocalDate.parse(entrada.nextLine());

            System.out.print("Data da próxima dose (AAAA-MM-DD): ");
            String proxima = entrada.nextLine();

            LocalDate dataProxima = proxima.isEmpty() ? null : LocalDate.parse(proxima);

            Vacina vacina = new Vacina(
                animal, nome, data, dataProxima, veterinario
            );

            vacinaService.cadastrar(vacina, veterinario);

            System.out.println("Vacina registrada com sucesso!");

        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void listar() {

        List<Vacina> lista = vacinaService.listar();

        System.out.println("\n===== VACINAS REGISTRADAS =====");

        if (lista.isEmpty()) {
            System.out.println("Nenhuma vacina cadastrada.");
            return;
        }

        for (Vacina v : lista) {

            System.out.println("Código: " + v.getId());
            System.out.println("Animal: " + v.getAnimal().getNome());
            System.out.println("Vacina: " + v.getNome());
            System.out.println("Aplicação: " + v.getDataAplicacao());
            System.out.println("Próxima dose: " + v.getDataProximaDose());
            System.out.println("Veterinário: " + v.getVeterinario().getNome());

            System.out.println("-------------------------");
        }
    }

    public void buscarPorAnimal() {

        System.out.print("ID do animal: ");
        int id = Integer.parseInt(entrada.nextLine());

        List<Vacina> vacinas = vacinaService.buscarPorAnimal(id);

        if (vacinas.isEmpty()) {
            System.out.println("Nenhuma vacina encontrada.");
            return;
        }

        for (Vacina v : vacinas) {
            System.out.println("Vacina: " + v.getNome());
            System.out.println("Data: " + v.getDataAplicacao());
            System.out.println("Próxima dose: " + v.getDataProximaDose());
            System.out.println("-------------------------");
        }
    }
}