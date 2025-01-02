package tarnishedghost.service.errorHandler;

public class ForbiddenException extends RuntimeException {
    private static final String MESSAGE = "Access denied";
    public ForbiddenException() {
        super(MESSAGE);
    }
}
