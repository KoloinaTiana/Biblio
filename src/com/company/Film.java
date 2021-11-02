package com.company;

public class Film extends Document {
	private String realisateur;
	private String producteur;
	public Film(String titre, Date date, String langue, boolean estdisponible, String realisateur, String producteur) {
		super(titre, date, langue, estdisponible);
		this.realisateur = realisateur;
		this.producteur = producteur;
	}
	@Override
	public String toString() {
		return "Film [realisateur=" + realisateur + ", producteur=" + producteur +super.toString()+ "]";
	}
	
	
}

 
