
package repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Atividade;

public class AtividadeRepository {

    private List<Atividade> atividades = new ArrayList<>();

    public void cadastrar(Atividade atividade) {

        if (atividade == null) {
            throw new IllegalArgumentException("Atividade inválida!");
        }

        if (buscar(atividade.getId()) != null) {
            throw new IllegalArgumentException("Atividade já cadastrada!");
        }

        atividades.add(atividade);
    }

    public List<Atividade> listar() {
        return Collections.unmodifiableList(
            new ArrayList<>(atividades)
        );
    }

    public Atividade buscar(int id) {

        for (Atividade atividade : atividades) {

            if (atividade.getId() == id) {
                return atividade;
            }
        }

        return null;
    }

    public void excluir(int id) {

        Atividade atividade = buscar(id);

        if (atividade == null) {
            throw new IllegalArgumentException("Atividade não encontrada!");
        }

        atividades.remove(atividade);
    }

    public List<Atividade> listarAtivas() {

        List<Atividade> lista = new ArrayList<>();

        for (Atividade atividade : atividades) {

            if (atividade.isAtiva()) {
                lista.add(atividade);
            }
        }

        return Collections.unmodifiableList(lista);
    }
}
