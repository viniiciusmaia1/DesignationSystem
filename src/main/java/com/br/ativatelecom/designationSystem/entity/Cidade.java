package com.br.ativatelecom.designationSystem.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@Table(name = "CIDADE")
public class Cidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_cidade")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_estado", referencedColumnName = "id")
    private Estado estado;

    public Cidade(String nome) {
        this.nome = nome;
    }

    public Cidade() {

    }

    public void setNome(String nome) {
        this.nome = removerAcentos(nome).toUpperCase();
    }

    private String removerAcentos(String nomeCidade) {
        return java.text.Normalizer.normalize(nomeCidade, java.text.Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");
    }
}
