package com.example.pagamento.domain.pagamento.repository;

import com.example.pagamento.domain.pagamento.StatusPagamento;
import com.example.pagamento.domain.pagamento.entity.Pagamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface PagamentoRepository extends MongoRepository<Pagamento, String> {
    Page<Pagamento> findByStatusPagamento(StatusPagamento status, Pageable pageable);

    List<Pagamento> findByValorPagamentoAndDataPagamentoAndDestinoPagamentoChavePix(BigDecimal bigDecimal, LocalDate localDate, String s);
}