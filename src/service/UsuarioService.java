package service;

import model.Usuario;
import repository.UsuarioRepository;
import exception.PermissaoNegadaException;

import java.util.List;

public class UsuarioService {

    private UsuarioRepository repositorio;

    public UsuarioService(UsuarioRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void cadastrar(Usuario usuario) {

        Usuario existente = repositorio.buscarPorEmail(usuario.getEmail());

        if (existente != null) {
            throw new IllegalArgumentException("Esse email já está cadastrado!");
        }

        repositorio.cadastrar(usuario);
    }

    public List<Usuario> listar() {
        return repositorio.listar();
    }

    public Usuario buscar(int id) {

        Usuario usuario = repositorio.buscar(id);

        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não encontrado!");
        }

        return usuario;
    }

    public void excluir(int id) {
        Usuario usuario = buscar(id);

        if (usuario instanceof model.Administrador) {
            throw new PermissaoNegadaException("Não é permitido excluir um administrador.");
        }

        usuario.desativar();
    }

    public Usuario autenticar(String email, String senha) {

        Usuario usuario = repositorio.buscarPorEmail(email);

        if (usuario == null || !usuario.verificarSenha(senha)) {
            throw new PermissaoNegadaException("Email ou senha incorretos!");
        }

        if (!usuario.isAtivo()) {
            throw new PermissaoNegadaException("Usuário desativado!");
        }

        return usuario;
    }
}