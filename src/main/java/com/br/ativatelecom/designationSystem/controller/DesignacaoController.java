package com.br.ativatelecom.designationSystem.controller;

import com.br.ativatelecom.designationSystem.dto.DesignacaoDTO;
import com.br.ativatelecom.designationSystem.others.UpdateStatusRequest;
import com.br.ativatelecom.designationSystem.service.DesignacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/designacoes")
public class DesignacaoController {

    private final DesignacaoService designacaoService;

    @Autowired
    public DesignacaoController(DesignacaoService designacaoService) {
        this.designacaoService = designacaoService;
    }

    @PostMapping
    public ResponseEntity<DesignacaoDTO> criarDesignacao(@RequestBody DesignacaoDTO request) {
        try {
            DesignacaoDTO novaDesignacao = designacaoService.criarDesignacao(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaDesignacao);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DesignacaoDTO> buscarDesignacao(@PathVariable Long id) {
        try {
            DesignacaoDTO designacao = designacaoService.buscarPorId(id);
            return ResponseEntity.ok(designacao);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<DesignacaoDTO> atualizarStatus(@PathVariable Long id, @RequestBody UpdateStatusRequest request) {
        try {
            DesignacaoDTO updatedDesignacao = designacaoService.atualizarStatus(id, request.getStatus());
            return ResponseEntity.ok(updatedDesignacao);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDesignacao(@PathVariable Long id) {
        try {
            designacaoService.deletarDesignacao(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<DesignacaoDTO>> listarDesignacoes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size) {
        List<DesignacaoDTO> designacoes = designacaoService.listarDesignacoes(page, size);
        return ResponseEntity.ok(designacoes);
    }
}