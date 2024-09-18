package com.br.ativatelecom.designationSystem.service;

import com.br.ativatelecom.designationSystem.entity.Cidade;
import com.br.ativatelecom.designationSystem.entity.Designacao;
import com.br.ativatelecom.designationSystem.repository.CidadeRepository;
import com.br.ativatelecom.designationSystem.repository.DesignacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DesignacaoService {

    @Autowired
    private DesignacaoRepository designacaoRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    public Designacao createDesignacao(Designacao designacao) {

        String nomeCidade = designacao.getCidade().getNome();
        Optional<Cidade> cidade = cidadeRepository.findByNomeIgnoreCase(nomeCidade);
        if (cidade.isPresent()) {
            designacao.setCidade(cidade.get());
        } else {
            throw new RuntimeException("Cidade não encontrada: " + nomeCidade);
        }

        return designacaoRepository.save(designacao);
    }

    public List<Designacao> findAll() {
        return designacaoRepository.findAll();
    }

    public Optional<Designacao> findById(Long id) {
        return designacaoRepository.findById(id);
    }

    public Designacao updateDesignacao(Long id, Designacao updatedDesignacao) {
        Optional<Designacao> designacao = designacaoRepository.findById(id);
        if (designacao.isPresent()) {
            Designacao existingDesignacao = designacao.get();
            existingDesignacao.setDesignacao(updatedDesignacao.getDesignacao());
            existingDesignacao.setProduto(updatedDesignacao.getProduto());
            existingDesignacao.setStatus(updatedDesignacao.getStatus());
            existingDesignacao.setParceiro(updatedDesignacao.getParceiro());
            return designacaoRepository.save(existingDesignacao);
        } else {
            throw new RuntimeException("Designação não encontrada");
        }
    }

    public void deleteDesignacao(Long id) {
        designacaoRepository.deleteById(id);
    }

    public List<Designacao> getAllDesignacoes() {
        return designacaoRepository.findAll();
    }
}
