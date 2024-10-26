package com.br.ativatelecom.designationSystem.service;

import com.br.ativatelecom.designationSystem.entity.Parceiro;
import com.br.ativatelecom.designationSystem.repository.ParceiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParceiroService {

    private final ParceiroRepository parceiroRepository;

    @Autowired
    public ParceiroService(ParceiroRepository parceiroRepository) {
        this.parceiroRepository = parceiroRepository;
    }

    public Parceiro findOrCreateParceiro(String nomeParceiro) {
        return parceiroRepository.findByNomeIgnoreCase(nomeParceiro)
                .orElseGet(() -> {
                    Parceiro novoParceiro = new Parceiro();
                    novoParceiro.setNome(nomeParceiro);
                    return parceiroRepository.save(novoParceiro);
                });
    }

    public List<Parceiro> listAllParceiros() {
        return parceiroRepository.findAll();
    }

    public Parceiro findById(Long id) {
        return parceiroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parceiro não encontrado"));
    }
}