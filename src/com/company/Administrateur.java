package com.company;

public class Administrateur {
  import java.util.ArrayList;
import java.util.List;

public class Administrateur {
	private List<Document> documents;
	private List<PC> pc;
	private List<Salle> salle;
	
	public Administrateur() {
		
		documents =new ArrayList<Document>();
		pc =  new ArrayList<PC>();
		salle =new ArrayList<Salle>();
	}


	public void ajouterDocument(Document d) {
		int nb=0;
		if(documents.contains(d))
		{
			for(Document dt:documents)
				{if(dt.equals(d))
					nb++;}
			System.out.println("document deja trouver, nb des livres maintenant est:"+nb+".");
		documents.add(d);
			
		}
		documents.add(d);
	}
	public void ajouterRoom(Salle d) {
		int nb=0;
		if(salle.contains(d))
		{
			for(Salle dt:salle)
				{if(dt.equals(d))
					nb++;}
			System.out.println("salle deja trouver, nb des livres maintenant est:"+nb+".");
		salle.add(d);
			
		}
		salle.add(d);
	}
	public void ajouterPC(PC d) {
		int nb=0;
		if(pc.contains(d))
		{
			for(PC dt:pc)
				{if(dt.equals(d))
					nb++;}
			System.out.println("PC deja trouver, nb des livres maintenant est:"+nb+".");
		pc.add(d);
			
		}
		pc.add(d);
	}


}
}
