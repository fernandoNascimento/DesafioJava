package com.example.pagamento.domain.pagamento.service;

import com.example.pagamento.domain.pagamento.PagamentoNotFoundException;
import com.example.pagamento.domain.pagamento.StatusPagamento;
import com.example.pagamento.domain.pagamento.dto.DadosAtualizarPagamento;
import com.example.pagamento.domain.pagamento.dto.DadosIncluirPagamento;
import com.example.pagamento.domain.pagamento.dto.DadosPagamento;
import com.example.pagamento.domain.pagamento.entity.Pagamento;
import com.example.pagamento.domain.pagamento.repository.PagamentoRepository;
import com.example.pagamento.domain.pagamento.validacoes.ValidadorRecorrenciaPagamento;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository repository;

    @Autowired
    private List<ValidadorRecorrenciaPagamento> validadorRecorrencia;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public DadosPagamento incluirPagamento(DadosIncluirPagamento dados) {
        var novoPagamento = new Pagamento(dados);

        validadorRecorrencia.forEach(v -> v.validar(novoPagamento));

        var pagamento = repository.findByValorPagamentoAndDataPagamentoAndDestinoPagamentoChavePix(
                dados.valorPagamento(),
                dados.dataPagamento(),
                dados.destinoPagamento().chavePix());

        var alerta = !pagamento.isEmpty() ? "Esse pagamento já foi incluído" : null;

        repository.save(novoPagamento);

        rabbitTemplate.convertAndSend("pagamento.incluido", novoPagamento);

        return new DadosPagamento(novoPagamento, alerta);
    }

    public Page<DadosPagamento> buscaPagamentos(StatusPagamento status, Pageable pageable) {
        return status != null && !status.equals(StatusPagamento.TODOS)
                ? repository.findByStatusPagamento(status, pageable).map(DadosPagamento::new)
                : repository.findAll(pageable).map(DadosPagamento::new);
    }

    public DadosPagamento atualizarPagamento(String id, DadosAtualizarPagamento dados) {
        var pagamento = repository.findById(id).orElse(null);

        if (pagamento != null) {
            pagamento.atualizarDados(dados);
            validadorRecorrencia.forEach(v -> v.validar(pagamento));
            repository.save(pagamento);
            rabbitTemplate.convertAndSend("pagamento.alterado", pagamento);
            return new DadosPagamento(pagamento, null);
        }
        throw new PagamentoNotFoundException("Pagamento não encontrado");
    }

    public void deletar(String id) {
        var pagamento = repository.findById(id).orElseThrow(() -> new PagamentoNotFoundException("Pagamento não encontrado"));
        rabbitTemplate.convertAndSend("pagamento.deletado", pagamento);
        repository.delete(pagamento);
    }


}
