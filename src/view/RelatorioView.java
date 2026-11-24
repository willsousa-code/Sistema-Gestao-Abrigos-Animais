
package view;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.Relatorio;
import service.RelatorioService;

public class RelatorioView {

    private RelatorioService relatorioService;

    public RelatorioView(RelatorioService relatorioService) {
        this.relatorioService = relatorioService;
    }

    public void listar() {

        List<Relatorio> relatorios = relatorioService.listar();

        StringBuilder texto = new StringBuilder();

        texto.append("===== RELATÓRIOS DO SISTEMA =====\n\n");

        if (relatorios.isEmpty()) {

            texto.append("Nenhum relatório cadastrado.");

        } else {

            for (Relatorio relatorio : relatorios) {

                texto.append("ID: ")
                    .append(relatorio.getId())
                    .append("\n");

                texto.append("Tipo: ")
                    .append(relatorio.getTipo())
                    .append("\n");

                texto.append("------------------------------\n");
            }
        }

        mostrarTexto(
            texto.toString(),
            "Relatórios do sistema"
        );
    }

    public void buscarPorTipo() {

        String tipo = JOptionPane.showInputDialog(
            null,
            "Digite o tipo do relatório:",
            "Buscar relatório",
            JOptionPane.QUESTION_MESSAGE
        );

        if (tipo == null) return;

        try {

            List<Relatorio> relatorios =
                relatorioService.buscarPorTipo(tipo);

            StringBuilder texto = new StringBuilder();

            texto.append("===== RESULTADO DA BUSCA =====\n\n");

            if (relatorios.isEmpty()) {

                texto.append("Nenhum relatório encontrado.");

            } else {

                for (Relatorio relatorio : relatorios) {

                    texto.append("Código: ")
                        .append(relatorio.getId())
                        .append("\n");

                    texto.append("Tipo: ")
                        .append(relatorio.getTipo())
                        .append("\n");

                    texto.append("------------------------------\n");
                }
            }

            mostrarTexto(
                texto.toString(),
                "Resultado da busca"
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

    public void excluir() {

        try {

            String entradaId = JOptionPane.showInputDialog(
                null,
                "Digite o ID do relatório que deseja excluir:",
                "Excluir relatório",
                JOptionPane.QUESTION_MESSAGE
            );

            if (entradaId == null) return;

            int id = Integer.parseInt(entradaId);

            int confirmacao = JOptionPane.showConfirmDialog(
                null,
                "Deseja realmente excluir este relatório?",
                "Confirmar exclusão",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
            );

            if (confirmacao != JOptionPane.YES_OPTION) return;

            relatorioService.excluir(id);

            JOptionPane.showMessageDialog(
                null,
                "Relatório excluído com sucesso!",
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
                "Erro na exclusão",
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
