package com.br.ativatelecom.designationSystem.controller;

import com.br.ativatelecom.designationSystem.dto.DesignacaoDTO;
import com.br.ativatelecom.designationSystem.service.DesignacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Agendamento", description = "Operações de agendamento de designações")
@RestController
@RequestMapping("/api/agendamento")
public class AgendamentoController {

    private final DesignacaoService designacaoService;

    @Autowired
    public AgendamentoController(DesignacaoService designacaoService) {
        this.designacaoService = designacaoService;
    }

    @Operation(summary = "Atualiza data de agendamento da designação")
    @PutMapping("/{id}")
    public ResponseEntity<DesignacaoDTO> atualizarAgendamento(
            @Parameter(description = "ID da designação") @PathVariable Long id,
            @RequestBody DesignacaoDTO dto) {
        try {
            DesignacaoDTO updatedDesignacao = designacaoService.updateAgendamento(id, dto.getDataAgendado());
            return ResponseEntity.ok(updatedDesignacao);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
