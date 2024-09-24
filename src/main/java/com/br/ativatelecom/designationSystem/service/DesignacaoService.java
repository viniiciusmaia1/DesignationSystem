package com.br.ativatelecom.designationSystem.service;

import com.br.ativatelecom.designationSystem.entity.Cidade;
import com.br.ativatelecom.designationSystem.entity.Designacao;
import com.br.ativatelecom.designationSystem.repository.CidadeRepository;
import com.br.ativatelecom.designationSystem.repository.DesignacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DesignacaoService {

    private final DesignacaoRepository designacaoRepository;
    private final CidadeRepository cidadeRepository;

    @Autowired
    public DesignacaoService(DesignacaoRepository designacaoRepository, CidadeRepository cidadeRepository) {
        this.designacaoRepository = designacaoRepository;
        this.cidadeRepository = cidadeRepository;
    }

    public Designacao criarDesignacao(Designacao designacao, String nomeCidade) {
        Cidade cidade = encontrarOuCriarCidade(nomeCidade);
        designacao.setCidade(cidade);
        return designacaoRepository.save(designacao);
    }

    public Designacao buscarPorId(Long id) {
        return designacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Designação não encontrada"));
    }

    public Designacao atualizarDesignacao(Long id, Designacao designacao) {
        Designacao existente = buscarPorId(id);
        existente.setDesignacao(designacao.getDesignacao());
        existente.setCidade(designacao.getCidade()); // Atualiza a cidade se necessário
        return designacaoRepository.save(existente);
    }

    public void deletarDesignacao(Long id) {
        if (!designacaoRepository.existsById(id)) {
            throw new RuntimeException("Designação não encontrada");
        }
        designacaoRepository.deleteById(id);
    }

    public List<Designacao> listarDesignacoes() {
        return designacaoRepository.findAll();
    }

    private Cidade encontrarOuCriarCidade(String nomeCidade) {
        return cidadeRepository.findByNomeIgnoreCase(nomeCidade)
                .orElseGet(() -> criarNovaCidade(nomeCidade));
    }

    private Cidade criarNovaCidade(String nomeCidade) {
        Cidade novaCidade = new Cidade();
        novaCidade.setNome(nomeCidade);
        return cidadeRepository.save(novaCidade);
    }
}
