
package view;

import java.util.List;
import java.util.Scanner;

import model.Usuario;
import model.Voluntario;
import model.Veterinario;
import model.Administrador;
import service.UsuarioService;

public class UsuarioView {

    private Scanner entrada = new Scanner(System.in);
    private UsuarioService usuarioService;

    public UsuarioView(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public void exibir() {

        int opcao = -1;

        while (opcao != 0) {

            System.out.println("\n===== GERENCIAR USUÁRIOS =====");
            System.out.println("1 - Cadastrar voluntário");
            System.out.println("2 - Cadastrar veterinário");
            System.out.println("3 - Cadastrar administrador");
            System.out.println("4 - Listar usuários");
            System.out.println("5 - Buscar usuário");
            System.out.println("6 - Desativar usuário");
            System.out.println("0 - Voltar");

            System.out.print("Escolha: ");

            try {
                opcao = Integer.parseInt(entrada.nextLine());

                switch (opcao) {
                    case 1:
                        cadastrarVoluntario();
                        break;
                    case 2:
                        cadastrarVeterinario();
                        break;
                    case 3:
                        cadastrarAdministrador();
                        break;
                    case 4:
                        listar();
                        break;
                    case 5:
                        buscar();
                        break;
                    case 6:
                        desativar();
                        break;
                    case 0:
                        System.out.println("Voltando...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }

            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido!");
            }
        }
    }

    private void cadastrarVoluntario() {

        System.out.println("\n===== NOVO VOLUNTÁRIO =====");

        System.out.print("Nome: ");
        String nome = entrada.nextLine();

        System.out.print("Email: ");
        String email = entrada.nextLine();

        System.out.print("Senha: ");
        String senha = entrada.nextLine();

        System.out.print("Telefone: ");
        String telefone = entrada.nextLine();

        System.out.print("Área de atuação: ");
        String area = entrada.nextLine();

        Voluntario v = new Voluntario(
            nome, email, senha, telefone, area
        );

        usuarioService.cadastrar(v);

        System.out.println("Voluntário cadastrado!");
    }

    private void cadastrarVeterinario() {

        System.out.println("\n===== NOVO VETERINÁRIO =====");

        System.out.print("Nome: ");
        String nome = entrada.nextLine();

        System.out.print("Email: ");
        String email = entrada.nextLine();

        System.out.print("Senha: ");
        String senha = entrada.nextLine();

        System.out.print("Telefone: ");
        String telefone = entrada.nextLine();

        System.out.print("CRMV: ");
        String crmv = entrada.nextLine();

        System.out.print("Especialidade: ");
        String especialidade = entrada.nextLine();

        Veterinario v = new Veterinario(
            nome, email, senha, telefone, crmv, especialidade
        );

        usuarioService.cadastrar(v);

        System.out.println("Veterinário cadastrado!");
    }

    private void cadastrarAdministrador() {

        System.out.println("\n===== NOVO ADMINISTRADOR =====");

        System.out.print("Nome: ");
        String nome = entrada.nextLine();

        System.out.print("Email: ");
        String email = entrada.nextLine();

        System.out.print("Senha: ");
        String senha = entrada.nextLine();

        System.out.print("Telefone: ");
        String telefone = entrada.nextLine();

        System.out.print("Nível de acesso: ");
        String nivel = entrada.nextLine();

        Administrador a = new Administrador(
            nome, email, senha, telefone, nivel
        );

        usuarioService.cadastrar(a);

        System.out.println("Administrador cadastrado!");
    }

    private void listar() {

        List<Usuario> usuarios = usuarioService.listar();

        System.out.println("\n===== USUÁRIOS CADASTRADOS =====");

        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }

        for (Usuario u : usuarios) {

            System.out.println("ID: " + u.getId());
            System.out.println("Nome: " + u.getNome());
            System.out.println("Email: " + u.getEmail());
            System.out.println("Tipo: " + u.getTipoUsuario());
            System.out.println("Ativo: " + u.isAtivo());

            System.out.println("-------------------------");
        }
    }

    private void buscar() {

        try {
            System.out.print("ID do usuário: ");
            int id = Integer.parseInt(entrada.nextLine());

            Usuario u = usuarioService.buscar(id);

            System.out.println("Nome: " + u.getNome());
            System.out.println("Email: " + u.getEmail());
            System.out.println("Tipo: " + u.getTipoUsuario());
            System.out.println("Ativo: " + u.isAtivo());

        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void desativar() {

        try {
            System.out.print("ID do usuário: ");
            int id = Integer.parseInt(entrada.nextLine());

            usuarioService.excluir(id);

            System.out.println("Usuário desativado!");

        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
