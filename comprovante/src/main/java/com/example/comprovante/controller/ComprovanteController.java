package com.example.comprovante.controller;

import com.example.comprovante.domain.comprovante.dto.DadosComprovante;
import com.example.comprovante.domain.comprovante.service.ComprovanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("comprovante")
public class ComprovanteController {

    @Autowired
    private ComprovanteService service;

    @GetMapping("/{idPagamento}")
    public ResponseEntity<Page<DadosComprovante>> buscarComprovantes(@PathVariable String idPagamento, @PageableDefault(size = 7) Pageable pageable) {
        var comprovantes = service.buscarComprovantes(idPagamento, pageable);
        return ResponseEntity.ok(comprovantes);
    }
}
