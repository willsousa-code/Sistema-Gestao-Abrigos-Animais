
package repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Animal;

public class AnimalRepository {

    private final List<Animal> animais;

    public AnimalRepository() {
        this.animais = new ArrayList<>();
    }

    public void cadastrar(Animal animal) {

        if (animal == null) {
            throw new IllegalArgumentException("Animal inválido!");
        }

        if (buscarPorId(animal.getId()) != null) {
            throw new IllegalArgumentException(
                "Animal já cadastrado!"
            );
        }

        animais.add(animal);
    }

    public Animal buscarPorId(int id) {

        if (id <= 0) {
            return null;
        }

        for (Animal animal : animais) {
            if (animal.getId() == id) {
                return animal;
            }
        }

        return null;
    }

    public List<Animal> listarTodos() {
        return Collections.unmodifiableList(
            new ArrayList<>(animais)
        );
    }

    public void atualizar(Animal animalAtualizado) {

        if (animalAtualizado == null) {
            throw new IllegalArgumentException("Animal inválido!");
        }

        for (int i = 0; i < animais.size(); i++) {

            Animal animalAtual = animais.get(i);

            if (animalAtual.getId() == animalAtualizado.getId()) {

                if ("Adotado".equalsIgnoreCase(
                        animalAtual.getStatusAdocao())
                        && !"Adotado".equalsIgnoreCase(
                        animalAtualizado.getStatusAdocao())) {

                    throw new IllegalStateException(
                        "Não é permitido alterar o status de um animal adotado para outro estado!"
                    );
                }

                animais.set(i, animalAtualizado);
                return;
            }
        }

        throw new IllegalArgumentException(
            "Animal não encontrado!"
        );
    }

    public void excluir(int id) {

        Animal animal = buscarPorId(id);

        if (animal == null) {
            throw new IllegalArgumentException(
                "Animal não encontrado!"
            );
        }

        if ("Adotado".equalsIgnoreCase(animal.getStatusAdocao())) {
            throw new IllegalArgumentException(
                "Não é permitido excluir um animal adotado do histórico!"
            );
        }

        animais.remove(animal);
    }
}
