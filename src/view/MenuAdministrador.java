
package view;

import java.util.Scanner;

import main.SistemaContexto;
import model.Administrador;
import model.Usuario;

public class MenuAdministrador {

    private Scanner entrada = new Scanner(System.in);
    private SistemaContexto contexto;

    private AnimalView animalView;
    private UsuarioView usuarioView;
    private AdocaoView adocaoView;
    private EventoView eventoView;
    private AtividadeView atividadeView;
    private RelatorioView relatorioView;

    public MenuAdministrador(SistemaContexto contexto) {
        this.contexto = contexto;

        animalView = new AnimalView(contexto.animalService);
        usuarioView = new UsuarioView(contexto.usuarioService);

        adocaoView = new AdocaoView(
            contexto.adocaoService,
            contexto.animalService,
            contexto.usuarioService
        );

        eventoView = new EventoView(contexto.eventoService);
        atividadeView = new AtividadeView(contexto.atividadeService);
        relatorioView = new RelatorioView(contexto.relatorioService);
    }

    public void exibir() {

        int opcao = -1;

        while (opcao != 0) {

            System.out.println("\n===== MENU DO ADMINISTRADOR =====");
            System.out.println("1 - Gerenciar animais");
            System.out.println("2 - Gerenciar usuários");
            System.out.println("3 - Aprovar adoções");
            System.out.println("4 - Gerenciar eventos");
            System.out.println("5 - Gerenciar atividades");
            System.out.println("6 - Relatórios");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(entrada.nextLine());

                switch (opcao) {
                    case 1:
                        animalView.exibir();
                        break;

                    case 2:
                        usuarioView.exibir();
                        break;

                    case 3:
                        gerenciarAdocoes();
                        break;

                    case 4:
                        gerenciarEventos();
                        break;

                    case 5:
                        gerenciarAtividades();
                        break;

                    case 6:
                        gerenciarRelatorios();
                        break;

                    case 0:
                        System.out.println("Voltando ao menu principal...");
                        break;

                    default:
                        System.out.println("Opção inválida!");
                }

            } catch (NumberFormatException e) {
                System.out.println("Digite apenas números!");
            } catch (RuntimeException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private void gerenciarAdocoes() {

        int opcao = -1;

        while (opcao != 0) {

            System.out.println("\n===== GERENCIAMENTO DE ADOÇÕES =====");
            System.out.println("1 - Listar solicitações");
            System.out.println("2 - Aprovar adoção");
            System.out.println("3 - Rejeitar adoção");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");

            try {
                opcao = Integer.parseInt(entrada.nextLine());

                switch (opcao) {
                    case 1:
                        adocaoView.listar();
                        break;

                    case 2: {
                        Administrador administrador = obterAdministrador();
                        adocaoView.aprovar(administrador);
                        break;
                    }

                    case 3: {
                        Administrador administrador = obterAdministrador();
                        adocaoView.rejeitar(administrador);
                        break;
                    }

                    case 0:
                        break;

                    default:
                        System.out.println("Opção inválida!");
                }

            } catch (NumberFormatException e) {
                System.out.println("Digite apenas números!");
            } catch (RuntimeException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private void gerenciarEventos() {

        int opcao = -1;

        while (opcao != 0) {

            System.out.println("\n===== GERENCIAMENTO DE EVENTOS =====");
            System.out.println("1 - Cadastrar evento");
            System.out.println("2 - Listar eventos");
            System.out.println("3 - Desativar evento");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");

            try {
                opcao = Integer.parseInt(entrada.nextLine());

                switch (opcao) {
                    case 1:
                        eventoView.cadastrar();
                        break;

                    case 2:
                        eventoView.listar();
                        break;

                    case 3:
                        eventoView.desativar();
                        break;

                    case 0:
                        break;

                    default:
                        System.out.println("Opção inválida!");
                }

            } catch (NumberFormatException e) {
                System.out.println("Digite apenas números!");
            } catch (RuntimeException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private void gerenciarAtividades() {

        int opcao = -1;

        while (opcao != 0) {

            System.out.println("\n===== GERENCIAMENTO DE ATIVIDADES =====");
            System.out.println("1 - Cadastrar atividade");
            System.out.println("2 - Listar atividades");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");

            try {
                opcao = Integer.parseInt(entrada.nextLine());

                switch (opcao) {
                    case 1:
                        atividadeView.cadastrar();
                        break;

                    case 2:
                        atividadeView.listar();
                        break;

                    case 0:
                        break;

                    default:
                        System.out.println("Opção inválida!");
                }

            } catch (NumberFormatException e) {
                System.out.println("Digite apenas números!");
            } catch (RuntimeException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private void gerenciarRelatorios() {

        int opcao = -1;

        while (opcao != 0) {

            System.out.println("\n===== RELATÓRIOS =====");
            System.out.println("1 - Listar relatórios");
            System.out.println("2 - Buscar por tipo");
            System.out.println("3 - Excluir relatório");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");

            try {
                opcao = Integer.parseInt(entrada.nextLine());

                switch (opcao) {
                    case 1:
                        relatorioView.listar();
                        break;

                    case 2:
                        relatorioView.buscarPorTipo();
                        break;

                    case 3:
                        relatorioView.excluir();
                        break;

                    case 0:
                        break;

                    default:
                        System.out.println("Opção inválida!");
                }

            } catch (NumberFormatException e) {
                System.out.println("Digite apenas números!");
            } catch (RuntimeException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private Administrador obterAdministrador() {

        System.out.print("Digite o ID do administrador: ");
        int id = Integer.parseInt(entrada.nextLine());

        Usuario usuario = contexto.usuarioService.buscar(id);

        if (!(usuario instanceof Administrador)) {
            throw new IllegalArgumentException(
                "O usuário informado não é um administrador!"
            );
        }

        if (!usuario.isAtivo()) {
            throw new IllegalArgumentException(
                "O administrador está desativado!"
            );
        }

        return (Administrador) usuario;
    }
}
