
package service;

import java.util.List;

import model.Evento;
import repository.EventoRepository;

public class EventoService {

    private EventoRepository repositorio;

    public EventoService(EventoRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void cadastrar(Evento evento) {

        if (evento == null) {
            throw new IllegalArgumentException(
                "Evento inválido!"
            );
        }

        if (evento.getNome() == null ||
            evento.getNome().trim().isEmpty()) {

            throw new IllegalArgumentException(
                "O nome do evento é obrigatório!"
            );
        }

        repositorio.cadastrar(evento);
    }

    public List<Evento> listar() {
        return repositorio.listar();
    }

    public Evento buscar(int id) {

        Evento evento = repositorio.buscar(id);

        if (evento == null) {
            throw new IllegalArgumentException(
                "Evento não encontrado!"
            );
        }

        return evento;
    }

    public void excluir(int id) {

        Evento evento = buscar(id);

        evento.desativar();
    }

    public List<Evento> listarAtivos() {
        return repositorio.listarAtivos();
    }

    public void desativar(int id) {

        Evento evento = buscar(id);

        evento.desativar();
    }
}
