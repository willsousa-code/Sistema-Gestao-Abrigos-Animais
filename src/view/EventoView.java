
package view;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.Evento;
import service.EventoService;

public class EventoView {

    private EventoService eventoService;

    public EventoView(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    public void cadastrar() {

        try {

            String nome = JOptionPane.showInputDialog(
                null,
                "Nome do evento:",
                "Cadastrar evento",
                JOptionPane.QUESTION_MESSAGE
            );

            if (nome == null) return;

            String descricao = JOptionPane.showInputDialog(
                null,
                "Descrição do evento:"
            );

            if (descricao == null) return;

            String entradaData = JOptionPane.showInputDialog(
                null,
                "Data do evento (AAAA-MM-DD):"
            );

            if (entradaData == null) return;

            LocalDate data = LocalDate.parse(entradaData);

            String local = JOptionPane.showInputDialog(
                null,
                "Local do evento:"
            );

            if (local == null) return;

            String tipo = JOptionPane.showInputDialog(
                null,
                "Tipo do evento:"
            );

            if (tipo == null) return;

            Evento evento = new Evento(
                nome,
                descricao,
                data,
                local,
                tipo
            );

            eventoService.cadastrar(evento);

            JOptionPane.showMessageDialog(
                null,
                "Evento cadastrado com sucesso!",
                "Cadastro concluído",
                JOptionPane.INFORMATION_MESSAGE
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

        List<Evento> eventos = eventoService.listar();

        StringBuilder texto = new StringBuilder();

        texto.append("===== EVENTOS CADASTRADOS =====\n\n");

        if (eventos.isEmpty()) {

            texto.append("Nenhum evento cadastrado.");

        } else {

            for (Evento evento : eventos) {

                texto.append("ID: ")
                    .append(evento.getId())
                    .append("\n");

                texto.append("Nome: ")
                    .append(evento.getNome())
                    .append("\n");

                texto.append("Descrição: ")
                    .append(evento.getDescricao())
                    .append("\n");

                texto.append("Data: ")
                    .append(evento.getData())
                    .append("\n");

                texto.append("Local: ")
                    .append(evento.getLocal())
                    .append("\n");

                texto.append("Tipo: ")
                    .append(evento.getTipo())
                    .append("\n");

                texto.append("Ativo: ")
                    .append(evento.isAtivo() ? "Sim" : "Não")
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
            "Eventos cadastrados",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    public void desativar() {

        try {

            String entradaId = JOptionPane.showInputDialog(
                null,
                "Digite o ID do evento que deseja desativar:",
                "Desativar evento",
                JOptionPane.QUESTION_MESSAGE
            );

            if (entradaId == null) return;

            int id = Integer.parseInt(entradaId);

            int confirmacao = JOptionPane.showConfirmDialog(
                null,
                "Deseja realmente desativar este evento?",
                "Confirmar desativação",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
            );

            if (confirmacao != JOptionPane.YES_OPTION) return;

            eventoService.desativar(id);

            JOptionPane.showMessageDialog(
                null,
                "Evento desativado com sucesso!",
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
                "Erro ao desativar evento",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
