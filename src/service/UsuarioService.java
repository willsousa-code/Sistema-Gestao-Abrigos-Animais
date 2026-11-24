
package service;

import java.util.List;

import model.Usuario;
import model.Administrador;
import repository.UsuarioRepository;

public class UsuarioService {

    private final UsuarioRepository repositorio;

    public UsuarioService(UsuarioRepository repositorio) {
        if (repositorio == null) {
            throw new IllegalArgumentException(
                "O repositório de usuários não pode ser nulo!"
            );
        }

        this.repositorio = repositorio;
    }

    public void cadastrar(Usuario usuario) {

        if (usuario == null) {
            throw new IllegalArgumentException("Usuário inválido!");
        }

        if (usuario.getNome() == null ||
                usuario.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome inválido!");
        }

        if (usuario.getEmail() == null ||
                usuario.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("E-mail inválido!");
        }

        String email = usuario.getEmail().trim();

        if (repositorio.buscarPorEmail(email) != null) {
            throw new IllegalArgumentException(
                "Já existe um usuário cadastrado com esse e-mail!"
            );
        }

        usuario.setEmail(email);
        repositorio.cadastrar(usuario);
    }

    public List<Usuario> listar() {
        return repositorio.listar();
    }

    public Usuario buscar(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido!");
        }

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

        if (email == null || email.trim().isEmpty() ||
                senha == null || senha.isEmpty()) {
            throw new IllegalArgumentException(
                "E-mail e senha são obrigatórios!"
            );
        }

        Usuario usuario = repositorio.buscarPorEmail(email.trim());

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
