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


    private DesignacaoDTO convertToDTO(Designacao designacao) {
        DesignacaoDTO dto = new DesignacaoDTO();
        dto.setId(designacao.getId());
        dto.setDesignacao(designacao.getDesignacao());
        dto.setNomeCidade(designacao.getCidade() != null ? designacao.getCidade().getNome() : null);
        dto.setStatus(designacao.getStatus());
        dto.setDataCriacao(designacao.getDataCriacao());
        dto.setDataUltimaModificacao(designacao.getDataUltimaModificacao());
        dto.setCvlan(designacao.getCvlan());
        dto.setSvlan(designacao.getSvlan());
        dto.setIpWan(designacao.getIpWan());
        dto.setCircuitIp(designacao.getCircuitIp());
        dto.setDataEnvioRb(designacao.getDataEnvioRb());
        dto.setDataAgendamento(designacao.getDataAgendamento());
        dto.setDataAgendado(designacao.getDataAgendado());
        dto.setDataInstalacao(designacao.getDataInstalacao());
        dto.setDataHomologacao(designacao.getDataHomologacao());
        dto.setDataEntregaOi(designacao.getDataEntregaOi());
        return dto;
    }

    public DesignacaoDTO(Long id, String designacao, String nomeCidade, StatusEnum status) {
        this.id = id;
        this.designacao = designacao;
        this.nomeCidade = nomeCidade;
        this.status = status;
    }

    public Designacao toEntity() {
        Designacao designacao = new Designacao();
        designacao.setDesignacao(this.designacao);
        designacao.setStatus(this.status);
        designacao.setCvlan(this.cvlan);
        designacao.setSvlan(this.svlan);
        designacao.setIpWan(this.ipWan);
        designacao.setCircuitIp(this.circuitIp);
        designacao.setDataCriacao(this.dataCriacao);
        designacao.setDataInstalacao(this.dataInstalacao);

        return designacao;
    }
}
