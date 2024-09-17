package com.br.ativatelecom.designationSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "RESPONSAVEL")
@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class Responsavel {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "nome")
        private String nome;

        @Column(name = "dta_cadastro")
        private LocalDateTime dtaCadastro;

        @Column(name = "dta_ultima_modificacao")
        private LocalDateTime dtaUltimaModificacao;

}

