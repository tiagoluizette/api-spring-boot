package br.com.apirestfull.server.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MiddlewareExceptionServer {
        
    @ExceptionHandler(serverError.class)
    public ResponseEntity<String> handleException(serverError error) {
        return ResponseEntity.status(error.getStatusCode()).body(error.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro interno");
    }
}
