package com.br.ativatelecom.designationSystem.controller;

import com.br.ativatelecom.designationSystem.dto.DesignacaoDTO;
import com.br.ativatelecom.designationSystem.entity.Designacao;
import com.br.ativatelecom.designationSystem.others.UpdateStatusRequest;
import com.br.ativatelecom.designationSystem.service.DesignacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/designacoes")
public class DesignacaoController {

    private final DesignacaoService designacaoService;

    @Autowired
    public DesignacaoController(DesignacaoService designacaoService) {
        this.designacaoService = designacaoService;
    }

    @PostMapping
    public ResponseEntity<Designacao> criarDesignacao(@RequestBody DesignacaoDTO request) {
        try {
            Designacao novaDesignacao = designacaoService.criarDesignacao(request.toDesignacao(), request.getNomeCidade());
            return ResponseEntity.ok(novaDesignacao);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Designacao> buscarDesignacao(@PathVariable Long id) {
        return ResponseEntity.ok(designacaoService.buscarPorId(id));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Designacao> atualizarStatus(@PathVariable Long id, @RequestBody UpdateStatusRequest request) {
        Designacao updatedDesignacao = designacaoService.atualizarStatus(id, request.getStatus());
        return ResponseEntity.ok(updatedDesignacao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDesignacao(@PathVariable Long id) {
        designacaoService.deletarDesignacao(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Designacao>> listarDesignacoes() {
        return ResponseEntity.ok(designacaoService.listarDesignacoes());
    }
}
