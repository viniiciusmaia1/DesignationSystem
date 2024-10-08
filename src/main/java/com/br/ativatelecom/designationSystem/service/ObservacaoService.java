package com.br.ativatelecom.designationSystem.service;

import com.br.ativatelecom.designationSystem.entity.Designacao;
import com.br.ativatelecom.designationSystem.entity.Observacao;
import com.br.ativatelecom.designationSystem.repository.ObservacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ObservacaoService {

    private final ObservacaoRepository observacaoRepository;

    @Autowired
    public ObservacaoService(ObservacaoRepository observacaoRepository) {
        this.observacaoRepository = observacaoRepository;
    }

    public Observacao criarObservacao(Long designacaoId, String titulo, String texto) {
        Observacao observacao = new Observacao();
        observacao.setTitulo(titulo);
        observacao.setObservacao(texto);
        observacao.setDataCriacao(LocalDateTime.now());
        observacao.setDataUltimaModificacao(LocalDateTime.now());

        Designacao designacao = new Designacao();
        designacao.setId(designacaoId);
        observacao.setDesignacao(designacao);
        return observacaoRepository.save(observacao);
    }

    public List<Observacao> listarObservacoesPorDesignacao(Long designacaoId) {
        return observacaoRepository.findByDesignacaoId(designacaoId);
    }

    public void removerObservacao(Long id) {
        if (!observacaoRepository.existsById(id)) {
            throw new RuntimeException("Observação não encontrada");
        }
        observacaoRepository.deleteById(id);
    }

    public Observacao atualizarObservacao(Long id, String titulo, String texto) {
        Observacao observacao = observacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Observação não encontrada"));
        observacao.setTitulo(titulo);
        observacao.setObservacao(texto);
        observacao.setDataUltimaModificacao(LocalDateTime.now());
        return observacaoRepository.save(observacao);
    }
}