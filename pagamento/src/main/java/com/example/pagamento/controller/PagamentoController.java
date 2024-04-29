package com.example.pagamento.controller;

import com.example.pagamento.domain.pagamento.StatusPagamento;
import com.example.pagamento.domain.pagamento.dto.DadosAtualizarPagamento;
import com.example.pagamento.domain.pagamento.dto.DadosIncluirPagamento;
import com.example.pagamento.domain.pagamento.dto.DadosPagamento;
import com.example.pagamento.domain.pagamento.service.PagamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("pagamento")
public class PagamentoController {

    @Autowired
    PagamentoService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosPagamento> incluirPagamento(@RequestBody @Valid DadosIncluirPagamento dadosIncluirPagamento, UriComponentsBuilder uriComponentsBuilder) {
        var pagamentoIncluido = service.incluirPagamento(dadosIncluirPagamento);
        var uri = uriComponentsBuilder.path("pagamento/{id}").buildAndExpand(pagamentoIncluido.id()).toUri();
        return ResponseEntity.created(uri).body(pagamentoIncluido);
    }

    @GetMapping()
    public ResponseEntity<Page<DadosPagamento>> buscaPagamentos(@RequestParam(required = false) @Valid StatusPagamento status, @PageableDefault(size = 7) Pageable pageable) {
        var pagamentos = service.buscaPagamentos(status, pageable);
        return ResponseEntity.ok(pagamentos);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizarPagamento(@PathVariable String id, @RequestBody @Valid DadosAtualizarPagamento dados) {
        var pagamento = service.atualizarPagamento(id, dados);
        return ResponseEntity.ok(pagamento);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable String id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
