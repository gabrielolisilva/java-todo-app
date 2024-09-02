package gabrieldev.backend.infra;

import gabrieldev.backend.exceptions.EventNotFoundedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(EventNotFoundedException.class)
    private ResponseEntity<ErrorDefaultMsg> eventNotFoundedException(EventNotFoundedException exception) {
        ErrorDefaultMsg errorDefaultMsg = new ErrorDefaultMsg(exception.getMessage(), "EVENT_NOT_FOUND");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDefaultMsg);
    }
}
