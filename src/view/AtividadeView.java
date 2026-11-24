
package view;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.Atividade;
import service.AtividadeService;

public class AtividadeView {

    private AtividadeService atividadeService;

    public AtividadeView(AtividadeService atividadeService) {
        this.atividadeService = atividadeService;
    }

    public void cadastrar() {

        try {

            String nome = JOptionPane.showInputDialog(
                null,
                "Nome da atividade:",
                "Cadastrar atividade",
                JOptionPane.QUESTION_MESSAGE
            );

            if (nome == null) return;

            String descricao = JOptionPane.showInputDialog(
                null,
                "Descrição da atividade:"
            );

            if (descricao == null) return;

            String entradaData = JOptionPane.showInputDialog(
                null,
                "Data (AAAA-MM-DD):"
            );

            if (entradaData == null) return;

            LocalDate data = LocalDate.parse(entradaData);

            String horario = JOptionPane.showInputDialog(
                null,
                "Horário:"
            );

            if (horario == null) return;

            String local = JOptionPane.showInputDialog(
                null,
                "Local:"
            );

            if (local == null) return;

            String entradaVagas = JOptionPane.showInputDialog(
                null,
                "Quantidade de vagas:"
            );

            if (entradaVagas == null) return;

            int vagas = Integer.parseInt(entradaVagas);

            Atividade atividade = new Atividade(
                nome,
                descricao,
                data,
                horario,
                local,
                vagas
            );

            atividadeService.cadastrar(atividade);

            JOptionPane.showMessageDialog(
                null,
                "Atividade cadastrada com sucesso!",
                "Cadastro concluído",
                JOptionPane.INFORMATION_MESSAGE
            );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                null,
                "Quantidade de vagas inválida! Digite um número inteiro.",
                "Entrada inválida",
                JOptionPane.WARNING_MESSAGE
            );

        } catch (DateTimeParseException e) {

            JOptionPane.showMessageDialog(
                null,
                "Data inválida! Utilize o formato AAAA-MM-DD.",
                "Erro de data",
                JOptionPane.WARNING_MESSAGE
            );

        } catch (RuntimeException e) {

            JOptionPane.showMessageDialog(
                null,
                "Erro: " + e.getMessage(),
                "Erro no cadastro",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public void listar() {

        List<Atividade> atividades = atividadeService.listar();

        StringBuilder texto = new StringBuilder();

        texto.append("===== ATIVIDADES CADASTRADAS =====\n\n");

        if (atividades.isEmpty()) {

            texto.append("Nenhuma atividade cadastrada.");

        } else {

            for (Atividade atividade : atividades) {

                texto.append("ID: ")
                    .append(atividade.getId())
                    .append("\n");

                texto.append("Nome: ")
                    .append(atividade.getNome())
                    .append("\n");

                texto.append("Descrição: ")
                    .append(atividade.getDescricao())
                    .append("\n");

                texto.append("Data: ")
                    .append(atividade.getData())
                    .append("\n");

                texto.append("Horário: ")
                    .append(atividade.getHorario())
                    .append("\n");

                texto.append("Local: ")
                    .append(atividade.getLocal())
                    .append("\n");

                texto.append("Vagas: ")
                    .append(atividade.getVagas())
                    .append("\n");

                texto.append("Participantes: ")
                    .append(atividade.getVagasOcupadas())
                    .append("\n");

                texto.append("Vagas restantes: ")
                    .append(
                        atividade.getVagas() - atividade.getVagasOcupadas()
                    )
                    .append("\n");

                texto.append("------------------------------\n");
            }
        }

        JTextArea area = new JTextArea(texto.toString(), 18, 45);
        area.setEditable(false);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);

        JOptionPane.showMessageDialog(
            null,
            new JScrollPane(area),
            "Atividades cadastradas",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    public void inscrever() {

        try {

            String entradaId = JOptionPane.showInputDialog(
                null,
                "Digite o ID da atividade:",
                "Inscrever participante",
                JOptionPane.QUESTION_MESSAGE
            );

            if (entradaId == null) return;

            int id = Integer.parseInt(entradaId);

            atividadeService.inscreverParticipante(id);

            JOptionPane.showMessageDialog(
                null,
                "Inscrição realizada com sucesso!",
                "Inscrição concluída",
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
                "Erro na inscrição",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public void cancelarInscricao() {

        try {

            String entradaId = JOptionPane.showInputDialog(
                null,
                "Digite o ID da atividade:",
                "Cancelar inscrição",
                JOptionPane.QUESTION_MESSAGE
            );

            if (entradaId == null) return;

            int id = Integer.parseInt(entradaId);

            int confirmacao = JOptionPane.showConfirmDialog(
                null,
                "Deseja realmente cancelar a inscrição?",
                "Confirmar cancelamento",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
            );

            if (confirmacao != JOptionPane.YES_OPTION) return;

            atividadeService.cancelarInscricao(id);

            JOptionPane.showMessageDialog(
                null,
                "Inscrição cancelada com sucesso!",
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
                "Erro no cancelamento",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
