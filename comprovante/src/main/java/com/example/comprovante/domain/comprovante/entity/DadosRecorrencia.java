package com.example.comprovante.domain.comprovante.entity;

import com.example.comprovante.domain.comprovante.Frequencia;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class DadosRecorrencia {
    private LocalDate dataFinal;
    private Frequencia frequencia;

    public DadosRecorrencia(DadosRecorrencia dadosRecorrencia) {
        this.dataFinal = dadosRecorrencia.dataFinal;
        this.frequencia = dadosRecorrencia.frequencia;
    }
}
