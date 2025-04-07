package com.br.ativatelecom.designationSystem.controller;

import com.br.ativatelecom.designationSystem.dto.ClienteDTO;
import com.br.ativatelecom.designationSystem.entity.Cliente;
import com.br.ativatelecom.designationSystem.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Tag(name = "Clientes", description = "Gerenciamento de clientes")
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Operation(summary = "Lista todos os clientes")
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listarClientes() {
        List<ClienteDTO> clienteDTOs = clienteService.findAll().stream()
                .map(cliente -> new ClienteDTO(cliente.getId(), cliente.getNome()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(clienteDTOs);
    }

    @Operation(summary = "Atualiza cliente vinculado a uma designação")
    @PutMapping("/{id}/cliente")
    public ResponseEntity<ClienteDTO> atualizarCliente(
            @Parameter(description = "ID da designação") @PathVariable Long id,
            @RequestBody Map<String, Long> payload) {
        try {
            Long novoClienteId = payload.get("clienteId");
            ClienteDTO updatedCliente = clienteService.updateCliente(id, novoClienteId);
            return ResponseEntity.ok(updatedCliente);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ClienteDTO(null, e.getMessage()));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}