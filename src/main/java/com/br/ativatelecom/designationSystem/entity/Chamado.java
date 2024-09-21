//package com.br.ativatelecom.designationSystem.entity;
//
//import jakarta.persistence.*;
//import lombok.Data;
//
//@Data
//@Entity
//@Table(name = "CHAMADOS")
//public class Chamado {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    @Column(name = "descricao", columnDefinition = "TEXT")
//    private String descricao;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "designacao_id", referencedColumnName = "id")
//    private Designacao designacao;
//
//    public Chamado() {}
//
//    public Chamado(String descricao, Designacao designacao) {
//        this.descricao = descricao;
//        this.designacao = designacao;
//    }
//}
