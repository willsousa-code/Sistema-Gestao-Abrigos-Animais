
package service;

import java.util.ArrayList;
import java.util.List;

import model.Notificacao;
import model.Usuario;
import repository.NotificacaoRepository;

public class NotificacaoService {

    private NotificacaoRepository repositorio;

    public NotificacaoService(NotificacaoRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void enviar(Usuario usuario, String mensagem) {

        if (usuario == null || !usuario.isAtivo()) {
            throw new IllegalArgumentException(
                "Destinatário inválido ou desativado!"
            );
        }

        if (mensagem == null || mensagem.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "A mensagem da notificação não pode ficar vazia!"
            );
        }

        Notificacao notificacao = new Notificacao(
            usuario,
            mensagem.trim()
        );

        repositorio.cadastrar(notificacao);
    }

    public List<Notificacao> listar() {
        return repositorio.listar();
    }

    public List<Notificacao> listarPorDestinatario(Usuario usuario) {

        if (usuario == null) {
            throw new IllegalArgumentException("Usuário inválido!");
        }

        List<Notificacao> resultado = new ArrayList<>();

        for (Notificacao notificacao : repositorio.listar()) {
            if (notificacao.getDestinatario().getId() == usuario.getId()) {
                resultado.add(notificacao);
            }
        }

        return resultado;
    }

    public List<Notificacao> listarNaoLidas() {
        return repositorio.listarNaoLidas();
    }

    public List<Notificacao> listarNaoLidas(Usuario usuario) {

        List<Notificacao> resultado = new ArrayList<>();

        for (Notificacao notificacao : listarPorDestinatario(usuario)) {
            if (!notificacao.isLida()) {
                resultado.add(notificacao);
            }
        }

        return resultado;
    }

    public Notificacao buscar(int id) {

        Notificacao notificacao = repositorio.buscar(id);

        if (notificacao == null) {
            throw new IllegalArgumentException(
                "Notificação não encontrada!"
            );
        }

        return notificacao;
    }

    public void marcarComoLida(int id) {
        buscar(id).marcarComoLida();
    }

    public void marcarComoLida(int id, Usuario usuario) {

        Notificacao notificacao = buscar(id);

        if (usuario == null ||
            notificacao.getDestinatario().getId() != usuario.getId()) {
            throw new IllegalArgumentException(
                "Você não pode alterar a notificação de outro usuário!"
            );
        }

        notificacao.marcarComoLida();
    }

    public void excluir(int id) {
        buscar(id);
        repositorio.excluir(id);
    }
}
