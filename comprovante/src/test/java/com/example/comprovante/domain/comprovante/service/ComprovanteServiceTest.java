package com.example.comprovante.domain.comprovante.service;

import com.example.comprovante.domain.comprovante.entity.Comprovante;
import com.example.comprovante.domain.comprovante.repository.ComprovanteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ComprovanteServiceTest {

    @InjectMocks
    private ComprovanteService service;

    @Mock
    private ComprovanteRepository comprovanteRepository;
    
    @Test
    void buscarComprovantes() {
        Pageable pageable = mock(Pageable.class);
        Page<Comprovante> comprovantes = Mockito.mock(Page.class);
        when(comprovanteRepository.findByIdPagamento(any(), any())).thenReturn(comprovantes);
        var idPagamento = UUID.randomUUID().toString();
        service.buscarComprovantes(idPagamento, pageable);
        Mockito.verify(comprovanteRepository, Mockito.times(1)).findByIdPagamento(idPagamento, pageable);
    }
}