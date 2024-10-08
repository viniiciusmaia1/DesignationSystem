package com.br.ativatelecom.designationSystem.controller;

import com.br.ativatelecom.designationSystem.dto.ObservacaoDTO;
import com.br.ativatelecom.designationSystem.entity.Observacao;
import com.br.ativatelecom.designationSystem.service.ObservacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/observacoes")
public class ObservacaoController {

    private final ObservacaoService observacaoService;

    @Autowired
    public ObservacaoController(ObservacaoService observacaoService) {
        this.observacaoService = observacaoService;
    }

    @PostMapping("/{designacaoId}")
    public ResponseEntity<Observacao> criarObservacao(@PathVariable Long designacaoId,
                                                      @RequestBody ObservacaoDTO dto) {
        Observacao novaObservao = observacaoService.createObservation(designacaoId, dto.getTítulo(), dto.getTexto());
        return ResponseEntity.status(HttpStatus.CREATED).body(novaObservao);
    }

    @GetMapping("/{designaoId}")
    public ResponseEntity<List<Observacao>> listarObservaes(@PathVariable Long designaoId) {
        List<Observacao> observaes = observacaoService.listAllObservationsForDesignations(designaoId);
        return ResponseEntity.ok(observaes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerObservao(@PathVariable Long id) {
        observacaoService.deleteObservations(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Observacao> atualizarobservao(@PathVariable Long id,
                                                      @RequestBody ObservacaoDTO dto) {
        Observacao updatedobservao = observacaoService.updateDesignacao(id, dto.getTítulo(), dto.getTexto());
        return ResponseEntity.ok(updatedobservao);
    }
}