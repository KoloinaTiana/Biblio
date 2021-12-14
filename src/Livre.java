public class Livre {

    protected String titre;
    protected String auteur;
    protected String sujet;
    protected String resume;

     /**
     * Constructeur de la classe Livre
     * @param _titre
     * @param _auteur
     * @param _sujet
     * @param _resume
     */
    public Livre(String _titre, String _auteur, String _sujet, String _resume) {
        this.titre = _titre;
        this.auteur = _auteur;
        this.sujet = _sujet;
        this.resume = _resume;
    }
}
