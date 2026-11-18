package model;

import java.time.LocalDateTime;

public class Notificacao {

    private final int id;
    private Usuario destinatario;
    private String mensagem;
    private LocalDateTime dataEnvio;
    private boolean lida;

    private static int contadorId = 1;

    public Notificacao(Usuario destinatario, String mensagem) {
        this.id = contadorId++;
        this.destinatario = destinatario;
        this.mensagem = mensagem;
        this.dataEnvio = LocalDateTime.now();
        this.lida = false;
    }

    public int getId() {
        return id;
    }

    public Usuario getDestinatario() {
        return destinatario;
    }

    public String getMensagem() {
        return mensagem;
    }

    public LocalDateTime getDataEnvio() {
        return dataEnvio;
    }

    public boolean isLida() {
        return lida;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public void marcarComoLida() {
        this.lida = true;
    }

    public void marcarComoNaoLida() {
        this.lida = false;
    }
}