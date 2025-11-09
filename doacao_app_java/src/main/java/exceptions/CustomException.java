package exceptions;

public class CustomException extends Exception {
    private String safeMessage;

    public CustomException(String safeMessage, Throwable cause) {
        super(safeMessage, cause);
        this.safeMessage = safeMessage;
        System.err.println("Erro interno: " + cause.getMessage());
    }

    public CustomException(String safeMessage) {
        super(safeMessage);
        this.safeMessage = safeMessage;
    }

    @Override
    public String getMessage() {
        return safeMessage;
    }

    @Override
    public String toString() {
        return "CustomException: " + safeMessage;
    }
}
