
package repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Evento;

public class EventoRepository {

    private List<Evento> eventos = new ArrayList<>();

    public void cadastrar(Evento evento) {

        if (evento == null) {
            throw new IllegalArgumentException("Evento inválido!");
        }

        if (buscar(evento.getId()) != null) {
            throw new IllegalArgumentException("Evento já cadastrado!");
        }

        eventos.add(evento);
    }

    public List<Evento> listar() {
        return Collections.unmodifiableList(
            new ArrayList<>(eventos)
        );
    }

    public Evento buscar(int id) {

        for (Evento evento : eventos) {

            if (evento.getId() == id) {
                return evento;
            }
        }

        return null;
    }

    public void excluir(int id) {

        Evento evento = buscar(id);

        if (evento == null) {
            throw new IllegalArgumentException("Evento não encontrado!");
        }

        eventos.remove(evento);
    }

    public List<Evento> listarAtivos() {

        List<Evento> ativos = new ArrayList<>();

        for (Evento evento : eventos) {

            if (evento.isAtivo()) {
                ativos.add(evento);
            }
        }

        return Collections.unmodifiableList(ativos);
    }
}
