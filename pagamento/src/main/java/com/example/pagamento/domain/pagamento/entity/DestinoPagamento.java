package com.example.pagamento.domain.pagamento.entity;

import com.example.pagamento.domain.pagamento.PagamentoException;
import com.example.pagamento.domain.pagamento.TipoChave;
import com.example.pagamento.domain.pagamento.dto.DadosDestinoPagamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class DestinoPagamento {
    private String chavePix;
    private TipoChave tipoChave;

    public DestinoPagamento(DadosDestinoPagamento dadosDestinoPagamento) {
        var chave = dadosDestinoPagamento.chavePix();
        if (validarRegex("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$", chave)) {
            this.tipoChave = TipoChave.CPF;
        } else if (validarRegex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", chave)) {
            this.tipoChave = TipoChave.EMAIL;
        } else if (validarRegex("\\([0-9]+\\)\\s[0-9]+-[0-9]+", chave)) {
            this.tipoChave = TipoChave.TELEFONE;
        } else if (validarRegex("^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$", chave)) {
            this.tipoChave = TipoChave.ALEATORIA;
        } else {
            throw new PagamentoException("Chave pix não identificada");
        }
        this.chavePix = chave;
    }

    public boolean validarRegex(String regex, String chave) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(chave);
        return matcher.matches();
    }

}
