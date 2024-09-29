package com.br.ativatelecom.designationSystem.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO {
    private Long id;
    private String nome;

    public ClienteDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

}