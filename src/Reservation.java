import java.util.ArrayList;
public class Reservation {

	private String date;
	private ArrayList<Pc> pcReserves;
	private ArrayList<Salle> salleReservees;

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
