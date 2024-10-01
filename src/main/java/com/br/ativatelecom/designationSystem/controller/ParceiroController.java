package com.br.ativatelecom.designationSystem.controller;

import com.br.ativatelecom.designationSystem.dto.ParceiroDTO;
import com.br.ativatelecom.designationSystem.entity.Parceiro;
import com.br.ativatelecom.designationSystem.repository.ParceiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/parceiros")
public class ParceiroController {

    private final ParceiroRepository parceiroRepository;

    @Autowired
    public ParceiroController(ParceiroRepository parceiroRepository) {
        this.parceiroRepository = parceiroRepository;
    }

    @GetMapping
    public ResponseEntity<List<ParceiroDTO>> listarParceiros() {
        List<Parceiro> parceiros = parceiroRepository.findAll();
        List<ParceiroDTO> parceirosDTO = parceiros.stream()
                .map(parceiro -> new ParceiroDTO(parceiro.getId(), parceiro.getNome()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(parceirosDTO);
    }
}

