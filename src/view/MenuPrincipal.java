
package view;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.BoxLayout;

import model.Usuario;
import model.Administrador;
import model.Veterinario;
import model.Voluntario;

import main.SistemaContexto;

public class MenuPrincipal {

    private SistemaContexto contexto;

    private MenuAdministrador administrador;
    private MenuVeterinario veterinario;
    private MenuVoluntario voluntario;

    public MenuPrincipal() {
        contexto = new SistemaContexto();

        administrador = new MenuAdministrador(contexto);
        veterinario = new MenuVeterinario(contexto);
        voluntario = new MenuVoluntario(contexto);
    }

    public void exibir() {

        boolean continuar = true;

        while (continuar) {

            JPanel painel = new JPanel();
            painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));

            JTextField campoEmail = new JTextField(20);
            JPasswordField campoSenha = new JPasswordField(20);

            painel.add(new JLabel("E-mail:"));
            painel.add(campoEmail);

            painel.add(new JLabel("Senha:"));
            painel.add(campoSenha);

            int opcao = JOptionPane.showConfirmDialog(
                null,
                painel,
                "Sistema de Abrigos e Resgates - Login",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
            );

            if (opcao != JOptionPane.OK_OPTION) {
                JOptionPane.showMessageDialog(
                    null,
                    "Sistema encerrado. Até logo!"
                );
                break;
            }

            String email = campoEmail.getText();
            String senha = new String(campoSenha.getPassword());

            try {

                Usuario usuario = contexto.usuarioService.autenticar(
                    email,
                    senha
                );

                JOptionPane.showMessageDialog(
                    null,
                    "Bem-vindo(a), " + usuario.getNome() + "!\n"
                    + "Perfil: " + usuario.getTipoUsuario(),
                    "Login realizado",
                    JOptionPane.INFORMATION_MESSAGE
                );

                if (usuario instanceof Administrador) {
                    administrador.exibir();

                } else if (usuario instanceof Veterinario) {
                    veterinario.exibir();

                } else if (usuario instanceof Voluntario) {
                    voluntario.exibir();

                } else {
                    JOptionPane.showMessageDialog(
                        null,
                        "Tipo de usuário não reconhecido!"
                    );
                }

            } catch (IllegalArgumentException e) {

                JOptionPane.showMessageDialog(
                    null,
                    e.getMessage(),
                    "Falha no login",
                    JOptionPane.ERROR_MESSAGE
                );
            }

        }
    }
}
