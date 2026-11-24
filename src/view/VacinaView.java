
package view;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.Vacina;
import model.Animal;
import model.Veterinario;

import service.VacinaService;
import service.AnimalService;

public class VacinaView {

    private VacinaService vacinaService;
    private AnimalService animalService;

    public VacinaView(
            VacinaService vacinaService,
            AnimalService animalService) {

        this.vacinaService = vacinaService;
        this.animalService = animalService;
    }

    public void cadastrar(Veterinario veterinario) {

        try {

            String entradaId = JOptionPane.showInputDialog(
                null,
                "Digite o ID do animal:",
                "Registrar vacina",
                JOptionPane.QUESTION_MESSAGE
            );

            if (entradaId == null) return;

            int id = Integer.parseInt(entradaId);

            Animal animal = animalService.buscar(id);

            String nome = JOptionPane.showInputDialog(
                null,
                "Nome da vacina:"
            );

            if (nome == null) return;

            String entradaData = JOptionPane.showInputDialog(
                null,
                "Data da aplicação (AAAA-MM-DD):"
            );

            if (entradaData == null) return;

            LocalDate data = LocalDate.parse(entradaData);

            String entradaProxima = JOptionPane.showInputDialog(
                null,
                "Data da próxima dose (AAAA-MM-DD):\n"
                + "Deixe em branco caso não exista próxima dose."
            );

            if (entradaProxima == null) return;

            LocalDate dataProxima = entradaProxima.trim().isEmpty()
                ? null
                : LocalDate.parse(entradaProxima);

            Vacina vacina = new Vacina(
                animal,
                nome,
                data,
                dataProxima,
                veterinario
            );

            vacinaService.cadastrar(vacina, veterinario);

            JOptionPane.showMessageDialog(
                null,
                "Vacina registrada com sucesso!",
                "Cadastro concluído",
                JOptionPane.INFORMATION_MESSAGE
            );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                null,
                "Digite um ID numérico válido!",
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
                "Erro ao registrar vacina",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public void listar() {

        List<Vacina> lista = vacinaService.listar();

        StringBuilder texto = new StringBuilder();

        texto.append("===== VACINAS REGISTRADAS =====\n\n");

        if (lista.isEmpty()) {

            texto.append("Nenhuma vacina cadastrada.");

        } else {

            for (Vacina vacina : lista) {

                texto.append("Código: ")
                    .append(vacina.getId())
                    .append("\n");

                texto.append("Animal: ")
                    .append(vacina.getAnimal().getNome())
                    .append("\n");

                texto.append("Vacina: ")
                    .append(vacina.getNome())
                    .append("\n");

                texto.append("Aplicação: ")
                    .append(vacina.getDataAplicacao())
                    .append("\n");

                texto.append("Próxima dose: ")
                    .append(vacina.getDataProximaDose() == null
                        ? "Não informada"
                        : vacina.getDataProximaDose())
                    .append("\n");

                texto.append("Veterinário: ")
                    .append(vacina.getVeterinario().getNome())
                    .append("\n");

                texto.append("------------------------------\n");
            }
        }

        mostrarTexto(
            texto.toString(),
            "Vacinas registradas"
        );
    }

    public void buscarPorAnimal() {

        try {

            String entradaId = JOptionPane.showInputDialog(
                null,
                "Digite o ID do animal:",
                "Histórico de vacinação",
                JOptionPane.QUESTION_MESSAGE
            );

            if (entradaId == null) return;

            int id = Integer.parseInt(entradaId);

            List<Vacina> vacinas = vacinaService.buscarPorAnimal(id);

            StringBuilder texto = new StringBuilder();

            texto.append("===== HISTÓRICO DE VACINAS =====\n\n");

            if (vacinas.isEmpty()) {

                texto.append("Nenhuma vacina encontrada para este animal.");

            } else {

                for (Vacina vacina : vacinas) {

                    texto.append("Animal: ")
                        .append(vacina.getAnimal().getNome())
                        .append("\n");

                    texto.append("Vacina: ")
                        .append(vacina.getNome())
                        .append("\n");

                    texto.append("Data da aplicação: ")
                        .append(vacina.getDataAplicacao())
                        .append("\n");

                    texto.append("Próxima dose: ")
                        .append(vacina.getDataProximaDose() == null
                            ? "Não informada"
                            : vacina.getDataProximaDose())
                        .append("\n");

                    texto.append("Veterinário: ")
                        .append(vacina.getVeterinario().getNome())
                        .append("\n");

                    texto.append("------------------------------\n");
                }
            }

            mostrarTexto(
                texto.toString(),
                "Histórico de vacinação"
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
