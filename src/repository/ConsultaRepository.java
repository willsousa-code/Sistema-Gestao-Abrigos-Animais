package repository;

import java.util.ArrayList;
import java.util.List;
import model.Consulta;

public class ConsultaRepository {

    private List<Consulta> consultas = new ArrayList<>();

    public void cadastrar(Consulta consulta) {
        consultas.add(consulta);
    }

    public List<Consulta> listar() {
        return consultas;
    }

    public Consulta buscar(int id) {

        for (Consulta c : consultas) {
            if (c.getId() == id) {
                return c;
            }
        }

        return null;
    }

    public void excluir(int id) {
        consultas.removeIf(c -> c.getId() == id);
    }

    public List<Consulta> buscarPorAnimal(int idAnimal) {

        List<Consulta> resultado = new ArrayList<>();

        for (Consulta c : consultas) {
            if (c.getAnimal().getId() == idAnimal) {
                resultado.add(c);
            }
        }

        return resultado;
    }
}