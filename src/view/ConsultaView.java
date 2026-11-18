package view;

import java.util.Scanner;
import java.util.List;

import model.Consulta;
import model.Animal;
import model.Veterinario;

import service.ConsultaService;
import service.AnimalService;

public class ConsultaView {

    private Scanner entrada = new Scanner(System.in);

    private ConsultaService consultaService;
    private AnimalService animalService;

    public ConsultaView(ConsultaService consultaService,
                        AnimalService animalService) {

        this.consultaService = consultaService;
        this.animalService = animalService;
    }

    public void cadastrar(Veterinario veterinario) {

        try {
            System.out.println("\n===== REGISTRAR CONSULTA =====");

            System.out.print("ID do animal: ");
            int id = Integer.parseInt(entrada.nextLine());

            Animal animal = animalService.buscar(id);

            System.out.print("Diagnóstico: ");
            String diagnostico = entrada.nextLine();

            System.out.print("Observações: ");
            String observacoes = entrada.nextLine();

            System.out.print("Tratamento indicado: ");
            String tratamento = entrada.nextLine();

            Consulta consulta = new Consulta(
                animal,
                veterinario,
                diagnostico,
                observacoes,
                tratamento
            );

            consultaService.cadastrarConsulta(consulta, veterinario);

            System.out.println("Consulta registrada com sucesso!");

        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void listar() {

        List<Consulta> consultas = consultaService.listar();

        System.out.println("\n===== CONSULTAS REGISTRADAS =====");

        if (consultas.isEmpty()) {
            System.out.println("Nenhuma consulta cadastrada.");
            return;
        }

        for (Consulta c : consultas) {

            System.out.println("Código: " + c.getId());
            System.out.println("Animal: " + c.getAnimal().getNome());
            System.out.println("Veterinário: " + c.getVeterinario().getNome());
            System.out.println("Data: " + c.getDataConsulta());
            System.out.println("Diagnóstico: " + c.getDiagnostico());
            System.out.println("Observações: " + c.getObservacoes());
            System.out.println("Tratamento: " + c.getTratamentoIndicado());

            System.out.println("-------------------------");
        }
    }

    public void buscarPorAnimal() {

        System.out.print("Digite o ID do animal: ");
        int id = Integer.parseInt(entrada.nextLine());

        List<Consulta> consultas = consultaService.buscarPorAnimal(id);

        if (consultas.isEmpty()) {
            System.out.println("Nenhuma consulta encontrada.");
            return;
        }

        for (Consulta c : consultas) {
            System.out.println("Data: " + c.getDataConsulta());
            System.out.println("Diagnóstico: " + c.getDiagnostico());
            System.out.println("Tratamento: " + c.getTratamentoIndicado());
            System.out.println("-------------------------");
        }
    }
}