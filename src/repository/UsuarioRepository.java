package repository;

import java.util.ArrayList;
import java.util.List;
import model.Usuario;

public class UsuarioRepository {

    private List<Usuario> usuarios = new ArrayList<>();

    public void cadastrar(Usuario usuario) {
        usuarios.add(usuario);
    }

    public List<Usuario> listar() {
        return usuarios;
    }

    public Usuario buscar(int id) {

        for (Usuario u : usuarios) {
            if (u.getId() == id) {
                return u;
            }
        }

        return null;
    }

    public void excluir(int id) {
        usuarios.removeIf(u -> u.getId() == id);
    }

    public Usuario buscarPorEmail(String email) {

        for (Usuario u : usuarios) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                return u;
            }
        }

        return null;
    }
}