package com.example.credit_application_service.infrastructure.exception;

import com.example.credit_application_service.application.usecase.CrearAfiliadoUseCaseImpl;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.Instant;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CrearAfiliadoUseCaseImpl.DuplicateDocumentoException.class)
    public ResponseEntity<ProblemDetail> handleDuplicateDocumento(CrearAfiliadoUseCaseImpl.DuplicateDocumentoException ex) {
        ProblemDetail pd = ProblemDetail.forStatus(409);
        pd.setType(URI.create("https://example.com/problems/duplicate-document"));
        pd.setTitle("Documento duplicado");
        pd.setDetail(ex.getMessage());
        pd.setProperty("timestamp", Instant.now().toString());
        return ResponseEntity.status(409).body(pd);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ProblemDetail> handleBadRequest(IllegalArgumentException ex) {
        ProblemDetail pd = ProblemDetail.forStatus(400);
        pd.setType(URI.create("https://example.com/problems/invalid-input"));
        pd.setTitle("Solicitud inválida");
        pd.setDetail(ex.getMessage());
        pd.setProperty("timestamp", Instant.now().toString());
        return ResponseEntity.badRequest().body(pd);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> handleValidation(MethodArgumentNotValidException ex) {
        String details = ex.getFieldErrors().stream()
                .map(fe -> fe.getField() + ": " + fe.getDefaultMessage())
                .collect(Collectors.joining("; "));

        ProblemDetail pd = ProblemDetail.forStatus(400);
        pd.setType(URI.create("https://example.com/problems/validation"));
        pd.setTitle("Errores de validación");
        pd.setDetail(details);
        pd.setProperty("timestamp", Instant.now().toString());
        return ResponseEntity.badRequest().body(pd);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleGeneric(Exception ex) {
        ProblemDetail pd = ProblemDetail.forStatus(500);
        pd.setType(URI.create("https://example.com/problems/internal-error"));
        pd.setTitle("Error interno");
        pd.setDetail(ex.getMessage());
        pd.setProperty("timestamp", Instant.now().toString());
        return ResponseEntity.status(500).body(pd);
    }
}
