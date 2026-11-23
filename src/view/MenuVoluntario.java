
package view;

import java.util.Scanner;

import main.SistemaContexto;
import model.Animal;
import model.Usuario;
import model.Voluntario;

public class MenuVoluntario {

    private Scanner entrada = new Scanner(System.in);
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
            System.out.println("Erro: " + e.getMessage());
            return;
        }

        int opcao = -1;

        while (opcao != 0) {

            System.out.println("\n===== MENU VOLUNTÁRIO =====");
            System.out.println("1 - Ver animais disponíveis");
            System.out.println("2 - Consultar eventos");
            System.out.println("3 - Ver atividades");
            System.out.println("4 - Participar de atividade");
            System.out.println("5 - Consultar compatibilidade");
            System.out.println("6 - Solicitar adoção");
            System.out.println("7 - Consultar notificações");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");

            try {
                opcao = Integer.parseInt(entrada.nextLine());

                switch (opcao) {

                    case 1:
                        listarAnimaisDisponiveis();
                        break;

                    case 2:
                        eventoView.listar();
                        break;

                    case 3:
                        atividadeView.listar();
                        break;

                    case 4:
                        atividadeView.inscrever();
                        break;

                    case 5:
                        compatibilidadeView.consultar();
                        break;

                    case 6:
                        adocaoView.solicitar();
                        break;

                    case 7:
                        consultarNotificacoes(voluntario);
                        break;

                    case 0:
                        System.out.println("Voltando ao menu principal...");
                        break;

                    default:
                        System.out.println("Opção inválida!");
                }

            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido!");
            } catch (RuntimeException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private Voluntario obterVoluntario() {

        System.out.print("Digite o ID do voluntário: ");
        int id = Integer.parseInt(entrada.nextLine());

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

        System.out.println("\n===== ANIMAIS DISPONÍVEIS =====");

        boolean encontrou = false;

        for (Animal animal : contexto.animalService.listarAnimais()) {

            if (animal.estaDisponivelParaAdocao()) {

                System.out.println("ID: " + animal.getId());
                System.out.println("Nome: " + animal.getNome());
                System.out.println("Espécie: " + animal.getEspecie());
                System.out.println("Raça: " + animal.getRaca());
                System.out.println("Estado de saúde: " + animal.getEstadoSaude());
                System.out.println("-----------------------------");

                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Não existem animais disponíveis no momento.");
        }
    }

    private void consultarNotificacoes(Voluntario voluntario) {

        System.out.println("\n===== NOTIFICAÇÕES =====");

        contexto.notificacaoService
            .listarPorDestinatario(voluntario)
            .forEach(notificacao -> {
                System.out.println("ID: " + notificacao.getId());
                System.out.println("Mensagem: " + notificacao.getMensagem());
                System.out.println("Data: " + notificacao.getDataEnvio());
                System.out.println("Lida: " + (notificacao.isLida() ? "Sim" : "Não"));
                System.out.println("-----------------------------");
            });
    }
}
