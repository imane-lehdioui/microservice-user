package com.cm.user.security.jwt;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.cm.user.security.services.UserDetailsImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {

	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

	@Autowired
	private JwtConfig jwtConfig;

	Long now = System.currentTimeMillis();

	public String generateJwtToken(Authentication authentication) {

		UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
		Claims claims = Jwts.claims().setSubject(authentication.getName());
		Collection<String> authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());
		claims.put("authorities", authorities);
		return Jwts.builder().setClaims(claims).setSubject((userPrincipal.getUsername())).setIssuedAt(new Date())
				.setExpiration(new Date(now + jwtConfig.getExpiration() * 1000))
				.signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret().getBytes()).compact();
	}

}
