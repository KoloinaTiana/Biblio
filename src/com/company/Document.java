package com.company;

public class Document {
	protected String titre;
	protected Date date;
	protected String langue;
	protected boolean estdisponible;
	public Document(String titre, Date date, String langue, boolean estdisponible) {
		super();
		this.titre = titre;
		this.date = date;
		this.langue = langue;
		this.estdisponible = estdisponible;
	}
	@Override
	public String toString() {
		return "Document [titre=" + titre + ", date=" + date + ", langue=" + langue + ", estdisponible=" + estdisponible
				+ "]";
	}
	
	
}
