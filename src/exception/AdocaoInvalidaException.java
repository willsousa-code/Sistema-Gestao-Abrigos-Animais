package exception;

public class AdocaoInvalidaException extends RuntimeException {

    public AdocaoInvalidaException(String mensagem) {
        super(mensagem);
    }
}