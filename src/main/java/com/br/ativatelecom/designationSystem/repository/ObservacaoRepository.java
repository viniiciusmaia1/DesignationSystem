package com.br.ativatelecom.designationSystem.repository;

import com.br.ativatelecom.designationSystem.entity.Observacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObservacaoRepository extends JpaRepository<Observacao, Long> {
    List<Observacao> findByDesignacaoId(Long designacaoId);
}
