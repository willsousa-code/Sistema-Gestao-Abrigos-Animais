
package service;

import java.util.List;

import model.Adocao;
import model.Animal;
import model.Usuario;
import model.Administrador;

import repository.AdocaoRepository;

import exception.AnimalIndisponivelException;
import exception.AnimalEmTratamentoException;
import exception.PermissaoNegadaException;
import exception.AdocaoInvalidaException;

public class AdocaoService {

    private AdocaoRepository repositorio;

    public AdocaoService(AdocaoRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void solicitarAdocao(Animal animal, Usuario usuario) {

        if (animal == null || usuario == null) {
            throw new IllegalArgumentException(
                "Animal e usuário são obrigatórios!"
            );
        }

        if (!usuario.isAtivo()) {
            throw new IllegalArgumentException(
                "Usuário desativado não pode solicitar adoção!"
            );
        }

        if (!animal.estaDisponivelParaAdocao()) {
            throw new AnimalIndisponivelException(
                "Este animal não está disponível para adoção!"
            );
        }

        if ("Crítico".equalsIgnoreCase(animal.getEstadoSaude())) {
            throw new AnimalEmTratamentoException(
                "Animal em estado crítico não pode ser adotado!"
            );
        }

        for (Adocao existente : repositorio.listar()) {

            if (existente.getAnimal().getId() == animal.getId()
                && "Pendente".equalsIgnoreCase(existente.getStatus())) {

                throw new AdocaoInvalidaException(
                    "Já existe uma solicitação pendente para este animal!"
                );
            }
        }

        Adocao adocao = new Adocao(animal, usuario);

        repositorio.cadastrar(adocao);
    }

    public void aprovar(int id, Usuario administrador) {

        if (!(administrador instanceof Administrador)
            || !administrador.isAtivo()) {

            throw new PermissaoNegadaException(
                "Somente administradores ativos podem aprovar adoções!"
            );
        }

        Adocao adocao = repositorio.buscarPorId(id);

        if (adocao == null) {
            throw new AdocaoInvalidaException(
                "Adoção não encontrada!"
            );
        }

        if (!"Pendente".equalsIgnoreCase(adocao.getStatus())) {
            throw new AdocaoInvalidaException(
                "Somente adoções pendentes podem ser aprovadas!"
            );
        }

        Animal animal = adocao.getAnimal();

        if (!animal.estaDisponivelParaAdocao()) {
            throw new AnimalIndisponivelException(
                "Este animal não está mais disponível!"
            );
        }

        if ("Crítico".equalsIgnoreCase(animal.getEstadoSaude())) {
            throw new AnimalEmTratamentoException(
                "Animal em estado crítico não pode ser adotado!"
            );
        }

        adocao.aprovar();
    }

    public void rejeitar(int id, Usuario administrador) {

        if (!(administrador instanceof Administrador)
            || !administrador.isAtivo()) {

            throw new PermissaoNegadaException(
                "Somente administradores ativos podem rejeitar adoções!"
            );
        }

        Adocao adocao = repositorio.buscarPorId(id);

        if (adocao == null) {
            throw new AdocaoInvalidaException(
                "Adoção não encontrada!"
            );
        }

        if (!"Pendente".equalsIgnoreCase(adocao.getStatus())) {
            throw new AdocaoInvalidaException(
                "Somente adoções pendentes podem ser rejeitadas!"
            );
        }

        adocao.rejeitar();
    }

    public List<Adocao> listar() {
        return repositorio.listar();
    }
}
