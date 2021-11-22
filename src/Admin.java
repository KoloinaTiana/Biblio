public class Admin extends Compte{

	public Admin(String _identifiant, String _motDePasse, String _prenom, String _nom) {
		super(_identifiant, _motDePasse,_prenom,_nom);
		this.prenom = _prenom;
		this.nom = _nom;
		this.identifiant = _identifiant;
		this.motDePasse = _motDePasse;
	}

}
