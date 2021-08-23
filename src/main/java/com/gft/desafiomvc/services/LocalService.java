package com.gft.desafiomvc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.desafiomvc.entities.Local;

import com.gft.desafiomvc.repositories.LocalRepository;

@Service
public class LocalService {
	
	
	@Autowired
	private LocalRepository localRepository;
	
	
	public Local salvarLocal(Local local) {

		return localRepository.save(local);
	}

	public List<Local> listarLocais() {

		return localRepository.findAll();
	}

	public Local obterLocal(Long id) throws Exception {

		Optional<Local> local = localRepository.findById(id);

		if (local.isEmpty()) {
			throw new Exception("local não encontrado");
		}

		return local.get();

	}

	public void excluirLocal(Long id) {

		localRepository.deleteById(id);
	}
}


