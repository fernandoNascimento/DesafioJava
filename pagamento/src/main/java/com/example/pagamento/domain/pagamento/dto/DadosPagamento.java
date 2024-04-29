package com.example.pagamento.domain.pagamento.dto;

import com.example.pagamento.domain.pagamento.StatusPagamento;
import com.example.pagamento.domain.pagamento.entity.DadosRecorrencia;
import com.example.pagamento.domain.pagamento.entity.DestinoPagamento;
import com.example.pagamento.domain.pagamento.entity.Pagamento;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosPagamento(String id,
                             StatusPagamento statusPagamento,
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

    public DadosPagamento(Pagamento pagamento) {
        this(pagamento.getId(),
                pagamento.getStatusPagamento(),
                pagamento.getDataInclusao(),
                pagamento.getDataPagamento(),
                pagamento.getValorPagamento(),
                pagamento.getDescricaoPagamento(),
                pagamento.getDadosRecorrencia(),
                pagamento.getDestinoPagamento(), null);
    }

    public DadosPagamento(Pagamento pagamento, String alerta) {
        this(pagamento.getId(),
                pagamento.getStatusPagamento(),
                pagamento.getDataInclusao(),
                pagamento.getDataPagamento(),
                pagamento.getValorPagamento(),
                pagamento.getDescricaoPagamento(),
                pagamento.getDadosRecorrencia(),
                pagamento.getDestinoPagamento(), alerta);
    }
}
