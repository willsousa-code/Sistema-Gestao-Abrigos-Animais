package repository;

import java.util.ArrayList;
import java.util.List;
import model.Vacina;

public class VacinaRepository {

    private List<Vacina> vacinas = new ArrayList<>();

    public void cadastrar(Vacina vacina) {
        vacinas.add(vacina);
    }

    public List<Vacina> listar() {
        return vacinas;
    }

    public Vacina buscar(int id) {

        for (Vacina v : vacinas) {
            if (v.getId() == id) {
                return v;
            }
        }

        return null;
    }

    public void excluir(int id) {
        vacinas.removeIf(v -> v.getId() == id);
    }

    public List<Vacina> buscarPorAnimal(int idAnimal) {

        List<Vacina> resultado = new ArrayList<>();

        for (Vacina v : vacinas) {
            if (v.getAnimal().getId() == idAnimal) {
                resultado.add(v);
            }
        }

        return resultado;
    }
}