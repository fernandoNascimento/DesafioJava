package com.example.comprovante.domain.comprovante.repository;

import com.example.comprovante.domain.comprovante.entity.Comprovante;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComprovanteRepository extends MongoRepository<Comprovante, String> {
    Page<Comprovante> findByIdPagamento(String id, Pageable pageable);
}
