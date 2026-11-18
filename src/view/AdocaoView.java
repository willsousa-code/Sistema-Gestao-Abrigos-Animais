package view;

import java.util.Scanner;
import model.*;
import service.AdocaoService;
import service.AnimalService;
import service.UsuarioService;

public class AdocaoView {

    private Scanner entrada = new Scanner(System.in);

    private AdocaoService adocaoService;
    private AnimalService animalService;
    private UsuarioService usuarioService;

    public AdocaoView(AdocaoService adocaoService,
                      AnimalService animalService,
                      UsuarioService usuarioService) {

        this.adocaoService = adocaoService;
        this.animalService = animalService;
        this.usuarioService = usuarioService;
    }

    public void solicitar() {

        try {
            System.out.println("\n===== SOLICITAR ADOÇÃO =====");

            System.out.print("ID do animal: ");
            int idAnimal = Integer.parseInt(entrada.nextLine());

            System.out.print("ID do interessado: ");
            int idUsuario = Integer.parseInt(entrada.nextLine());

            Animal animal = animalService.buscar(idAnimal);
            Usuario usuario = usuarioService.buscar(idUsuario);

            adocaoService.solicitarAdocao(animal, usuario);

            System.out.println("Solicitação realizada com sucesso!");

        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void listar() {

        System.out.println("\n===== SOLICITAÇÕES DE ADOÇÃO =====");

        for (Adocao a : adocaoService.listar()) {

            System.out.println("Código: " + a.getId());
            System.out.println("Animal: " + a.getAnimal().getNome());
            System.out.println("Interessado: " + a.getSolicitante().getNome());
            System.out.println("Data: " + a.getDataSolicitacao());
            System.out.println("Status: " + a.getStatus());

            System.out.println("--------------------------");
        }
    }

    public void aprovar(Administrador administrador) {

        try {
            System.out.print("ID da adoção: ");
            int id = Integer.parseInt(entrada.nextLine());

            adocaoService.aprovar(id, administrador);

            System.out.println("Adoção aprovada!");

        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void rejeitar(Administrador administrador) {

        try {
            System.out.print("ID da adoção: ");
            int id = Integer.parseInt(entrada.nextLine());

            adocaoService.rejeitar(id, administrador);

            System.out.println("Adoção rejeitada!");

        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}