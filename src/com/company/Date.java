package com.company;

public class Date {

	private int jour;
	private int mois;
	private int annee;
	public Date(int jour, int mois, int annee) {
		
		this.jour = jour;
		this.mois = mois;
		this.annee = annee;
	}
	@Override
	public String toString() {
		return "Date [jour=" + jour + ", mois=" + mois + ", annee=" + annee + "]";
	}
	
	
}
