package com.cm.user.payload;

import java.util.List;

public class UserResponse {
	private long id;
	private String usernam;

	private String accessToken;

	private List<Long> roles;
	private String pic;
	private String fullname;
	private long idPersonnel;
	private long idDivision;
	private long idService;

	public UserResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserResponse(long id, String usernam, String accessToken, List<Long> roles, String pic, String fullname,
			long idPersonnel, long idDivision, long idService) {
		super();
		this.id = id;
		this.usernam = usernam;

		this.accessToken = accessToken;
		this.roles = roles;
		this.pic = pic;
		this.fullname = fullname;
		this.idPersonnel = idPersonnel;
		this.idDivision = idDivision;
		this.idService = idService;
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