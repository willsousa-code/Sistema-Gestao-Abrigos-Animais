package repository;

import java.util.ArrayList;
import java.util.List;
import model.Notificacao;

public class NotificacaoRepository {

    private List<Notificacao> notificacoes = new ArrayList<>();

    public void cadastrar(Notificacao notificacao) {
        notificacoes.add(notificacao);
    }

    public List<Notificacao> listar() {
        return notificacoes;
    }

    public Notificacao buscar(int id) {

        for (Notificacao n : notificacoes) {
            if (n.getId() == id) {
                return n;
            }
        }

        return null;
    }

    public void excluir(int id) {
        notificacoes.removeIf(n -> n.getId() == id);
    }

    public List<Notificacao> listarNaoLidas() {

        List<Notificacao> lista = new ArrayList<>();

        for (Notificacao n : notificacoes) {
            if (!n.isLida()) {
                lista.add(n);
            }
        }

        return lista;
    }
}