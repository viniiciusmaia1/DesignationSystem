package com.br.ativatelecom.designationSystem.repository;

import com.br.ativatelecom.designationSystem.entity.Designacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DesignacaoRepository extends JpaRepository<Designacao, Long> {
}
