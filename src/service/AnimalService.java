package service;

import model.Animal;
import repository.AnimalRepository;
import exception.AnimalEmTratamentoException;

import java.util.List;

public class AnimalService {

    private AnimalRepository repositorio;

    public AnimalService(AnimalRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void cadastrar(Animal animal) {
        repositorio.cadastrar(animal);
    }

    public List<Animal> listarAnimais() {
        return repositorio.listarTodos();
    }

    public Animal buscar(int id) {

        Animal animal = repositorio.buscarPorId(id);

        if (animal == null) {
            throw new IllegalArgumentException("Animal não encontrado!");
        }

        return animal;
    }

    public void excluir(int id) {
        Animal animal = buscar(id);

        if (animal.getStatusAdocao().equalsIgnoreCase("Adotado")) {
            throw new IllegalArgumentException("Não é permitido excluir um animal adotado.");
        }

        repositorio.excluir(id);
    }

    public void disponibilizar(int id) {

        Animal animal = buscar(id);

        if (animal.getEstadoSaude().equalsIgnoreCase("Crítico")) {
            throw new AnimalEmTratamentoException("Animal em tratamento crítico não pode ser disponibilizado.");
        }

        animal.disponibilizarParaAdocao();
    }
}