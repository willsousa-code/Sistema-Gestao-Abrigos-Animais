package service;

import model.Animal;
import model.Usuario;
import model.Compatibilidade;

public class CompatibilidadeService {

    public Compatibilidade calcular(Animal animal, Usuario usuario) {

        if (animal == null || usuario == null) {
            throw new IllegalArgumentException("Dados inválidos!");
        }

        Compatibilidade resultado = new Compatibilidade(animal, usuario);

        resultado.calcularCompatibilidade();

        return resultado;
    }

}