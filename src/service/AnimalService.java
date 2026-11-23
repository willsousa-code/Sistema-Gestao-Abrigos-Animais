
package service;

import java.util.List;

import model.Animal;
import model.Usuario;
import model.Veterinario;
import repository.AnimalRepository;
import exception.AnimalEmTratamentoException;

public class AnimalService {

    private AnimalRepository repositorio;

    public AnimalService(AnimalRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void cadastrar(Animal animal) {

        if (animal == null) {
            throw new IllegalArgumentException("Animal inválido!");
        }

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

        if ("Adotado".equalsIgnoreCase(animal.getStatusAdocao())) {
            throw new IllegalArgumentException(
                "Não é permitido excluir animais adotados!"
            );
        }

        repositorio.excluir(id);
    }

    public void disponibilizar(int id) {

        Animal animal = buscar(id);

        if ("Crítico".equalsIgnoreCase(animal.getEstadoSaude())) {
            throw new AnimalEmTratamentoException(
                "Animal em estado crítico não pode ser disponibilizado para adoção!"
            );
        }

        animal.disponibilizarParaAdocao();
    }

    public void atualizarSaude(int id, String estadoSaude, Usuario usuario) {

        if (!(usuario instanceof Veterinario) || !usuario.isAtivo()) {
            throw new IllegalArgumentException(
                "Somente veterinários ativos podem atualizar a saúde dos animais!"
            );
        }

        if (estadoSaude == null || estadoSaude.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "O estado de saúde não pode ficar vazio!"
            );
        }

        Animal animal = buscar(id);
        animal.setEstadoSaude(estadoSaude.trim());
    }
}
