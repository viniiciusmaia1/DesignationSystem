package com.br.ativatelecom.designationSystem.controller;

import com.br.ativatelecom.designationSystem.dto.DesignacaoDTO;
import com.br.ativatelecom.designationSystem.dto.ParceiroDTO;
import com.br.ativatelecom.designationSystem.entity.Parceiro;
import com.br.ativatelecom.designationSystem.service.DesignacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/parceiros")
public class ParceiroController {

    private final DesignacaoService designacaoService;

    @Autowired
    public ParceiroController(DesignacaoService designacaoService) {
        this.designacaoService = designacaoService;
    }

    @GetMapping
    public ResponseEntity<List<ParceiroDTO>> listarParceiros() {
        List<Parceiro> parceiros = designacaoService.listAllParceiros();
        List<ParceiroDTO> parceirosDTO = parceiros.stream()
                .map(parceiro -> new ParceiroDTO(parceiro.getId(), parceiro.getNome()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(parceirosDTO);
    }

    @PutMapping("/{designacaoId}/parceiro")
    public ResponseEntity<DesignacaoDTO> atualizarParceiro(@PathVariable Long designacaoId, @RequestBody Map<String, Long> payload) {
        try {
            Long parceiroId = payload.get("parceiroId");
            DesignacaoDTO updatedDesignacao = designacaoService.updateParceiro(designacaoId, parceiroId);
            return ResponseEntity.ok(updatedDesignacao);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

