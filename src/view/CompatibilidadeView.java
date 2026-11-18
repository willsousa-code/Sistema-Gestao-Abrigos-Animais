package view;

import java.util.Scanner;

import model.Animal;
import model.Usuario;
import model.Compatibilidade;

import service.AnimalService;
import service.UsuarioService;
import service.CompatibilidadeService;

public class CompatibilidadeView {

    private Scanner entrada = new Scanner(System.in);

    private AnimalService animalService;
    private UsuarioService usuarioService;
    private CompatibilidadeService compatibilidadeService;

    public CompatibilidadeView(AnimalService animalService,
                               UsuarioService usuarioService,
                               CompatibilidadeService compatibilidadeService) {

        this.animalService = animalService;
        this.usuarioService = usuarioService;
        this.compatibilidadeService = compatibilidadeService;
    }

    public void consultar() {

        try {
            System.out.println("\n===== COMPATIBILIDADE PARA ADOÇÃO =====");

            System.out.print("ID do animal: ");
            int idAnimal = Integer.parseInt(entrada.nextLine());

            System.out.print("ID do interessado: ");
            int idUsuario = Integer.parseInt(entrada.nextLine());

            Animal animal = animalService.buscar(idAnimal);
            Usuario usuario = usuarioService.buscar(idUsuario);

            Compatibilidade resultado =
                compatibilidadeService.calcular(animal, usuario);

            System.out.println("\n===== RESULTADO =====");

            System.out.println("Animal: " + animal.getNome());
            System.out.println("Interessado: " + usuario.getNome());

            System.out.println("Pontuação: " + resultado.getPontuacao());
            System.out.println("Classificação: " + resultado.getClassificacao());

            System.out.println("Justificativa: " + resultado.getJustificativa());

        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}