package com.example.pagamento.domain.pagamento.validacoes;


import com.example.pagamento.domain.pagamento.entity.Pagamento;

public interface ValidadorRecorrenciaPagamento {

    void validar(Pagamento dados);

}
