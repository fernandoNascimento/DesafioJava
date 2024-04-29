package com.example.pagamento.domain.pagamento.validacoes;

import com.example.pagamento.domain.pagamento.Frequencia;
import com.example.pagamento.domain.pagamento.PagamentoException;
import com.example.pagamento.domain.pagamento.entity.Pagamento;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component("ValidadorRecorrenciaSemestral")
public class ValidadorRecorrenciaSemestral implements ValidadorRecorrenciaPagamento {

    @Override
    public void validar(Pagamento dados) {
        var valorLimite = new BigDecimal("150");
        var dataAtual = LocalDate.now();
        var limiteData = dataAtual.plusMonths(42);

        if (isSemestral(dados) && dados.getValorPagamento().compareTo(valorLimite) > 0 && dados.getDadosRecorrencia().getDataFinal().isAfter(limiteData)) {
            throw new PagamentoException("A data final não pode ser superior a 42 meses para recorrência semestral");
        }
    }

    private boolean isSemestral(Pagamento dados) {
        return dados.getDadosRecorrencia() != null && dados.getDadosRecorrencia().getFrequencia().equals(Frequencia.SEMESTRAL);
    }
}
