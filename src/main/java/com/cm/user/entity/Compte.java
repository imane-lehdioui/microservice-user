package com.cm.user.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "users_compte")
public class Compte {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(unique = true)
	private String username;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	private boolean actife;
	private String pic = "./assets/media/users/default.jpg";
	private String fullname;

	private long idPersonnel = 0;
	@Column(nullable = true)
	private long idDivision = 0;
	@Column(nullable = true)
	private long idService = 0;

	@ManyToMany(fetch = FetchType.LAZY)

	@JoinTable(name = "users_compte_roles", joinColumns = {
			@JoinColumn(name = "compte_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "role_id", referencedColumnName = "id")

	})
	private Set<Roles> roles = new HashSet<>();

	public Compte() {
		super();

	}

	public Compte(String username, String password, boolean actife) {
		super();
		this.username = username;
		this.password = password;
		this.actife = actife;
	}

	public Compte(long id, String username, boolean actife) {
		super();
		this.id = id;
		this.username = username;
		this.actife = actife;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActife() {
		return actife;
	}

	public void setActife(boolean actife) {
		this.actife = actife;
	}

	public Set<Roles> getRoles() {
		return roles;
	}

	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public long getIdPersonnel() {
		return idPersonnel;
	}

	public void setIdPersonnel(long idPersonnel) {
		this.idPersonnel = idPersonnel;
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

}