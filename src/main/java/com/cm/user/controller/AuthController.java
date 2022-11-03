package com.cm.user.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cm.user.payload.AuthResponse;
import com.cm.user.payload.LoginRequest;
import com.cm.user.proxy.PersonnelProxy;
import com.cm.user.repository.CompteRepository;
import com.cm.user.repository.RolesRepository;
import com.cm.user.security.jwt.JwtUtils;
import com.cm.user.security.services.UserDetailsImpl;

@RestController
@RequestMapping("/Users")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	CompteRepository userRepository;

	@Autowired
	RolesRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
	@Autowired
	private PersonnelProxy personnelProxy;

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		return ResponseEntity.ok(new AuthResponse(userDetails.getId(), userDetails.getUsername(), jwt

		));

	}

}