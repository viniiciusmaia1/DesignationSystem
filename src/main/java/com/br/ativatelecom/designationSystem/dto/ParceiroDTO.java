package com.br.ativatelecom.designationSystem.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParceiroDTO {

        private Long id;
        private String nome;

        public ParceiroDTO(Long id, String nome) {
            this.id = id;
            this.nome = nome;
        }
}

