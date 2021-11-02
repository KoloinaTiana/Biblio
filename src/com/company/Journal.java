package com.company;

public class Journal extends Document {
	private String nomEditorialiste;

	public Journal(String titre, Date date, String langue, boolean estdisponible, String nomEditorialiste) {
		super(titre, date, langue, estdisponible);
		this.nomEditorialiste = nomEditorialiste;
	}

	@Override
	public String toString() {
		return "Journal [nomEditorialiste=" + nomEditorialiste +super.toString()+ "]";
	 }
	
	
}
