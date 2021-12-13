import java.util.ArrayList;
public class Reservation {

	private String date;
	private ArrayList<Pc> pcReserves;
	private ArrayList<Salle> salleReservees;

	/** creer une reservation de pc et salle en date
     * passé en argument
     * @param _date
     * date de reservation
     * @param _pcAReserves
     * PC à réserver
     * @param _salleAReservees
     * Salle à réserver
     * @return reservation
     */
	public Reservation(String _date, ArrayList<Pc> _pcReserves , ArrayList<Salle> _salleReservees) {
		this.date = _date;

		for (Pc pc : _pcReserves) {
			pc.setEstReserve(true);
		}
		for (Salle salle : _salleReservees) {
			salle.setEstReserve(true);
		}
		this.pcReserves = _pcReserves;
		this.salleReservees = _salleReservees;
	}
}
