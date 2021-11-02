package com.company;

import java.util.ArrayList;
import java.util.List;

public class Emprunt {

	private Date date;
	private Date datelimite;
	private List<Document> dpcuments = new ArrayList<Document>();
	public Emprunt(Date date, Date datelimite) {
		this.date = date;
		this.datelimite = datelimite;
	}
	@Override
	public String toString() {
		return "Emprunt [date=" + date + ", datelimite=" + datelimite + "]";
	}
	
	
	
	
	
}
