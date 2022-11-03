package com.cm.user.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users_roles")
public class Roles {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String title;
	private String description;
	private boolean isCoreRole;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })

	@javax.persistence.OrderBy("id ASC")
	@JoinTable(name = "users_roles_permissions", joinColumns = {
			@JoinColumn(name = "role_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "persmission_id", referencedColumnName = "id") })
	private Set<Fonctionnalite> permissions = new HashSet<>();

	public Roles() {
		super();

	}

	public Roles(long id, String title) {
		super();
		this.id = id;
		this.title = title;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Fonctionnalite> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<Fonctionnalite> permissions) {
		this.permissions = permissions;
	}

	public boolean isCoreRole() {
		return isCoreRole;
	}

	public void setCoreRole(boolean isCoreRole) {
		this.isCoreRole = isCoreRole;
	}

}