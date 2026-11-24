
package view;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;

import model.Usuario;
import model.Voluntario;
import model.Veterinario;
import model.Administrador;
import service.UsuarioService;

public class UsuarioView {

    private UsuarioService usuarioService;

    public UsuarioView(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public void exibir() {

        boolean continuar = true;

        while (continuar) {

            String[] opcoes = {
                "Cadastrar voluntário",
                "Cadastrar veterinário",
                "Cadastrar administrador",
                "Listar usuários",
                "Buscar usuário",
                "Desativar usuário",
                "Voltar"
            };

            int opcao = JOptionPane.showOptionDialog(
                null,
                "===== GERENCIAMENTO DE USUÁRIOS =====",
                "Sistema de Abrigos e Resgates",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                opcoes,
                opcoes[0]
            );

            try {

                switch (opcao) {

                    case 0:
                        cadastrarVoluntario();
                        break;

                    case 1:
                        cadastrarVeterinario();
                        break;

                    case 2:
                        cadastrarAdministrador();
                        break;

                    case 3:
                        listar();
                        break;

                    case 4:
                        buscar();
                        break;

                    case 5:
                        desativar();
                        break;

                    case 6:
                    case JOptionPane.CLOSED_OPTION:
                        continuar = false;
                        break;

                    default:
                        break;
                }

            } catch (RuntimeException e) {

                JOptionPane.showMessageDialog(
                    null,
                    "Erro: " + e.getMessage(),
                    "Erro na operação",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    private void cadastrarVoluntario() {

        String[] dados = solicitarDados(
            "Novo voluntário",
            new String[] {
                "Nome:",
                "Email:",
                "Senha:",
                "Telefone:",
                "Área de atuação:"
            }
        );

        if (dados == null) return;

        Voluntario voluntario = new Voluntario(
            dados[0],
            dados[1],
            dados[2],
            dados[3],
            dados[4]
        );

        usuarioService.cadastrar(voluntario);

        JOptionPane.showMessageDialog(
            null,
            "Voluntário cadastrado com sucesso!",
            "Cadastro concluído",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void cadastrarVeterinario() {

        String[] dados = solicitarDados(
            "Novo veterinário",
            new String[] {
                "Nome:",
                "Email:",
                "Senha:",
                "Telefone:",
                "CRMV:",
                "Especialidade:"
            }
        );

        if (dados == null) return;

        Veterinario veterinario = new Veterinario(
            dados[0],
            dados[1],
            dados[2],
            dados[3],
            dados[4],
            dados[5]
        );

        usuarioService.cadastrar(veterinario);

        JOptionPane.showMessageDialog(
            null,
            "Veterinário cadastrado com sucesso!",
            "Cadastro concluído",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void cadastrarAdministrador() {

        String[] dados = solicitarDados(
            "Novo administrador",
            new String[] {
                "Nome:",
                "Email:",
                "Senha:",
                "Telefone:",
                "Nível de acesso:"
            }
        );

        if (dados == null) return;

        Administrador administrador = new Administrador(
            dados[0],
            dados[1],
            dados[2],
            dados[3],
            dados[4]
        );

        usuarioService.cadastrar(administrador);

        JOptionPane.showMessageDialog(
            null,
            "Administrador cadastrado com sucesso!",
            "Cadastro concluído",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    private String[] solicitarDados(String titulo, String[] rotulos) {

        JPanel painel = new JPanel(new GridLayout(rotulos.length, 2, 8, 8));

        JTextField[] campos = new JTextField[rotulos.length];
        JPasswordField campoSenha = null;

        for (int i = 0; i < rotulos.length; i++) {

            painel.add(new javax.swing.JLabel(rotulos[i]));

            if (rotulos[i].equals("Senha:")) {

                campoSenha = new JPasswordField(20);
                painel.add(campoSenha);

            } else {

                campos[i] = new JTextField(20);
                painel.add(campos[i]);
            }
        }

        int resultado = JOptionPane.showConfirmDialog(
            null,
            painel,
            titulo,
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE
        );

        if (resultado != JOptionPane.OK_OPTION) {
            return null;
        }

        String[] dados = new String[rotulos.length];

        for (int i = 0; i < rotulos.length; i++) {

            if (rotulos[i].equals("Senha:")) {
                dados[i] = new String(campoSenha.getPassword());
            } else {
                dados[i] = campos[i].getText();
            }
        }

        return dados;
    }

    private void listar() {

        List<Usuario> usuarios = usuarioService.listar();

        StringBuilder texto = new StringBuilder();

        texto.append("===== USUÁRIOS CADASTRADOS =====\n\n");

        if (usuarios.isEmpty()) {

            texto.append("Nenhum usuário cadastrado.");

        } else {

            for (Usuario usuario : usuarios) {

                texto.append("ID: ")
                    .append(usuario.getId())
                    .append("\n");

                texto.append("Nome: ")
                    .append(usuario.getNome())
                    .append("\n");

                texto.append("Email: ")
                    .append(usuario.getEmail())
                    .append("\n");

                texto.append("Tipo: ")
                    .append(usuario.getTipoUsuario())
                    .append("\n");

                texto.append("Ativo: ")
                    .append(usuario.isAtivo() ? "Sim" : "Não")
                    .append("\n");

                texto.append("-----------------------------\n");
            }
        }

        mostrarTexto(texto.toString(), "Usuários cadastrados");
    }

    private void buscar() {

        try {

            String entradaId = JOptionPane.showInputDialog(
                null,
                "Digite o ID do usuário:"
            );

            if (entradaId == null) return;

            int id = Integer.parseInt(entradaId);

            Usuario usuario = usuarioService.buscar(id);

            String texto =
                "ID: " + usuario.getId() + "\n"
                + "Nome: " + usuario.getNome() + "\n"
                + "Email: " + usuario.getEmail() + "\n"
                + "Tipo: " + usuario.getTipoUsuario() + "\n"
                + "Ativo: " + (usuario.isAtivo() ? "Sim" : "Não");

            JOptionPane.showMessageDialog(
                null,
                texto,
                "Usuário encontrado",
                JOptionPane.INFORMATION_MESSAGE
            );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                null,
                "Digite um ID numérico válido!",
                "Entrada inválida",
                JOptionPane.WARNING_MESSAGE
            );

        } catch (RuntimeException e) {

            JOptionPane.showMessageDialog(
                null,
                "Erro: " + e.getMessage(),
                "Erro na busca",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void desativar() {

        try {

            String entradaId = JOptionPane.showInputDialog(
                null,
                "Digite o ID do usuário que deseja desativar:"
            );

            if (entradaId == null) return;

            int id = Integer.parseInt(entradaId);

            int confirmacao = JOptionPane.showConfirmDialog(
                null,
                "Deseja realmente desativar este usuário?",
                "Confirmar desativação",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
            );

            if (confirmacao != JOptionPane.YES_OPTION) {
                return;
            }

            usuarioService.excluir(id);

            JOptionPane.showMessageDialog(
                null,
                "Usuário desativado com sucesso!",
                "Operação concluída",
                JOptionPane.INFORMATION_MESSAGE
            );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                null,
                "Digite um ID numérico válido!",
                "Entrada inválida",
                JOptionPane.WARNING_MESSAGE
            );

        } catch (RuntimeException e) {

            JOptionPane.showMessageDialog(
                null,
                "Erro: " + e.getMessage(),
                "Erro na desativação",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void mostrarTexto(String conteudo, String titulo) {

        JTextArea area = new JTextArea(conteudo, 18, 40);
        area.setEditable(false);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);

        JOptionPane.showMessageDialog(
            null,
            new JScrollPane(area),
            titulo,
            JOptionPane.INFORMATION_MESSAGE
        );
    }
}
