package com.gft.desafiomvc.services;



import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.desafiomvc.entities.Lote;
import com.gft.desafiomvc.repositories.LoteRepository;

@Service
public class LoteService {

	@Autowired
	private LoteRepository loteRepository;
	
	

	public Lote salvarLote(Lote lote) {
		lote.setQntRestante(lote.getQntRecebida());
		return loteRepository.save(lote);
	}

	public List<Lote> listarLotes() {
		
		return loteRepository.findAll();
	
	}
	
	public List<Lote> listarValidos(){
		List<Lote> todos = loteRepository.findAll();
		 
		Date now = new Date();
		List<Lote> validos = todos.stream().filter(i ->(i.getDataValidade().after(now) && i.getQntRestante()>0)).collect(Collectors.toList());
				
		return validos;
		
	}
	
	
	

	public Lote obterLote(Long id) throws Exception {

		Optional<Lote> lote = loteRepository.findById(id);

		if (lote.isEmpty()) {
			throw new Exception("Lote não encontrado");
		}

		return lote.get();

	}

	public void excluirLote(Long id) {

		loteRepository.deleteById(id);
	}
	
	public Lote diminuiVacina(Lote lote) {
			
		lote.setQntRestante(lote.getQntRestante()-1);
	
		return loteRepository.save(lote);
	}
	
}
