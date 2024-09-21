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

    public Designacao(String designacao) {
        this.designacao = designacao;
    }

    @PrePersist
    protected void onCreate() {
        this.dataCriacao = LocalDateTime.now();
        this.dtaUltimaModificacao = LocalDateTime.now();
        this.status= StatusEnum.VIABILIDADE;
    }

    @PreUpdate
    protected void onUpdate() {
        this.dtaUltimaModificacao = LocalDateTime.now();
    }
}