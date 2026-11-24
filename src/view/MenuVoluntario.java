
package view;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import main.SistemaContexto;
import model.Animal;
import model.Usuario;
import model.Voluntario;

public class MenuVoluntario {

    private SistemaContexto contexto;

    private EventoView eventoView;
    private AtividadeView atividadeView;
    private CompatibilidadeView compatibilidadeView;
    private AdocaoView adocaoView;

    public MenuVoluntario(SistemaContexto contexto) {
        this.contexto = contexto;

        eventoView = new EventoView(contexto.eventoService);
        atividadeView = new AtividadeView(contexto.atividadeService);

        compatibilidadeView = new CompatibilidadeView(
            contexto.animalService,
            contexto.usuarioService,
            contexto.compatibilidadeService
        );

        adocaoView = new AdocaoView(
            contexto.adocaoService,
            contexto.animalService,
            contexto.usuarioService
        );
    }

    public void exibir() {

        Voluntario voluntario;

        try {
            voluntario = obterVoluntario();
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
                "Ver animais disponíveis",
                "Consultar eventos",
                "Ver atividades",
                "Participar de atividade",
                "Consultar compatibilidade",
                "Solicitar adoção",
                "Consultar notificações",
                "Voltar"
            };

            int opcao = JOptionPane.showOptionDialog(
                null,
                "===== MENU VOLUNTÁRIO =====\n\n"
                    + "Bem-vindo(a), " + voluntario.getNome() + "!",
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
                        listarAnimaisDisponiveis();
                        break;

                    case 1:
                        eventoView.listar();
                        break;

                    case 2:
                        atividadeView.listar();
                        break;

                    case 3:
                        atividadeView.inscrever();
                        break;

                    case 4:
                        compatibilidadeView.consultar();
                        break;

                    case 5:
                        adocaoView.solicitar();
                        break;

                    case 6:
                        consultarNotificacoes(voluntario);
                        break;

                    case 7:
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

    private Voluntario obterVoluntario() {

        String entrada = JOptionPane.showInputDialog(
            null,
            "Digite o ID do voluntário:",
            "Identificação do voluntário",
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

        if (!(usuario instanceof Voluntario)) {
            throw new IllegalArgumentException(
                "O usuário informado não é um voluntário!"
            );
        }

        if (!usuario.isAtivo()) {
            throw new IllegalArgumentException(
                "O voluntário está desativado!"
            );
        }

        return (Voluntario) usuario;
    }

    private void listarAnimaisDisponiveis() {

        StringBuilder texto = new StringBuilder();

        texto.append("===== ANIMAIS DISPONÍVEIS =====\n\n");

        boolean encontrou = false;

        for (Animal animal : contexto.animalService.listarAnimais()) {

            if (animal.estaDisponivelParaAdocao()) {

                texto.append("ID: ")
                    .append(animal.getId())
                    .append("\n");

                texto.append("Nome: ")
                    .append(animal.getNome())
                    .append("\n");

                texto.append("Espécie: ")
                    .append(animal.getEspecie())
                    .append("\n");

                texto.append("Raça: ")
                    .append(animal.getRaca())
                    .append("\n");

                texto.append("Estado de saúde: ")
                    .append(animal.getEstadoSaude())
                    .append("\n");

                texto.append("-----------------------------\n");

                encontrou = true;
            }
        }

        if (!encontrou) {
            texto.append(
                "Não existem animais disponíveis no momento."
            );
        }

        JTextArea area = new JTextArea(texto.toString(), 18, 40);
        area.setEditable(false);

        JOptionPane.showMessageDialog(
            null,
            new JScrollPane(area),
            "Animais disponíveis",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void consultarNotificacoes(Voluntario voluntario) {

        StringBuilder texto = new StringBuilder();

        texto.append("===== NOTIFICAÇÕES =====\n\n");

        contexto.notificacaoService
            .listarPorDestinatario(voluntario)
            .forEach(notificacao -> {

                texto.append("ID: ")
                    .append(notificacao.getId())
                    .append("\n");

                texto.append("Mensagem: ")
                    .append(notificacao.getMensagem())
                    .append("\n");

                texto.append("Data: ")
                    .append(notificacao.getDataEnvio())
                    .append("\n");

                texto.append("Lida: ")
                    .append(notificacao.isLida() ? "Sim" : "Não")
                    .append("\n");

                texto.append("-----------------------------\n");
            });

        if (texto.toString().equals("===== NOTIFICAÇÕES =====\n\n")) {
            texto.append("Você não possui notificações.");
        }

        JTextArea area = new JTextArea(texto.toString(), 18, 40);
        area.setEditable(false);

        JOptionPane.showMessageDialog(
            null,
            new JScrollPane(area),
            "Minhas notificações",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
}
