package com.example.pagamento.domain.pagamento.entity;

import com.example.pagamento.domain.pagamento.Frequencia;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
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
    @FutureOrPresent
    private LocalDate dataFinal;
    @NotNull
    private Frequencia frequencia;

}
