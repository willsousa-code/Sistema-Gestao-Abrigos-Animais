package view;

import java.util.List;
import java.util.Scanner;

import model.Relatorio;
import service.RelatorioService;

public class RelatorioView {

    private Scanner entrada = new Scanner(System.in);
    private RelatorioService relatorioService;

    public RelatorioView(RelatorioService relatorioService) {
        this.relatorioService = relatorioService;
    }

    public void listar() {

        List<Relatorio> relatorios = relatorioService.listar();

        System.out.println("\n===== RELATÓRIOS DO SISTEMA =====");

        if (relatorios.isEmpty()) {
            System.out.println("Nenhum relatório cadastrado.");
            return;
        }

        for (Relatorio r : relatorios) {

            System.out.println("ID: " + r.getId());
            System.out.println("Tipo: " + r.getTipo());

            System.out.println("-------------------------");
        }
    }

    public void buscarPorTipo() {

        System.out.print("Digite o tipo do relatório: ");
        String tipo = entrada.nextLine();

        List<Relatorio> relatorios =
            relatorioService.buscarPorTipo(tipo);

        if (relatorios.isEmpty()) {
            System.out.println("Nenhum relatório encontrado.");
            return;
        }

        System.out.println("\n===== RESULTADO DA BUSCA =====");

        for (Relatorio r : relatorios) {

            System.out.println("Código: " + r.getId());
            System.out.println("Tipo: " + r.getTipo());

            System.out.println("-------------------------");
        }
    }

    public void excluir() {

        try {
            System.out.print("ID do relatório: ");
            int id = Integer.parseInt(entrada.nextLine());

            relatorioService.excluir(id);

            System.out.println("Relatório excluído com sucesso!");

        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}