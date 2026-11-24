
package view;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import main.SistemaContexto;
import model.Animal;
import model.Usuario;
import model.Veterinario;

public class MenuVeterinario {

    private SistemaContexto contexto;

    private ConsultaView consultaView;
    private VacinaView vacinaView;

    public MenuVeterinario(SistemaContexto contexto) {
        this.contexto = contexto;

        consultaView = new ConsultaView(
            contexto.consultaService,
            contexto.animalService
        );

        vacinaView = new VacinaView(
            contexto.vacinaService,
            contexto.animalService
        );
    }

    public void exibir() {

        Veterinario veterinario;

        try {
            veterinario = obterVeterinario();
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(
                null,
                "Erro: " + e.getMessage(),
                "Acesso negado",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        boolean continuar = true;

        while (continuar) {

            String[] opcoes = {
                "Consultar animais",
                "Registrar consulta",
                "Registrar vacina",
                "Consultar histórico médico",
                "Atualizar informações de saúde",
                "Voltar"
            };

            int opcao = JOptionPane.showOptionDialog(
                null,
                "===== MENU VETERINÁRIO =====\n\n"
                    + "Bem-vindo(a), " + veterinario.getNome() + "!",
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
                        listarAnimais();
                        break;

                    case 1:
                        consultaView.cadastrar(veterinario);
                        break;

                    case 2:
                        vacinaView.cadastrar(veterinario);
                        break;

                    case 3:
                        consultarHistorico();
                        break;

                    case 4:
                        atualizarSaude(veterinario);
                        break;

                    case 5:
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

    private Veterinario obterVeterinario() {

        String entrada = JOptionPane.showInputDialog(
            null,
            "Digite o ID do veterinário:",
            "Identificação do veterinário",
            JOptionPane.QUESTION_MESSAGE
        );

        if (entrada == null) {
            throw new IllegalArgumentException(
                "Acesso cancelado pelo usuário."
            );
        }

        int id;

        try {
            id = Integer.parseInt(entrada);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                "Digite um ID numérico válido!"
            );
        }

        Usuario usuario = contexto.usuarioService.buscar(id);

        if (!(usuario instanceof Veterinario)) {
            throw new IllegalArgumentException(
                "O usuário informado não é um veterinário!"
            );
        }

        if (!usuario.isAtivo()) {
            throw new IllegalArgumentException(
                "O veterinário está desativado!"
            );
        }

        return (Veterinario) usuario;
    }

    private void listarAnimais() {

        StringBuilder texto = new StringBuilder();

        texto.append("===== ANIMAIS CADASTRADOS =====\n\n");

        for (Animal animal : contexto.animalService.listarAnimais()) {

            texto.append("ID: ")
                .append(animal.getId())
                .append("\n");

            texto.append("Nome: ")
                .append(animal.getNome())
                .append("\n");

            texto.append("Espécie: ")
                .append(animal.getEspecie())
                .append("\n");

            texto.append("Saúde: ")
                .append(animal.getEstadoSaude())
                .append("\n");

            texto.append("-----------------------------\n");
        }

        if (contexto.animalService.listarAnimais().isEmpty()) {
            texto.append("Nenhum animal cadastrado.");
        }

        JTextArea area = new JTextArea(texto.toString(), 18, 40);
        area.setEditable(false);

        JOptionPane.showMessageDialog(
            null,
            new JScrollPane(area),
            "Animais cadastrados",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void consultarHistorico() {

        String[] opcoes = {
            "Consultar consultas",
            "Consultar vacinas",
            "Voltar"
        };

        int opcao = JOptionPane.showOptionDialog(
            null,
            "Escolha o histórico que deseja consultar:",
            "Histórico médico",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            opcoes,
            opcoes[0]
        );

        switch (opcao) {

            case 0:
                consultaView.listar();
                break;

            case 1:
                vacinaView.listar();
                break;

            default:
                break;
        }
    }

    private void atualizarSaude(Veterinario veterinario) {

        String entradaId = JOptionPane.showInputDialog(
            null,
            "Digite o ID do animal:",
            "Atualizar saúde",
            JOptionPane.QUESTION_MESSAGE
        );

        if (entradaId == null) {
            return;
        }

        int id;

        try {
            id = Integer.parseInt(entradaId);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                null,
                "Digite um ID numérico válido!",
                "Entrada inválida",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        String estado = JOptionPane.showInputDialog(
            null,
            "Informe o novo estado de saúde:",
            "Atualizar saúde",
            JOptionPane.QUESTION_MESSAGE
        );

        if (estado == null) {
            return;
        }

        contexto.animalService.atualizarSaude(
            id,
            estado,
            veterinario
        );

        JOptionPane.showMessageDialog(
            null,
            "Informações de saúde atualizadas com sucesso!",
            "Operação concluída",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
}
