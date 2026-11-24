
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

    private final SistemaContexto contexto;

    private final MenuAdministrador administrador;
    private final MenuVeterinario veterinario;
    private final MenuVoluntario voluntario;

    public MenuPrincipal() {
        contexto = new SistemaContexto();

        administrador = new MenuAdministrador(contexto);
        veterinario = new MenuVeterinario(contexto);
        voluntario = new MenuVoluntario(contexto);
    }

    public void exibir() {

        boolean continuar = true;

        while (continuar) {

            String[] opcoes = {
                "Entrar",
                "Cadastrar-se",
                "Sobre o sistema",
                "Encerrar"
            };

            int opcao = JOptionPane.showOptionDialog(
                null,
                "Bem-vindo ao Sistema de Gerenciamento de Abrigos "
                + "e Resgates de Animais!\n\n"
                + "Escolha uma opção:",
                "Sistema de Abrigos e Resgates",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                opcoes,
                opcoes[0]
            );

            switch (opcao) {

                case 0:
                    realizarLogin();
                    break;

                case 1:
                    realizarCadastro();
                    break;

                case 2:
                    mostrarSobre();
                    break;

                case 3:
                case JOptionPane.CLOSED_OPTION:
                    continuar = false;

                    JOptionPane.showMessageDialog(
                        null,
                        "Obrigado por utilizar nosso sistema!\n"
                        + "Até logo!",
                        "Encerramento",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                    break;

                default:
                    continuar = false;
                    break;
            }
        }
    }

    private void realizarLogin() {

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
            "Login do Sistema",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE
        );

        if (opcao != JOptionPane.OK_OPTION) {
            return;
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
                    "Tipo de usuário não reconhecido!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
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

    private void realizarCadastro() {

        String[] tipos = {
            "Voluntário",
            "Veterinário"
        };

        String tipo = (String) JOptionPane.showInputDialog(
            null,
            "Selecione o tipo de cadastro:",
            "Cadastro de Usuário",
            JOptionPane.QUESTION_MESSAGE,
            null,
            tipos,
            tipos[0]
        );

        if (tipo == null) {
            return;
        }

        JTextField campoNome = new JTextField(20);
        JTextField campoEmail = new JTextField(20);
        JPasswordField campoSenha = new JPasswordField(20);
        JTextField campoTelefone = new JTextField(20);

        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));

        painel.add(new JLabel("Nome completo:"));
        painel.add(campoNome);

        painel.add(new JLabel("E-mail:"));
        painel.add(campoEmail);

        painel.add(new JLabel("Senha:"));
        painel.add(campoSenha);

        painel.add(new JLabel("Telefone:"));
        painel.add(campoTelefone);

        JTextField campoAdicional = new JTextField(20);

        if (tipo.equals("Voluntário")) {
            painel.add(new JLabel("Área de atuação:"));
        } else {
            painel.add(new JLabel("CRMV:"));
        }

        painel.add(campoAdicional);

        if (tipo.equals("Veterinário")) {
            painel.add(new JLabel("Especialidade:"));

            JTextField campoEspecialidade = new JTextField(20);
            painel.add(campoEspecialidade);

            int opcao = JOptionPane.showConfirmDialog(
                null,
                painel,
                "Cadastro de Veterinário",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
            );

            if (opcao != JOptionPane.OK_OPTION) {
                return;
            }

            try {

                Veterinario novoVeterinario = new Veterinario(
                    campoNome.getText(),
                    campoEmail.getText(),
                    new String(campoSenha.getPassword()),
                    campoTelefone.getText(),
                    campoAdicional.getText(),
                    campoEspecialidade.getText()
                );

                contexto.usuarioService.cadastrar(novoVeterinario);

                JOptionPane.showMessageDialog(
                    null,
                    "Cadastro realizado com sucesso!\n"
                    + "Agora você já pode entrar no sistema.",
                    "Cadastro concluído",
                    JOptionPane.INFORMATION_MESSAGE
                );

            } catch (IllegalArgumentException e) {

                JOptionPane.showMessageDialog(
                    null,
                    e.getMessage(),
                    "Erro no cadastro",
                    JOptionPane.ERROR_MESSAGE
                );
            }

        } else {

            int opcao = JOptionPane.showConfirmDialog(
                null,
                painel,
                "Cadastro de Voluntário",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
            );

            if (opcao != JOptionPane.OK_OPTION) {
                return;
            }

            try {

                Voluntario novoVoluntario = new Voluntario(
                    campoNome.getText(),
                    campoEmail.getText(),
                    new String(campoSenha.getPassword()),
                    campoTelefone.getText(),
                    campoAdicional.getText()
                );

                contexto.usuarioService.cadastrar(novoVoluntario);

                JOptionPane.showMessageDialog(
                    null,
                    "Cadastro realizado com sucesso!\n"
                    + "Agora você já pode entrar no sistema.",
                    "Cadastro concluído",
                    JOptionPane.INFORMATION_MESSAGE
                );

            } catch (IllegalArgumentException e) {

                JOptionPane.showMessageDialog(
                    null,
                    e.getMessage(),
                    "Erro no cadastro",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    private void mostrarSobre() {

        JOptionPane.showMessageDialog(
            null,
            "SISTEMA DE GERENCIAMENTO DE ABRIGOS "
            + "E RESGATES DE ANIMAIS\n\n"
            + "O sistema tem como objetivo facilitar o gerenciamento "
            + "de animais resgatados, adoções, consultas veterinárias, "
            + "vacinas, atividades e voluntários.\n\n"
            + "Perfis disponíveis:\n"
            + "- Administrador\n"
            + "- Veterinário\n"
            + "- Voluntário\n\n"
            + "Desenvolvido como projeto acadêmico de Programação "
            + "Orientada a Objetos.",
            "Sobre o Sistema",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
}
