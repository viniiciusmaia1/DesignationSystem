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
@Table(name = "CIDADE")
public class Cidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_cidade")
    private String nomeCidade;

    @ManyToOne
    @JoinColumn(name = "id_estado", referencedColumnName = "id")
    private Long idEstado;

}
