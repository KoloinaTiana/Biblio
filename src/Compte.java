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

	public String getIdentifiant() {
		return identifiant;
	}

	public String getMotDePasse() {
		return motDePasse;
	}
}
