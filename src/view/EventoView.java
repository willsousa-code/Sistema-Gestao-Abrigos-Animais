package view;

import java.util.Scanner;
import java.util.List;

import model.Evento;
import service.EventoService;

public class EventoView {

    private Scanner entrada = new Scanner(System.in);
    private EventoService eventoService;

    public EventoView(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    public void cadastrar() {

        try {
            System.out.println("\n===== CADASTRAR EVENTO =====");

            System.out.print("Nome: ");
            String nome = entrada.nextLine();

            System.out.print("Descrição: ");
            String descricao = entrada.nextLine();

            System.out.print("Data (AAAA-MM-DD): ");
            java.time.LocalDate data =
                java.time.LocalDate.parse(entrada.nextLine());

            System.out.print("Local: ");
            String local = entrada.nextLine();

            System.out.print("Tipo do evento: ");
            String tipo = entrada.nextLine();

            Evento evento = new Evento(
                nome, descricao, data, local, tipo
            );

            eventoService.cadastrar(evento);

            System.out.println("Evento cadastrado!");

        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void listar() {

        List<Evento> eventos = eventoService.listar();

        System.out.println("\n===== EVENTOS CADASTRADOS =====");

        if (eventos.isEmpty()) {
            System.out.println("Nenhum evento cadastrado.");
            return;
        }

        for (Evento e : eventos) {

            System.out.println("ID: " + e.getId());
            System.out.println("Nome: " + e.getNome());
            System.out.println("Descrição: " + e.getDescricao());
            System.out.println("Data: " + e.getData());
            System.out.println("Local: " + e.getLocal());
            System.out.println("Tipo: " + e.getTipo());
            System.out.println("Ativo: " + e.isAtivo());

            System.out.println("-------------------------");
        }
    }

    public void desativar() {

        try {
            System.out.print("ID do evento: ");
            int id = Integer.parseInt(entrada.nextLine());

            eventoService.desativar(id);

            System.out.println("Evento desativado!");

        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}