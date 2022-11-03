package com.cm.user.payload;

import java.util.List;

public class RoleResponse {
	private long id;
	private String title;
	private List<Long> permissions;
	private boolean isCoreRole;

	public RoleResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RoleResponse(long id, String title, List<Long> permissions, boolean isCoreRole) {
		super();
		this.id = id;
		this.title = title;
		this.permissions = permissions;
		this.isCoreRole = isCoreRole;
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

	public List<Long> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Long> permissions) {
		this.permissions = permissions;
	}

	public boolean isCoreRole() {
		return isCoreRole;
	}

	public void setCoreRole(boolean isCoreRole) {
		this.isCoreRole = isCoreRole;
	}

}