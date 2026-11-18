package model;

import java.time.LocalDate;

public class Consulta {

    private final int id;
    private Animal animal;
    private Veterinario veterinario;
    private LocalDate dataConsulta;
    private String diagnostico;
    private String observacoes;
    private String tratamentoIndicado;

    private static int contadorId = 1;

    public Consulta(Animal animal, Veterinario veterinario,
                    String diagnostico, String observacoes,
                    String tratamentoIndicado) {

        this.id = contadorId++;
        this.animal = animal;
        this.veterinario = veterinario;
        this.dataConsulta = LocalDate.now();
        this.diagnostico = diagnostico;
        this.observacoes = observacoes;
        this.tratamentoIndicado = tratamentoIndicado;
    }

    public int getId() {
        return id;
    }

    public Animal getAnimal() {
        return animal;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public LocalDate getDataConsulta() {
        return dataConsulta;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public String getTratamentoIndicado() {
        return tratamentoIndicado;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public void setTratamentoIndicado(String tratamentoIndicado) {
        this.tratamentoIndicado = tratamentoIndicado;
    }
}