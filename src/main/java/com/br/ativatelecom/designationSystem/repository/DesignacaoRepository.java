package com.br.ativatelecom.designationSystem.repository;

import com.br.ativatelecom.designationSystem.dto.DesignacaoDTO;
import com.br.ativatelecom.designationSystem.entity.Designacao;
import com.br.ativatelecom.designationSystem.enuns.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface DesignacaoRepository extends JpaRepository<Designacao, Long> {
    Optional<Designacao> findByDesignacao(String designacao);
    List<Designacao> findByCidade_Nome(String nomeCidade);
    List<Designacao> findByStatus(StatusEnum status);
    List<Designacao> findByDataCriacaoBetween(LocalDateTime inicio, LocalDateTime fim);

    @Query("SELECT d FROM Designacao d WHERE d.cidade.nome = :nomeCidade AND d.status = :status")
    List<Designacao> findByCidadeNomeAndStatus(@Param("nomeCidade") String nomeCidade, @Param("status") StatusEnum status);

    @Query("SELECT new com.br.ativatelecom.designationSystem.dto.DesignacaoDTO(d.id, d.designacao, c.nome, d.status) FROM Designacao d JOIN d.cidade c")
    List<DesignacaoDTO> findAllDesignacoesWithCidade();
}