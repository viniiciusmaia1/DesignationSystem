package com.br.ativatelecom.designationSystem.controller;

import com.br.ativatelecom.designationSystem.enuns.StatusEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/status")
public class StatusController {

    @GetMapping
    public List<String> listarStatus() {
        return Arrays.stream(StatusEnum.values())
                .map(StatusEnum::getStatus)
                .collect(Collectors.toList());
    }
}