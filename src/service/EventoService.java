package service;

import model.Evento;
import repository.EventoRepository;

import java.util.List;

public class EventoService {

    private EventoRepository repositorio;

    public EventoService(EventoRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void cadastrar(Evento evento) {
        repositorio.cadastrar(evento);
    }

    public List<Evento> listar() {
        return repositorio.listar();
    }

    public Evento buscar(int id) {

        Evento evento = repositorio.buscar(id);

        if (evento == null) {
            throw new IllegalArgumentException("Evento não encontrado!");
        }

        return evento;
    }

    public void excluir(int id) {
        buscar(id);
        repositorio.excluir(id);
    }

    public List<Evento> listarAtivos() {
        return repositorio.listarAtivos();
    }

    public void desativar(int id) {
        Evento evento = buscar(id);
        evento.desativar();
    }
}