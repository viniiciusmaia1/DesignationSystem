package com.br.ativatelecom.designationSystem.service;

import com.br.ativatelecom.designationSystem.dto.ClienteDTO;
import com.br.ativatelecom.designationSystem.entity.Cliente;
import com.br.ativatelecom.designationSystem.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente findOrCreateCliente(String nomeCliente) {
        return clienteRepository.findByNome(nomeCliente)
                .orElseGet(() -> {
                    Cliente novoCliente = new Cliente();
                    novoCliente.setNome(nomeCliente);
                    novoCliente.setDataCriacao(LocalDateTime.now());
                    novoCliente.setDataUltimaModificacao(LocalDateTime.now());

                    return clienteRepository.save(novoCliente);
                });
    }

    public Cliente findById(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public ClienteDTO updateCliente(Long id, Long novoClienteId) {
        Cliente existente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        if (novoClienteId == null) {
            throw new IllegalArgumentException("ID do novo cliente não pode ser nulo");
        }

        if (id.equals(novoClienteId)) {
            throw new IllegalArgumentException("O novo ID do cliente deve ser diferente do ID atual");
        }

        Cliente novoCliente = clienteRepository.findById(novoClienteId)
                .orElseThrow(() -> new RuntimeException("Novo cliente não encontrado"));

        existente.setNome(novoCliente.getNome());

        Cliente clienteAtualizado = clienteRepository.save(existente);
        return convertToDTO(clienteAtualizado);
    }

    private ClienteDTO convertToDTO(Cliente cliente) {
        return new ClienteDTO(cliente.getId(), cliente.getNome());
    }
}