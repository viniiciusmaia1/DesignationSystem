package com.br.ativatelecom.designationSystem.entity;

import com.br.ativatelecom.designationSystem.enuns.StatusEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "DESIGNACOES")
@Getter
@Setter
@NoArgsConstructor
public class Designacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "designacao", nullable = false)
    private String designacao;

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "data_ultima_modificacao")
    private LocalDateTime dataUltimaModificacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", referencedColumnName = "id", nullable = false)
    private Cliente cliente;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusEnum status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cidade", referencedColumnName = "id", nullable = false)
    private Cidade cidade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_parceiro", referencedColumnName = "id", nullable = false)
    private Parceiro parceiro;

    // Dados importantes
    private Integer cvlan;
    private Integer svlan;
    private String ipWan;
    private String circuitIp;

    // Dados gerenciais
    private LocalDateTime dataEnvioRb;
    private LocalDateTime dataAgendamento;
    private LocalDateTime dataAgendado;
    private LocalDateTime dataInstalacao;
    private LocalDateTime dataHomologacao;
    private LocalDateTime dataEntregaOi;

    public Designacao(String designacao, Cidade cidade, Parceiro parceiro) {
        this.designacao = designacao;
        this.cidade = cidade;
        this.dataCriacao = LocalDateTime.now();
        this.dataUltimaModificacao = LocalDateTime.now();
        this.status = StatusEnum.VIABILIDADE;
        this.parceiro = parceiro;
    }

    public Designacao(String designacao) {
        this.designacao = designacao;
        this.dataCriacao = LocalDateTime.now();
        this.dataUltimaModificacao = LocalDateTime.now();
        this.status = StatusEnum.VIABILIDADE;
    }

    @PreUpdate
    protected void onUpdate() {
        this.dataUltimaModificacao = LocalDateTime.now();
    }

    @PrePersist
    protected void onCreate() {
        this.dataCriacao = LocalDateTime.now();
        this.dataUltimaModificacao = LocalDateTime.now();
    }

    public void atualizarStatus(StatusEnum novoStatus) {
        this.status = novoStatus;
        this.dataUltimaModificacao = LocalDateTime.now();
        atualizarDatasPorStatus();
    }

    private void atualizarDatasPorStatus() {
        LocalDateTime agora = LocalDateTime.now();
        switch (this.status) {
            case AGENDADO -> this.dataAgendamento = agora;
            case INSTALADO -> this.dataInstalacao = agora;
            case HOMOLOGADO -> this.dataHomologacao = agora;
            case ENTREGUE_PORTAL_OI -> this.dataEntregaOi = agora;
            case ENVIO_RB -> this.dataEnvioRb = agora;
            case AGENDAMENTO -> this.dataAgendamento = agora;
        }
    }

    public String getCidadeNome() {
        return cidade != null ? cidade.getNome() : null;
    }

    public String getClienteNome() {
        return cliente != null ? cliente.getNome() : null;
    }

    public String getParceiroNome() {
        return parceiro != null ? parceiro.getNome() : null;
    }
}