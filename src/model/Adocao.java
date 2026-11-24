
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

        if (animal == null) {
            throw new IllegalArgumentException(
                "O animal informado é inválido!"
            );
        }

        if (solicitante == null) {
            throw new IllegalArgumentException(
                "O solicitante informado é inválido!"
            );
        }

        if (!solicitante.isAtivo()) {
            throw new IllegalArgumentException(
                "O solicitante está desativado!"
            );
        }

        if (!animal.estaDisponivelParaAdocao()) {
            throw new IllegalStateException(
                "Não é possível solicitar a adoção de um animal indisponível!"
            );
        }

        this.id = contadorId++;
        this.animal = animal;
        this.solicitante = solicitante;
        this.dataSolicitacao = LocalDate.now();
        this.dataConclusao = null;
        this.status = "Pendente";
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
        if (!"Pendente".equalsIgnoreCase(status)) {
            throw new IllegalStateException(
                "Não é possível alterar o animal após a conclusão da solicitação!"
            );
        }

        if (animal == null || !animal.estaDisponivelParaAdocao()) {
            throw new IllegalArgumentException(
                "O animal informado não está disponível para adoção!"
            );
        }

        this.animal = animal;
    }

    public void setSolicitante(Usuario solicitante) {
        if (!"Pendente".equalsIgnoreCase(status)) {
            throw new IllegalStateException(
                "Não é possível alterar o solicitante após a conclusão da solicitação!"
            );
        }

        if (solicitante == null || !solicitante.isAtivo()) {
            throw new IllegalArgumentException(
                "O solicitante informado é inválido ou está desativado!"
            );
        }

        this.solicitante = solicitante;
    }

    public void aprovar() {
        if (!"Pendente".equalsIgnoreCase(status)) {
            throw new IllegalStateException(
                "Somente solicitações pendentes podem ser aprovadas!"
            );
        }

        if (animal == null || !animal.estaDisponivelParaAdocao()) {
            throw new IllegalStateException(
                "O animal não está mais disponível para adoção!"
            );
        }

        animal.marcarComoAdotado();
        this.status = "Aprovada";
        this.dataConclusao = LocalDate.now();
    }

    public void rejeitar() {
        if (!"Pendente".equalsIgnoreCase(status)) {
            throw new IllegalStateException(
                "Somente solicitações pendentes podem ser rejeitadas!"
            );
        }

        this.status = "Rejeitada";
        this.dataConclusao = LocalDate.now();
    }

    public void registrarDevolucao(String motivo) {
        if (!"Aprovada".equalsIgnoreCase(status)) {
            throw new IllegalStateException(
                "Somente adoções aprovadas podem ser devolvidas!"
            );
        }

        if (motivo == null || motivo.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "O motivo da devolução não pode ficar vazio!"
            );
        }

        animal.disponibilizarParaAdocao();

        this.status = "Devolvida";
        this.motivoDevolucao = motivo.trim();
        this.dataConclusao = LocalDate.now();
    }
}
