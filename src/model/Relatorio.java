
package model;

import java.time.LocalDateTime;

public class Relatorio {

    private final int id;
    private String titulo;
    private String descricao;
    private String tipo;
    private final LocalDateTime dataGeracao;

    private static int contadorId = 1;

    public Relatorio(String titulo, String descricao, String tipo) {

        validarTexto(titulo, "O título do relatório não pode ficar vazio!");
        validarTexto(
            descricao,
            "A descrição do relatório não pode ficar vazia!"
        );
        validarTexto(tipo, "O tipo do relatório não pode ficar vazio!");

        this.id = contadorId++;
        this.titulo = titulo.trim();
        this.descricao = descricao.trim();
        this.tipo = tipo.trim();
        this.dataGeracao = LocalDateTime.now();
    }

    private void validarTexto(String valor, String mensagem) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(mensagem);
        }
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
        validarTexto(titulo, "O título do relatório não pode ficar vazio!");
        this.titulo = titulo.trim();
    }

    public void setDescricao(String descricao) {
        validarTexto(
            descricao,
            "A descrição do relatório não pode ficar vazia!"
        );
        this.descricao = descricao.trim();
    }

    public void setTipo(String tipo) {
        validarTexto(tipo, "O tipo do relatório não pode ficar vazio!");
        this.tipo = tipo.trim();
    }
}
