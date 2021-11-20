public class Livre {

    private static Livre instance;

    protected int identifiant;
    protected String titre;
    protected String auteur;
    protected String sujet;
    protected String resume;

    public static Livre getInstance() {
        if( instance == null ) {
            instance = new Livre();
        }
        return instance;
    }
}
