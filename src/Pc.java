
public class Pc {

	private int id;
	private boolean estReserve;

	/**
	 * Constructeur de la classe Pc
	 * @param _id
	 */
	public Pc(int _id) {
		this.estReserve = false;
		this.id = _id;
	}

	/**
	 * Permet de modifier la valeur de la boolean estReserve
	 * @param estReserve
	 */
	public void setEstReserve(boolean estReserve) {
		this.estReserve = estReserve;
	}
}
