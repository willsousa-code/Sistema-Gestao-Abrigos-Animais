
package service;

import java.util.List;
import model.Atividade;
import repository.AtividadeRepository;

public class AtividadeService {

    private AtividadeRepository repositorio;

    public AtividadeService(AtividadeRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void cadastrar(Atividade atividade) {

        if (atividade == null) {
            throw new IllegalArgumentException("Atividade inválida!");
        }

        if (atividade.getVagas() <= 0) {
            throw new IllegalArgumentException("A atividade precisa ter pelo menos uma vaga!");
        }

        repositorio.cadastrar(atividade);
    }

    public List<Atividade> listar() {
        return repositorio.listar();
    }

    public Atividade buscar(int id) {

        Atividade atividade = repositorio.buscar(id);

        if (atividade == null) {
            throw new IllegalArgumentException("Atividade não encontrada!");
        }

        return atividade;
    }

    public void excluir(int id) {

        Atividade atividade = buscar(id);

        if (atividade.getVagasOcupadas() > 0) {
            throw new IllegalArgumentException("Não é possível excluir uma atividade com participantes inscritos!");
        }

        repositorio.excluir(id);
    }

    public List<Atividade> listarAtivas() {
        return repositorio.listarAtivas();
    }

    public void inscreverParticipante(int id) {

        Atividade atividade = buscar(id);

        if (!atividade.isAtiva()) {
            throw new IllegalArgumentException("Essa atividade está inativa!");
        }

        if (!atividade.temVagasDisponiveis()) {
            throw new IllegalArgumentException("Não existem vagas disponíveis!");
        }

        atividade.adicionarParticipante();
    }

    public void cancelarInscricao(int id) {

        Atividade atividade = buscar(id);

        if (atividade.getVagasOcupadas() <= 0) {
            throw new IllegalArgumentException("Não existem participantes inscritos nessa atividade!");
        }

        atividade.removerParticipante();
    }
}
