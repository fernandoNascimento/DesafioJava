package com.example.pagamento.domain.pagamento.dto;

import com.example.pagamento.domain.pagamento.entity.DadosRecorrencia;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.FutureOrPresent;

import java.math.BigDecimal;
import java.time.LocalDate;


public record DadosAtualizarPagamento(
        @FutureOrPresent
        LocalDate dataInclusao,
        @FutureOrPresent
        LocalDate dataPagamento,
        @DecimalMin(value = "0.01", inclusive = true)
        BigDecimal valorPagamento,
        String descricaoPagamento,
        @Valid
        DadosRecorrencia dadosRecorrencia,
        @Valid
        DadosDestinoPagamento destinoPagamento) {
}
