
package service;

import java.util.List;

import model.Vacina;
import model.Usuario;
import model.Veterinario;

import repository.VacinaRepository;

import exception.PermissaoNegadaException;
import exception.VacinaDuplicadaException;

public class VacinaService {

    private VacinaRepository repositorio;

    public VacinaService(VacinaRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void cadastrar(Vacina vacina, Usuario usuario) {

        if (!(usuario instanceof Veterinario) || !usuario.isAtivo()) {
            throw new PermissaoNegadaException(
                "Somente veterinários ativos podem cadastrar vacinas!"
            );
        }

        if (vacina == null || vacina.getAnimal() == null ||
            vacina.getVeterinario() == null ||
            vacina.getNome() == null ||
            vacina.getDataAplicacao() == null) {

            throw new IllegalArgumentException(
                "Os dados da vacina estão incompletos!"
            );
        }

        if (vacina.getVeterinario().getId() != usuario.getId()) {
            throw new PermissaoNegadaException(
                "O veterinário informado não corresponde ao usuário autorizado!"
            );
        }

        for (Vacina existente : repositorio.listar()) {

            boolean mesmoAnimal =
                existente.getAnimal().getId() == vacina.getAnimal().getId();

            boolean mesmoNome =
                existente.getNome().equalsIgnoreCase(vacina.getNome());

            boolean mesmaData =
                existente.getDataAplicacao().equals(vacina.getDataAplicacao());

            if (mesmoAnimal && mesmoNome && mesmaData) {
                throw new VacinaDuplicadaException(
                    "Esta vacina já foi registrada para este animal nesta data!"
                );
            }
        }

        repositorio.cadastrar(vacina);
    }

    public List<Vacina> listar() {
        return repositorio.listar();
    }

    public Vacina buscar(int id) {

        Vacina vacina = repositorio.buscar(id);

        if (vacina == null) {
            throw new IllegalArgumentException(
                "Vacina não encontrada!"
            );
        }

        return vacina;
    }

    public List<Vacina> buscarPorAnimal(int idAnimal) {
        return repositorio.buscarPorAnimal(idAnimal);
    }

    public void excluir(int id, Usuario usuario) {

        if (!(usuario instanceof Veterinario) || !usuario.isAtivo()) {
            throw new PermissaoNegadaException(
                "Somente veterinários ativos podem excluir vacinas!"
            );
        }

        buscar(id);

        repositorio.excluir(id);
    }
}
