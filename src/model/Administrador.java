package model;

public class Administrador extends Usuario {

    private String nivelAcesso;

    public Administrador(String nome, String email, String senha,
                         String telefone, String nivelAcesso) {

        super(nome, email, senha, telefone);
        this.nivelAcesso = nivelAcesso;
    }

    public String getNivelAcesso() {
        return nivelAcesso;
    }

    public void setNivelAcesso(String nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }

    public void gerenciarUsuarios() {
        System.out.println("Gerenciando usuários...");
    }

    public void gerenciarAnimais() {
        System.out.println("Gerenciando animais...");
    }

    public void aprovarAdocao() {
        System.out.println("Adoção aprovada pelo administrador.");
    }

    @Override
    public String getTipoUsuario() {
        return "Administrador";
    }
}