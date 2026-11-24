
package model;

import java.time.LocalDateTime;

public class Notificacao {

    private final int id;
    private final Usuario destinatario;
    private String mensagem;
    private final LocalDateTime dataEnvio;
    private boolean lida;

    private static int contadorId = 1;

    public Notificacao(Usuario destinatario, String mensagem) {

        if (destinatario == null) {
            throw new IllegalArgumentException(
                "O destinatário da notificação não pode ser nulo!"
            );
        }

        if (!destinatario.isAtivo()) {
            throw new IllegalArgumentException(
                "Não é possível enviar notificação para um usuário desativado!"
            );
        }

        validarMensagem(mensagem);

        this.id = contadorId++;
        this.destinatario = destinatario;
        this.mensagem = mensagem.trim();
        this.dataEnvio = LocalDateTime.now();
        this.lida = false;
    }

    private void validarMensagem(String mensagem) {
        if (mensagem == null || mensagem.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "A mensagem da notificação não pode ficar vazia!"
            );
        }
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
        validarMensagem(mensagem);
        this.mensagem = mensagem.trim();
    }

    public void marcarComoLida() {
        this.lida = true;
    }

    public void marcarComoNaoLida() {
        this.lida = false;
    }
}
