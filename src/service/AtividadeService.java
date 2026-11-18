package service;

import model.Atividade;
import repository.AtividadeRepository;

import java.util.List;

public class AtividadeService {

    private AtividadeRepository repositorio;

    public AtividadeService(AtividadeRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void cadastrar(Atividade atividade) {
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
        buscar(id);
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
        atividade.removerParticipante();
    }
}