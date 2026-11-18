package service;

import model.Consulta;
import model.Veterinario;
import model.Usuario;
import repository.ConsultaRepository;
import exception.PermissaoNegadaException;

import java.util.List;

public class ConsultaService {

    private ConsultaRepository repositorio;

    public ConsultaService(ConsultaRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void cadastrarConsulta(Consulta consulta, Usuario veterinario) {

        if (!(veterinario instanceof Veterinario)) {
            throw new PermissaoNegadaException(
                "Somente veterinários podem registrar consultas."
            );
        }

        repositorio.cadastrar(consulta);
    }

    public List<Consulta> listar() {
        return repositorio.listar();
    }

    public Consulta buscar(int id) {

        Consulta c = repositorio.buscar(id);

        if (c == null) {
            throw new IllegalArgumentException("Consulta não encontrada!");
        }

        return c;
    }

    public List<Consulta> buscarPorAnimal(int idAnimal) {
        return repositorio.buscarPorAnimal(idAnimal);
    }
}