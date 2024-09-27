package com.br.ativatelecom.designationSystem.dto;

import com.br.ativatelecom.designationSystem.entity.Designacao;
import com.br.ativatelecom.designationSystem.enuns.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DesignacaoDTO {
    private Long id;
    private String designacao;
    private String nomeCidade;
    private StatusEnum status;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataUltimaModificacao;

    // Dados importantes
    private Integer cvlan;
    private Integer svlan;
    private Integer ipWan;
    private String circuitIp;

    // Dados gerenciais
    private LocalDateTime dataEnvioRb;
    private LocalDateTime dataAgendamento;
    private LocalDateTime dataAgendado;
    private LocalDateTime dataInstalacao;
    private LocalDateTime dataHomologacao;
    private LocalDateTime dataEntregaOi;


    public DesignacaoDTO(Designacao designacao) {
        this.id = designacao.getId();
        this.designacao = designacao.getDesignacao();
        this.nomeCidade = designacao.getCidade() != null ? designacao.getCidade().getNome() : null;
        this.status = designacao.getStatus();
        this.dataCriacao = designacao.getDataCriacao();
        this.dataUltimaModificacao = designacao.getDataUltimaModificacao();
        this.cvlan = designacao.getCvlan();
        this.svlan = designacao.getSvlan();
        this.ipWan = designacao.getIpWan();
        this.circuitIp = designacao.getCircuitIp();
        this.dataEnvioRb = designacao.getDataEnvioRb();
        this.dataAgendamento = designacao.getDataAgendamento();
        this.dataAgendado = designacao.getDataAgendado();
        this.dataInstalacao = designacao.getDataInstalacao();
        this.dataHomologacao = designacao.getDataHomologacao();
        this.dataEntregaOi = designacao.getDataEntregaOi();
    }


    public Designacao toEntity() {
        Designacao designacao = new Designacao();
        designacao.setDesignacao(this.designacao);
        designacao.setStatus(this.status);
        designacao.setCvlan(this.cvlan);
        designacao.setSvlan(this.svlan);
        designacao.setIpWan(this.ipWan);
        designacao.setCircuitIp(this.circuitIp);

        return designacao;
    }
}
