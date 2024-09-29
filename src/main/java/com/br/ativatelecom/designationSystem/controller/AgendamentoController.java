package com.br.ativatelecom.designationSystem.controller;

import com.br.ativatelecom.designationSystem.dto.ClienteDTO;
import com.br.ativatelecom.designationSystem.dto.DesignacaoDTO;
import com.br.ativatelecom.designationSystem.entity.Cliente;
import com.br.ativatelecom.designationSystem.repository.ClienteRepository;
import com.br.ativatelecom.designationSystem.service.DesignacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clientes")
public class AgendamentoController {

    private final DesignacaoService designacaoService;

    @Autowired
    public AgendamentoController(DesignacaoService designacaoService) {
        this.designacaoService = designacaoService;
    }

    @PutMapping("/{id}/agendamento")
    public ResponseEntity<DesignacaoDTO> atualizarAgendamento(@PathVariable Long id, @RequestBody DesignacaoDTO dto) {
        try {
            DesignacaoDTO updatedDesignacao = designacaoService.atualizarAgendamento(id, dto.getDataAgendado());
            return ResponseEntity.ok(updatedDesignacao);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
