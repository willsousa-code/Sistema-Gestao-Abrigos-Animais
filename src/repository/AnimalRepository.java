package repository;

import java.util.ArrayList;
import java.util.List;
import model.Animal;

public class AnimalRepository {

    private List<Animal> animais;

    public AnimalRepository() {
        this.animais = new ArrayList<>();
    }

    public void cadastrar(Animal animal) {
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
        return animais;
    }

    public void atualizar(Animal animalAtualizado) {
        for (int i = 0; i < animais.size(); i++) {
            if (animais.get(i).getId() == animalAtualizado.getId()) {
                animais.set(i, animalAtualizado);
                return;
            }
        }
    }

    public void excluir(int id) {
        animais.removeIf(animal -> animal.getId() == id);
    }
}