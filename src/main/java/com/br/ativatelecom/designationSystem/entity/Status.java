package com.br.ativatelecom.designationSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "STATUS")
@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status")
    private String status;

    @Column(name = "dta_cadastro")
    private LocalDateTime dtaCadastro;

    @Column(name = "dta_ultima_modificacao")
    private LocalDateTime dtaUltimaModificacao;
}

