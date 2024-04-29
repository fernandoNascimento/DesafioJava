package com.example.comprovante.domain.comprovante.entity;

import com.example.comprovante.domain.comprovante.TipoChave;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class DestinoPagamento {
    private String chavePix;
    private TipoChave tipoChave;

    public DestinoPagamento(DestinoPagamento destinoPagamento) {
        this.chavePix = destinoPagamento.chavePix;
        this.tipoChave = destinoPagamento.tipoChave;
    }
}
