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
        this.id = contadorId++;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.ativo = true;
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
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public boolean verificarSenha(String senhaInformada) {
        return this.senha.equals(senhaInformada);
    }

    public void ativar() {
        this.ativo = true;
    }

    public void desativar() {
        this.ativo = false;
    }

    public abstract String getTipoUsuario();
}