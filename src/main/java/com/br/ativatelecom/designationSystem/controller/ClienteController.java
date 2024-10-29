package com.br.ativatelecom.designationSystem.controller;

import com.br.ativatelecom.designationSystem.dto.ClienteDTO;
import com.br.ativatelecom.designationSystem.entity.Cliente;
import com.br.ativatelecom.designationSystem.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listarClientes() {
        List<Cliente> clientes = clienteService.findAll();
        List<ClienteDTO> clienteDTOs = clientes.stream()
                .map(cliente -> new ClienteDTO(cliente.getId(), cliente.getNome()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(clienteDTOs);
    }

    @PutMapping("/{id}/cliente")
    public ResponseEntity<ClienteDTO> atualizarCliente(@PathVariable Long id, @RequestBody Map<String, Long> payload) {
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