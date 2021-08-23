package com.gft.desafiomvc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.desafiomvc.entities.Local;

@Repository
public interface LocalRepository extends JpaRepository<Local, Long> {

	
	
}
