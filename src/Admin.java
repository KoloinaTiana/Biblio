public class Admin extends Compte{

	/**
	 * Constructeur de la classe Admin
	 * @param _identifiant identifiant de l'admin
	 * @param _motDePasse mot de passe de l'admin
	 * @param _prenom prenom de l'admin
	 * @param _nom nom de l'admin
	 */
	public Admin(String _identifiant, String _motDePasse, String _prenom, String _nom) {
		super(_identifiant, _motDePasse,_prenom,_nom);
		this.prenom = _prenom;
		this.nom = _nom;
		this.identifiant = _identifiant;
		this.motDePasse = _motDePasse;
	}

}
