package com.cm.user.security.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cm.user.entity.Compte;
import com.cm.user.entity.Roles;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 1L;

	private long id;

	private String username;

	@JsonIgnore
	private String password;
	private List<Long> roles;
	private String pic;
	private String fullname;

	private long idPersonnel;
	private long idDivision;
	private long idService;

	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(long l, String username, String fullname, long idPersonnel, long idDivision, long idService,
			String pic, String password, Collection<? extends GrantedAuthority> authorities, List<Long> roles) {
		this.id = l;
		this.username = username;
		this.password = password;
		this.authorities = authorities;
		this.roles = roles;
		this.fullname = fullname;
		this.pic = pic;
		this.idPersonnel = idPersonnel;
		this.idDivision = idDivision;
		this.idService = idService;
	}

	public static UserDetailsImpl build(Compte user) {
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		user.getRoles().forEach(r -> {
			r.getPermissions().forEach(f -> {
				authorities.add(new SimpleGrantedAuthority("AUTHORITY_" + f.getId()));
			});

		});
		List<Long> roles = new ArrayList<>();
		for (Roles r : user.getRoles())
			roles.add(r.getId());

		return new UserDetailsImpl(user.getId(), user.getUsername(), user.getFullname(), user.getIdPersonnel(),
				user.getIdDivision(), user.getIdService(), user.getPic(), user.getPassword(), authorities, roles);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public long getId() {
		return id;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public List<Long> getRoles() {
		return roles;
	}

	public void setRoles(List<Long> roles) {
		this.roles = roles;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public long getIdPersonnel() {
		return idPersonnel;
	}

	public void setIdPersonnel(long idPersonnel) {
		this.idPersonnel = idPersonnel;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public long getIdDivision() {
		return idDivision;
	}

	public void setIdDivision(long idDivision) {
		this.idDivision = idDivision;
	}

	public long getIdService() {
		return idService;
	}

	public void setIdService(long idService) {
		this.idService = idService;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}

}