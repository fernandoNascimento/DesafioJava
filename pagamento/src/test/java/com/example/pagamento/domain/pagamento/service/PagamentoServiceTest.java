package com.example.pagamento.domain.pagamento.service;

import com.example.pagamento.domain.pagamento.Frequencia;
import com.example.pagamento.domain.pagamento.PagamentoNotFoundException;
import com.example.pagamento.domain.pagamento.StatusPagamento;
import com.example.pagamento.domain.pagamento.dto.DadosAtualizarPagamento;
import com.example.pagamento.domain.pagamento.dto.DadosDestinoPagamento;
import com.example.pagamento.domain.pagamento.dto.DadosIncluirPagamento;
import com.example.pagamento.domain.pagamento.dto.DadosPagamento;
import com.example.pagamento.domain.pagamento.entity.DadosRecorrencia;
import com.example.pagamento.domain.pagamento.entity.Pagamento;
import com.example.pagamento.domain.pagamento.repository.PagamentoRepository;
import com.example.pagamento.domain.pagamento.validacoes.ValidadorRecorrenciaPagamento;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PagamentoServiceTest {

    @InjectMocks
    private PagamentoService service;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @Mock
    private PagamentoRepository pagamentoRepository;

    @Mock
    private List<ValidadorRecorrenciaPagamento> validadorRecorrencia;

    private DadosIncluirPagamento dadosIncluirPagamento;

    private DadosAtualizarPagamento dadosAtualizarPagamento;

    @BeforeEach
    void setup() {
        dadosIncluirPagamento = new DadosIncluirPagamento(
                LocalDate.now(),
                LocalDate.now(),
                BigDecimal.valueOf(51.00),
                "desc",
                new DadosRecorrencia(LocalDate.now().plusDays(30), Frequencia.SEMANAL),
                new DadosDestinoPagamento(UUID.randomUUID().toString()));

        dadosAtualizarPagamento = new DadosAtualizarPagamento(
                LocalDate.now(),
                LocalDate.now(),
                BigDecimal.valueOf(52.00),
                "desc",
                new DadosRecorrencia(LocalDate.now().plusDays(30), Frequencia.SEMANAL),
                new DadosDestinoPagamento(UUID.randomUUID().toString()));
    }

    @Test
    void incluirPagamentoSemAlerta() {
        DadosPagamento dadosPagamento = service.incluirPagamento(dadosIncluirPagamento);
        Assertions.assertNull(dadosPagamento.alerta());
        Mockito.verify(pagamentoRepository, Mockito.times(1)).save(any());
    }

    @Test
    void incluirPagamentoComAlerta() {
        when(pagamentoRepository.findByValorPagamentoAndDataPagamentoAndDestinoPagamentoChavePix(any(), any(), any())).thenReturn(List.of(new Pagamento()));
        DadosPagamento dadosPagamento = service.incluirPagamento(dadosIncluirPagamento);
        Assertions.assertNotNull(dadosPagamento.alerta());
        Mockito.verify(pagamentoRepository, Mockito.times(1)).save(any());
    }

    @Test
    void buscaPagamentosComStatusEfetuadoouAgendado() {
        Pageable pageable = mock(Pageable.class);
        Page<Pagamento> pagamentos = Mockito.mock(Page.class);
        when(pagamentoRepository.findByStatusPagamento(any(), any())).thenReturn(pagamentos);
        service.buscaPagamentos(StatusPagamento.EFETUADO, pageable);
        Mockito.verify(pagamentoRepository, Mockito.times(1)).findByStatusPagamento(any(), any());
        Mockito.verify(pagamentoRepository, Mockito.never()).findAll();
    }

    @Test
    void buscaPagamentosSemStatusOuTodos() {
        Pageable pageable = mock(Pageable.class);
        Page<Pagamento> pagamentos = Mockito.mock(Page.class);
        when(pagamentoRepository.findAll(pageable)).thenReturn(pagamentos);
        service.buscaPagamentos(StatusPagamento.TODOS, pageable);
        Mockito.verify(pagamentoRepository, Mockito.times(1)).findAll(pageable);
        Mockito.verify(pagamentoRepository, Mockito.never()).findByStatusPagamento(any(), any());
    }

    @Test
    void atualizarPagamento() {
        when(pagamentoRepository.findById(any())).thenReturn(Optional.of(new Pagamento()));
        DadosPagamento dadosPagamento = service.atualizarPagamento(UUID.randomUUID().toString(), dadosAtualizarPagamento);
        Mockito.verify(pagamentoRepository, Mockito.times(1)).save(any());
        Assertions.assertEquals(dadosAtualizarPagamento.valorPagamento(), dadosPagamento.valorPagamento());
    }

    @Test
    void atualizarPagamentoRetornaExceptionCasoPagamentoNaoExista() {
        Assertions.assertThrows(PagamentoNotFoundException.class,
                () -> service.atualizarPagamento(UUID.randomUUID().toString(),
                        dadosAtualizarPagamento), "Pagamento não encontrado");
    }

    @Test
    void deletarPagamento() {
        when(pagamentoRepository.findById(any())).thenReturn(Optional.of(new Pagamento()));
        service.deletar(UUID.randomUUID().toString());
    }

    @Test
    void deletarPagamentoRetornaExceptionCasoPagamentoNaoExista() {
        Assertions.assertThrows(PagamentoNotFoundException.class,
                () -> service.deletar(UUID.randomUUID().toString())
                , "Pagamento não encontrado");
    }
}