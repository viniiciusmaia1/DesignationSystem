package com.br.ativatelecom.designationSystem.service;

import com.br.ativatelecom.designationSystem.converter.GenericConverter;
import com.br.ativatelecom.designationSystem.dto.DesignacaoDTO;
import com.br.ativatelecom.designationSystem.entity.Designacao;
import com.br.ativatelecom.designationSystem.repository.DesignacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DesignacaoService {

    @Autowired
    private DesignacaoRepository designacaoRepository;

    @Autowired
    private GenericConverter genericConverter;

    public DesignacaoDTO criarDesignacao(DesignacaoDTO dto) {

        Designacao designacao = genericConverter.convertToEntity(dto, Designacao.class);

        designacao.setDtaCadastro(LocalDateTime.now());
        designacao.setDtaUltimaModificacao(LocalDateTime.now());

        Designacao savedEntity = designacaoRepository.save(designacao);

        return genericConverter.convertToDto(savedEntity, DesignacaoDTO.class);
    }
}
