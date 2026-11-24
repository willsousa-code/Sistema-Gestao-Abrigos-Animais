
package model;

import java.time.LocalDate;

public class Atividade {

    private final int id;
    private String nome;
    private String descricao;
    private LocalDate data;
    private String horario;
    private String local;
    private int vagas;
    private int vagasOcupadas;
    private boolean ativa;

    private static int contadorId = 1;

    public Atividade(String nome, String descricao, LocalDate data,
                     String horario, String local, int vagas) {

        validarTexto(nome, "O nome da atividade não pode ficar vazio!");
        validarTexto(descricao, "A descrição não pode ficar vazia!");
        validarData(data);
        validarTexto(horario, "O horário não pode ficar vazio!");
        validarTexto(local, "O local não pode ficar vazio!");
        validarVagas(vagas);

        this.id = contadorId++;
        this.nome = nome.trim();
        this.descricao = descricao.trim();
        this.data = data;
        this.horario = horario.trim();
        this.local = local.trim();
        this.vagas = vagas;
        this.vagasOcupadas = 0;
        this.ativa = true;
    }

    private void validarTexto(String valor, String mensagem) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(mensagem);
        }
    }

    private void validarData(LocalDate data) {
        if (data == null) {
            throw new IllegalArgumentException(
                "A data da atividade não pode ser nula!"
            );
        }
    }

    private void validarVagas(int vagas) {
        if (vagas <= 0) {
            throw new IllegalArgumentException(
                "A quantidade de vagas deve ser maior que zero!"
            );
        }
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDate getData() {
        return data;
    }

    public String getHorario() {
        return horario;
    }

    public String getLocal() {
        return local;
    }

    public int getVagas() {
        return vagas;
    }

    public int getVagasOcupadas() {
        return vagasOcupadas;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setNome(String nome) {
        validarTexto(nome, "O nome da atividade não pode ficar vazio!");
        this.nome = nome.trim();
    }

    public void setDescricao(String descricao) {
        validarTexto(descricao, "A descrição não pode ficar vazia!");
        this.descricao = descricao.trim();
    }

    public void setData(LocalDate data) {
        validarData(data);
        this.data = data;
    }

    public void setHorario(String horario) {
        validarTexto(horario, "O horário não pode ficar vazio!");
        this.horario = horario.trim();
    }

    public void setLocal(String local) {
        validarTexto(local, "O local não pode ficar vazio!");
        this.local = local.trim();
    }

    public void setVagas(int vagas) {
        validarVagas(vagas);

        if (vagas < vagasOcupadas) {
            throw new IllegalArgumentException(
                "A quantidade de vagas não pode ser menor que as inscrições realizadas!"
            );
        }

        this.vagas = vagas;
    }

    public boolean temVagasDisponiveis() {
        return ativa && vagasOcupadas < vagas;
    }

    public void adicionarParticipante() {
        if (!ativa) {
            throw new IllegalStateException(
                "Não é possível participar de uma atividade inativa!"
            );
        }

        if (!temVagasDisponiveis()) {
            throw new IllegalStateException(
                "Não existem vagas disponíveis nesta atividade!"
            );
        }

        vagasOcupadas++;
    }

    public void removerParticipante() {
        if (vagasOcupadas <= 0) {
            throw new IllegalStateException(
                "Não existem participantes inscritos nesta atividade!"
            );
        }

        vagasOcupadas--;
    }

    public void ativar() {
        this.ativa = true;
    }

    public void desativar() {
        this.ativa = false;
    }
}
