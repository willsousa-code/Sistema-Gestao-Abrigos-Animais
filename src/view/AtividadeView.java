package view;

import java.util.Scanner;
import java.util.List;
import java.time.LocalDate;

import model.Atividade;
import service.AtividadeService;

public class AtividadeView {

    private Scanner entrada = new Scanner(System.in);
    private AtividadeService atividadeService;

    public AtividadeView(AtividadeService atividadeService) {
        this.atividadeService = atividadeService;
    }

    public void cadastrar() {

        try {
            System.out.println("\n===== CADASTRAR ATIVIDADE =====");

            System.out.print("Nome: ");
            String nome = entrada.nextLine();

            System.out.print("Descrição: ");
            String descricao = entrada.nextLine();

            System.out.print("Data (AAAA-MM-DD): ");
            LocalDate data = LocalDate.parse(entrada.nextLine());

            System.out.print("Horário: ");
            String horario = entrada.nextLine();

            System.out.print("Local: ");
            String local = entrada.nextLine();

            System.out.print("Quantidade de vagas: ");
            int vagas = Integer.parseInt(entrada.nextLine());

            Atividade atividade = new Atividade(
                nome, descricao, data, horario, local, vagas
            );

            atividadeService.cadastrar(atividade);

            System.out.println("Atividade cadastrada com sucesso!");

        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void listar() {

        List<Atividade> atividades = atividadeService.listar();

        System.out.println("\n===== ATIVIDADES =====");

        if (atividades.isEmpty()) {
            System.out.println("Nenhuma atividade cadastrada.");
            return;
        }

        for (Atividade a : atividades) {

            System.out.println("ID: " + a.getId());
            System.out.println("Nome: " + a.getNome());
            System.out.println("Descrição: " + a.getDescricao());
            System.out.println("Data: " + a.getData());
            System.out.println("Horário: " + a.getHorario());
            System.out.println("Local: " + a.getLocal());
            System.out.println("Vagas: " + a.getVagas());
            System.out.println("Participantes: " + a.getVagasOcupadas());

            System.out.println("-------------------------");
        }
    }

    public void inscrever() {

        try {
            System.out.print("ID da atividade: ");
            int id = Integer.parseInt(entrada.nextLine());

            atividadeService.inscreverParticipante(id);

            System.out.println("Inscrição realizada com sucesso!");

        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void cancelarInscricao() {

        try {
            System.out.print("ID da atividade: ");
            int id = Integer.parseInt(entrada.nextLine());

            atividadeService.cancelarInscricao(id);

            System.out.println("Inscrição cancelada!");

        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}