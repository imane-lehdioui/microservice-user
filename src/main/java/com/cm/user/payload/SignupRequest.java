package com.cm.user.payload;

import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SignupRequest {
	@NotBlank
	@Size(min = 3, max = 20)
	private String username;

	private Set<String> roles;

	@NotBlank
	@Size(min = 6, max = 40)
	private String password;

	public SignupRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SignupRequest(@NotBlank @Size(min = 3, max = 20) String username, Set<String> roles,
			@NotBlank @Size(min = 6, max = 40) String password) {
		super();
		this.username = username;
		this.roles = roles;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}