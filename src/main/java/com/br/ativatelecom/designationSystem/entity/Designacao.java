package com.br.ativatelecom.designationSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "DESIGNACOES")
@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class Designacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne //x
    @JoinColumn(name = "id_cidade", referencedColumnName = "id")
    private Long idCidade;

    @Column(name = "id_produto")
    private Long idProduto;

    @Column(name = "id_status")
    private Long idStatus;

    @Column(name = "id_parceiro")
    private Long idParceiro;

    @Column(name = "id_funcionario")
    private Long idFuncionario;

    @Column(name = "dta_cadastro")
    private LocalDateTime dtaCadastro;

    @Column(name = "dta_ultima_modificacao")
    private LocalDateTime dtaUltimaModificacao;

    @ElementCollection
    private List<String> observacoes;

    @ElementCollection
    private List<String> chamados;

    @Column(name = "dta_instalacao")
    private LocalDateTime dtaInstalacao;

    @Column(name = "dta_homologacao")
    private LocalDateTime dtaHomologacao;

    @Column(name = "dta_entrega")
    private LocalDateTime dtaEntrega;

    @Column(name = "mensagem")
    private String mensagem;

    @Column(name = "nome_funcionario_oi")
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

