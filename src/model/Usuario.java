
package model;

public abstract class Usuario {

    private final int id;
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private boolean ativo;

    private static int contadorId = 1;

    public Usuario(String nome, String email, String senha, String telefone) {
        validarDados(nome, "O nome não pode ficar vazio!");
        validarDados(email, "O e-mail não pode ficar vazio!");
        validarDados(senha, "A senha não pode ficar vazia!");
        validarDados(telefone, "O telefone não pode ficar vazio!");

        this.id = contadorId++;
        this.nome = nome.trim();
        this.email = email.trim();
        this.senha = senha;
        this.telefone = telefone.trim();
        this.ativo = true;
    }

    private void validarDados(String valor, String mensagem) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(mensagem);
        }
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setNome(String nome) {
        validarDados(nome, "O nome não pode ficar vazio!");
        this.nome = nome.trim();
    }

    public void setEmail(String email) {
        validarDados(email, "O e-mail não pode ficar vazio!");
        this.email = email.trim();
    }

    public void setSenha(String senha) {
        validarDados(senha, "A senha não pode ficar vazia!");
        this.senha = senha;
    }

    public void setTelefone(String telefone) {
        validarDados(telefone, "O telefone não pode ficar vazio!");
        this.telefone = telefone.trim();
    }

    public boolean verificarSenha(String senhaInformada) {
        return senhaInformada != null && this.senha.equals(senhaInformada);
    }

    public void alterarSenha(String senhaAtual, String novaSenha) {
        if (!verificarSenha(senhaAtual)) {
            throw new IllegalArgumentException("Senha atual incorreta!");
        }

        validarDados(novaSenha, "A nova senha não pode ficar vazia!");
        this.senha = novaSenha;
    }

    public void ativar() {
        this.ativo = true;
    }

    public void desativar() {
        this.ativo = false;
    }

    public abstract String getTipoUsuario();
}
