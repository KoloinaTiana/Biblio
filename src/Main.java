import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
public class Main {

    public static void main(String[] args) {

        /*Client benjamin = new Client("Benji4411","Benji41","Benjamin", "Mahoudeau", "73 route de fontaine en sologne, Cour-Cheverny","0254792985", "mahoudeaubenjamin@gmail.com");
        Bibliotheque.getInstance().addCompte(benjamin);

        Admin admin = new Admin("admin","admin","Benjamin", "Mahoudeau");
        Bibliotheque.getInstance().addCompte(admin);
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/

        FenetreAjoutLivre frame = FenetreAjoutLivre.getInstance();
        frame.setVisible(true);
    }

}

