package com.example.pagamento.domain.pagamento.dto;

import jakarta.validation.constraints.NotNull;

public record DadosDestinoPagamento(@NotNull String chavePix) {
}
