
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

        validarTexto(nome, "O nome do evento não pode ficar vazio!");
        validarTexto(descricao, "A descrição não pode ficar vazia!");
        validarTexto(local, "O local não pode ficar vazio!");
        validarTexto(tipo, "O tipo do evento não pode ficar vazio!");
        validarData(data);

        this.id = contadorId++;
        this.nome = nome.trim();
        this.descricao = descricao.trim();
        this.data = data;
        this.local = local.trim();
        this.tipo = tipo.trim();
        this.ativo = true;
    }

    private void validarTexto(String valor, String mensagem) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(mensagem);
        }
    }

    private void validarData(LocalDate data) {
        if (data == null) {
            throw new IllegalArgumentException(
                "A data do evento não pode ser nula!"
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
        validarTexto(nome, "O nome do evento não pode ficar vazio!");
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

    public void setLocal(String local) {
        validarTexto(local, "O local não pode ficar vazio!");
        this.local = local.trim();
    }

    public void setTipo(String tipo) {
        validarTexto(tipo, "O tipo do evento não pode ficar vazio!");
        this.tipo = tipo.trim();
    }

    public void ativar() {
        this.ativo = true;
    }

    public void desativar() {
        this.ativo = false;
    }
}
