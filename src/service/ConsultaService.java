
package service;

import java.util.List;

import model.Consulta;
import model.Usuario;
import model.Veterinario;

import repository.ConsultaRepository;

import exception.PermissaoNegadaException;

public class ConsultaService {

    private ConsultaRepository repositorio;

    public ConsultaService(ConsultaRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void cadastrarConsulta(Consulta consulta, Usuario usuario) {

        if (consulta == null) {
            throw new IllegalArgumentException(
                "Consulta inválida!"
            );
        }

        if (!(usuario instanceof Veterinario) || !usuario.isAtivo()) {
            throw new PermissaoNegadaException(
                "Somente veterinários ativos podem cadastrar consultas!"
            );
        }

        if (consulta.getAnimal() == null ||
            consulta.getVeterinario() == null) {

            throw new IllegalArgumentException(
                "A consulta precisa ter um animal e um veterinário!"
            );
        }

        if (consulta.getVeterinario().getId() != usuario.getId()) {
            throw new PermissaoNegadaException(
                "O veterinário informado não corresponde ao usuário autorizado!"
            );
        }

        repositorio.cadastrar(consulta);
    }

    public List<Consulta> listar() {
        return repositorio.listar();
    }

    public Consulta buscar(int id) {

        Consulta consulta = repositorio.buscar(id);

        if (consulta == null) {
            throw new IllegalArgumentException(
                "Consulta não encontrada!"
            );
        }

        return consulta;
    }

    public List<Consulta> buscarPorAnimal(int idAnimal) {
        return repositorio.buscarPorAnimal(idAnimal);
    }
}
