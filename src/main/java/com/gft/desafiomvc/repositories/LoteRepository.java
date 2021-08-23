package com.gft.desafiomvc.repositories;




import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.gft.desafiomvc.entities.Lote;

@Repository
public interface LoteRepository extends JpaRepository<Lote, Long> {

	
}
