package com.gft.desafiomvc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.gft.desafiomvc.entities.Vacinacao;

import com.gft.desafiomvc.repositories.VacinacaoRepository;

@Service
public class VacinacaoService {
	
	@Autowired
	private VacinacaoRepository vacinacaoRepository;

	
	public Vacinacao salvarVacinacao(Vacinacao vacinacao) {
		
		return vacinacaoRepository.save(vacinacao);
	}

	public List<Vacinacao> listarVacinacao() {

		return vacinacaoRepository.findAll();
	}

	public Vacinacao obterVacinacao(Long id) throws Exception {

		Optional<Vacinacao> vacinacao = vacinacaoRepository.findById(id);

		if (vacinacao.isEmpty()) {
			throw new Exception("Vacinação não encontrada");
		}

		return vacinacao.get();

	}

	public void excluirVacinacao(Long id) {
		
		vacinacaoRepository.deleteById(id);
	}
}
