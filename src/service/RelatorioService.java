
package service;

import java.util.List;

import model.Relatorio;
import repository.RelatorioRepository;

public class RelatorioService {

    private RelatorioRepository repositorio;

    public RelatorioService(RelatorioRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void cadastrar(Relatorio relatorio) {

        if (relatorio == null) {
            throw new IllegalArgumentException(
                "Relatório inválido!"
            );
        }

        if (relatorio.getTitulo() == null ||
            relatorio.getTitulo().trim().isEmpty()) {

            throw new IllegalArgumentException(
                "O título do relatório é obrigatório!"
            );
        }

        if (relatorio.getTipo() == null ||
            relatorio.getTipo().trim().isEmpty()) {

            throw new IllegalArgumentException(
                "O tipo do relatório é obrigatório!"
            );
        }

        repositorio.cadastrar(relatorio);
    }

    public List<Relatorio> listar() {
        return repositorio.listar();
    }

    public Relatorio buscar(int id) {

        Relatorio relatorio = repositorio.buscar(id);

        if (relatorio == null) {
            throw new IllegalArgumentException(
                "Relatório não encontrado!"
            );
        }

        return relatorio;
    }

    public void excluir(int id) {

        buscar(id);

        repositorio.excluir(id);
    }

    public List<Relatorio> buscarPorTipo(String tipo) {

        if (tipo == null || tipo.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Informe um tipo de relatório válido!"
            );
        }

        return repositorio.buscarPorTipo(tipo.trim());
    }
}
