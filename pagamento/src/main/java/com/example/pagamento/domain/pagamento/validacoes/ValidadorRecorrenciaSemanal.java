package com.example.pagamento.domain.pagamento.validacoes;

import com.example.pagamento.domain.pagamento.Frequencia;
import com.example.pagamento.domain.pagamento.PagamentoException;
import com.example.pagamento.domain.pagamento.entity.Pagamento;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component("ValidadorRecorrenciaSemanal")
public class ValidadorRecorrenciaSemanal implements ValidadorRecorrenciaPagamento {

    @Override
    public void validar(Pagamento dados) {
        var valorLimite = new BigDecimal("50");
        var dataAtual = LocalDate.now();
        var limiteData = dataAtual.plusMonths(12);

        if (isSemanal(dados) && dados.getValorPagamento().compareTo(valorLimite) > 0 && dados.getDadosRecorrencia().getDataFinal().isAfter(limiteData)) {
            throw new PagamentoException("A data final não pode ser superior a 12 meses para recorrência semanal");
        }
    }

    private boolean isSemanal(Pagamento dados) {
        return dados.getDadosRecorrencia() != null && dados.getDadosRecorrencia().getFrequencia().equals(Frequencia.SEMANAL);
    }
}
