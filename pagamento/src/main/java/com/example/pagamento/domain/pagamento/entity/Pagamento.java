package com.example.pagamento.domain.pagamento.entity;

import com.example.pagamento.domain.pagamento.StatusPagamento;
import com.example.pagamento.domain.pagamento.dto.DadosAtualizarPagamento;
import com.example.pagamento.domain.pagamento.dto.DadosIncluirPagamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Pagamento {

    @Id
    private String id;
    private StatusPagamento statusPagamento;
    private LocalDate dataInclusao;
    private LocalDate dataPagamento;
    private BigDecimal valorPagamento;
    private String descricaoPagamento;
    private DadosRecorrencia dadosRecorrencia;
    private DestinoPagamento destinoPagamento;

    public Pagamento(DadosIncluirPagamento dados) {
        this.statusPagamento = getStatusPagamento(dados);
        this.dataInclusao = dados.dataInclusao();
        this.dataPagamento = dados.dataPagamento();
        this.valorPagamento = dados.valorPagamento();
        this.descricaoPagamento = dados.descricaoPagamento();
        this.dadosRecorrencia = dados.dadosRecorrencia();
        this.destinoPagamento = new DestinoPagamento(dados.destinoPagamento());

    }

    private StatusPagamento getStatusPagamento(DadosIncluirPagamento dados) {
        return dados.dataPagamento().equals(LocalDate.now())
                ? StatusPagamento.EFETUADO
                : StatusPagamento.AGENDADO;
    }

    private StatusPagamento atualizaStatusPagamento(DadosAtualizarPagamento dados) {
        return dados.dataPagamento().equals(LocalDate.now())
                ? StatusPagamento.EFETUADO
                : StatusPagamento.AGENDADO;
    }

    public void atualizarDados(DadosAtualizarPagamento dados) {
        if (dados.dataInclusao() != null) {
            this.dataInclusao = dados.dataInclusao();
        }
        if (dados.dataPagamento() != null) {
            this.dataPagamento = dados.dataPagamento();
            this.statusPagamento = atualizaStatusPagamento(dados);
        }
        if (dados.valorPagamento() != null) {
            this.valorPagamento = dados.valorPagamento();
        }
        if (dados.descricaoPagamento() != null) {
            this.descricaoPagamento = dados.descricaoPagamento();
        }
        if (dados.dadosRecorrencia() != null) {
            this.dadosRecorrencia = dados.dadosRecorrencia();
        }
        if (dados.destinoPagamento() != null) {
            this.destinoPagamento = new DestinoPagamento(dados.destinoPagamento());
        }

    }
}
