
package view;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.Animal;
import service.AnimalService;

public class AnimalView {

    private AnimalService animalService;

    public AnimalView(AnimalService animalService) {
        this.animalService = animalService;
    }

    public void exibir() {

        boolean continuar = true;

        while (continuar) {

            String[] opcoes = {
                "Cadastrar animal",
                "Listar animais",
                "Buscar animal por ID",
                "Excluir animal",
                "Disponibilizar para adoção",
                "Voltar"
            };

            int opcao = JOptionPane.showOptionDialog(
                null,
                "===== GERENCIAMENTO DE ANIMAIS =====",
                "Sistema de Abrigos e Resgates",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                opcoes,
                opcoes[0]
            );

            switch (opcao) {

                case 0:
                    cadastrar();
                    break;

                case 1:
                    listar();
                    break;

                case 2:
                    buscar();
                    break;

                case 3:
                    excluir();
                    break;

                case 4:
                    disponibilizar();
                    break;

                case 5:
                case JOptionPane.CLOSED_OPTION:
                    continuar = false;
                    break;

                default:
                    break;
            }
        }
    }

    private void cadastrar() {

        try {

            String nome = JOptionPane.showInputDialog(
                null, "Nome do animal:"
            );

            if (nome == null) return;

            String especie = JOptionPane.showInputDialog(
                null, "Espécie:"
            );

            if (especie == null) return;

            String raca = JOptionPane.showInputDialog(
                null, "Raça:"
            );

            if (raca == null) return;

            String entradaIdade = JOptionPane.showInputDialog(
                null, "Idade:"
            );

            if (entradaIdade == null) return;

            int idade = Integer.parseInt(entradaIdade);

            String porte = JOptionPane.showInputDialog(
                null, "Porte:"
            );

            if (porte == null) return;

            String saude = JOptionPane.showInputDialog(
                null, "Estado de saúde:"
            );

            if (saude == null) return;

            String entradaData = JOptionPane.showInputDialog(
                null,
                "Data do resgate (AAAA-MM-DD):"
            );

            if (entradaData == null) return;

            LocalDate data = LocalDate.parse(entradaData);

            Animal animal = new Animal(
                nome,
                especie,
                raca,
                idade,
                porte,
                saude,
                data
            );

            animalService.cadastrar(animal);

            JOptionPane.showMessageDialog(
                null,
                "Animal cadastrado com sucesso!",
                "Cadastro concluído",
                JOptionPane.INFORMATION_MESSAGE
            );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                null,
                "Idade inválida! Digite um número inteiro.",
                "Erro de entrada",
                JOptionPane.WARNING_MESSAGE
            );

        } catch (DateTimeParseException e) {

            JOptionPane.showMessageDialog(
                null,
                "Data inválida! Utilize o formato AAAA-MM-DD.",
                "Erro de data",
                JOptionPane.WARNING_MESSAGE
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

    private void listar() {

        List<Animal> animais = animalService.listarAnimais();

        StringBuilder texto = new StringBuilder();

        texto.append("===== ANIMAIS CADASTRADOS =====\n\n");

        if (animais.isEmpty()) {

            texto.append("Nenhum animal cadastrado.");

        } else {

            for (Animal animal : animais) {
                texto.append(formatarAnimal(animal));
                texto.append("\n------------------------------\n");
            }
        }

        mostrarTexto(
            texto.toString(),
            "Animais cadastrados"
        );
    }

    private void buscar() {

        try {

            String entradaId = JOptionPane.showInputDialog(
                null,
                "Digite o ID do animal:"
            );

            if (entradaId == null) return;

            int id = Integer.parseInt(entradaId);

            Animal animal = animalService.buscar(id);

            mostrarTexto(
                formatarAnimal(animal),
                "Animal encontrado"
            );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                null,
                "ID inválido! Digite um número.",
                "Erro",
                JOptionPane.WARNING_MESSAGE
            );

        } catch (IllegalArgumentException e) {

            JOptionPane.showMessageDialog(
                null,
                e.getMessage(),
                "Animal não encontrado",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void excluir() {

        try {

            String entradaId = JOptionPane.showInputDialog(
                null,
                "Digite o ID do animal que deseja excluir:"
            );

            if (entradaId == null) return;

            int id = Integer.parseInt(entradaId);

            int confirmacao = JOptionPane.showConfirmDialog(
                null,
                "Deseja realmente excluir este animal?",
                "Confirmar exclusão",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
            );

            if (confirmacao != JOptionPane.YES_OPTION) {
                return;
            }

            animalService.excluir(id);

            JOptionPane.showMessageDialog(
                null,
                "Animal excluído com sucesso!",
                "Operação concluída",
                JOptionPane.INFORMATION_MESSAGE
            );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                null,
                "ID inválido!",
                "Erro",
                JOptionPane.WARNING_MESSAGE
            );

        } catch (IllegalArgumentException e) {

            JOptionPane.showMessageDialog(
                null,
                e.getMessage(),
                "Erro na exclusão",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void disponibilizar() {

        try {

            String entradaId = JOptionPane.showInputDialog(
                null,
                "Digite o ID do animal:"
            );

            if (entradaId == null) return;

            int id = Integer.parseInt(entradaId);

            animalService.disponibilizar(id);

            JOptionPane.showMessageDialog(
                null,
                "Animal disponibilizado para adoção!",
                "Operação concluída",
                JOptionPane.INFORMATION_MESSAGE
            );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                null,
                "ID inválido!",
                "Erro",
                JOptionPane.WARNING_MESSAGE
            );

        } catch (IllegalArgumentException e) {

            JOptionPane.showMessageDialog(
                null,
                e.getMessage(),
                "Erro ao disponibilizar",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private String formatarAnimal(Animal animal) {

        StringBuilder texto = new StringBuilder();

        texto.append("ID: ").append(animal.getId()).append("\n");
        texto.append("Nome: ").append(animal.getNome()).append("\n");
        texto.append("Espécie: ").append(animal.getEspecie()).append("\n");
        texto.append("Raça: ").append(animal.getRaca()).append("\n");
        texto.append("Idade: ").append(animal.getIdade()).append("\n");
        texto.append("Porte: ").append(animal.getPorte()).append("\n");
        texto.append("Saúde: ").append(animal.getEstadoSaude()).append("\n");
        texto.append("Data do resgate: ").append(animal.getDataResgate()).append("\n");
        texto.append("Status: ").append(animal.getStatusAdocao()).append("\n");

        return texto.toString();
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
