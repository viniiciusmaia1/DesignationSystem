package com.br.ativatelecom.designationSystem.repository;

import com.br.ativatelecom.designationSystem.entity.Designacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DesignacaoRepository extends JpaRepository<Designacao, Long> {
    Optional<Designacao> findByDesignacao(String designacao);
}
