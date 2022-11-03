package com.cm.user.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cm.user.entity.Fonctionnalite;

public interface FonctionnaliteRepository extends JpaRepository<Fonctionnalite, Integer> {
	
	List<Fonctionnalite> findAllByOrderByIdDivisionAscIdServiceDescLevelDesc();
}