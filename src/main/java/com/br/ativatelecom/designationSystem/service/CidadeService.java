package com.br.ativatelecom.designationSystem.service;

import com.br.ativatelecom.designationSystem.entity.Cidade;
import com.br.ativatelecom.designationSystem.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    public Cidade buscarOuCriarCidade(String nomeCidade) {
        String nomeFormatado = formatarNomeCidade(nomeCidade);

        Optional<Cidade> cidadeOptional = cidadeRepository.findByNomeIgnoreCase(nomeFormatado);

        if (cidadeOptional.isPresent()) {
            return cidadeOptional.get();
        }

        Cidade novaCidade = new Cidade();
        novaCidade.setNome(nomeFormatado);
        return cidadeRepository.save(novaCidade);
    }

    private String formatarNomeCidade(String nomeCidade) {
        return removerAcentos(nomeCidade).toUpperCase();
    }

    private String removerAcentos(String nome) {
        return java.text.Normalizer.normalize(nome, java.text.Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");
    }
}
