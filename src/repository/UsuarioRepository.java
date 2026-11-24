
package repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Usuario;

public class UsuarioRepository {

    private final List<Usuario> usuarios = new ArrayList<>();

    public void cadastrar(Usuario usuario) {

        if (usuario == null) {
            throw new IllegalArgumentException("Usuário inválido!");
        }

        if (buscar(usuario.getId()) != null) {
            throw new IllegalArgumentException(
                "Usuário já cadastrado!"
            );
        }

        if (usuario.getEmail() == null ||
                usuario.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException(
                "E-mail inválido!"
            );
        }

        if (buscarPorEmail(usuario.getEmail()) != null) {
            throw new IllegalArgumentException(
                "Já existe um usuário cadastrado com esse e-mail!"
            );
        }

        usuarios.add(usuario);
    }

    public List<Usuario> listar() {
        return Collections.unmodifiableList(
            new ArrayList<>(usuarios)
        );
    }

    public Usuario buscar(int id) {

        if (id <= 0) {
            return null;
        }

        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                return usuario;
            }
        }

        return null;
    }

    public void excluir(int id) {

        Usuario usuario = buscar(id);

        if (usuario == null) {
            throw new IllegalArgumentException(
                "Usuário não encontrado!"
            );
        }

        usuarios.remove(usuario);
    }

    public Usuario buscarPorEmail(String email) {

        if (email == null || email.trim().isEmpty()) {
            return null;
        }

        String emailPesquisa = email.trim();

        for (Usuario usuario : usuarios) {

            if (usuario.getEmail() != null &&
                    usuario.getEmail().equalsIgnoreCase(emailPesquisa)) {
                return usuario;
            }
        }

        return null;
    }
}
