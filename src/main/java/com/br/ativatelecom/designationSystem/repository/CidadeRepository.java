package com.br.ativatelecom.designationSystem.repository;

import com.br.ativatelecom.designationSystem.entity.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {
    Optional<Cidade> findByNomeIgnoreCase(String nome);
}