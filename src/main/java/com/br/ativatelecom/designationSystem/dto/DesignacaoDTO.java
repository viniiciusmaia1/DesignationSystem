package com.br.ativatelecom.designationSystem.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class DesignacaoDTO {

    private Long id;
    private Long fkCidade;
    private Long fkProblema;
    private Long fkStatus;
    private Long fkParceiro;
    private Long fkFuncionario;
    private LocalDateTime dtaCadastro;
    private LocalDateTime dtaUltimaModificacao;
    private List<String> observacoes;
    private List<String> chamados;
    private LocalDateTime dtaInstalacao;
    private LocalDateTime dtaHomologacao;
    private LocalDateTime dtaEntrega;
    private String desMensagem;
    private String nomFuncionarioOi;
    private Integer prazo;
    private Double mensalidadeParceiro;
    private Double mensalidadeOs;
    private Double taxaInstalacao;
    private String endereco;
    private String enderecoServidor;
    private String cvlan;
    private String ipwan;
    private String svlan;
    private Integer velocidade;
}
