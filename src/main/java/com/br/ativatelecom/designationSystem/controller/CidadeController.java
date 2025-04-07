package com.br.ativatelecom.designationSystem.controller;

import com.br.ativatelecom.designationSystem.dto.CidadeDTO;
import com.br.ativatelecom.designationSystem.entity.Cidade;
import com.br.ativatelecom.designationSystem.service.CidadeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Cidades", description = "Consulta de cidades disponíveis")
@RestController
@RequestMapping("/api/cidades")
public class CidadeController {

    private final CidadeService cidadeService;

    @Autowired
    public CidadeController(CidadeService cidadeService) {
        this.cidadeService = cidadeService;
    }

    @Operation(summary = "Lista todas as cidades cadastradas")
    @GetMapping
    public ResponseEntity<List<CidadeDTO>> listarcidades() {
        List<CidadeDTO> cidadesDTO = cidadeService.listAllCities().stream()
                .map(cidade -> new CidadeDTO(cidade.getId(), cidade.getNome()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(cidadesDTO);
    }
}
