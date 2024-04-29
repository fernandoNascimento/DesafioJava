package com.example.pagamento.domain.pagamento.validacoes;

import com.example.pagamento.domain.pagamento.Frequencia;
import com.example.pagamento.domain.pagamento.PagamentoException;
import com.example.pagamento.domain.pagamento.dto.DadosDestinoPagamento;
import com.example.pagamento.domain.pagamento.dto.DadosIncluirPagamento;
import com.example.pagamento.domain.pagamento.entity.DadosRecorrencia;
import com.example.pagamento.domain.pagamento.entity.Pagamento;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class ValidadorRecorrenciaMensalTest {

    @InjectMocks
    private ValidadorRecorrenciaMensal validador;

    private Pagamento pagamento;

    @BeforeEach
    void setup() {
        pagamento = new Pagamento(new DadosIncluirPagamento(
                LocalDate.now(),
                LocalDate.now(),
                BigDecimal.valueOf(101.00),
                "desc",
                new DadosRecorrencia(LocalDate.now().plusMonths(25), Frequencia.MENSAL),
                new DadosDestinoPagamento(UUID.randomUUID().toString())));
    }

    @Test
    void deveRetornarExceptionCasoRecebaValorDeRecorrenciaMaiorQue100eDataFinalMaiorQue24Meses() {
        Assertions.assertThrows(PagamentoException.class, () -> validador.validar(pagamento));
    }

}