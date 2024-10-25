package com.br.ativatelecom.designationSystem.service;

import com.br.ativatelecom.designationSystem.dto.CidadeDTO;
import com.br.ativatelecom.designationSystem.dto.DesignacaoDTO;
import com.br.ativatelecom.designationSystem.entity.Cidade;
import com.br.ativatelecom.designationSystem.entity.Designacao;
import com.br.ativatelecom.designationSystem.entity.Parceiro;
import com.br.ativatelecom.designationSystem.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;


    public List<Cidade> listAllCities() {
        return cidadeRepository.findAll();
    }

    private CidadeDTO convertToDTO(Cidade cidade) {
        CidadeDTO dto = new CidadeDTO();
        dto = dto.convertedToDTO(cidade);
        return dto;
    }

    private String formatarNomeCidade(String nomeCidade) {
        return removerAcentos(nomeCidade).toUpperCase();
    }

    private String removerAcentos(String nome) {
        return java.text.Normalizer.normalize(nome, java.text.Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");
    }
}
