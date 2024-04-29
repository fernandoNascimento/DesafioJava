package com.example.pagamento.infra.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.validation.FieldError;

public record BodyException(@JsonInclude(JsonInclude.Include.NON_NULL) String campo, String mensagem) {

    public BodyException(String msg) {
        this(null, msg);
    }

    public BodyException(FieldError fieldError) {
        this(fieldError.getField(), fieldError.getDefaultMessage());
    }
}
