package model;

import java.time.LocalDate;

public class Evento {

    private final int id;
    private String nome;
    private String descricao;
    private LocalDate data;
    private String local;
    private String tipo;
    private boolean ativo;

    private static int contadorId = 1;

    public Evento(String nome, String descricao, LocalDate data,
                  String local, String tipo) {

        this.id = contadorId++;
        this.nome = nome;
        this.descricao = descricao;
        this.data = data;
        this.local = local;
        this.tipo = tipo;
        this.ativo = true;
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

    public String getLocal() {
        return local;
    }

    public String getTipo() {
        return tipo;
    }

    public boolean isAtivo() {
        return ativo;
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

    public void setLocal(String local) {
        this.local = local;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void ativar() {
        this.ativo = true;
    }

    public void desativar() {
        this.ativo = false;
    }
}