package repository;

import java.util.ArrayList;
import java.util.List;
import model.Evento;

public class EventoRepository {

    private List<Evento> eventos = new ArrayList<>();

    public void cadastrar(Evento evento) {
        eventos.add(evento);
    }

    public List<Evento> listar() {
        return eventos;
    }

    public Evento buscar(int id) {

        for (Evento e : eventos) {
            if (e.getId() == id) {
                return e;
            }
        }

        return null;
    }

    public void excluir(int id) {
        eventos.removeIf(e -> e.getId() == id);
    }

    public List<Evento> listarAtivos() {

        List<Evento> ativos = new ArrayList<>();

        for (Evento e : eventos) {
            if (e.isAtivo()) {
                ativos.add(e);
            }
        }

        return ativos;
    }
}