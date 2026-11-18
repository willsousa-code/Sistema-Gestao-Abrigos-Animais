package view;

import java.util.List;
import java.util.Scanner;

import model.Notificacao;
import model.Usuario;
import service.NotificacaoService;

public class NotificacaoView {

    private Scanner entrada = new Scanner(System.in);
    private NotificacaoService notificacaoService;

    public NotificacaoView(NotificacaoService notificacaoService) {
        this.notificacaoService = notificacaoService;
    }

    public void enviar(Usuario usuario) {

        try {
            System.out.println("\n===== ENVIAR NOTIFICAÇÃO =====");

            System.out.print("Mensagem: ");
            String mensagem = entrada.nextLine();

            notificacaoService.enviar(usuario, mensagem);

            System.out.println("Notificação enviada!");

        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void listar() {

        List<Notificacao> notificacoes = notificacaoService.listar();

        System.out.println("\n===== NOTIFICAÇÕES =====");

        if (notificacoes.isEmpty()) {
            System.out.println("Nenhuma notificação encontrada.");
            return;
        }

        for (Notificacao n : notificacoes) {

            System.out.println("ID: " + n.getId());
            System.out.println("Mensagem: " + n.getMensagem());
            System.out.println("Data: " + n.getDataEnvio());
            System.out.println("Status: " + (n.isLida() ? "Lida" : "Não lida"));

            System.out.println("-------------------------");
        }
    }

    public void marcarComoLida() {

        try {
            System.out.print("ID da notificação: ");
            int id = Integer.parseInt(entrada.nextLine());

            notificacaoService.marcarComoLida(id);

            System.out.println("Notificação marcada como lida!");

        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void listarNaoLidas() {

        List<Notificacao> lista = notificacaoService.listarNaoLidas();

        System.out.println("\n===== NOTIFICAÇÕES NÃO LIDAS =====");

        if (lista.isEmpty()) {
            System.out.println("Não existem notificações pendentes.");
            return;
        }

        for (Notificacao n : lista) {
            System.out.println(n.getId() + " - " + n.getMensagem());
        }
    }
}