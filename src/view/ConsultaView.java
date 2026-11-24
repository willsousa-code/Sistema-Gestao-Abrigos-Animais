
package view;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.Consulta;
import model.Animal;
import model.Veterinario;

import service.ConsultaService;
import service.AnimalService;

public class ConsultaView {

    private ConsultaService consultaService;
    private AnimalService animalService;

    public ConsultaView(
            ConsultaService consultaService,
            AnimalService animalService) {

        this.consultaService = consultaService;
        this.animalService = animalService;
    }

    public void cadastrar(Veterinario veterinario) {

        try {

            String entradaId = JOptionPane.showInputDialog(
                null,
                "Digite o ID do animal:",
                "Registrar consulta",
                JOptionPane.QUESTION_MESSAGE
            );

            if (entradaId == null) return;

            int id = Integer.parseInt(entradaId);

            Animal animal = animalService.buscar(id);

            String diagnostico = JOptionPane.showInputDialog(
                null,
                "Informe o diagnóstico:"
            );

            if (diagnostico == null) return;

            String observacoes = JOptionPane.showInputDialog(
                null,
                "Informe as observações:"
            );

            if (observacoes == null) return;

            String tratamento = JOptionPane.showInputDialog(
                null,
                "Informe o tratamento indicado:"
            );

            if (tratamento == null) return;

            Consulta consulta = new Consulta(
                animal,
                veterinario,
                diagnostico,
                observacoes,
                tratamento
            );

            consultaService.cadastrarConsulta(
                consulta,
                veterinario
            );

            JOptionPane.showMessageDialog(
                null,
                "Consulta registrada com sucesso!",
                "Consulta concluída",
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
                "Erro ao registrar consulta",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public void listar() {

        List<Consulta> consultas = consultaService.listar();

        StringBuilder texto = new StringBuilder();

        texto.append("===== CONSULTAS REGISTRADAS =====\n\n");

        if (consultas.isEmpty()) {

            texto.append("Nenhuma consulta cadastrada.");

        } else {

            for (Consulta consulta : consultas) {

                texto.append("Código: ")
                    .append(consulta.getId())
                    .append("\n");

                texto.append("Animal: ")
                    .append(consulta.getAnimal().getNome())
                    .append("\n");

                texto.append("Veterinário: ")
                    .append(consulta.getVeterinario().getNome())
                    .append("\n");

                texto.append("Data: ")
                    .append(consulta.getDataConsulta())
                    .append("\n");

                texto.append("Diagnóstico: ")
                    .append(consulta.getDiagnostico())
                    .append("\n");

                texto.append("Observações: ")
                    .append(consulta.getObservacoes())
                    .append("\n");

                texto.append("Tratamento: ")
                    .append(consulta.getTratamentoIndicado())
                    .append("\n");

                texto.append("------------------------------\n");
            }
        }

        mostrarTexto(
            texto.toString(),
            "Consultas registradas"
        );
    }

    public void buscarPorAnimal() {

        try {

            String entradaId = JOptionPane.showInputDialog(
                null,
                "Digite o ID do animal:",
                "Histórico médico",
                JOptionPane.QUESTION_MESSAGE
            );

            if (entradaId == null) return;

            int id = Integer.parseInt(entradaId);

            List<Consulta> consultas =
                consultaService.buscarPorAnimal(id);

            StringBuilder texto = new StringBuilder();

            texto.append("===== HISTÓRICO DE CONSULTAS =====\n\n");

            if (consultas.isEmpty()) {

                texto.append("Nenhuma consulta encontrada para este animal.");

            } else {

                for (Consulta consulta : consultas) {

                    texto.append("Animal: ")
                        .append(consulta.getAnimal().getNome())
                        .append("\n");

                    texto.append("Veterinário: ")
                        .append(consulta.getVeterinario().getNome())
                        .append("\n");

                    texto.append("Data: ")
                        .append(consulta.getDataConsulta())
                        .append("\n");

                    texto.append("Diagnóstico: ")
                        .append(consulta.getDiagnostico())
                        .append("\n");

                    texto.append("Tratamento: ")
                        .append(consulta.getTratamentoIndicado())
                        .append("\n");

                    texto.append("------------------------------\n");
                }
            }

            mostrarTexto(
                texto.toString(),
                "Histórico médico do animal"
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
                "Erro na consulta",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void mostrarTexto(String conteudo, String titulo) {

        JTextArea area = new JTextArea(conteudo, 18, 45);

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
