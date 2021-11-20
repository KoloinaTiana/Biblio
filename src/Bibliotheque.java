import java.util.ArrayList;

public class Bibliotheque {

    private static Bibliotheque instance;

    private ArrayList<Compte> comptes;
    @SuppressWarnings("unused")
    private ArrayList<Pc> machines;
    @SuppressWarnings("unused")
    private ArrayList<Salle> salles;

    private Bibliotheque() {
        machines = new ArrayList<Pc>();
        salles = new ArrayList<Salle>();
        comptes = new ArrayList<Compte>();
    }


    public Compte connexion(String id, String mdp) throws UserNotFoundException, IncorrectPasswordException{
        Compte openClient = null;
        for (Compte c : comptes) {
            if (c.getIdentifiant().equals(id) ) {
                openClient = c;
                System.out.println("Utilisateur trouvé");
            }
        }
        if (openClient == null)
            throw new UserNotFoundException("Utilisateur non trouvé");
        else if (openClient.getMotDePasse().equals(mdp))
            return openClient;
        else
            throw new IncorrectPasswordException("Mot de passe incorrect");

    }

    public void inscription (String id, String mdp, String prenom, String nom, String adresse, String nTel, String eMail) {
        Client newClient = new Client(id,mdp,prenom,nom,adresse,nTel,eMail);
        comptes.add(newClient);
    }

    public void addCompte(Compte c) {
        comptes.add(c);
    }

    public static Bibliotheque getInstance() {
        if( instance == null ) {
            instance = new Bibliotheque();
        }
        return instance;
    }
    public boolean isUserExist(String id) {
        for (Compte c : comptes) {
            if (c.getIdentifiant().equals(id) ) {
                return true;
            }
        }
        return false;
    }
}
