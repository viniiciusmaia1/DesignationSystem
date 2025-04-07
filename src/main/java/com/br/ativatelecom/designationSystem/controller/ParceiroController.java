package com.br.ativatelecom.designationSystem.controller;

import com.br.ativatelecom.designationSystem.dto.DesignacaoDTO;
import com.br.ativatelecom.designationSystem.dto.ParceiroDTO;
import com.br.ativatelecom.designationSystem.entity.Parceiro;
import com.br.ativatelecom.designationSystem.service.DesignacaoService;
import com.br.ativatelecom.designationSystem.service.ParceiroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Tag(name = "Parceiros", description = "Gerenciamento de parceiros e vínculo com designações")
@RestController
@RequestMapping("/api/parceiros")
public class ParceiroController {

    private final ParceiroService parceiroService;
    private final DesignacaoService designacaoService;

    @Autowired
    public ParceiroController(ParceiroService parceiroService, DesignacaoService designacaoService) {
        this.parceiroService = parceiroService;
        this.designacaoService = designacaoService;
    }

    @Operation(summary = "Lista todos os parceiros")
    @GetMapping
    public ResponseEntity<List<ParceiroDTO>> listarParceiros() {
        List<ParceiroDTO> parceirosDTO = parceiroService.listAllParceiros().stream()
                .map(parceiro -> new ParceiroDTO(parceiro.getId(), parceiro.getNome()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(parceirosDTO);
    }

    @Operation(summary = "Atualiza o parceiro de uma designação")
    @PutMapping("/{designacaoId}/parceiro")
    public ResponseEntity<DesignacaoDTO> atualizarParceiro(
            @Parameter(description = "ID da designação") @PathVariable Long designacaoId,
            @RequestBody Map<String, Long> payload) {
        try {
            Long parceiroId = payload.get("parceiroId");
            DesignacaoDTO updatedDesignacao = designacaoService.updateParceiro(designacaoId, parceiroId);
            return ResponseEntity.ok(updatedDesignacao);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

