package service;

import model.*;
import repository.AdocaoRepository;
import exception.*;

import java.util.List;

public class AdocaoService {

    private AdocaoRepository repositorio;

    public AdocaoService(AdocaoRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void solicitarAdocao(Animal animal, Usuario usuario) {

        if (!animal.estaDisponivelParaAdocao()) {
            throw new AnimalIndisponivelException("Esse animal não está disponível!");
        }

        if (animal.getEstadoSaude().equalsIgnoreCase("Crítico")) {
            throw new AnimalEmTratamentoException("Animal em tratamento crítico!");
        }

        Adocao adocao = new Adocao(animal, usuario);

        repositorio.cadastrar(adocao);
    }

    public void aprovar(int id, Usuario administrador) {

        if (!(administrador instanceof Administrador)) {
            throw new PermissaoNegadaException("Somente administradores podem aprovar adoções.");
        }

        Adocao adocao = repositorio.buscarPorId(id);

        if (adocao == null) {
            throw new AdocaoInvalidaException("Adoção não encontrada!");
        }

        if (!adocao.getStatus().equals("Pendente")) {
            throw new AdocaoInvalidaException("Essa adoção já foi analisada.");
        }

        if (!adocao.getAnimal().estaDisponivelParaAdocao()) {
            throw new AnimalIndisponivelException("Animal não está mais disponível!");
        }

        adocao.aprovar();
    }

    public void rejeitar(int id, Usuario administrador) {

        if (!(administrador instanceof Administrador)) {
            throw new PermissaoNegadaException("Apenas o administrador pode rejeitar.");
        }

        Adocao a = repositorio.buscarPorId(id);

        if (a == null) {
            throw new AdocaoInvalidaException("Adoção não encontrada!");
        }

        a.rejeitar();
    }

    public List<Adocao> listar() {
        return repositorio.listar();
    }
}