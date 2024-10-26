package com.br.ativatelecom.designationSystem.service;

import com.br.ativatelecom.designationSystem.entity.Cidade;
import com.br.ativatelecom.designationSystem.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeService {

    private final CidadeRepository cidadeRepository;

    @Autowired
    public CidadeService(CidadeRepository cidadeRepository) {
        this.cidadeRepository = cidadeRepository;
    }

    public Cidade findOrCreateCidade(String nomeCidade) {
        return cidadeRepository.findByNomeIgnoreCase(nomeCidade)
                .orElseGet(() -> {
                    Cidade novaCidade = new Cidade();
                    novaCidade.setNome(nomeCidade);
                    return cidadeRepository.save(novaCidade);
                });
    }

    public List<Cidade> listAllCities() {
        return cidadeRepository.findAll();
    }

}
