
package view;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.*;
import service.AdocaoService;
import service.AnimalService;
import service.UsuarioService;

public class AdocaoView {

    private AdocaoService adocaoService;
    private AnimalService animalService;
    private UsuarioService usuarioService;

    public AdocaoView(
            AdocaoService adocaoService,
            AnimalService animalService,
            UsuarioService usuarioService) {

        this.adocaoService = adocaoService;
        this.animalService = animalService;
        this.usuarioService = usuarioService;
    }

    public void solicitar() {

        try {

            String entradaAnimal = JOptionPane.showInputDialog(
                null,
                "Digite o ID do animal:",
                "Solicitar adoção",
                JOptionPane.QUESTION_MESSAGE
            );

            if (entradaAnimal == null) return;

            int idAnimal = Integer.parseInt(entradaAnimal);

            String entradaUsuario = JOptionPane.showInputDialog(
                null,
                "Digite o ID do interessado:",
                "Solicitar adoção",
                JOptionPane.QUESTION_MESSAGE
            );

            if (entradaUsuario == null) return;

            int idUsuario = Integer.parseInt(entradaUsuario);

            Animal animal = animalService.buscar(idAnimal);
            Usuario usuario = usuarioService.buscar(idUsuario);

            adocaoService.solicitarAdocao(animal, usuario);

            JOptionPane.showMessageDialog(
                null,
                "Solicitação realizada com sucesso!",
                "Adoção solicitada",
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
                "Erro na solicitação",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public void listar() {

        List<Adocao> adocoes = adocaoService.listar();

        StringBuilder texto = new StringBuilder();

        texto.append("===== SOLICITAÇÕES DE ADOÇÃO =====\n\n");

        if (adocoes.isEmpty()) {

            texto.append("Nenhuma solicitação de adoção cadastrada.");

        } else {

            for (Adocao adocao : adocoes) {

                texto.append("Código: ")
                    .append(adocao.getId())
                    .append("\n");

                texto.append("Animal: ")
                    .append(adocao.getAnimal().getNome())
                    .append("\n");

                texto.append("Interessado: ")
                    .append(adocao.getSolicitante().getNome())
                    .append("\n");

                texto.append("Data: ")
                    .append(adocao.getDataSolicitacao())
                    .append("\n");

                texto.append("Status: ")
                    .append(adocao.getStatus())
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
            "Solicitações de adoção",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    public void aprovar(Administrador administrador) {

        try {

            String entradaId = JOptionPane.showInputDialog(
                null,
                "Digite o ID da adoção que deseja aprovar:",
                "Aprovar adoção",
                JOptionPane.QUESTION_MESSAGE
            );

            if (entradaId == null) return;

            int id = Integer.parseInt(entradaId);

            int confirmacao = JOptionPane.showConfirmDialog(
                null,
                "Deseja realmente aprovar esta adoção?",
                "Confirmar aprovação",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
            );

            if (confirmacao != JOptionPane.YES_OPTION) return;

            adocaoService.aprovar(id, administrador);

            JOptionPane.showMessageDialog(
                null,
                "Adoção aprovada com sucesso!",
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
                "Erro na aprovação",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public void rejeitar(Administrador administrador) {

        try {

            String entradaId = JOptionPane.showInputDialog(
                null,
                "Digite o ID da adoção que deseja rejeitar:",
                "Rejeitar adoção",
                JOptionPane.QUESTION_MESSAGE
            );

            if (entradaId == null) return;

            int id = Integer.parseInt(entradaId);

            int confirmacao = JOptionPane.showConfirmDialog(
                null,
                "Deseja realmente rejeitar esta adoção?",
                "Confirmar rejeição",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
            );

            if (confirmacao != JOptionPane.YES_OPTION) return;

            adocaoService.rejeitar(id, administrador);

            JOptionPane.showMessageDialog(
                null,
                "Adoção rejeitada com sucesso!",
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
                "Erro na rejeição",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
