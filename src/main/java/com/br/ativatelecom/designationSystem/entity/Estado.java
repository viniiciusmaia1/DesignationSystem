package com.br.ativatelecom.designationSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@Entity
@Getter
@EqualsAndHashCode
@AllArgsConstructor
@Table(name = "ESTADO")
public class Estado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_estado")
    private String nomeEstado;

    @Column(name = "siglaEstado")
    private String siglaEstado;

}