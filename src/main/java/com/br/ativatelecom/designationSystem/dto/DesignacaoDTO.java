package com.br.ativatelecom.designationSystem.dto;

import com.br.ativatelecom.designationSystem.entity.Designacao;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class DesignacaoDTO {

    private String designacao;
    private String nomeCidade;

    public Designacao toDesignacao() {
        return new Designacao(this.designacao);
    }
}
