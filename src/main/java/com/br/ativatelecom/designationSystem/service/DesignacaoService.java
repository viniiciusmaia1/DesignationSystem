package com.br.ativatelecom.designationSystem.service;

import com.br.ativatelecom.designationSystem.entity.Cidade;
import com.br.ativatelecom.designationSystem.entity.Designacao;
import com.br.ativatelecom.designationSystem.enuns.StatusEnum;
import com.br.ativatelecom.designationSystem.repository.CidadeRepository;
import com.br.ativatelecom.designationSystem.repository.DesignacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        validarDesignacaoUnica(designacao.getDesignacao());
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
        validarDesignacaoUnica(designacao.getDesignacao());
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

    private void validarDesignacaoUnica(String nome) {
        if (designacaoRepository.findByDesignacao(nome).isPresent()) {
            throw new RuntimeException("Já existe uma designação com o nome: " + nome);
        }
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

    public Designacao atualizarStatus(Long id, StatusEnum novoStatus) {
        Designacao designacao = designacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Designação não encontrada"));

        designacao.atualizarStatus(novoStatus);
        return designacaoRepository.save(designacao);
    }
}
