package com.br.ativatelecom.designationSystem.entity;

import com.br.ativatelecom.designationSystem.enuns.ProdutoEnum;
import com.br.ativatelecom.designationSystem.enuns.StatusEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "produto", nullable = false)
    private ProdutoEnum produtoEnum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cidade", referencedColumnName = "id", nullable = false)
    private Cidade cidade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_parceiro", referencedColumnName = "id", nullable = false)
    private Parceiro parceiro;

    @OneToMany(mappedBy = "designacao", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Observacao> observacoes;

    // Dados importantes
    private Integer cvlan;
    private Integer svlan;
    private String ipWan;
    private String circuitIp;

    // Dados de datas
    private LocalDateTime dataEnvioRb;
    private LocalDateTime dataAgendamento;
    private LocalDateTime dataAgendado;
    private LocalDateTime dataInstalacao;
    private LocalDateTime dataHomologacao;
    private LocalDateTime dataEntregaOi;


    public Designacao(String designacao, Cidade cidade, Parceiro parceiro, List<Observacao> observacoes) {
        this.designacao = designacao;
        this.cidade = cidade;
        this.dataCriacao = LocalDateTime.now();
        this.dataUltimaModificacao = LocalDateTime.now();
        this.status = StatusEnum.VIABILIDADE;
        this.parceiro = parceiro;
        this.observacoes = observacoes;
        this.produtoEnum = ProdutoEnum.VPN_VIP;

        if (observacoes != null) {
            for (Observacao obs : observacoes) {
                obs.setDesignacao(this);
            }
        }
    }

    public Designacao(String designacao) {
        this.designacao = designacao;
        this.dataCriacao = LocalDateTime.now();
        this.dataUltimaModificacao = LocalDateTime.now();
        this.status = StatusEnum.VIABILIDADE;
        this.observacoes = null;
        this.produtoEnum = ProdutoEnum.VPN_VIP;
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
            case ENTREGUE -> this.dataEntregaOi = agora;
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