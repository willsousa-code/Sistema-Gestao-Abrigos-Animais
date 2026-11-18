package exception;

public class VacinaDuplicadaException extends RuntimeException {

    public VacinaDuplicadaException(String mensagem) {
        super(mensagem);
    }
}