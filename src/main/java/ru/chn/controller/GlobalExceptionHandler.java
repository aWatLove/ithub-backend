package ru.chn.controller;

import com.sun.jdi.request.DuplicateRequestException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.chn.dto.response.MessageResponse;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<MessageResponse> handleEntityNotFoundException(EntityNotFoundException ex) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<MessageResponse> handleDuplicateKeyException(DuplicateKeyException ex) {
        return ResponseEntity.badRequest().body(new MessageResponse("Username already exists"));
    }
    @ExceptionHandler(DuplicateRequestException.class)
    public ResponseEntity<MessageResponse> handleDuplicateRequestException(DuplicateRequestException ex){
        return ResponseEntity.badRequest().body(new MessageResponse(ex.getMessage()));
    }
}
