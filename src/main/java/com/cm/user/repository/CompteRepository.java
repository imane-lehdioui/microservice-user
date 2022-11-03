package com.cm.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cm.user.entity.Compte;


public interface CompteRepository extends JpaRepository<Compte, Long> {

	Optional<Compte> findByUsername(String uid);
	Boolean existsByUsername(String username);
	public Compte findById(long id);
	
	@Query("select new com.cm.user.entity.Compte(c.id,c.username,c.actife) from Compte c") 
	   List<Compte> findCompteIndex();
	   
}