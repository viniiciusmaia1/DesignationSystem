package com.br.ativatelecom.designationSystem.controller;

import com.br.ativatelecom.designationSystem.dto.DesignacaoDTO;
import com.br.ativatelecom.designationSystem.others.UpdateStatusRequest;
import com.br.ativatelecom.designationSystem.service.DesignacaoService;
import com.br.ativatelecom.designationSystem.utils.LimitListValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Designações", description = "Gerenciamento completo de designações")
@RestController
@RequestMapping("/api/designacoes")
public class DesignacaoController {

    private final DesignacaoService designacaoService;

    @Autowired
    public DesignacaoController(DesignacaoService designacaoService) {
        this.designacaoService = designacaoService;
    }

    @Operation(summary = "Criar uma nova designação")
    @PostMapping
    public ResponseEntity<DesignacaoDTO> criarDesignacao(@RequestBody DesignacaoDTO request) {
        try {
            DesignacaoDTO novaDesignacao = designacaoService.createDesignation(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaDesignacao);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Buscar designação por ID")
    @GetMapping("/{id}")
    public ResponseEntity<DesignacaoDTO> buscarDesignacao(
            @Parameter(description = "ID da designação") @PathVariable Long id) {
        try {
            DesignacaoDTO designacao = designacaoService.findById(id);
            return ResponseEntity.ok(designacao);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Atualizar o status da designação")
    @PutMapping("/{id}/status")
    public ResponseEntity<DesignacaoDTO> atualizarStatus(
            @Parameter(description = "ID da designação") @PathVariable Long id,
            @RequestBody UpdateStatusRequest request) {
        try {
            DesignacaoDTO updatedDesignacao = designacaoService.updateStatus(id, request.getStatus());
            return ResponseEntity.ok(updatedDesignacao);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Deletar designação")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDesignacao(
            @Parameter(description = "ID da designação") @PathVariable Long id) {
        try {
            designacaoService.deleteDesignation(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Listar designações com paginação")
    @GetMapping
    public ResponseEntity<List<DesignacaoDTO>> listarDesignacoes(
            @RequestParam(defaultValue = "20") int limit) {

        if (!LimitListValidator.isLimiteValido(limit)) {
            return ResponseEntity.badRequest().build();
        }

        List<DesignacaoDTO> designacoes = designacaoService.listAllWithLimit(limit);
        return ResponseEntity.ok(designacoes);
    }

    @Operation(summary = "Atualizar dados técnicos da designação")
    @PutMapping("/{id}/dados-tecnicos")
    public ResponseEntity<DesignacaoDTO> atualizarDadosTecnicos(
            @Parameter(description = "ID da designação") @PathVariable Long id,
            @RequestBody DesignacaoDTO dto) {
        try {
            DesignacaoDTO updatedDesignacao = designacaoService.updateTechnicalsInfo(id, dto);
            return ResponseEntity.ok(updatedDesignacao);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Atualizar dados cadastrais da designação")
    @PutMapping("/{id}/dados-cadastrais")
    public ResponseEntity<DesignacaoDTO> atualizarInfoCadastral(
            @Parameter(description = "ID da designação") @PathVariable Long id,
            @RequestBody DesignacaoDTO dto) {
        try {
            DesignacaoDTO updatedDesignacao = designacaoService.updateCadastralInfo(id, dto);
            return ResponseEntity.ok(updatedDesignacao);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}