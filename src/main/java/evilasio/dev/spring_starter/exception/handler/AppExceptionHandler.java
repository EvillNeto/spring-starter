package evilasio.dev.spring_starter.exception.handler;

import java.nio.file.AccessDeniedException;
import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import evilasio.dev.spring_starter.exception.StandardError;
import evilasio.dev.spring_starter.exception.StandardException;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<StandardError> handle(AccessDeniedException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        StandardError error = new StandardError(
                Instant.now(),
                status.value(),
                "Access Denied Exception",
                e.getMessage(),
                request.getRequestURI(),
                null);
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(StandardException.class)
    public ResponseEntity<StandardError> handle(StandardException e, HttpServletRequest request) {
        HttpStatus status = e.getStatus();
        StandardError error = new StandardError(
                Instant.now(),
                status.value(),
                e.getName(),
                e.getMessage(),
                request.getRequestURI(),
                e.getExtraInfo());
        return ResponseEntity.status(status).body(error);
    }
}
