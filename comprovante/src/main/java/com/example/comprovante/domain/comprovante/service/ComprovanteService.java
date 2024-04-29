package com.example.comprovante.domain.comprovante.service;

import com.example.comprovante.domain.comprovante.TipoComprovante;
import com.example.comprovante.domain.comprovante.dto.DadosComprovante;
import com.example.comprovante.domain.comprovante.dto.DadosPagamento;
import com.example.comprovante.domain.comprovante.entity.Comprovante;
import com.example.comprovante.domain.comprovante.repository.ComprovanteRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

@Service
public class ComprovanteService {

    @Autowired
    private ComprovanteRepository repository;

    @RabbitListener(queues = "pagamento.incluido")
    public void comprovantePagamentoIncluido(DadosPagamento pagamento) {
        var comprovante = new Comprovante(pagamento, TipoComprovante.PAGAMENTO_INCLUIDO);
        repository.save(comprovante);
    }

    @RabbitListener(queues = "pagamento.alterado")
    public void comprovantePagamentoAlterado(DadosPagamento pagamento) {
        var comprovante = new Comprovante(pagamento, TipoComprovante.PAGAMENTO_ALTERADO);
        repository.save(comprovante);
    }

    @RabbitListener(queues = "pagamento.deletado")
    public void comprovantePagamentoDeletado(DadosPagamento pagamento) {
        var comprovante = new Comprovante(pagamento, TipoComprovante.PAGAMENTO_DELETADO);
        repository.save(comprovante);
    }

    public Page<DadosComprovante> buscarComprovantes(String idPagamento, @PageableDefault(size = 7) Pageable pageable) {
        return repository.findByIdPagamento(idPagamento, pageable).map(DadosComprovante::new);
    }

}
