
package repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Notificacao;

public class NotificacaoRepository {

    private List<Notificacao> notificacoes = new ArrayList<>();

    public void cadastrar(Notificacao notificacao) {

        if (notificacao == null) {
            throw new IllegalArgumentException("Notificação inválida!");
        }

        if (buscar(notificacao.getId()) != null) {
            throw new IllegalArgumentException("Notificação já cadastrada!");
        }

        notificacoes.add(notificacao);
    }

    public List<Notificacao> listar() {
        return Collections.unmodifiableList(
            new ArrayList<>(notificacoes)
        );
    }

    public Notificacao buscar(int id) {

        for (Notificacao notificacao : notificacoes) {

            if (notificacao.getId() == id) {
                return notificacao;
            }
        }

        return null;
    }

    public void excluir(int id) {

        Notificacao notificacao = buscar(id);

        if (notificacao == null) {
            throw new IllegalArgumentException(
                "Notificação não encontrada!"
            );
        }

        notificacoes.remove(notificacao);
    }

    public List<Notificacao> listarNaoLidas() {

        List<Notificacao> lista = new ArrayList<>();

        for (Notificacao notificacao : notificacoes) {

            if (!notificacao.isLida()) {
                lista.add(notificacao);
            }
        }

        return Collections.unmodifiableList(lista);
    }
}
