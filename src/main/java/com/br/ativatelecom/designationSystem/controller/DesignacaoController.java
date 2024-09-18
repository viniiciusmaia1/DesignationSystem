package com.br.ativatelecom.designationSystem.controller;

import com.br.ativatelecom.designationSystem.entity.Designacao;
import com.br.ativatelecom.designationSystem.service.DesignacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/designacoes")
public class DesignacaoController {

    @Autowired
    private final DesignacaoService designacaoService;

    public DesignacaoController(DesignacaoService designacaoService) {
        this.designacaoService = designacaoService;
    }

    @PostMapping
    public ResponseEntity<Designacao> createDesignacao(@RequestBody Designacao designacao) {
        return ResponseEntity.ok(designacaoService.createDesignacao(designacao));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Designacao> updateDesignacao(@PathVariable Long id, @RequestBody Designacao designacao) {
        return ResponseEntity.ok(designacaoService.updateDesignacao(id, designacao));
    }

    @GetMapping
    public ResponseEntity<List<Designacao>> getAllDesignacoes() {
        return ResponseEntity.ok(designacaoService.getAllDesignacoes());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDesignacao(@PathVariable Long id) {
        designacaoService.deleteDesignacao(id);
        return ResponseEntity.noContent().build();
    }
}



