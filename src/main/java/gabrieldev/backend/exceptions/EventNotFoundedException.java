package gabrieldev.backend.exceptions;

public class EventNotFoundedException extends RuntimeException {
    public EventNotFoundedException() {
        super("Data not found");
    }

    public EventNotFoundedException(String message) {
        super(message);
    }
}
