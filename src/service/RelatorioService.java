package service;

import model.Relatorio;
import repository.RelatorioRepository;

import java.util.List;

public class RelatorioService {

    private RelatorioRepository repositorio;

    public RelatorioService(RelatorioRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void cadastrar(Relatorio relatorio) {
        repositorio.cadastrar(relatorio);
    }

    public List<Relatorio> listar() {
        return repositorio.listar();
    }

    public Relatorio buscar(int id) {

        Relatorio r = repositorio.buscar(id);

        if (r == null) {
            throw new IllegalArgumentException("Relatório não encontrado!");
        }

        return r;
    }

    public void excluir(int id) {
        buscar(id);
        repositorio.excluir(id);
    }

    public List<Relatorio> buscarPorTipo(String tipo) {
        return repositorio.buscarPorTipo(tipo);
    }
}