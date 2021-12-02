import java.util.ArrayList;
public class Client extends Compte{

    private ArrayList<Reservation> reservations;

    public int ID;
    public String adresse;
    public String numeroTelephone;
    public String eMail;
    //private boolean estAbonne;

    public Client(int ID, String _identifiant, String _motDePasse, String _prenom, String _nom, String _adresse, String _numeroTelephone, String _Email) {

        super(_identifiant,_motDePasse,_prenom,_nom);
        this.ID = ID;
        this.prenom = _prenom;
        this.nom = _nom;
        this.adresse = _adresse;
        this.numeroTelephone = _numeroTelephone;
        this.eMail = _Email;
        //this.estAbonne = false;
        this.reservations = new ArrayList<Reservation>();
    }

    public void reserver(String date, ArrayList<Pc> pcAReserves, ArrayList<Salle> salleAReservees) {

        Reservation newReservation = new Reservation(date,pcAReserves,salleAReservees);
        reservations.add(newReservation);

    }

    public int getID() {
        return ID;
    }

    public String getAdresse() {
        return adresse;
    }

    public String geteMail() {
        return eMail;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }
}
