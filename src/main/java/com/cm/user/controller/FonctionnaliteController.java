package com.cm.user.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cm.user.bein.LignePermission;
import com.cm.user.entity.Fonctionnalite;
import com.cm.user.payload.PermissionResponse;
import com.cm.user.repository.FonctionnaliteRepository;
import com.cm.user.security.services.UserDetailsServiceImpl;

@RestController
@RequestMapping("/Users")
public class FonctionnaliteController {

	@Autowired
	private FonctionnaliteRepository fonctionnaliteRepository;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@GetMapping("/fonctionnalites/index")
	public List<Fonctionnalite> getAllFonctionnalites() {
		return fonctionnaliteRepository.findAllByOrderByIdDivisionAscIdServiceDescLevelDesc();
	}

	@GetMapping("/authorities/index")
	@Transactional
	public List<LignePermission> getAllAuthorities() {
		List<Fonctionnalite> fonctionnalites = fonctionnaliteRepository.findAll();
		List<LignePermission> lignes = new ArrayList<LignePermission>();

		for (Fonctionnalite f : fonctionnalites) {
			if (f.getPath() != null) {
				LignePermission lp = new LignePermission();

				lp.setPath(f.getPath());
				lp.setAuthority("AUTHORITY_" + f.getId());
				lignes.add(lp);
			}
		}
		return lignes;
	}

	@GetMapping("/authorities/{username}")
	public Collection<? extends GrantedAuthority> getUserAuthorities(
			@PathVariable(value = "username") String username) {

		return userDetailsService.loadUserByUsername(username).getAuthorities();
	}

	@GetMapping("/permissions/all")
	@Transactional
	public List<PermissionResponse> getAllRolesn() {

		List<Fonctionnalite> permissions = fonctionnaliteRepository
				.findAllByOrderByIdDivisionAscIdServiceDescLevelDesc();
		List<PermissionResponse> liste = new ArrayList<>();
		for (Fonctionnalite p : permissions) {
			PermissionResponse reponse = new PermissionResponse();

			reponse.setId(p.getId());
			reponse.setName(p.getName());
			reponse.setLevel(p.getLevel());
			reponse.setTitle(p.getTitle());

			liste.add(reponse);
		}

		return liste;
	}
}