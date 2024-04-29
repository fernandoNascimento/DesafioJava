package com.example.pagamento.infra.exception;

import com.example.pagamento.domain.pagamento.PagamentoException;
import com.example.pagamento.domain.pagamento.PagamentoNotFoundException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class TratarExceptions {

    @ExceptionHandler(PagamentoException.class)
    public ResponseEntity tratarErroPagamento(PagamentoException ex) {
        return ResponseEntity.badRequest().body(new BodyException(ex.getMessage()));
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErroValidation(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(BodyException::new).toList());
    }

    @ExceptionHandler(PagamentoNotFoundException.class)
    public ResponseEntity tratarErroPagamentoNaoEncontrado(PagamentoNotFoundException ex) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        if (ex.getCause() instanceof InvalidFormatException invalidFormatException) {
            var fieldName = invalidFormatException.getPath().stream().map(JsonMappingException.Reference::getFieldName).findFirst().orElse("unknown");
            return ResponseEntity.badRequest().body(List.of(new BodyException(fieldName, "Valor inválido " + invalidFormatException.getValue())));
        } else {
            return ResponseEntity.badRequest().body(new BodyException(null, "Requisição inválida"));
        }
    }
}