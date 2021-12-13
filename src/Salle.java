
public class Salle {

	private int nbChaises;
	private int nbTables;
	private boolean vProjecteur;
	private double superficie;
	private boolean estReserve;

	/**
	 * Constructeur de la classe Salle
	 * @param _nbChaises
	 * @param _nbTables
	 * @param _vProjecteur
	 * @param _superficie
	 */
	public Salle(int _nbChaises, int _nbTables, boolean _vProjecteur, double _superficie) {

		this.nbChaises = _nbChaises;
		this.nbTables = _nbTables;
		this.vProjecteur = _vProjecteur;
		this.superficie = _superficie;
		this.estReserve = estReserve = false;

	}

	/**
	 * change la valeur de la boolean estReserve
	 * @param estReserve indique si la salle est reservé
	 */
	public void setEstReserve(boolean estReserve) {
		this.estReserve = estReserve;
	}
}
