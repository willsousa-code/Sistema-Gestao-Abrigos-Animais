package model;

import java.time.LocalDate;

public class Vacina {

    private final int id;
    private Animal animal;
    private String nome;
    private LocalDate dataAplicacao;
    private LocalDate dataProximaDose;
    private Veterinario veterinario;

    private static int contadorId = 1;

    public Vacina(Animal animal, String nome, LocalDate dataAplicacao,
                  LocalDate dataProximaDose, Veterinario veterinario) {

        this.id = contadorId++;
        this.animal = animal;
        this.nome = nome;
        this.dataAplicacao = dataAplicacao;
        this.dataProximaDose = dataProximaDose;
        this.veterinario = veterinario;
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
        this.nome = nome;
    }

    public void setDataAplicacao(LocalDate dataAplicacao) {
        this.dataAplicacao = dataAplicacao;
    }

    public void setDataProximaDose(LocalDate dataProximaDose) {
        this.dataProximaDose = dataProximaDose;
    }
}