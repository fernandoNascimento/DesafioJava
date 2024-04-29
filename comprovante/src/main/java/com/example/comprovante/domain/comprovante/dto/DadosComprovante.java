package com.example.comprovante.domain.comprovante.dto;

import com.example.comprovante.domain.comprovante.entity.Comprovante;
import com.example.comprovante.domain.comprovante.entity.DadosRecorrencia;
import com.example.comprovante.domain.comprovante.entity.DestinoPagamento;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosComprovante(String id,
                               String idPagamento,
                               LocalDate dataInclusao,
                               LocalDate dataPagamento,
                               BigDecimal valorPagamento,
                               @JsonInclude(JsonInclude.Include.NON_NULL)
                               String descricaoPagamento,
                               @JsonInclude(JsonInclude.Include.NON_NULL)
                               DadosRecorrencia dadosRecorrencia,
                               @JsonInclude(JsonInclude.Include.NON_NULL)
                               DestinoPagamento destinoPagamento) {

    public DadosComprovante(Comprovante comprovante) {
        this(comprovante.getId(),
                comprovante.getIdPagamento(),
                comprovante.getDataInclusao(),
                comprovante.getDataPagamento(),
                comprovante.getValorPagamento(),
                comprovante.getDescricaoPagamento(),
                comprovante.getDadosRecorrencia(),
                comprovante.getDestinoPagamento());
    }
}
