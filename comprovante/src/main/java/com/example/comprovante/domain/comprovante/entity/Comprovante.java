package com.example.comprovante.domain.comprovante.entity;

import com.example.comprovante.domain.comprovante.TipoComprovante;
import com.example.comprovante.domain.comprovante.dto.DadosPagamento;
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
public class Comprovante {
    @Id
    private String id;
    private String idPagamento;
    private LocalDate dataInclusao;
    private LocalDate dataPagamento;
    private BigDecimal valorPagamento;
    private String descricaoPagamento;
    private DadosRecorrencia dadosRecorrencia;
    private DestinoPagamento destinoPagamento;
    private TipoComprovante tipoComprovante;


    public Comprovante(DadosPagamento pagamento, TipoComprovante tipoComprovante) {
        this.idPagamento = pagamento.id();
        this.dataInclusao = pagamento.dataInclusao();
        this.dataPagamento = pagamento.dataPagamento();
        this.valorPagamento = pagamento.valorPagamento();
        this.descricaoPagamento = pagamento.descricaoPagamento();
        this.dadosRecorrencia = new DadosRecorrencia(pagamento.dadosRecorrencia());
        this.destinoPagamento = new DestinoPagamento(pagamento.destinoPagamento());
        this.tipoComprovante = tipoComprovante;
    }
}
