
package repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Vacina;

public class VacinaRepository {

    private List<Vacina> vacinas = new ArrayList<>();

    public void cadastrar(Vacina vacina) {

        if (vacina == null) {
            throw new IllegalArgumentException("Vacina inválida!");
        }

        if (buscar(vacina.getId()) != null) {
            throw new IllegalArgumentException("Vacina já cadastrada!");
        }

        vacinas.add(vacina);
    }

    public List<Vacina> listar() {
        return Collections.unmodifiableList(
            new ArrayList<>(vacinas)
        );
    }

    public Vacina buscar(int id) {

        for (Vacina vacina : vacinas) {

            if (vacina.getId() == id) {
                return vacina;
            }
        }

        return null;
    }

    public void excluir(int id) {

        Vacina vacina = buscar(id);

        if (vacina == null) {
            throw new IllegalArgumentException("Vacina não encontrada!");
        }

        vacinas.remove(vacina);
    }

    public List<Vacina> buscarPorAnimal(int idAnimal) {

        List<Vacina> resultado = new ArrayList<>();

        for (Vacina vacina : vacinas) {

            if (vacina.getAnimal() != null &&
                vacina.getAnimal().getId() == idAnimal) {

                resultado.add(vacina);
            }
        }

        return Collections.unmodifiableList(resultado);
    }
}
