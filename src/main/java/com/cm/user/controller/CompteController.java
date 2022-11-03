package com.cm.user.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cm.user.entity.Compte;
import com.cm.user.exception.ResourceNotFoundException;
import com.cm.user.payload.UserResponse;
import com.cm.user.proxy.PersonnelProxy;
import com.cm.user.repository.CompteRepository;
import com.cm.user.security.jwt.JwtConfig;
import com.cm.user.security.services.UserDetailsImpl;
import com.cm.user.security.services.UserDetailsServiceImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@RestController
@RequestMapping("/Users")
public class CompteController {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private CompteRepository compteRepository;

	@Autowired
	private UserDetailsServiceImpl usersDetailservice;

	@Autowired
	private JwtConfig jwtConfig;
	@Autowired
	private PersonnelProxy personnelProxy;

	@GetMapping("comptes/index")
	public List<Compte> getAllCompts() {
		return compteRepository.findCompteIndex();
	}

	@GetMapping("comptes/show/{id}")
	public ResponseEntity<Compte> getCompteById(@PathVariable(value = "id") int compteId) {
		Compte compte = compteRepository.findById(compteId);

		return ResponseEntity.ok().body(compte);
	}
	/*
	 * @GetMapping("/Users/comptes/checkUsername/{uid}") public boolean
	 * checkUid(@PathVariable(value = "uid") String uid) {
	 * 
	 * 
	 * return userService.checkUsername(uid); }
	 */

	@PostMapping("comptes/new")
	public Compte signUp(@RequestBody Compte compte) {
		Optional<Compte> c = compteRepository.findByUsername(compte.getUsername());
		if (c.isPresent())
			throw new RuntimeException("ce compte existe deja!!");

		compte.setActife(true);

		compte.setPassword(bCryptPasswordEncoder.encode(compte.getPassword()));

		return compteRepository.save(compte);
	}

	@GetMapping("comptes/byToken")
	@Transactional
	public ResponseEntity<?> getCompteByTokenn(HttpServletRequest request) {
		String header = request.getHeader(jwtConfig.getHeader());
		String token = header.replace(jwtConfig.getPrefix(), "");
		Claims claims = Jwts.parser().setSigningKey(jwtConfig.getSecret().getBytes()).parseClaimsJws(token).getBody();
		String username = claims.getSubject();
		UserDetailsImpl userDetails = (UserDetailsImpl) usersDetailservice.loadUserByUsername(username);

		return ResponseEntity
				.ok(new UserResponse(userDetails.getId(), userDetails.getUsername(), header, userDetails.getRoles(),

						userDetails.getPic(), userDetails.getFullname(), userDetails.getIdPersonnel(),
						userDetails.getIdDivision(), userDetails.getIdService()

				));

	}

	@PutMapping("/comptes/edit/{id}")
	public ResponseEntity<Compte> updateCompte(@PathVariable(value = "id") long compteId,
			@Valid @RequestBody Compte compte) throws ResourceNotFoundException {

		if (compte.getPassword() != null) {
			compte.setPassword(bCryptPasswordEncoder.encode(compte.getPassword()));

		}else {
			Compte c = compteRepository.findById(compteId);
			compte.setPassword(c.getPassword());
		}

		final Compte updateCompte = compteRepository.save(compte);
		return ResponseEntity.ok(updateCompte);
	}

	@DeleteMapping("/comptes/delete/{id}")
	public void deleteCompte(@PathVariable(value = "id") Long compteId) {

		compteRepository.deleteById(compteId);
		;

	}

}