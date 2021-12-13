
public abstract class Compte {

	protected String identifiant;
	protected String motDePasse;
	protected String prenom;
	protected String nom;

	
	public Compte(String _identifiant, String _motDePasse, String _prenom, String _nom) {
		this.identifiant = _identifiant;
		this.motDePasse = _motDePasse;
		this.prenom = _prenom;
		this.nom = _nom;
	}
	
	/** affiche l'identifiant 
     * @return identifiant
     */
	
	public String getIdentifiant() {
		return identifiant;
	}

	/** affiche le mot de passe 
     * @return le mot de passe
     */
	public String getMotDePasse() {
		return motDePasse;
	}
}
