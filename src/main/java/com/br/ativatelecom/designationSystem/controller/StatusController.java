package com.br.ativatelecom.designationSystem.controller;

import com.br.ativatelecom.designationSystem.enuns.StatusEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Status", description = "Consulta dos status possíveis de uma designação")
@RestController
@RequestMapping("/api/status")
public class StatusController {

    @Operation(summary = "Lista todos os status disponíveis")
    @GetMapping
    public List<String> listarStatus() {
        return Arrays.stream(StatusEnum.values())
                .map(StatusEnum::getStatus)
                .collect(Collectors.toList());
    }
}