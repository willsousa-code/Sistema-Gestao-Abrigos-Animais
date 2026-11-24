
package view;

import javax.swing.JOptionPane;

import model.Animal;
import model.Usuario;
import model.Compatibilidade;

import service.AnimalService;
import service.UsuarioService;
import service.CompatibilidadeService;

public class CompatibilidadeView {

    private AnimalService animalService;
    private UsuarioService usuarioService;
    private CompatibilidadeService compatibilidadeService;

    public CompatibilidadeView(
            AnimalService animalService,
            UsuarioService usuarioService,
            CompatibilidadeService compatibilidadeService) {

        this.animalService = animalService;
        this.usuarioService = usuarioService;
        this.compatibilidadeService = compatibilidadeService;
    }

    public void consultar() {

        try {

            String entradaAnimal = JOptionPane.showInputDialog(
                null,
                "Digite o ID do animal:",
                "Consultar compatibilidade",
                JOptionPane.QUESTION_MESSAGE
            );

            if (entradaAnimal == null) return;

            int idAnimal = Integer.parseInt(entradaAnimal);

            String entradaUsuario = JOptionPane.showInputDialog(
                null,
                "Digite o ID do interessado:",
                "Consultar compatibilidade",
                JOptionPane.QUESTION_MESSAGE
            );

            if (entradaUsuario == null) return;

            int idUsuario = Integer.parseInt(entradaUsuario);

            Animal animal = animalService.buscar(idAnimal);
            Usuario usuario = usuarioService.buscar(idUsuario);

            Compatibilidade resultado =
                compatibilidadeService.calcular(animal, usuario);

            StringBuilder texto = new StringBuilder();

            texto.append("===== RESULTADO DA COMPATIBILIDADE =====\n\n");

            texto.append("Animal: ")
                .append(animal.getNome())
                .append("\n");

            texto.append("Interessado: ")
                .append(usuario.getNome())
                .append("\n\n");

            texto.append("Pontuação: ")
                .append(resultado.getPontuacao())
                .append("\n\n");

            texto.append("Classificação: ")
                .append(resultado.getClassificacao())
                .append("\n\n");

            texto.append("Justificativa:\n")
                .append(resultado.getJustificativa());

            JOptionPane.showMessageDialog(
                null,
                texto.toString(),
                "Resultado da compatibilidade",
                JOptionPane.INFORMATION_MESSAGE
            );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                null,
                "Digite IDs numéricos válidos!",
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
}
