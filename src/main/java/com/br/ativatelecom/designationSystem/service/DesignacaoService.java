package com.br.ativatelecom.designationSystem.service;

import com.br.ativatelecom.designationSystem.dto.DesignacaoDTO;
import com.br.ativatelecom.designationSystem.entity.Cidade;
import com.br.ativatelecom.designationSystem.entity.Cliente;
import com.br.ativatelecom.designationSystem.entity.Designacao;
import com.br.ativatelecom.designationSystem.entity.Parceiro;
import com.br.ativatelecom.designationSystem.enuns.StatusEnum;
import com.br.ativatelecom.designationSystem.repository.CidadeRepository;
import com.br.ativatelecom.designationSystem.repository.ClienteRepository;
import com.br.ativatelecom.designationSystem.repository.DesignacaoRepository;
import com.br.ativatelecom.designationSystem.repository.ParceiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DesignacaoService {

    private final DesignacaoRepository designacaoRepository;
    private final CidadeService cidadeService;
    private final ClienteRepository clienteRepository;
    private final ParceiroRepository parceiroRepository;

    @Autowired
    public DesignacaoService(DesignacaoRepository designacaoRepository, CidadeService cidadeService, ClienteRepository clienteRepository, ParceiroRepository parceiroRepository) {
        this.designacaoRepository = designacaoRepository;
        this.cidadeService = cidadeService;
        this.clienteRepository = clienteRepository;
        this.parceiroRepository = parceiroRepository;
    }

    //Designacao:
    public DesignacaoDTO createDesignation(DesignacaoDTO dto) {
        UniqueDesignationVerification(dto.getDesignacao());

        Cliente cliente = findOrCreateCliente(dto.getClienteNome());
        Cidade cidade = cidadeService.findOrCreateCidade(dto.getNomeCidade()); // Uso do novo serviço
        Parceiro parceiro = findOrCreateParceiro(dto.getParceiroNome());

        Designacao designacao = new Designacao();
        designacao.setDesignacao(dto.getDesignacao());
        designacao.setCidade(cidade);
        designacao.setCliente(cliente);
        designacao.setParceiro(parceiro);

        Designacao savedDesignacao = designacaoRepository.save(designacao);

        return convertToDTO(savedDesignacao);
    }

    public DesignacaoDTO findById(Long id) {
        Designacao designacao = designacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Designação não encontrada"));
        return convertToDTO(designacao);
    }


    public DesignacaoDTO updateDesignation(Long id, DesignacaoDTO dto) {
        Designacao existente = designacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Designação não encontrada"));
        if (!existente.getDesignacao().equals(dto.getDesignacao())) {
            UniqueDesignationVerification(dto.getDesignacao());
        }
        existente.setDesignacao(dto.getDesignacao());
        Cidade cidade = cidadeService.findOrCreateCidade(dto.getNomeCidade());
        existente.setCidade(cidade);
        Designacao updatedDesignacao = designacaoRepository.save(existente);
        return convertToDTO(updatedDesignacao);
    }

    public void deleteDesignation(Long id) {
        if (!designacaoRepository.existsById(id)) {
            throw new RuntimeException("Designação não encontrada");
        }
        designacaoRepository.deleteById(id);
    }

    public List<DesignacaoDTO> listAllDesignations(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Designacao> designacoesPage = designacaoRepository.findAll(pageable);
        return designacoesPage.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private void UniqueDesignationVerification(String nome) {
        if (designacaoRepository.findByDesignacao(nome).isPresent()) {
            throw new RuntimeException("Já existe uma designação com o nome: " + nome);
        }
    }

    private DesignacaoDTO convertToDTO(Designacao designacao) {
        DesignacaoDTO dto = new DesignacaoDTO();
        dto = dto.convertedToDTO(designacao);
        return dto;
    }

    //Cliente
    public DesignacaoDTO updateCliente(Long id, Long clienteId) {
        Designacao existente = designacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Designação não encontrada"));

        if (clienteId == null) {
            throw new IllegalArgumentException("ID do cliente não pode ser nulo");
        }

        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        existente.setCliente(cliente);

        Designacao updatedDesignacao = designacaoRepository.save(existente);
        return convertToDTO(updatedDesignacao);
    }

    private Cliente findOrCreateCliente(String nomeCliente) {
        return clienteRepository.findByNomeIgnoreCase(nomeCliente)
                .orElseGet(() -> {
                    Cliente novoCliente = new Cliente();
                    novoCliente.setNome(nomeCliente);
                    return clienteRepository.save(novoCliente);
                });
    }


    //Status
    public DesignacaoDTO updateStatus(Long id, StatusEnum novoStatus) {
        Designacao designacao = designacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Designação não encontrada"));
        designacao.atualizarStatus(novoStatus);
        Designacao updatedDesignacao = designacaoRepository.save(designacao);
        return convertToDTO(updatedDesignacao);
    }

    //Parceiro
    private Parceiro findOrCreateParceiro(String nomeParceiro) {
        return parceiroRepository.findByNomeIgnoreCase(nomeParceiro)
                .orElseGet(() -> {
                    Parceiro novoParceiro = new Parceiro();
                    novoParceiro.setNome(nomeParceiro);
                    return parceiroRepository.save(novoParceiro);
                });
    }

    public DesignacaoDTO updateParceiro(Long designacaoId, Long parceiroId) {
        Designacao existente = designacaoRepository.findById(designacaoId)
                .orElseThrow(() -> new RuntimeException("Designação não encontrada"));

        if (parceiroId == null) {
            throw new IllegalArgumentException("ID do parceiro não pode ser nulo");
        }

        Parceiro parceiro = parceiroRepository.findById(parceiroId)
                .orElseThrow(() -> new RuntimeException("Parceiro não encontrado"));

        existente.setParceiro(parceiro);

        Designacao updatedDesignacao = designacaoRepository.save(existente);
        return convertToDTO(updatedDesignacao);
    }

    public List<Parceiro> listAllParceiros() {
        return parceiroRepository.findAll();
    }

    //Dados tecnicos
    public DesignacaoDTO updateTechnicalsInfo(Long id, DesignacaoDTO dto) {
        Designacao existente = designacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Designação não encontrada"));

        if (dto.getCvlan() != null) {
            existente.setCvlan(dto.getCvlan());
        }
        if (dto.getSvlan() != null) {
            existente.setSvlan(dto.getSvlan());
        }
        if (dto.getIpWan() != null) {
            existente.setIpWan(dto.getIpWan());
        }
        if (dto.getCircuitIp() != null) {
            existente.setCircuitIp(dto.getCircuitIp());
        }

        Designacao updatedDesignacao = designacaoRepository.save(existente);
        return convertToDTO(updatedDesignacao);
    }

    //Agendamento
    public DesignacaoDTO updateAgendamento(Long id, LocalDateTime dataAgendado) {
        Designacao existente = designacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Designação não encontrada"));

        if (dataAgendado.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("A data agendada não pode ser anterior à data atual");
        }

        existente.setDataAgendado(dataAgendado);
        existente.setDataAgendamento(LocalDateTime.now());

        Designacao updatedDesignacao = designacaoRepository.save(existente);
        return convertToDTO(updatedDesignacao);
    }

}