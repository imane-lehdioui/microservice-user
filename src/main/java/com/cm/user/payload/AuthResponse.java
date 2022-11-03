package com.cm.user.payload;

public class AuthResponse {
	private long id;
	private String usernam;

	private String accessToken;

	public AuthResponse(long id, String usernam, String accessToken) {
		super();
		this.id = id;
		this.usernam = usernam;
		this.accessToken = accessToken;
	}

	public AuthResponse() {
		super();

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsernam() {
		return usernam;
	}

	public void setUsernam(String usernam) {
		this.usernam = usernam;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

}