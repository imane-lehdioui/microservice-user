package com.cm.user.payload;

import java.util.List;

public class JwtResponse {
	private String accessToken;
	private String fullname;
	private long id;
	private String username;
	private String pic;
	private List<Long> roles;

	public JwtResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JwtResponse(String accessToken, long l, String username, String fullname, String pic, List<Long> roles) {
		super();
		this.accessToken = "Bearer " + accessToken;
		this.pic = pic;
		this.fullname = fullname;
		this.id = l;
		this.username = username;

		this.roles = roles;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
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

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

}