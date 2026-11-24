
package model;

import java.time.LocalDate;

public class Consulta {

    private final int id;
    private final Animal animal;
    private final Veterinario veterinario;
    private final LocalDate dataConsulta;
    private String diagnostico;
    private String observacoes;
    private String tratamentoIndicado;

    private static int contadorId = 1;

    public Consulta(Animal animal, Veterinario veterinario,
                    String diagnostico, String observacoes,
                    String tratamentoIndicado) {

        if (animal == null) {
            throw new IllegalArgumentException(
                "O animal da consulta não pode ser nulo!"
            );
        }

        if (veterinario == null || !veterinario.isAtivo()) {
            throw new IllegalArgumentException(
                "É necessário informar um veterinário ativo!"
            );
        }

        validarTexto(diagnostico, "O diagnóstico não pode ficar vazio!");
        validarTexto(
            tratamentoIndicado,
            "O tratamento indicado não pode ficar vazio!"
        );

        this.id = contadorId++;
        this.animal = animal;
        this.veterinario = veterinario;
        this.dataConsulta = LocalDate.now();
        this.diagnostico = diagnostico.trim();
        this.observacoes = observacoes == null ? "" : observacoes.trim();
        this.tratamentoIndicado = tratamentoIndicado.trim();
    }

    private void validarTexto(String valor, String mensagem) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(mensagem);
        }
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
        validarTexto(diagnostico, "O diagnóstico não pode ficar vazio!");
        this.diagnostico = diagnostico.trim();
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes == null ? "" : observacoes.trim();
    }

    public void setTratamentoIndicado(String tratamentoIndicado) {
        validarTexto(
            tratamentoIndicado,
            "O tratamento indicado não pode ficar vazio!"
        );
        this.tratamentoIndicado = tratamentoIndicado.trim();
    }
}
