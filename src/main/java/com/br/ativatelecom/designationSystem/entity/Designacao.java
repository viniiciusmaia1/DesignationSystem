package com.br.ativatelecom.designationSystem.entity;

import com.br.ativatelecom.designationSystem.enuns.StatusEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "DESIGNACOES")
@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class Designacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "designacao")
    private String designacao;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @Column(name = "dta_ultima_modificacao")
    private LocalDateTime dtaUltimaModificacao;

    @Column(name = "status")
    private StatusEnum status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cidade", referencedColumnName = "id")
    private Cidade cidade;

    // Dados importantes

    @Column(name = "cvlan")
    private Integer cvlan;

    @Column(name = "svlan")
    private Integer svlan;

    @Column(name = "ip_wan")
    private Integer ipwan;

    @Column(name = "ip")
    private String circuitIp;

    // Dados gerenciais

    @Column(name = "dta_envio_rb")
    private LocalDateTime dtaEnvioRb;

    @Column(name = "dta_agendamento")
    private LocalDateTime dtaAgendamento;

    @Column(name = "dta_agendado")
    private LocalDateTime dtaAgendado;

    @Column(name = "dta_instalacao")
    private LocalDateTime dtaInstalacao;

    @Column(name = "dta_homologacao")
    private LocalDateTime dtaHomologacao;

    @Column(name = "dta_entrega_oi")
    private LocalDateTime dtaEntregaOi;

    //

    public Designacao(String designacao) {
        this.designacao = designacao;
    }

    @PrePersist
    protected void onCreate() {
        this.dataCriacao = LocalDateTime.now();
        this.dtaUltimaModificacao = LocalDateTime.now();
        this.status = StatusEnum.VIABILIDADE;
    }

    @PreUpdate
    protected void onUpdate() {
        this.dtaUltimaModificacao = LocalDateTime.now();
    }

    public void atualizarStatus(StatusEnum novoStatus) {

//        if (this.status == StatusEnum.INSTALADO && novoStatus == StatusEnum.AGENDADO) {
//            throw new IllegalArgumentException("Não é permitido voltar para o estado AGENDADO a partir de INSTALADO.");
//        }

        this.status = novoStatus;
        this.atualizarDatasPorStatus();
    }

    private void atualizarDatasPorStatus() {
        LocalDateTime agora = LocalDateTime.now();
        switch (this.status) {
            case AGENDADO:
                this.dtaAgendado = agora;
                break;
            case INSTALADO:
                this.dtaInstalacao = agora;
                break;
            case HOMOLOGADO:
                this.dtaHomologacao = agora;
                break;
            case ENTREGUE_PORTAL_OI:
                this.dtaEntregaOi = agora;
                break;
            default:
                break;
        }
        this.dtaUltimaModificacao = agora;
    }
}