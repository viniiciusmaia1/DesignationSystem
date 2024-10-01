package com.br.ativatelecom.designationSystem.dto;

import com.br.ativatelecom.designationSystem.entity.Cliente;
import com.br.ativatelecom.designationSystem.entity.Designacao;
import com.br.ativatelecom.designationSystem.enuns.StatusEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy - HH:mm:ss")
    private LocalDateTime dataCriacao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy - HH:mm:ss")
    private LocalDateTime dataUltimaModificacao;

    //Cliente
    private String clienteNome;
    private Long clienteId;

    //Parceiro
    private String parceiroNome;
    private Long parceiroId;

    // Dados importantes
    private Integer cvlan;
    private Integer svlan;
    private String ipWan;
    private String circuitIp;

    // Dados gerenciais
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy - HH:mm:ss")
    private LocalDateTime dataEnvioRb;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy - HH:mm:ss")
    private LocalDateTime dataAgendamento;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy - HH:mm:ss")
    private LocalDateTime dataAgendado;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy - HH:mm:ss")
    private LocalDateTime dataInstalacao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy - HH:mm:ss")
    private LocalDateTime dataHomologacao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy - HH:mm:ss")
    private LocalDateTime dataEntregaOi;

    private DesignacaoDTO convertToDTO(Designacao designacao) {
        DesignacaoDTO dto = new DesignacaoDTO();
        dto.setId(designacao.getId());
        dto.setDesignacao(designacao.getDesignacao());
        dto.setNomeCidade(designacao.getCidade() != null ? designacao.getCidade().getNome() : null);
        dto.setStatus(designacao.getStatus());
        dto.setDataCriacao(designacao.getDataCriacao());
        dto.setDataUltimaModificacao(designacao.getDataUltimaModificacao());
        dto.setDataEnvioRb(designacao.getDataEnvioRb());
        dto.setDataAgendamento(designacao.getDataAgendamento());
        dto.setDataAgendado(designacao.getDataAgendado());
        dto.setDataInstalacao(designacao.getDataInstalacao());
        dto.setDataHomologacao(designacao.getDataHomologacao());
        dto.setDataEntregaOi(designacao.getDataEntregaOi());
        dto.setCvlan(designacao.getCvlan());
        dto.setSvlan(designacao.getSvlan());
        dto.setIpWan(designacao.getIpWan());
        dto.setCircuitIp(designacao.getCircuitIp());
        dto.setClienteNome(designacao.getCliente() != null ? designacao.getCliente().getNome() : null);
        dto.setClienteId(designacao.getCliente() != null ? designacao.getCliente().getId() : null);
        dto.setParceiroId(designacao.getParceiro().getId());
        dto.setParceiroNome(designacao.getParceiro() != null ? designacao.getParceiro().getNome() : null);

        return dto;
    }

    public DesignacaoDTO convertedToDTO(Designacao designacao) {
        return convertToDTO(designacao);
    }

    public DesignacaoDTO(Long id, String designacao, String nomeCidade, StatusEnum status, Cliente cliente, String nomeParceiro) {
        this.id = id;
        this.designacao = designacao;
        this.nomeCidade = nomeCidade;
        this.clienteNome = cliente.getNome();
        this.status = status;
        this.parceiroNome = nomeParceiro;
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
