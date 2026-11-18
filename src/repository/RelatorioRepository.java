package repository;

import java.util.ArrayList;
import java.util.List;
import model.Relatorio;

public class RelatorioRepository {

    private List<Relatorio> relatorios = new ArrayList<>();

    public void cadastrar(Relatorio relatorio) {
        relatorios.add(relatorio);
    }

    public List<Relatorio> listar() {
        return relatorios;
    }

    public Relatorio buscar(int id) {

        for (Relatorio r : relatorios) {
            if (r.getId() == id) {
                return r;
            }
        }

        return null;
    }

    public void excluir(int id) {
        relatorios.removeIf(r -> r.getId() == id);
    }

    public List<Relatorio> buscarPorTipo(String tipo) {

        List<Relatorio> lista = new ArrayList<>();

        for (Relatorio r : relatorios) {
            if (r.getTipo().equalsIgnoreCase(tipo)) {
                lista.add(r);
            }
        }

        return lista;
    }
}