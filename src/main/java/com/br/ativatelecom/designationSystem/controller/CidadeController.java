package com.br.ativatelecom.designationSystem.controller;

import com.br.ativatelecom.designationSystem.dto.CidadeDTO;
import com.br.ativatelecom.designationSystem.entity.Cidade;
import com.br.ativatelecom.designationSystem.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cidades")
public class CidadeController {
    private final CidadeService cidadeService;

    @Autowired
    public CidadeController(CidadeService cidadeService) {
        this.cidadeService = cidadeService;
    }

    @GetMapping
    public ResponseEntity<List<CidadeDTO>> listarcidades() {
        List<Cidade> cidades = cidadeService.listAllCities();
        List<CidadeDTO> cidadesDTO = cidades.stream()
                .map(cidade -> new CidadeDTO(cidade.getId(), cidade.getNome()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(cidadesDTO);
    }
}
