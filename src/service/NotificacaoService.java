package service;

import model.Notificacao;
import model.Usuario;
import repository.NotificacaoRepository;

import java.util.List;

public class NotificacaoService {

    private NotificacaoRepository repositorio;

    public NotificacaoService(NotificacaoRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void enviar(Usuario usuario, String mensagem) {

        Notificacao n = new Notificacao(usuario, mensagem);

        repositorio.cadastrar(n);
    }

    public List<Notificacao> listar() {
        return repositorio.listar();
    }

    public List<Notificacao> listarNaoLidas() {
        return repositorio.listarNaoLidas();
    }

    public void marcarComoLida(int id) {

        Notificacao n = repositorio.buscar(id);

        if (n == null) {
            throw new IllegalArgumentException("Notificação não encontrada!");
        }

        n.marcarComoLida();
    }

    public void excluir(int id) {
        repositorio.excluir(id);
    }
}