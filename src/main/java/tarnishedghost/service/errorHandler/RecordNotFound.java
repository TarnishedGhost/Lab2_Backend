package tarnishedghost.service.errorHandler;
import java.util.UUID;
public class RecordNotFoundException extends RuntimeException {
    private static final String MESSAGE = "Record with id %s not found";
    public RecordNotFoundException(UUID id) {
        super(String.format(MESSAGE, id));
    }
}