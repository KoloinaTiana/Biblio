import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Year;
import java.util.ArrayList;
import java.sql.Date;
public class Bibliotheque{

    private static Bibliotheque instance;


    @SuppressWarnings("unused")
    private ArrayList<Pc> machines;
    @SuppressWarnings("unused")
    private ArrayList<Salle> salles;

    private Connection connexion;

    private Bibliotheque() {
        machines = new ArrayList<Pc>();
        salles = new ArrayList<Salle>();
        connexion = databaseConnexion();
    }


    public Compte connexion(String id, String mdp) throws UserNotFoundException, IncorrectPasswordException , SQLException{
        Compte openClient;
        openClient = trouverUtilisateur(id);
        if (openClient == null) {
            openClient = trouverAdmin(id);
            if (openClient == null)
                throw new UserNotFoundException("Compte non trouvé");
        }
        if (openClient.getMotDePasse().equals(mdp))
            return openClient;

        else
            throw new IncorrectPasswordException("Mot de passe incorrect");

    }

    public String findCode(String mail, int code) throws IncorrectCodeException, SQLException{
        try {
            Statement stmt = connexion.createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM client WHERE Email ='"+ mail +"' AND Code ="+code);
            if (res.next()) {
                return "OK";
            }else{
                throw new IncorrectCodeException();
            }
        } catch (SQLException e) {
            throw e;
        }

    }

    public String findMail(String mail) throws MailNotFoundException, SQLException{
        Compte openClient;
        openClient = trouverUtilisateurMail(mail);
        if (openClient == null) {
            throw new MailNotFoundException();
        }else{
            return "OK";
        }

    }

