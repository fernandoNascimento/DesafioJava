package com.example.comprovante.domain.comprovante.dto;

import com.example.comprovante.domain.comprovante.entity.DadosRecorrencia;
import com.example.comprovante.domain.comprovante.entity.DestinoPagamento;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosPagamento(String id,
                             LocalDate dataInclusao,
                             LocalDate dataPagamento,
                             BigDecimal valorPagamento,
                             @JsonInclude(JsonInclude.Include.NON_NULL)
                             String descricaoPagamento,
                             @JsonInclude(JsonInclude.Include.NON_NULL)
                             DadosRecorrencia dadosRecorrencia,
                             DestinoPagamento destinoPagamento,
                             @JsonInclude(JsonInclude.Include.NON_NULL)
                             String alerta) {
}
