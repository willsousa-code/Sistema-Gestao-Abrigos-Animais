
package model;

public class Administrador extends Usuario {

    private String nivelAcesso;

    public Administrador(String nome, String email, String senha,
                         String telefone, String nivelAcesso) {

        super(nome, email, senha, telefone);
        setNivelAcesso(nivelAcesso);
    }

    public String getNivelAcesso() {
        return nivelAcesso;
    }

    public void setNivelAcesso(String nivelAcesso) {
        if (nivelAcesso == null || nivelAcesso.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "O nível de acesso não pode ficar vazio!"
            );
        }

        this.nivelAcesso = nivelAcesso.trim();
    }

    public void gerenciarUsuarios() {
        System.out.println("Acesso ao gerenciamento de usuários autorizado.");
    }

    public void gerenciarAnimais() {
        System.out.println("Acesso ao gerenciamento de animais autorizado.");
    }

    public void aprovarAdocao() {
        System.out.println("Administrador autorizado a aprovar adoções.");
    }

    @Override
    public String getTipoUsuario() {
        return "Administrador";
    }
}
