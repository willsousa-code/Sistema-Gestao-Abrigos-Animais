
package repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Animal;

public class AnimalRepository {

    private List<Animal> animais;

    public AnimalRepository() {
        this.animais = new ArrayList<>();
    }

    public void cadastrar(Animal animal) {

        if (animal == null) {
            throw new IllegalArgumentException("Animal inválido!");
        }

        if (buscarPorId(animal.getId()) != null) {
            throw new IllegalArgumentException("Animal já cadastrado!");
        }

        animais.add(animal);
    }

    public Animal buscarPorId(int id) {

        for (Animal animal : animais) {
            if (animal.getId() == id) {
                return animal;
            }
        }

        return null;
    }

    public List<Animal> listarTodos() {
        return Collections.unmodifiableList(new ArrayList<>(animais));
    }

    public void atualizar(Animal animalAtualizado) {

        if (animalAtualizado == null) {
            throw new IllegalArgumentException("Animal inválido!");
        }

        for (int i = 0; i < animais.size(); i++) {

            if (animais.get(i).getId() == animalAtualizado.getId()) {
                animais.set(i, animalAtualizado);
                return;
            }
        }

        throw new IllegalArgumentException("Animal não encontrado!");
    }

    public void excluir(int id) {

        Animal animal = buscarPorId(id);

        if (animal == null) {
            throw new IllegalArgumentException("Animal não encontrado!");
        }

        if ("Adotado".equalsIgnoreCase(animal.getStatusAdocao())) {
            throw new IllegalArgumentException(
                "Não é permitido excluir um animal adotado do histórico!"
            );
        }

        animais.remove(animal);
    }
}
