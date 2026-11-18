package model;

import java.time.LocalDateTime;

public class Relatorio {

    private final int id;
    private String titulo;
    private String descricao;
    private String tipo;
    private LocalDateTime dataGeracao;

    private static int contadorId = 1;

    public Relatorio(String titulo, String descricao, String tipo) {
        this.id = contadorId++;
        this.titulo = titulo;
        this.descricao = descricao;
        this.tipo = tipo;
        this.dataGeracao = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public LocalDateTime getDataGeracao() {
        return dataGeracao;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}