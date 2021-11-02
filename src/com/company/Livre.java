package com.company;

public class Livre extends Document {
	private String auteur;
	protected String sujet;
	protected String description;
	protected int edition;
	protected int nbpage;
	public Livre(String titre, Date date, String langue, boolean estdisponible, String auteur, String sujet,
			String description, int edition, int nbpage) {
		super(titre, date, langue, estdisponible);
		this.auteur = auteur;
		this.sujet = sujet;
		this.description = description;
		this.edition = edition;
		this.nbpage = nbpage;
	}
	@Override
	public String toString() {
		return "Livre [auteur=" + auteur + ", sujet=" + sujet + ", description=" + description + ", edition=" + edition
				+ ", nbpage=" + nbpage +super.toString()+ "]";
	}
	
	
	
}
