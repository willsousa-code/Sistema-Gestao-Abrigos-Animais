
package model;

import java.time.LocalDate;

public class Vacina {

    private final int id;
    private final Animal animal;
    private String nome;
    private LocalDate dataAplicacao;
    private LocalDate dataProximaDose;
    private final Veterinario veterinario;

    private static int contadorId = 1;

    public Vacina(Animal animal, String nome, LocalDate dataAplicacao,
                  LocalDate dataProximaDose, Veterinario veterinario) {

        if (animal == null) {
            throw new IllegalArgumentException(
                "O animal da vacinação não pode ser nulo!"
            );
        }

        if (veterinario == null || !veterinario.isAtivo()) {
            throw new IllegalArgumentException(
                "É necessário informar um veterinário ativo!"
            );
        }

        validarNome(nome);
        validarDataAplicacao(dataAplicacao);
        validarDataProximaDose(dataAplicacao, dataProximaDose);

        this.id = contadorId++;
        this.animal = animal;
        this.nome = nome.trim();
        this.dataAplicacao = dataAplicacao;
        this.dataProximaDose = dataProximaDose;
        this.veterinario = veterinario;
    }

    private void validarNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "O nome da vacina não pode ficar vazio!"
            );
        }
    }

    private void validarDataAplicacao(LocalDate dataAplicacao) {
        if (dataAplicacao == null || dataAplicacao.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException(
                "A data de aplicação da vacina é inválida!"
            );
        }
    }

    private void validarDataProximaDose(
            LocalDate dataAplicacao, LocalDate dataProximaDose) {

        if (dataProximaDose != null
                && (dataAplicacao == null
                || dataProximaDose.isBefore(dataAplicacao))) {
            throw new IllegalArgumentException(
                "A próxima dose não pode ser anterior à aplicação!"
            );
        }
    }

    public int getId() {
        return id;
    }

    public Animal getAnimal() {
        return animal;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataAplicacao() {
        return dataAplicacao;
    }

    public LocalDate getDataProximaDose() {
        return dataProximaDose;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public void setNome(String nome) {
        validarNome(nome);
        this.nome = nome.trim();
    }

    public void setDataAplicacao(LocalDate dataAplicacao) {
        validarDataAplicacao(dataAplicacao);
        validarDataProximaDose(dataAplicacao, dataProximaDose);
        this.dataAplicacao = dataAplicacao;
    }

    public void setDataProximaDose(LocalDate dataProximaDose) {
        validarDataProximaDose(this.dataAplicacao, dataProximaDose);
        this.dataProximaDose = dataProximaDose;
    }
}
