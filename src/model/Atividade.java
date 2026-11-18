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

        this.id = contadorId++;
        this.nome = nome;
        this.descricao = descricao;
        this.data = data;
        this.horario = horario;
        this.local = local;
        this.vagas = vagas;
        this.vagasOcupadas = 0;
        this.ativa = true;
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
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public void setVagas(int vagas) {
        this.vagas = vagas;
    }

    public boolean temVagasDisponiveis() {
        return vagasOcupadas < vagas;
    }

    public void adicionarParticipante() {
        if (temVagasDisponiveis()) {
            vagasOcupadas++;
        }
    }

    public void removerParticipante() {
        if (vagasOcupadas > 0) {
            vagasOcupadas--;
        }
    }

    public void ativar() {
        this.ativa = true;
    }

    public void desativar() {
        this.ativa = false;
    }
}