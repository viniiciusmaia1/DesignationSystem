package com.br.ativatelecom.designationSystem.service;

import com.br.ativatelecom.designationSystem.dto.DesignacaoDTO;
import com.br.ativatelecom.designationSystem.entity.Cidade;
import com.br.ativatelecom.designationSystem.entity.Cliente;
import com.br.ativatelecom.designationSystem.entity.Designacao;
import com.br.ativatelecom.designationSystem.entity.Parceiro;
import com.br.ativatelecom.designationSystem.enuns.StatusEnum;
import com.br.ativatelecom.designationSystem.repository.DesignacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final ClienteService clienteService;
    private final ParceiroService parceiroService;

    @Autowired
    public DesignacaoService(DesignacaoRepository designacaoRepository, CidadeService cidadeService,
                             ClienteService clienteService, ParceiroService parceiroService) {
        this.designacaoRepository = designacaoRepository;
        this.cidadeService = cidadeService;
        this.clienteService = clienteService;
        this.parceiroService = parceiroService;
    }

    //Designacao:
    public DesignacaoDTO createDesignation(DesignacaoDTO dto) {

        UniqueDesignationVerification(dto.getDesignacao());

        Cliente cliente = clienteService.findOrCreateCliente(dto.getClienteNome());
        Cidade cidade = cidadeService.findOrCreateCidade(dto.getNomeCidade());
        Parceiro parceiro = parceiroService.findOrCreateParceiro(dto.getParceiroNome());

        Designacao designacao = new Designacao(dto.getDesignacao());
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

    public List<DesignacaoDTO> listAllWithLimit(int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        return designacaoRepository.findAll(pageable)
                .stream()
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


    //Status
    public DesignacaoDTO updateStatus(Long id, StatusEnum novoStatus) {
        Designacao designacao = designacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Designação não encontrada"));
        designacao.atualizarStatus(novoStatus);
        Designacao updatedDesignacao = designacaoRepository.save(designacao);
        return convertToDTO(updatedDesignacao);
    }

    public DesignacaoDTO updateParceiro(Long designacaoId, Long parceiroId) {
        Designacao existente = designacaoRepository.findById(designacaoId)
                .orElseThrow(() -> new RuntimeException("Designação não encontrada"));

        if (parceiroId == null) {
            throw new IllegalArgumentException("ID do parceiro não pode ser nulo");
        }

        Parceiro parceiro = parceiroService.findById(parceiroId);

        existente.setParceiro(parceiro);

        Designacao updatedDesignacao = designacaoRepository.save(existente);
        return convertToDTO(updatedDesignacao);
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

    //Dados cadastrais
    public DesignacaoDTO updateCadastralInfo(Long id, DesignacaoDTO dto) {
        Designacao existente = designacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Designação não encontrada"));

        boolean updated = false;

        if (dto.getClienteNome() != null && !dto.getClienteNome().isEmpty()) {
            Cliente cliente = clienteService.findOrCreateCliente(dto.getClienteNome());
            existente.setCliente(cliente);
            updated = true;
        }

        if (dto.getNomeCidade() != null && !dto.getNomeCidade().isEmpty()) {
            Cidade cidade = cidadeService.findOrCreateCidade(dto.getNomeCidade());
            existente.setCidade(cidade);
            updated = true;
        }

        if (dto.getParceiroNome() != null && !dto.getParceiroNome().isEmpty()) {
            Parceiro parceiro = parceiroService.findOrCreateParceiro(dto.getParceiroNome());
            existente.setParceiro(parceiro);
            updated = true;
        }

        if (updated) {
            Designacao updatedDesignacao = designacaoRepository.save(existente);
            return convertToDTO(updatedDesignacao);
        } else {
            throw new IllegalArgumentException("Nenhuma informação válida fornecida para atualização");
        }
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