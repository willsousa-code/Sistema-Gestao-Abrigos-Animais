package repository;

import java.util.ArrayList;
import java.util.List;
import model.Atividade;

public class AtividadeRepository {

    private List<Atividade> atividades = new ArrayList<>();

    public void cadastrar(Atividade atividade) {
        atividades.add(atividade);
    }

    public List<Atividade> listar() {
        return atividades;
    }

    public Atividade buscar(int id) {

        for (Atividade a : atividades) {
            if (a.getId() == id) {
                return a;
            }
        }

        return null;
    }

    public void excluir(int id) {
        atividades.removeIf(a -> a.getId() == id);
    }

    public List<Atividade> listarAtivas() {

        List<Atividade> lista = new ArrayList<>();

        for (Atividade a : atividades) {
            if (a.isAtiva()) {
                lista.add(a);
            }
        }

        return lista;
    }
}