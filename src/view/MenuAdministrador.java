
package view;

import javax.swing.JOptionPane;

import main.SistemaContexto;
import model.Administrador;
import model.Usuario;

public class MenuAdministrador {

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

        boolean continuar = true;

        while (continuar) {

            String[] opcoes = {
                "Gerenciar animais",
                "Gerenciar usuários",
                "Aprovar adoções",
                "Gerenciar eventos",
                "Gerenciar atividades",
                "Relatórios",
                "Voltar"
            };

            int opcao = JOptionPane.showOptionDialog(
                null,
                "Bem-vindo ao painel administrativo!\n\n"
                    + "Selecione uma funcionalidade:",
                "Sistema de Abrigos e Resgates - Administrador",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                opcoes,
                opcoes[0]
            );

            try {

                switch (opcao) {

                    case 0:
                        animalView.exibir();
                        break;

                    case 1:
                        usuarioView.exibir();
                        break;

                    case 2:
                        gerenciarAdocoes();
                        break;

                    case 3:
                        gerenciarEventos();
                        break;

                    case 4:
                        gerenciarAtividades();
                        break;

                    case 5:
                        gerenciarRelatorios();
                        break;

                    case 6:
                    case JOptionPane.CLOSED_OPTION:
                        continuar = false;
                        break;

                    default:
                        continuar = false;
                        break;
                }

            } catch (RuntimeException e) {

                JOptionPane.showMessageDialog(
                    null,
                    e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    private void gerenciarAdocoes() {

        String[] opcoes = {
            "Listar solicitações",
            "Aprovar adoção",
            "Rejeitar adoção",
            "Voltar"
        };

        int opcao = JOptionPane.showOptionDialog(
            null,
            "Gerenciamento de adoções",
            "Adoções",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null,
            opcoes,
            opcoes[0]
        );

        try {

            switch (opcao) {

                case 0:
                    adocaoView.listar();
                    break;

                case 1: {
                    Administrador administrador = obterAdministrador();
                    if (administrador != null) {
                        adocaoView.aprovar(administrador);
                    }
                    break;
                }

                case 2: {
                    Administrador administrador = obterAdministrador();
                    if (administrador != null) {
                        adocaoView.rejeitar(administrador);
                    }
                    break;
                }

                default:
                    break;
            }

        } catch (RuntimeException e) {

            JOptionPane.showMessageDialog(
                null,
                e.getMessage(),
                "Erro nas adoções",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void gerenciarEventos() {

        String[] opcoes = {
            "Cadastrar evento",
            "Listar eventos",
            "Desativar evento",
            "Voltar"
        };

        int opcao = JOptionPane.showOptionDialog(
            null,
            "Gerenciamento de eventos",
            "Eventos",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null,
            opcoes,
            opcoes[0]
        );

        try {

            switch (opcao) {

                case 0:
                    eventoView.cadastrar();
                    break;

                case 1:
                    eventoView.listar();
                    break;

                case 2:
                    eventoView.desativar();
                    break;

                default:
                    break;
            }

        } catch (RuntimeException e) {

            JOptionPane.showMessageDialog(
                null,
                e.getMessage(),
                "Erro nos eventos",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void gerenciarAtividades() {

        String[] opcoes = {
            "Cadastrar atividade",
            "Listar atividades",
            "Voltar"
        };

        int opcao = JOptionPane.showOptionDialog(
            null,
            "Gerenciamento de atividades",
            "Atividades",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null,
            opcoes,
            opcoes[0]
        );

        try {

            switch (opcao) {

                case 0:
                    atividadeView.cadastrar();
                    break;

                case 1:
                    atividadeView.listar();
                    break;

                default:
                    break;
            }

        } catch (RuntimeException e) {

            JOptionPane.showMessageDialog(
                null,
                e.getMessage(),
                "Erro nas atividades",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void gerenciarRelatorios() {

        String[] opcoes = {
            "Listar relatórios",
            "Buscar por tipo",
            "Excluir relatório",
            "Voltar"
        };

        int opcao = JOptionPane.showOptionDialog(
            null,
            "Gerenciamento de relatórios",
            "Relatórios",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null,
            opcoes,
            opcoes[0]
        );

        try {

            switch (opcao) {

                case 0:
                    relatorioView.listar();
                    break;

                case 1:
                    relatorioView.buscarPorTipo();
                    break;

                case 2:
                    relatorioView.excluir();
                    break;

                default:
                    break;
            }

        } catch (RuntimeException e) {

            JOptionPane.showMessageDialog(
                null,
                e.getMessage(),
                "Erro nos relatórios",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private Administrador obterAdministrador() {

        String identificacao = JOptionPane.showInputDialog(
            null,
            "Digite o ID do administrador:",
            "Identificação administrativa",
            JOptionPane.QUESTION_MESSAGE
        );

        if (identificacao == null) {
            return null;
        }

        try {

            int id = Integer.parseInt(identificacao.trim());

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

        } catch (NumberFormatException e) {

            throw new IllegalArgumentException(
                "Digite um ID numérico válido!"
            );
        }
    }
}
