package com.br.ativatelecom.designationSystem.service;

import com.br.ativatelecom.designationSystem.entity.Designacao;
import com.br.ativatelecom.designationSystem.repository.DesignacaoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DesignacaoService {

    @Autowired
    private DesignacaoRepository designacaoRepository;

    public Designacao criarDesignacao(Designacao designacao) {
        return designacaoRepository.save(designacao);
    }

    public Designacao buscarPorId(Long id) {
        return designacaoRepository.findById(id).orElseThrow(() -> new RuntimeException("Designação não encontrada"));
    }

    public Designacao atualizarDesignacao(Long id, Designacao designacao) {
        Designacao existente = buscarPorId(id);
        existente.setDesignacao(designacao.getDesignacao());
        return designacaoRepository.save(existente);
    }

    public void deletarDesignacao(Long id) {
        designacaoRepository.deleteById(id);
    }

    public List<Designacao> listarDesignacoes() {
        return designacaoRepository.findAll();
    }
}
