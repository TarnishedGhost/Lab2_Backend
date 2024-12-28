package tarnishedghost.service.errorHandler;
public class InvalidArgumentsException extends RuntimeException {
    public InvalidArgumentsException(String message) {
        super(message);
    }
}