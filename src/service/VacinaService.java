package service;

import model.Vacina;
import model.Veterinario;
import model.Usuario;
import repository.VacinaRepository;
import exception.PermissaoNegadaException;
import exception.VacinaDuplicadaException;

import java.util.List;

public class VacinaService {

    private VacinaRepository repositorio;

    public VacinaService(VacinaRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void cadastrar(Vacina vacina, Usuario usuario) {

        if (!(usuario instanceof Veterinario)) {
            throw new PermissaoNegadaException(
                "Somente veterinários podem registrar vacinas."
            );
        }

        List<Vacina> lista = repositorio.buscarPorAnimal(
            vacina.getAnimal().getId()
        );

        for (Vacina v : lista) {

            if (v.getNome().equalsIgnoreCase(vacina.getNome()) &&
                v.getDataAplicacao().equals(vacina.getDataAplicacao())) {

                throw new VacinaDuplicadaException(
                    "Essa vacina já foi registrada nessa data."
                );
            }
        }

        repositorio.cadastrar(vacina);
    }

    public List<Vacina> listar() {
        return repositorio.listar();
    }

    public List<Vacina> buscarPorAnimal(int idAnimal) {
        return repositorio.buscarPorAnimal(idAnimal);
    }

    public Vacina buscar(int id) {

        Vacina v = repositorio.buscar(id);

        if (v == null) {
            throw new IllegalArgumentException("Vacina não encontrada!");
        }

        return v;
    }

    public void excluir(int id, Usuario usuario) {

        if (!(usuario instanceof Veterinario)) {
            throw new PermissaoNegadaException(
                "Somente veterinários podem excluir registros de vacinas."
            );
        }

        buscar(id);
        repositorio.excluir(id);
    }
}