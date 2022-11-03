package com.cm.user.bein;

public class PersonnelBein {

	  
	     
	   private String nom;	   
       private String prenom;
       private long idDivision;
       private long idService;
	
       public PersonnelBein() {
		super();
		
	   }

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
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
