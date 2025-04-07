package com.br.ativatelecom.designationSystem.controller;

import com.br.ativatelecom.designationSystem.dto.ObservacaoDTO;
import com.br.ativatelecom.designationSystem.entity.Observacao;
import com.br.ativatelecom.designationSystem.service.ObservacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Observações", description = "Gerenciamento de observações das designações")
@RestController
@RequestMapping("/api/observacoes")
public class ObservacaoController {

    private final ObservacaoService observacaoService;

    @Autowired
    public ObservacaoController(ObservacaoService observacaoService) {
        this.observacaoService = observacaoService;
    }

    @Operation(summary = "Cria uma nova observação para uma designação")
    @PostMapping("/{designacaoId}")
    public ResponseEntity<Observacao> criarObservacao(
            @Parameter(description = "ID da designação") @PathVariable Long designacaoId,
            @RequestBody ObservacaoDTO dto) {
        Observacao nova = observacaoService.createObservation(designacaoId, dto.getTítulo(), dto.getTexto());
        return ResponseEntity.status(HttpStatus.CREATED).body(nova);
    }

    @Operation(summary = "Lista observações de uma designação")
    @GetMapping("/{designacaoId}")
    public ResponseEntity<List<Observacao>> listarObservacoes(
            @Parameter(description = "ID da designação") @PathVariable Long designacaoId) {
        List<Observacao> observacoes = observacaoService.listAllObservationsForDesignations(designacaoId);
        return ResponseEntity.ok(observacoes);
    }

    @Operation(summary = "Remove uma observação")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerObservao(@Parameter(description = "ID da observação") @PathVariable Long id) {
        observacaoService.deleteObservations(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Atualiza uma observação")
    @PutMapping("/{id}")
    public ResponseEntity<Observacao> atualizarObservacao(
            @Parameter(description = "ID da observação") @PathVariable Long id,
            @RequestBody ObservacaoDTO dto) {
        Observacao atualizada = observacaoService.updateDesignacao(id, dto.getTítulo(), dto.getTexto());
        return ResponseEntity.ok(atualizada);
    }
}