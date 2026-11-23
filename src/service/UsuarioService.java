
package service;

import java.util.List;

import model.Usuario;
import model.Administrador;
import repository.UsuarioRepository;

public class UsuarioService {

    private UsuarioRepository repositorio;

    public UsuarioService(UsuarioRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void cadastrar(Usuario usuario) {

        if (usuario == null) {
            throw new IllegalArgumentException("Usuário inválido!");
        }

        if (usuario.getEmail() == null ||
            usuario.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("E-mail inválido!");
        }

        if (repositorio.buscarPorEmail(usuario.getEmail()) != null) {
            throw new IllegalArgumentException(
                "Já existe um usuário cadastrado com esse e-mail!"
            );
        }

        repositorio.cadastrar(usuario);
    }

    public List<Usuario> listar() {
        return repositorio.listar();
    }

    public Usuario buscar(int id) {

        Usuario usuario = repositorio.buscar(id);

        if (usuario == null) {
            throw new IllegalArgumentException(
                "Usuário não encontrado!"
            );
        }

        return usuario;
    }

    public void excluir(int id) {

        Usuario usuario = buscar(id);

        if (usuario instanceof Administrador) {
            throw new IllegalArgumentException(
                "Não é permitido excluir um administrador!"
            );
        }

        usuario.desativar();
    }

    public Usuario autenticar(String email, String senha) {

        if (email == null || senha == null) {
            throw new IllegalArgumentException(
                "E-mail e senha são obrigatórios!"
            );
        }

        Usuario usuario = repositorio.buscarPorEmail(email);

        if (usuario == null) {
            throw new IllegalArgumentException(
                "Usuário não encontrado!"
            );
        }

        if (!usuario.isAtivo()) {
            throw new IllegalArgumentException(
                "Este usuário está desativado!"
            );
        }

        if (!usuario.verificarSenha(senha)) {
            throw new IllegalArgumentException(
                "Senha incorreta!"
            );
        }

        return usuario;
    }
}
