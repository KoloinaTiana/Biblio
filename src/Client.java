import java.util.ArrayList;
public class Client extends Compte{

    private ArrayList<Reservation> reservations;


    public String adresse;
    public String numeroTelephone;
    public String eMail;
    //private boolean estAbonne;

    public Client(String _identifiant, String _motDePasse, String _prenom, String _nom, String _adresse, String _numeroTelephone, String _Email) {

        super(_identifiant,_motDePasse,_prenom,_nom);
        this.prenom = _prenom;
        this.nom = _nom;
        this.adresse = _adresse;
        this.numeroTelephone = _numeroTelephone;
        this.eMail = _Email;
        //this.estAbonne = false;
        this.reservations = new ArrayList<Reservation>();
    }

    //ajouter exception client deja abonné
	/*
	public void abonner() throws Exception{
		if (estAbonne == true)
			throw new Exception();
		else
		this.estAbonne = true;
	}
	*/
    public void reserver(String date, ArrayList<Pc> pcAReserves, ArrayList<Salle> salleAReservees) {

        Reservation newReservation = new Reservation(date,pcAReserves,salleAReservees);
        reservations.add(newReservation);

    }

}
