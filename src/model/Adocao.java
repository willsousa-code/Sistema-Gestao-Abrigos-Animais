package model;

import java.time.LocalDate;

public class Adocao {

    private final int id;
    private Animal animal;
    private Usuario solicitante;
    private LocalDate dataSolicitacao;
    private LocalDate dataConclusao;
    private String status;
    private String motivoDevolucao;

    private static int contadorId = 1;

    public Adocao(Animal animal, Usuario solicitante) {
        this.id = contadorId++;
        this.animal = animal;
        this.solicitante = solicitante;
        this.dataSolicitacao = LocalDate.now();
        this.status = "Pendente";
        this.dataConclusao = null;
        this.motivoDevolucao = null;
    }

    public int getId() {
        return id;
    }

    public Animal getAnimal() {
        return animal;
    }

    public Usuario getSolicitante() {
        return solicitante;
    }

    public LocalDate getDataSolicitacao() {
        return dataSolicitacao;
    }

    public LocalDate getDataConclusao() {
        return dataConclusao;
    }

    public String getStatus() {
        return status;
    }

    public String getMotivoDevolucao() {
        return motivoDevolucao;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public void setSolicitante(Usuario solicitante) {
        this.solicitante = solicitante;
    }

    public void aprovar() {
        this.status = "Aprovada";
        this.dataConclusao = LocalDate.now();
        this.animal.marcarComoAdotado();
    }

    public void rejeitar() {
        this.status = "Rejeitada";
        this.dataConclusao = LocalDate.now();
    }

    public void registrarDevolucao(String motivo) {
        this.status = "Devolvida";
        this.motivoDevolucao = motivo;
        this.animal.disponibilizarParaAdocao();
    }
}