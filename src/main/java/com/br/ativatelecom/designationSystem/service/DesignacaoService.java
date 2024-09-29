package com.br.ativatelecom.designationSystem.service;

import com.br.ativatelecom.designationSystem.dto.DesignacaoDTO;
import com.br.ativatelecom.designationSystem.entity.Cidade;
import com.br.ativatelecom.designationSystem.entity.Designacao;
import com.br.ativatelecom.designationSystem.enuns.StatusEnum;
import com.br.ativatelecom.designationSystem.repository.CidadeRepository;
import com.br.ativatelecom.designationSystem.repository.DesignacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DesignacaoService {

    private final DesignacaoRepository designacaoRepository;
    private final CidadeRepository cidadeRepository;

    @Autowired
    public DesignacaoService(DesignacaoRepository designacaoRepository, CidadeRepository cidadeRepository) {
        this.designacaoRepository = designacaoRepository;
        this.cidadeRepository = cidadeRepository;
    }

    public DesignacaoDTO criarDesignacao(DesignacaoDTO dto) {
        validarDesignacaoUnica(dto.getDesignacao());
        Cidade cidade = encontrarOuCriarCidade(dto.getNomeCidade());
        Designacao designacao = new Designacao(dto.getDesignacao());
        designacao.setCidade(cidade);
        Designacao savedDesignacao = designacaoRepository.save(designacao);
        System.out.println("Nome da cidade recebido: " + dto.getNomeCidade());
        return convertToDTO(savedDesignacao);
    }

    public DesignacaoDTO buscarPorId(Long id) {
        Designacao designacao = designacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Designação não encontrada"));
        return convertToDTO(designacao);
    }

    public DesignacaoDTO atualizarDesignacao(Long id, DesignacaoDTO dto) {
        Designacao existente = designacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Designação não encontrada"));
        if (!existente.getDesignacao().equals(dto.getDesignacao())) {
            validarDesignacaoUnica(dto.getDesignacao());
        }
        existente.setDesignacao(dto.getDesignacao());
        Cidade cidade = encontrarOuCriarCidade(dto.getNomeCidade());
        existente.setCidade(cidade);
        Designacao updatedDesignacao = designacaoRepository.save(existente);
        return convertToDTO(updatedDesignacao);
    }

    public void deletarDesignacao(Long id) {
        if (!designacaoRepository.existsById(id)) {
            throw new RuntimeException("Designação não encontrada");
        }
        designacaoRepository.deleteById(id);
    }

    public List<DesignacaoDTO> listarDesignacoes(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Designacao> designacoesPage = designacaoRepository.findAll(pageable);
        return designacoesPage.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public DesignacaoDTO atualizarStatus(Long id, StatusEnum novoStatus) {
        Designacao designacao = designacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Designação não encontrada"));
        designacao.atualizarStatus(novoStatus);
        Designacao updatedDesignacao = designacaoRepository.save(designacao);
        return convertToDTO(updatedDesignacao);
    }

    private void validarDesignacaoUnica(String nome) {
        if (designacaoRepository.findByDesignacao(nome).isPresent()) {
            throw new RuntimeException("Já existe uma designação com o nome: " + nome);
        }
    }

    private Cidade encontrarOuCriarCidade(String nomeCidade) {
        return cidadeRepository.findByNomeIgnoreCase(nomeCidade)
                .orElseGet(() -> {
                    Cidade novaCidade = new Cidade();
                    novaCidade.setNome(nomeCidade);
                    return cidadeRepository.save(novaCidade);
                });
    }

    private DesignacaoDTO convertToDTO(Designacao designacao) {
        DesignacaoDTO dto = new DesignacaoDTO();
        dto = dto.convertedToDTO(designacao);
        return dto;
    }

    public DesignacaoDTO atualizarDadosTecnicos(Long id, DesignacaoDTO dto) {
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
}