package com.br.ativatelecom.designationSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.text.Normalizer;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Column(name = "designacao")
    private String designacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cidade", referencedColumnName = "id")
    private Cidade cidade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_produto", referencedColumnName = "id")
    private Produto produto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_status", referencedColumnName = "id")
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_parceiro", referencedColumnName = "id")
    private Parceiro parceiro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_responsavel", referencedColumnName = "id")
    private Responsavel responsavel;

    @Column(name = "dta_cadastro")
    private LocalDateTime dtaCadastro;

    @Column(name = "dta_ultima_modificacao")
    private LocalDateTime dtaUltimaModificacao;

    @OneToMany(mappedBy = "designacao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Observacao> observacoes = new ArrayList<>();

    @OneToMany(mappedBy = "designacao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Chamado> chamados = new ArrayList<>();

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

    @Column(name = "prazo")
    private Integer prazo;

    @Column(name = "mensalidade_parceiro")
    private Double mensalidadeParceiro;

    @Column(name = "mensalidade_os")
    private Double mensalidadeOs;

    @Column(name = "taxa_instalacao")
    private Double taxaInstalacao;

    @Column(name = "endereco")
    private String endereco;

    @Column(name = "endereco_servidor")
    private String enderecoServidor;

    @Column(name = "cvlan")
    private String cvlan;

    @Column(name = "ipwan")
    private String ipwan;

    @Column(name = "svlan")
    private String svlan;

    @Column(name = "velocidade")
    private Integer velocidade;

    public Designacao(String designacao, Cidade cidade, Produto produto) {
        this.designacao = designacao;
        this.cidade = cidade;
        this.produto = produto;
    }

    @PrePersist
    protected void onCreate() {
        this.dtaCadastro = LocalDateTime.now();
        this.dtaUltimaModificacao = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.dtaUltimaModificacao = LocalDateTime.now();
    }

    public void setCidade(Cidade cidade) {
        String nomeNormalizado = normalizarCidade(cidade.getNome());
        this.cidade.setNome(nomeNormalizado);
    }

    private String normalizarCidade(String nome) {
        nome = Normalizer.normalize(nome, Normalizer.Form.NFD);
        nome = nome.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return nome.toUpperCase();
    }
}