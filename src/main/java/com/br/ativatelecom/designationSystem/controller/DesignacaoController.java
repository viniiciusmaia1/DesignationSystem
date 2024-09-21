package com.br.ativatelecom.designationSystem.controller;

import com.br.ativatelecom.designationSystem.entity.Designacao;
import com.br.ativatelecom.designationSystem.service.DesignacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/designacoes")
public class DesignacaoController {

    @Autowired
    private DesignacaoService designacaoService;

    @PostMapping
    public ResponseEntity<Designacao> criarDesignacao(@RequestBody Designacao designacao) {
        Designacao novaDesignacao = designacaoService.criarDesignacao(designacao);
        return ResponseEntity.ok(novaDesignacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Designacao> buscarDesignacao(@PathVariable Long id) {
        return ResponseEntity.ok(designacaoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Designacao> atualizarDesignacao(@PathVariable Long id, @RequestBody Designacao designacao) {
        Designacao designacaoAtualizada = designacaoService.atualizarDesignacao(id, designacao);
        return ResponseEntity.ok(designacaoAtualizada);
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


