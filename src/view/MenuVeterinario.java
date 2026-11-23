
package view;

import java.util.Scanner;

import main.SistemaContexto;
import model.Animal;
import model.Usuario;
import model.Veterinario;

public class MenuVeterinario {

    private Scanner entrada = new Scanner(System.in);
    private SistemaContexto contexto;

    private ConsultaView consultaView;
    private VacinaView vacinaView;

    public MenuVeterinario(SistemaContexto contexto) {
        this.contexto = contexto;

        consultaView = new ConsultaView(
            contexto.consultaService,
            contexto.animalService
        );

        vacinaView = new VacinaView(
            contexto.vacinaService,
            contexto.animalService
        );
    }

    public void exibir() {

        Veterinario veterinario;

        try {
            veterinario = obterVeterinario();
        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
            return;
        }

        int opcao = -1;

        while (opcao != 0) {

            System.out.println("\n===== MENU VETERINÁRIO =====");
            System.out.println("1 - Consultar animais");
            System.out.println("2 - Registrar consulta");
            System.out.println("3 - Registrar vacina");
            System.out.println("4 - Consultar histórico médico");
            System.out.println("5 - Atualizar informações de saúde");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");

            try {
                opcao = Integer.parseInt(entrada.nextLine());

                switch (opcao) {
                    case 1:
                        listarAnimais();
                        break;

                    case 2:
                        consultaView.cadastrar(veterinario);
                        break;

                    case 3:
                        vacinaView.cadastrar(veterinario);
                        break;

                    case 4:
                        consultarHistorico();
                        break;

                    case 5:
                        atualizarSaude(veterinario);
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

    private Veterinario obterVeterinario() {

        System.out.print("Digite o ID do veterinário: ");
        int id = Integer.parseInt(entrada.nextLine());

        Usuario usuario = contexto.usuarioService.buscar(id);

        if (!(usuario instanceof Veterinario)) {
            throw new IllegalArgumentException(
                "O usuário informado não é um veterinário!"
            );
        }

        if (!usuario.isAtivo()) {
            throw new IllegalArgumentException(
                "O veterinário está desativado!"
            );
        }

        return (Veterinario) usuario;
    }

    private void listarAnimais() {

        System.out.println("\n===== ANIMAIS CADASTRADOS =====");

        for (Animal animal : contexto.animalService.listarAnimais()) {
            System.out.println("ID: " + animal.getId());
            System.out.println("Nome: " + animal.getNome());
            System.out.println("Espécie: " + animal.getEspecie());
            System.out.println("Saúde: " + animal.getEstadoSaude());
            System.out.println("-----------------------------");
        }
    }

    private void consultarHistorico() {

        int opcao = -1;

        while (opcao != 0) {

            System.out.println("\n===== HISTÓRICO MÉDICO =====");
            System.out.println("1 - Consultar consultas");
            System.out.println("2 - Consultar vacinas");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");

            try {
                opcao = Integer.parseInt(entrada.nextLine());

                switch (opcao) {
                    case 1:
                        consultaView.listar();
                        break;

                    case 2:
                        vacinaView.listar();
                        break;

                    case 0:
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

    private void atualizarSaude(Veterinario veterinario) {

        System.out.print("Digite o ID do animal: ");
        int id = Integer.parseInt(entrada.nextLine());

        System.out.print("Informe o novo estado de saúde: ");
        String estado = entrada.nextLine();

        contexto.animalService.atualizarSaude(
            id,
            estado,
            veterinario
        );

        System.out.println("Informações de saúde atualizadas!");
    }
}
