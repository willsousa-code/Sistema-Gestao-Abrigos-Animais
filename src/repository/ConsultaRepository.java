
package repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Consulta;

public class ConsultaRepository {

    private List<Consulta> consultas = new ArrayList<>();

    public void cadastrar(Consulta consulta) {

        if (consulta == null) {
            throw new IllegalArgumentException("Consulta inválida!");
        }

        if (buscar(consulta.getId()) != null) {
            throw new IllegalArgumentException("Consulta já cadastrada!");
        }

        consultas.add(consulta);
    }

    public List<Consulta> listar() {
        return Collections.unmodifiableList(
            new ArrayList<>(consultas)
        );
    }

    public Consulta buscar(int id) {

        for (Consulta consulta : consultas) {

            if (consulta.getId() == id) {
                return consulta;
            }
        }

        return null;
    }

    public void excluir(int id) {

        Consulta consulta = buscar(id);

        if (consulta == null) {
            throw new IllegalArgumentException("Consulta não encontrada!");
        }

        consultas.remove(consulta);
    }

    public List<Consulta> buscarPorAnimal(int idAnimal) {

        List<Consulta> resultado = new ArrayList<>();

        for (Consulta consulta : consultas) {

            if (consulta.getAnimal() != null &&
                consulta.getAnimal().getId() == idAnimal) {

                resultado.add(consulta);
            }
        }

        return Collections.unmodifiableList(resultado);
    }
}
