package com.br.ativatelecom.designationSystem.controller;

import com.br.ativatelecom.designationSystem.dto.DesignacaoDTO;
import com.br.ativatelecom.designationSystem.entity.Designacao;
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
            return ResponseEntity.badRequest().body(null); // Retorna 400 Bad Request em caso de erro
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Designacao> buscarDesignacao(@PathVariable Long id) {
        return ResponseEntity.ok(designacaoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Designacao> atualizarDesignacao(@PathVariable Long id, @RequestBody DesignacaoDTO request) {
        try {
            Designacao designacaoAtualizada = designacaoService.atualizarDesignacao(id, request.toDesignacao());
            return ResponseEntity.ok(designacaoAtualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null); // Retorna 400 Bad Request em caso de erro
        }
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
