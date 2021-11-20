public class Salle {
	
	private int nbChaises;
	private int nbTables;
	private boolean vProjecteur;
	private double superficie;
	private boolean estReserve;
	
	public Salle(int _nbChaises, int _nbTables, boolean _vProjecteur, double _superficie) {
		
		this.nbChaises = _nbChaises;
		this.nbTables = _nbTables;
		this.vProjecteur = _vProjecteur;
		this.superficie = _superficie;
		this.estReserve = estReserve = false;
		
	}

	public void setEstReserve(boolean estReserve) {
		this.estReserve = estReserve;
	}
}
