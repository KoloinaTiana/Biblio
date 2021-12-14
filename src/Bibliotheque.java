import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;

public class Bibliotheque{

    private static Bibliotheque instance;

    private Connection connexion;
    
     /**
     * Constructeur de la classe Bibliotheque
     * initialise l'instance de connexion de la base de données
     */
    private Bibliotheque() {
        connexion = databaseConnexion();
    }

     /**
     * Permet de vérifier les informations de connexion,
     * cherche d'abord dans la base de données des clients
     * puis dans la base de données des admins
     * et enfin vérifie si le mot de passe est bon
     * @param id identifiant 
     * @param mdp mot de passe
     * @return compte
     * @throws UserNotFoundException compte non trouvé
     * @throws IncorrectPasswordException mot de passe incorrecte
     * @throws SQLException
     */
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

    /**
     * Vérifie si le code de vérification envoyé par mail est correcte
     * @param mail adresse mail du client
     * @param code code de vérification envoyé par mail
     * @return 'ok' si le code est bon 
     * @throws IncorrectCodeException code incorrecte 
     * @throws SQLException
     */
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

    /**
     * Vérifie si un client est associé à un abonnement dans la base de données
     * @param id id_client
     * @return 'ok' si l'abonnement se trouve dans la base de données, 'ko' sinon
     * @throws SQLException
     */
    public String hasSubscribed(int id) throws SQLException{
        try {
            Statement stmt = connexion.createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM abonnement WHERE ID_client ="+id);
            if (res.next()) {
                return "OK";
            }else{
                return "KO";
            }
        } catch (SQLException e) {
            throw e;
        }

    }

    /**
     * Vérifie si un mail est associé à un client dans la base de données
     * @param mail
     * @return 'OK' si un client est trouvé
     * @throws MailNotFoundException le mail n'appartient à aucun client
     * @throws SQLException
     */
    public String findMail(String mail) throws MailNotFoundException, SQLException{
        Compte openClient;
        openClient = trouverUtilisateurMail(mail);
        if (openClient == null) {
            throw new MailNotFoundException();
        }else{
            return "OK";
        }

    }

     /**
     * Inscrit un nouveau client dans la base de données
     * @param id identifiant 
     * @param mdp mot de passe
     * @param prenom
     * @param nom
     * @param adresse
     * @param nTel numero de telephone
     * @param eMail adresse mail
     */
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

    /**
     * Modifie les informations d'un client réferencé par son id_cleint dans la base de données 
     * @param id id_client
     * @param ident identifiant
     * @param mdp mot de passe
     * @param prenom
     * @param nom
     * @param adresse
     * @param nTel numero de telephone
     * @param eMail adresse mail
     */
    public void modificationCompte (int id,String ident, String mdp, String prenom, String nom, String adresse, String nTel, String eMail) {
        try {
            Statement stmt = connexion.createStatement();
            stmt.executeUpdate("UPDATE client SET `Identifiant`= '"+ident+"', `Mot de Passe`= '"+mdp+"', `Prenom`= '"+prenom+"', `Nom`= '"+nom+"', `Adresse`= '"+adresse+"', `Numero de telephone`= '"+nTel+"', `Email`= '"+eMail+"' WHERE `ID_client`="+id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

     /**
     * Connexion à la base de données
     * @return instance de connexion à la base de données
     */
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

     /**
     * Cherche un admin dans la base de données à partir de son identifiant
     * @param id identifiant
     * @return l'instance de l'admin si l'admin est trouvé, null sinon
     * @throws SQLException
     */
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

     /**
     * Cherche un client dans la base de données à partir de son identifiant
     * @param id identifiant
     * @return l'instance du client s'il est trouvé, null sinon
     * @throws UserNotFoundException
     * @throws SQLException
     */
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

     /**
     * Cherche un client dans la base de données à partir de son mail
     * @param mail
     * @return l'instance du client s'il est trouvé, null sinon
     * @throws SQLException
     */
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

     /**
     * Renvoie l'instance de la Bibliotheque si elle existe ou en crée une si elle n'existe pas
     * @return instance du singleton Bibliotheque
     */
    public static Bibliotheque getInstance() {
        if( instance == null ) {
            instance = new Bibliotheque();
        }
        return instance;
    }

     /**
     * Vérifie si un client existe dans la base de données à partir de son identifiant
     * @param id identifiant
     * @return true si le client est trouvé, false sinon
     * @throws SQLException
     */
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

    /**
     * Vérifie si l'id_client est bien assosié à un indentifiant client entrée en paramètre
     * @param id id_client
     * @param ident identifiant
     * @return true s'il le client est trouvé, false sinon
     * @throws SQLException
     */
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

     /**
     * Ajoute une salle à la base de données
     * @param nbrchaise
     * @param nbrtable
     * @param projecteur
     * @param taille
     * @throws SQLException
     */
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

    /**
     * Renvoie les information de l'abonnement d'un client
     * @param id id_client
     * @return instance d'abonnement du client
     * @throws SQLException
     */
    public String[] abonnementInfo(int id) throws SQLException {
        String requete = "SELECT * FROM `abonnement` WHERE `ID_client` = "+ id;

        try {
            Statement stmt = connexion.createStatement();
            ResultSet res = stmt.executeQuery(requete);
            if (res.next()) {
                int ID = res.getInt("ID_abonnement");
                Date debut = res.getDate("Date_debut");
                Date fin = res.getDate("Date_fin");
                int ID_client = res.getInt("ID_client");
                String subscribe[] = {String.valueOf(ID), String.valueOf(debut), String.valueOf(fin), String.valueOf(ID_client)};
                System.out.println(subscribe);
                return subscribe;
            }else
                return null;
        } catch (SQLException e) {
            System.out.println(e);
            throw new SQLException();
        }
    }

    /**
     * Renvoie l'instance de connexion à la base de données
     * @return instance de connexion à la base de données
     */
    public Connection getConnexion() {
        return connexion;
    }

    /**
     * Ajoute un Pc à la base de données
     * @param marque
     * @param sn numéro de série
     * @throws SQLException
     */
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

    /**
     * Ajoute un livre à la base de données
     * @param titre
     * @param auteur
     * @param sujet
     * @param resume
     * @throws SQLException
     */
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

    /**
     * Ajoute un journal à la base de données
     * @param Editorial
     * @param Date
     * @param Nom
     * @throws SQLException
     */
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

    /**
     * Ajoute un film à la base de données
     * @param titre
     * @param realisateur
     * @param annee
     * @throws SQLException
     */
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

    /**
     * Modifie les informations d'un client trouvé à partir de son identifiant
     * @param identifiant
     * @param nom
     * @param prenom
     * @param adresse
     * @param telephone
     * @param email
     * @throws SQLException
     */
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

    /**
     * Supprime un client de la base de données
     * @param id identifiant
     * @throws SQLException
     */
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

    /**
     * Modifie le mot de passe d'un client trouvé à partir de son mail
     * @param m
     * @param password
     */
    public void newMDP(String m, String password) {
        try {
            Statement stmt = connexion.createStatement();
            stmt.executeUpdate("UPDATE client SET `Mot de Passe`= '"+password+"' WHERE `Email`='"+m+"'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
