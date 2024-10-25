package com.br.ativatelecom.designationSystem.dto;

import com.br.ativatelecom.designationSystem.entity.Cidade;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CidadeDTO {

    private Long id;
    private String nome;

    public CidadeDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    private CidadeDTO convertToDTO(Cidade cidade)  {
        CidadeDTO dto = new CidadeDTO();
        dto.setId(cidade.getId());
        dto.setNome(cidade.getNome());
        return dto;
    }

    public CidadeDTO convertedToDTO(Cidade cidade) {
        return convertToDTO(cidade);
    }
}
