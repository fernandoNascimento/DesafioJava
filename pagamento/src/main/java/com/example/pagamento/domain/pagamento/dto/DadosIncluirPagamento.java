package com.example.pagamento.domain.pagamento.dto;

import com.example.pagamento.domain.pagamento.entity.DadosRecorrencia;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;


public record DadosIncluirPagamento(
        @NotNull
        @FutureOrPresent
        LocalDate dataInclusao,
        @NotNull
        @FutureOrPresent
        LocalDate dataPagamento,
        @NotNull
        @DecimalMin(value = "0.01", inclusive = true)
        BigDecimal valorPagamento,
        String descricaoPagamento,
        @Valid
        DadosRecorrencia dadosRecorrencia,
        @NotNull
        @Valid
        DadosDestinoPagamento destinoPagamento) {
}