    public void inscriptionClient (String id, String mdp, String prenom, String nom, String adresse, String nTel, String eMail) {
        try {
            Statement stmt = connexion.createStatement();
            stmt.executeUpdate("INSERT INTO client (`ID_client`, `Identifiant`, `Mot de Passe`, `Prenom`, `Nom`, `Adresse`, `Numero de telephone`, `Email`) VALUES (NULL,'"
                    + id+ "'" +","+ "'"+ mdp+ "'" +","+ "'"+ prenom+ "'" +","
                    + "'"+ nom+ "'" +","+ "'"+ adresse+ "'" +","+ "'"+ nTel+ "'" +","+ "'"+ eMail+ "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modificationCompte (int id,String ident, String mdp, String prenom, String nom, String adresse, String nTel, String eMail) {
        try {
            Statement stmt = connexion.createStatement();
            stmt.executeUpdate("UPDATE client SET `Identifiant`= '"+ident+"', `Mot de Passe`= '"+mdp+"', `Prenom`= '"+prenom+"', `Nom`= '"+nom+"', `Adresse`= '"+adresse+"', `Numero de telephone`= '"+nTel+"', `Email`= '"+eMail+"' WHERE `ID_client`="+id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection databaseConnexion(){
        String url = "jdbc:mysql://localhost:3306/bibliotheque";
        String userName = "root";
        String password = "";

        try {
            Connection con = DriverManager.getConnection(url, userName, password);
            return con;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    public Admin trouverAdmin(String id) throws SQLException{
        try {
            Statement stmt = connexion.createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM admin WHERE Identifiant ='"+ id +"'");
            if (res.next()) {
                System.out.println("Compte Admin trouvé");
                String Identifiant = res.getString("Identifiant");
                String MDP = res.getString("Mot de Passe");
                String prenom = res.getString("Prenom");
                String nom = res.getString("Nom");
                System.out.println(Identifiant + " " + MDP + " " + prenom + " " + nom);
                Admin admin = new Admin(Identifiant,MDP,prenom,nom);
                return admin;
            }else
                return null;
        } catch (SQLException e) {
            throw e;
        }
    }

    public Client trouverUtilisateur(String id) throws UserNotFoundException, SQLException{
        try {
            Statement stmt = connexion.createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM client WHERE Identifiant ='"+ id +"'");
            if (res.next()) {
                System.out.println("Compte Utilisateur trouve");
                int ID = res.getInt("ID_client");
                String Identifiant = res.getString("Identifiant");
                String MDP = res.getString("Mot de Passe");
                String prenom = res.getString("Prenom");
                String nom = res.getString("Nom");
                String adresse = res.getString("Adresse");
                String numeroTelephone = res.getString("Numero de telephone");
                String eMail = res.getString("Email");
                Client client = new Client(ID, Identifiant,MDP,prenom,nom,adresse,numeroTelephone,eMail);
                return client;
            }else
                return null;
        } catch (SQLException e) {
            throw e;
        }
    }

    public Client trouverUtilisateurMail(String mail) throws SQLException{
        try {
            Statement stmt = connexion.createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM client WHERE Email ='"+ mail +"'");
            if (res.next()) {
                System.out.println("Compte Utilisateur trouvé");
                int ID = res.getInt("ID_client");
                String Identifiant = res.getString("Identifiant");
                String MDP = res.getString("Mot de Passe");
                String prenom = res.getString("Prenom");
                String nom = res.getString("Nom");
                String adresse = res.getString("Adresse");
                String numeroTelephone = res.getString("Numero de telephone");
                String eMail = res.getString("Email");
                System.out.println(Identifiant + " " + MDP + " " + prenom + " " + nom+ " " + adresse+ " " + numeroTelephone+ " " + eMail);
                Client client = new Client(ID, Identifiant,MDP,prenom,nom,adresse,numeroTelephone,eMail);
                return client;
            }else
                return null;
        } catch (SQLException e) {
            throw e;
        }
    }

    public static Bibliotheque getInstance() {
        if( instance == null ) {
            instance = new Bibliotheque();
        }
        return instance;
    }

    public boolean isUserExist(String id) throws SQLException{
        try {
            Statement stmt = connexion.createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM client WHERE Identifiant ='"+ id +"'");
            if (res.next()) {
                return true;
            }else
                return false;
        } catch (SQLException e) {
            throw e;
        }
    }

    public boolean isDifferentUser(int id,String ident) throws SQLException{
        try {
            Statement stmt = connexion.createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM client WHERE Identifiant ='"+ ident +"' AND ID_client != "+ id);
            if (res.next()) {
                return true;
            }else
                return false;
        } catch (SQLException e) {
            throw e;
        }
    }

    public void ajoutsalle(int nbrchaise, int nbrtable, int projecteur, int taille) throws SQLException {
        String requete = "INSERT INTO `salle`(`Nombre_chaise`,`Nombre_table`, `Projecteur`, `Taille`) VALUES ('"+ nbrchaise +"','"+ nbrtable +"','"+ projecteur +"','"+ taille +"')";

        try {
            Statement stmt = connexion.createStatement();
            int nbMaj = stmt.executeUpdate(requete);
            System.out.println(nbMaj + "salle ajoutÃ©e");
        } catch (SQLException e) {
            throw new SQLException();
        }
    }

    public Connection getConnexion() {
        return connexion;
    }

    public void ajoutpc (String marque, String sn) throws SQLException {
        String requete = "INSERT INTO `pc`(`Marque`,`SN`) VALUES ('"+ marque +"','"+ sn +"')";

        try {
            Statement stmt = connexion.createStatement();
            int nbMaj = stmt.executeUpdate(requete);
            System.out.println(nbMaj + "pc ajouté");
        } catch (SQLException e) {
            throw new SQLException();
        }
    }

    public void ajoutlivre (String titre, String auteur, String sujet, String resume) throws SQLException {
        String requete = "INSERT INTO `livre`(`Titre`,`Auteur`, `Resume`, `Sujet`) VALUES ('"+ titre +"','"+ auteur +"','"+ resume +"','"+ sujet +"')";

        try {
            Statement stmt = connexion.createStatement();
            int nbMaj = stmt.executeUpdate(requete);
            System.out.println(nbMaj + "livre ajouté");
        } catch (SQLException e) {
            throw new SQLException();
        }
    }

    public void ajoutJournal (String Editorial, String Date, String Nom) throws SQLException {
        String requete = "INSERT INTO `journal`(`Editorial`,`Journal_date`, `Journal_nom`) VALUES ('"+ Editorial +"','"+ Date +"','"+ Nom +"')";

        try {
            Statement stmt = connexion.createStatement();
            int nbMaj = stmt.executeUpdate(requete);
            System.out.println(nbMaj + "journal ajouté");
        } catch (SQLException e) {
            throw new SQLException();
        }
    }

    public void ajoutFilm(String titre, String realisateur, Year annee) throws SQLException {
        String requete = "INSERT INTO `film`(`Titre`,`Realisateur`, `Annee_sortie`) VALUES ('"+ titre +"','"+ realisateur +"','"+ annee+"')";

        try {
            Statement stmt = connexion.createStatement();
            int nbMaj = stmt.executeUpdate(requete);
            System.out.println(nbMaj + "film ajouté");
        } catch (SQLException e) {
            throw new SQLException();
        }
    }

    public void modifUser(String identifiant, String nom, String prenom, String adresse, String telephone, String email) throws SQLException {
        String requete = "UPDATE `client` SET `Nom` ='"+nom+"', `Prenom` ='"+prenom+"', `Numero de telephone` ='"+telephone+"', `Adresse` ='"+adresse+"', `Email` ='"+email+"' WHERE `Identifiant` ='"+identifiant+"'";
        try {
            Statement stmt = connexion.createStatement();
            int nbMaj = stmt.executeUpdate(requete);
            System.out.println(nbMaj + "user modifié");
        } catch (SQLException e) {
            System.out.println(e);
            throw new SQLException();
        }
    }

    public void deleteUser(String id) throws SQLException {
        String requete = "DELETE FROM `client` WHERE `Identifiant` ='"+id+"'";
        try {
            Statement stmt = connexion.createStatement();
            int nbMaj = stmt.executeUpdate(requete);
            System.out.println(nbMaj + "user supprimé");
        } catch (SQLException e) {
            System.out.println(e);
            throw new SQLException();
        }
    }

    public void newMDP(String m, String password) {
        try {
            Statement stmt = connexion.createStatement();
            stmt.executeUpdate("UPDATE client SET `Mot de Passe`= '"+password+"' WHERE `Email`='"+m+"'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
