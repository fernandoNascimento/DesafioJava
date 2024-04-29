package com.example.pagamento.domain.pagamento;

public class PagamentoNotFoundException extends RuntimeException {
    public PagamentoNotFoundException(String message) {
        super(message);
    }
}
