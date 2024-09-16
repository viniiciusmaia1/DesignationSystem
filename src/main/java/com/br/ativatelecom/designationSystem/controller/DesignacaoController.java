package com.br.ativatelecom.designationSystem.controller;

import com.br.ativatelecom.designationSystem.dto.DesignacaoDTO;
import com.br.ativatelecom.designationSystem.service.DesignacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/designacoes")
public class DesignacaoController {

    @Autowired
    private DesignacaoService designacaoService;

    @PostMapping
    public ResponseEntity<DesignacaoDTO> criarDesignacao(@RequestBody DesignacaoDTO dto) {
        DesignacaoDTO responseDto = designacaoService.criarDesignacao(dto);
        return ResponseEntity.ok(responseDto);
    }
}


